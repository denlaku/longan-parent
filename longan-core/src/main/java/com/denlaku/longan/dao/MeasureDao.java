package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.Measure;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface MeasureDao {

	/**
	 * 增加度量
	 * @param measure
	 * @return
	 */
	@VarOp
	int add(Measure measure);

	/**
	 * 删除组件度量
	 * @param componentId
	 * @return
	 */
	int delete(String componentId);

	/**
	 * 查询组件度量
	 * @param componentId
	 * @return
	 */
	List<Measure> list(String componentId);
}
