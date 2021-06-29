package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.Component;
import com.denlaku.longan.vo.ComponentCache;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface ComponentDao {

	@VarOp
	int add(Component component);
	
	int delete(String dashboardId);
	
	Component get(String id);

	List<Component> list(String dashboard);
	
	List<ComponentCache> listCache(String dashboard);
	
	ComponentCache getCache(String id);
	
	@VarOp
	int addCache(ComponentCache cache);
	
	int deleteCache(String id);
	
}
