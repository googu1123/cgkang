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

 
@Repository("UserDao")
public class UserDao 
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
 
    public HashMap<String , Object> userLogin(HashMap<String , Object> reqHashMap) 
    {
    	HashMap<String , Object> login_result = null;
    	
    	try 
    	{
    		login_result = sqlSession.selectOne("user.userLogin",reqHashMap);
    		
    	}
    	catch (Exception e) 
    	{
    		log.error("------------------------");
    		log.error(e.toString());
    		log.error("------------------------");
    		
//    		throw e;
    	}
    	
    	return login_result;
    }
    
    public int lastLoginUpdate(HashMap<String , Object> reqHashMap)
    {
    	return sqlSession.update("user.lastLoginUpdate", reqHashMap);
    }
    
}

