package com.denlaku.longan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.TenantMemberDao;
import com.denlaku.longan.vo.Tenant;
import com.denlaku.longan.vo.TenantMember;

/**
 * @author tianx
 */
@Service
public class TenantMemberService {

	@Autowired
	private TenantMemberDao tenantMemberDao;

	public String nextId() {
		return SnowFlakeService.nextId("tenant-member");
	}

	public List<TenantMember> list(Tenant tenant) {
		return tenantMemberDao.list(tenant);
	}

	public TenantMember get(String id) {
		return tenantMemberDao.get(id);
	}

	public int add(TenantMember tenantMember) {
		tenantMember.setId(nextId());
		return tenantMemberDao.add(tenantMember);
	}

	public int delete(String id) {
		return tenantMemberDao.delete(id);
	}

}
