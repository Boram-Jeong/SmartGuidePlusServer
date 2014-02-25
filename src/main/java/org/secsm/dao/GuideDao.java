package org.secsm.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.secsm.model.Guide;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class GuideDao {
	
	@Inject
	@Qualifier("sqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;
	
	public void insertGuide(Guide guide){
		sqlSessionTemplate.insert("GuideDao.insertGuide", guide);
	}
	
	public Map<String, Object> selectGuideByIdx(String idx){
		return sqlSessionTemplate.selectOne("GuideDao.selectGuideByIdx", idx);
	}
	
	public List<Map<String, Object>> selectGuideByName(Guide guide){
		return sqlSessionTemplate.selectList("GuideDao.selectGuideByName", guide);
	}
	
	public List<Map<String, Object>> selectGuideOfRes(Guide guide){
		return sqlSessionTemplate.selectList("GuideDao.selectGuideOfRes", guide);
	}
	
	public List<Map<String, Object>> selectGuides(Guide guide){
		return sqlSessionTemplate.selectList("GuideDao.selectGuides", guide);
	}
	
	public void updateGuide(Guide guide){
		sqlSessionTemplate.update("GuideDao.updateGuide", guide);
	}
	
	public void updateDownload(Guide guide){
		sqlSessionTemplate.update("GuideDao.updateDownload", guide);
	}
	
	public void deleteGuide(String idx){
		sqlSessionTemplate.delete("GuideDao.deleteGuide", idx);
	}

}
