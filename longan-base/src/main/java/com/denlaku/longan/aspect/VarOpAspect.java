package com.denlaku.longan.aspect;

import java.util.Collection;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.denlaku.longan.Current;
import com.denlaku.longan.vo.Base;
import com.denlaku.longan.vo.TenantBase;
import com.denlaku.longan.vo.VarBase;

/**
 * @author tianx
 */
@Component
@Aspect
public class VarOpAspect {
	@Before("@annotation(com.denlaku.longan.anno.VarOp)")
	public void before(JoinPoint jp) {
		Object[] args = jp.getArgs();
		String userId = Current.getUserId();
		String tenantId = Current.getTenantId();
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				setDefault(args[0], userId, tenantId);
			}
		}
	}
	
	private void setDefault(Object data, String userId, String tenantId) {
		if (data == null) {
			return;
		}
		if (data instanceof TenantBase) {
			TenantBase tBase = (TenantBase) data;
			tBase.setCreatedBy(userId);
			tBase.setUpdatedBy(userId);
			tBase.setTenantId(tenantId);
		} else if (data instanceof VarBase) {
			VarBase vBase = (VarBase) data;
			vBase.setCreatedBy(userId);
			vBase.setUpdatedBy(userId);
		} else if (data instanceof Base) {
			((Base) data).setCreatedBy(userId);
		} else if (data instanceof Collection<?>) {
			Collection<?> list = (Collection<?>)data;
			list.forEach(item -> setDefault(item, userId, tenantId));
		}
	}
}
