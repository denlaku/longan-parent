package com.denlaku.longan.dao;

import java.util.List;

import com.denlaku.longan.anno.VarOp;
import com.denlaku.longan.qo.CustomApiQuery;
import com.denlaku.longan.vo.CustomApi;
import com.denlaku.longan.vo.CustomApiField;
import org.springframework.stereotype.Repository;

/**
 * @author tianx
 */
@Repository
public interface CustomApiDao {

	@VarOp
	int add(CustomApi customApi);

	@VarOp
	int update(CustomApi customApi);
	
	int delete(CustomApiQuery query);
	
	CustomApi get(CustomApiQuery query);
	
	List<CustomApi> list(CustomApiQuery query);
	
	List<CustomApiField> listFields(String apiId);
	
	@VarOp
	int addFields(List<CustomApiField> fields);
	
	int deleteFields(String apiId);
}
