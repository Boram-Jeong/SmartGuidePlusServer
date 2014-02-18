package org.secsm.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.secsm.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Inject
	@Qualifier("sqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;
	
	public void insertUser(User user){
		sqlSessionTemplate.insert("UserDao.insertUser", user);
	}
	
	public Map<String, Object> selectUser(String user_id){
		return sqlSessionTemplate.selectOne("UserDao.getUserByID", user_id);
	}
	
	public Map<String, Object> getRegitidByPhone(User user){
		return sqlSessionTemplate.selectOne("UserDao.getUserByPhone", user);
	}
	
	public void updateUser(User user){
		sqlSessionTemplate.update("UserDao.updateUser", user);
	}
	
	public void deleteUser(String user_id){
		sqlSessionTemplate.delete("UserDao.deleteUser", user_id);
	}
	
	public List<Map<String, Object>> selectUsers(){
		return sqlSessionTemplate.selectList("UserDao.getUsers");
	}
	
}
