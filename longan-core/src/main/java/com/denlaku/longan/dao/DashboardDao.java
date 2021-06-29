package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.Dashboard;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface DashboardDao {

	/**
	 * 新增仪表板
	 * @param dashboard
	 * @return
	 */
	@VarOp
	int add(Dashboard dashboard);

	/**
	 * 更新仪表板
	 * @param dashboard
	 * @return
	 */
	@VarOp
	int update(Dashboard dashboard);

	/**
	 * 删除仪表板
	 * @param id
	 * @return
	 */
	int delete(String id);

	/**
	 * 获取仪表板
	 * @param id
	 * @return
	 */
	Dashboard get(String id);

	/**
	 * 查询仪表板
	 * @param dashboard
	 * @return
	 */
	List<Dashboard> list(Dashboard dashboard);

}
