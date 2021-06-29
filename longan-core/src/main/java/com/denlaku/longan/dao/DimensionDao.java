package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.Dimension;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface DimensionDao {

	@VarOp
	int add(Dimension dimension);

	int delete(String componentId);

	List<Dimension> list(String componentId);
}
