package org.secsm.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.secsm.model.Request;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class RequestDao {
	
	@Inject
	@Qualifier("sqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;
	
	public void insertRequest(Request req){
		sqlSessionTemplate.insert("RequestDao.insertRequset", req);
	}
	
	public List<Map<String, Object>> selectRequests(){
		return sqlSessionTemplate.selectList("RequestDao.getRequest");
	}
	
	public List<Map<String, Object>> selectRequestById(String user_id){
		return sqlSessionTemplate.selectList("RequestDao.getRequestById", user_id);
	}
	
	public void updateGuide(Request req){
		sqlSessionTemplate.update("RequestDao.updateRequest", req);
	}
	
	public void deleteGuide(String rid){
		sqlSessionTemplate.delete("RequestDao.deleteRequest", rid);
	}
}
