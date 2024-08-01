package com.spring.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

 
@Repository("DeliveryDao")
public class DeliveryDao 
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    @Resource(name="sqlSession")
    private SqlSession sqlSession;
    
//    @Autowired
//    @Resource(name="sqlSession2")
//    private SqlSession sqlSession2;
//    
    @Autowired
	private DataSourceTransactionManager transactionManager ;
 
    public void setSqlSession(SqlSession sqlSession)
    {
    	this.sqlSession = sqlSession;
    }

    
    public boolean insert(HashMap<String , Object> reqHashMap) 
    {
    	
    	boolean r = false;
    	
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    	TransactionStatus status = transactionManager.getTransaction(def);
    	
    	try 
    	{
    		sqlSession.insert("delivery.insert",reqHashMap);
    		
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

    public HashMap<String , Object> search(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("delivery.search", reqHashMap);
    }
    
    public int selectCnt(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("delivery.selectCnt", reqHashMap);
    }
//    
//    public int userIdCheck(HashMap<String , Object> reqHashMap) 
//    {
//    	return sqlSession2.selectOne("covi_smart.userIdCheck", reqHashMap);
//    }
//    
    public int partnerCheck(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("delivery.partnerCheck", reqHashMap);
    }
    
    public int idCheck(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("delivery.idCheck", reqHashMap);
    }
    
    public int idCheck_210802(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("delivery.idCheck_210802", reqHashMap);
    }
    
    public int selectCntTsis(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("delivery.selectCntTsis", reqHashMap);
    }
    
    public int selectCntTsis_210802(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("delivery.selectCntTsis_210802", reqHashMap);
    }
    
    
    public HashMap<String , Object> searchTsis(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("delivery.searchTsis", reqHashMap);
    }
    
    public HashMap<String , Object> searchTsis_210802(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("delivery.searchTsis_210802", reqHashMap);
    }
    
    
    public boolean insertTsis(HashMap<String , Object> reqHashMap) 
    {
    	
    	boolean r = false;
    	
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    	TransactionStatus status = transactionManager.getTransaction(def);
    	
    	try 
    	{
    		sqlSession.insert("delivery.insertTsis",reqHashMap);
    		
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
    
    public boolean insertTsis_210802(HashMap<String , Object> reqHashMap) 
    {
    	
    	boolean r = false;
    	
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    	TransactionStatus status = transactionManager.getTransaction(def);
    	
    	try 
    	{
    		sqlSession.insert("delivery.insertTsis_210802",reqHashMap);
    		
    		transactionManager.commit(status);
    		
    		r = true;
    		
    		System.out.println("r >>>>" + r);
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

