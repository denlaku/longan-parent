package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.Filter;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface FilterDao {
	@VarOp
	int add(Filter filter);

	int delete(String componentId);

	List<Filter> list(String componentId);
}
