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

 
@Repository("PartnerDao")
public class PartnerDao 
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
    
    public int getRegTotalCount(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("delivery.getRegTotalCount", reqHashMap);
    }
    	
    public List<HashMap<String , Object>> getRegList(HashMap<String , Object> reqHashMap) 
    {
        return sqlSession.selectList("delivery.getRegList", reqHashMap);
    }
    
    public int getPartnerTotalCount(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("delivery.getPartnerTotalCount", reqHashMap);
    }
    
    public List<HashMap<String , Object>> getPartnerList(HashMap<String , Object> reqHashMap) 
    {
        return sqlSession.selectList("delivery.getPartnerList", reqHashMap);
    }

 
}

