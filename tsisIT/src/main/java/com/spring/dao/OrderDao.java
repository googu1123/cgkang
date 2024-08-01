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

 
@Repository("OrderDao")
public class OrderDao 
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    @Resource(name="sqlSession")
    private SqlSession sqlSession;
    
    @Autowired
    @Resource(name="sqlSession2")
    private SqlSession sqlSession2;
    
    @Autowired
	private DataSourceTransactionManager transactionManager ;
 
    public void setSqlSession(SqlSession sqlSession)
    {
    	this.sqlSession = sqlSession;
    }
    
    public void setSqlSession2(SqlSession sqlSession2)
    {
    	this.sqlSession2 = sqlSession2;
    }
    
    public int order_cnt_mm(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("order.order_cnt_mm", reqHashMap);
    }
    
    public int orderCheck(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("order.orderCheck", reqHashMap);
    }
    
    public int getMyOrderTotalCount(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("order.getMyOrderTotalCount", reqHashMap);
    }
    
    public List<HashMap<String , Object>> getMyOrderList(HashMap<String , Object> reqHashMap) 
    {
        return sqlSession.selectList("order.getMyOrderList", reqHashMap);
    }
    
    public boolean orderSave(HashMap<String , Object> reqHashMap) 
    {
    	
    	boolean r = false;
    	
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    	TransactionStatus status = transactionManager.getTransaction(def);
    	
    	try 
    	{
    		sqlSession.insert("order.orderSave",reqHashMap);
    		
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

    public HashMap<String , Object> getOrderTotalCount(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("order.getOrderTotalCount", reqHashMap);
    }
    
    public List<HashMap<String , Object>> getOrderList(HashMap<String , Object> reqHashMap) 
    {
        return sqlSession.selectList("order.getOrderList", reqHashMap);
    }
    
    public List<HashMap<String , Object>> getOrderListExcel(HashMap<String , Object> reqHashMap) 
    {
        return sqlSession.selectList("order.getOrderListExcel", reqHashMap);
    }
    
    public HashMap<String , Object> getOrderCntMM(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("order.getOrderCntMM", reqHashMap);
    }
    
    public HashMap<String , Object> getOrderInfo(HashMap<String , Object> reqHashMap) 
    {
    	return sqlSession.selectOne("order.getOrderInfo", reqHashMap);
    }
    

    public HashMap<String , Object> userLogin(HashMap<String , Object> reqHashMap) 
    {
    	HashMap<String , Object> login_result = null;
    	
    	try 
    	{
    		//login_result = sqlSession2.selectOne("covi_smart.userIdCheckView",reqHashMap);
    		login_result = sqlSession2.selectOne("covi_smart.userIdCheck",reqHashMap);
    		
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
    
    public int orderTimeCheck() 
    {
    	return sqlSession.selectOne("order.orderTimeCheck");
    }
}

