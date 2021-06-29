package com.denlaku.longan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denlaku.longan.dao.TenantDao;
import com.denlaku.longan.vo.Tenant;

/**
 * @author tianx
 */
@Service
public class TenantService {

	@Autowired
	private TenantDao tenantDao;

	public List<Tenant> list(Tenant tenant) {
		return tenantDao.list(tenant);
	}

	public Tenant get(String id) {
		return tenantDao.get(id);
	}

	public int add(Tenant tenant) {
		return tenantDao.add(tenant);
	}

	public int update(Tenant tenant) {
		return tenantDao.update(tenant);
	}

	public int delete(String id) {
		return tenantDao.delete(id);
	}
}
