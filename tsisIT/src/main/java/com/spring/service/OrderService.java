package com.spring.service;

// import java.text.SimpleDateFormat;
// import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.OrderDao;


@Service("OrderService")
public class OrderService 
{
    @Resource(name="OrderDao")
    private OrderDao orderDao;
    
    public int order_cnt_mm(HashMap<String , Object> reqHashMap) 
    {
    	return orderDao.order_cnt_mm(reqHashMap);
    }
    
    public int orderCheck(HashMap<String , Object> reqHashMap) 
    {
    	return orderDao.orderCheck(reqHashMap);
    }
    
    public boolean orderTimeCheck() 
    {
    	boolean r = false;
		if (orderDao.orderTimeCheck()>0){
			r = true;
		}else{
			r = false;
		}
    	return r;
    }
    
    public boolean orderSave(HashMap<String , Object> reqHashMap) 
    {
    	return orderDao.orderSave(reqHashMap);
    }
    
    public HashMap<String, Object> getOrderCntMM(HashMap<String , Object> reqHashMap) 
    {
    	return orderDao.getOrderCntMM(reqHashMap);
    }
    
    public int getMyOrderTotalCount(HashMap<String , Object> reqHashMap) 
    {
        return orderDao.getMyOrderTotalCount(reqHashMap);
    }
    
    public List<HashMap<String, Object>> getMyOrderList(HashMap<String , Object> reqHashMap) 
    {
        return orderDao.getMyOrderList(reqHashMap);
    }
    
    public HashMap<String, Object> getOrderTotalCount(HashMap<String , Object> reqHashMap) 
    {
        return orderDao.getOrderTotalCount(reqHashMap);
    }
    
    public List<HashMap<String, Object>> getOrderList(HashMap<String , Object> reqHashMap) 
    {
        return orderDao.getOrderList(reqHashMap);
    }
    
    public List<HashMap<String, Object>> getOrderListExcel(HashMap<String , Object> reqHashMap) 
    {
        return orderDao.getOrderListExcel(reqHashMap);
    }
    
    public HashMap<String, Object> getOrderInfo(HashMap<String , Object> reqHashMap) 
    {
    	return orderDao.getOrderInfo(reqHashMap);
    }
    
    //로그인
    public HashMap<String , Object> userLogIn(HashMap<String , Object> reqHashMap) 
    {
    	return orderDao.userLogin(reqHashMap);
    }
    
}
