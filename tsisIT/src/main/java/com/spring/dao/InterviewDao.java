package com.spring.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

 
@Repository("InterviewDao")
public class InterviewDao 
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private SqlSession sqlSession;
    
    @Autowired
	private DataSourceTransactionManager transactionManager ;
 
    public void setSqlSession(SqlSession sqlSession)
    {
    	this.sqlSession = sqlSession;
    }
    
    
    public List<HashMap<String , Object>> getInterviewList(HashMap<String , Object> reqHashMap) 
    {
        return sqlSession.selectList("interview.getInterviewList", reqHashMap);
    }
    
    public List<HashMap<String , Object>> getInterviewListGubun(HashMap<String , Object> reqHashMap) 
    {
        return sqlSession.selectList("interview.getInterviewListGubun", reqHashMap);
    }
    
    public HashMap<String , Object> getInterviewInfo(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("interview.getInterviewInfo", reqHashMap);
    }
    
    public int selectCntPhone(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("interview.selectCntPhone", reqHashMap);
    }
    
    public HashMap<String , Object> getInterviewCheck(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("interview.getInterviewCheck", reqHashMap);
    }
    
    public int phoneNumberCheck(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("interview.phoneNumberCheck", reqHashMap);
    }
    
    public HashMap<String , Object> getPhoneNumberInfo(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("interview.getPhoneNumberInfo", reqHashMap);
    }
    
    
    public int selectCntTime(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("interview.selectCntTime", reqHashMap);
    }
    
    public int selectCntTimeGubun(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("interview.selectCntTimeGubun", reqHashMap);
    }
    
    public boolean insert_interview(HashMap<String , Object> reqHashMap) 
    {
    	
    	boolean r = false;
    	
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    	TransactionStatus status = transactionManager.getTransaction(def);
    	
    	try 
    	{
    		sqlSession.insert("interview.insert_interview",reqHashMap);
    		
    		transactionManager.commit(status);
    		
    		r = true;
    	}
    	catch (Exception e) 
    	{
    		log.error("------------------------");
    		log.error(e.toString());
    		log.error("------------------------");
    		
    		transactionManager.rollback(status);
    		r = false;
//    		throw e;
    	}
    	
    	
    	return r;
    }
 
}

