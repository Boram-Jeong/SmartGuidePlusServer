package org.secsm.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.secsm.model.FilePush;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class FilePushDao {
	
	@Inject
	@Qualifier("sqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;
	
	public void insertPush(FilePush filePush){
		sqlSessionTemplate.insert("FilePushDao.insertFilePush", filePush);
	}
	
	public List<Map<String, Object>> checkPushFileById(String user_id){
		return sqlSessionTemplate.selectList("FilePushDao.selectPushById", user_id);
	}

}
