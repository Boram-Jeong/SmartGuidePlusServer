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
	
	public void insertRequest(Request requset){
		sqlSessionTemplate.insert("RequestDao.insertRequset", requset);
	}
	
	public List<Map<String, Object>> selectRequests(){
		return sqlSessionTemplate.selectList("RequestDao.getRequest");
	}
	
	public Map<String, Object> selectRequestById(String user_id){
		return sqlSessionTemplate.selectOne("RequestDao.getRequestById", user_id);
	}
	
	public void updateGuide(Request requset){
		sqlSessionTemplate.update("RequestDao.updateRequest", requset);
	}
	
	public void deleteGuide(String rid){
		sqlSessionTemplate.delete("RequestDao.deleteRequest", rid);
	}
}
