package com.denlaku.longan.aspect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.denlaku.longan.util.Maps;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.denlaku.longan.AppException;
import com.denlaku.longan.qo.UserQuery;
import com.denlaku.longan.service.UserService;
import com.denlaku.longan.vo.Base;
import com.denlaku.longan.util.Resp;
import com.denlaku.longan.util.Return;
import com.denlaku.longan.vo.User;
import com.denlaku.longan.vo.VarBase;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianx
 */
@Component
@Aspect
@Slf4j
public class ControllerAspect {

	@Autowired
	private UserService userService;

	@Around("@annotation(org.springframework.web.bind.annotation.GetMapping)"
			+ " or @annotation(org.springframework.web.bind.annotation.PostMapping)"
			+ " or @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object around(ProceedingJoinPoint proceeding) {
		try {
			return proceeding.proceed();
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
			if (e instanceof AppException) {
				return Resp.error(e.getMessage());
			}
			return Resp.error("请求异常");
		}
	}

	@AfterReturning(value = "@annotation(com.denlaku.longan.anno.VarBy)", returning = "result")
	public void afterReturning(Object result) {
		if (result == null) {
			return;
		}
		Object data = null;
		Return<?> rtn = null;
		if (result instanceof Return) {
			rtn = (Return<?>) result;
			data = rtn.getData();
		}
		if (data == null) {
			return;
		}
		List<String> userIds = new ArrayList<>();
		if (data instanceof Collection) {
			Collection<?> list = (Collection<?>) data;
			list.forEach(obj -> collectUserIds(userIds, obj));
		} else {
			collectUserIds(userIds, data);
		}
		Map<String, User> userMap = Maps.ofSize(userIds.size());
		if (!userIds.isEmpty()) {
			UserQuery userQuery = new UserQuery();
			userQuery.setIds(userIds);
			Map<String, User> listMap = userService.listMap(userQuery);
			if (listMap != null) {
				userMap.putAll(listMap);
			}
		}
		rtn.setUmap(userMap);
	}

	private void collectUserIds(List<String> userIds, Object obj) {
		if (obj == null) {
			return;
		}
		String createdBy = null;
		String updatedBy = null;
		if (obj instanceof VarBase) {
			VarBase vBase = (VarBase) obj;
			createdBy = vBase.getCreatedBy();
			updatedBy = vBase.getUpdatedBy();
		} else if (obj instanceof Base) {
			Base base = (Base) obj;
			createdBy = base.getCreatedBy();
		}
		if (createdBy != null && !userIds.contains(createdBy)) {
			userIds.add(createdBy);
		}
		if (updatedBy != null && !userIds.contains(updatedBy)) {
			userIds.add(updatedBy);
		}
	}

}
