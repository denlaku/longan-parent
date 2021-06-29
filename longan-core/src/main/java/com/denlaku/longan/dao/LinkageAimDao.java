package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.vo.LinkageAim;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface LinkageAimDao {

	@VarOp
	int batchAdd(List<LinkageAim> linkage);

	int delete(String linkageId);

	List<LinkageAim> list(String linkageId);
	
	
	
}
