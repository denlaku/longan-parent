package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.Goingin;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface GoinginDao {
	
	@VarOp
	int add(Goingin goingin);

	int delete(String componentId);

	List<Goingin> list(String componentId);
}
