package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.Linkage;
import com.denlaku.longan.vo.LinkageAim;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface LinkageDao {

	/**
	 * 增加联动
	 * @param linkage
	 * @return
	 */
	@VarOp
	int add(Linkage linkage);

	/**
	 * 删除组件联动
	 * @param componentId
	 * @return
	 */
	int delete(String componentId);

	/**
	 * 查询组件联动
	 * @param componentId
	 * @return
	 */
	List<Linkage> list(String componentId);

	/**
	 * 添加联动目标
	 * @param linkage
	 * @return
	 */
	@VarOp
	int addAims(List<LinkageAim> linkage);

	/**
	 * 删除联动目标
	 * @param linkageId
	 * @return
	 */
	int deleteAims(String linkageId);

	/**
	 * 查询联动目标
	 * @param linkageId
	 * @return
	 */
	List<LinkageAim> listAims(String linkageId);
	
	
	
}
