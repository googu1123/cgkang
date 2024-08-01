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

 
@Repository("TsisDao")
public class TsisDao 
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
    
    public int getTsisRegTotalCount(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("delivery.getTsisRegTotalCount", reqHashMap);
    }
    
    public int getTsisRegTotalCount_210802(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("delivery.getTsisRegTotalCount_210802", reqHashMap);
    }
    	
    public List<HashMap<String , Object>> getTsisRegList(HashMap<String , Object> reqHashMap) 
    {
        return sqlSession.selectList("delivery.getTsisRegList", reqHashMap);
    }
    
    public List<HashMap<String , Object>> getTsisRegList_210802(HashMap<String , Object> reqHashMap) 
    {
        return sqlSession.selectList("delivery.getTsisRegList_210802", reqHashMap);
    }
    
    public int getTsisTotalCount(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("delivery.getTsisTotalCount", reqHashMap);
    }
    
    public List<HashMap<String , Object>> getTsisList(HashMap<String , Object> reqHashMap) 
    {
        return sqlSession.selectList("delivery.getTsisList", reqHashMap);
    }

 
}

