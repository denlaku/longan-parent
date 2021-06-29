package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.PowerMatch;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface PowerMatchDao {

	/**
	 * 新增权限匹配
	 * @param powerMatch 权限匹配
	 * @return
	 */
	@VarOp
	int add(PowerMatch powerMatch);

	/**
	 * 更新新增权限匹配
	 * @param dataSetId 数据集id
	 * @return
	 */
	int delete(String dataSetId);

	/**
	 * 删除权限匹配
	 * @param dataSetId
	 * @return
	 */
	List<PowerMatch> list(String dataSetId);
	
}
