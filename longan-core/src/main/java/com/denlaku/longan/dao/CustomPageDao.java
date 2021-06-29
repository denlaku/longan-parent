package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.qo.CustomPageQuery;
import com.denlaku.longan.vo.CustomPage;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface CustomPageDao {

	@VarOp
	int add(CustomPage customPage);

	@VarOp
	int update(CustomPage customPage);

	int delete(CustomPageQuery query);

	List<CustomPage> list(CustomPage customPage);

	CustomPage get(CustomPageQuery query);
}
