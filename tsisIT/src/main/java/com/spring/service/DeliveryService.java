package com.spring.service;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.DeliveryDao;


@Service("DeliveryService")
public class DeliveryService 
{
    @Resource(name="DeliveryDao")
    private DeliveryDao deliveryDao;
    
    
    public boolean insert(HashMap<String , Object> reqHashMap) 
    {
    	return deliveryDao.insert(reqHashMap);
    }
    
    public HashMap<String, Object> search(HashMap<String , Object> reqHashMap)
    {
    	return deliveryDao.search(reqHashMap);
    }
    
    public int selectCnt(HashMap<String , Object> reqHashMap) 
    {
    	return deliveryDao.selectCnt(reqHashMap);
    }
    
//    public int userIdCheck(HashMap<String , Object> reqHashMap) 
//    {
//    	return deliveryDao.userIdCheck(reqHashMap);
//    }
    
    public int partnerCheck(HashMap<String , Object> reqHashMap) 
    {
    	return deliveryDao.partnerCheck(reqHashMap);
    }
    
    public int idCheck(HashMap<String , Object> reqHashMap) 
    {
    	return deliveryDao.idCheck(reqHashMap);
    }
    
    public int idCheck_210802(HashMap<String , Object> reqHashMap) 
    {
    	return deliveryDao.idCheck_210802(reqHashMap);
    }
    
    public int selectCntTsis(HashMap<String , Object> reqHashMap) 
    {
    	return deliveryDao.selectCntTsis(reqHashMap);
    }
    
    public int selectCntTsis_210802(HashMap<String , Object> reqHashMap) 
    {
    	return deliveryDao.selectCntTsis_210802(reqHashMap);
    }
    
    public HashMap<String, Object> searchTsis(HashMap<String , Object> reqHashMap)
    {
    	return deliveryDao.searchTsis(reqHashMap);
    }
    
    public HashMap<String, Object> searchTsis_210802(HashMap<String , Object> reqHashMap)
    {
    	return deliveryDao.searchTsis_210802(reqHashMap);
    }
    
    public boolean insertTsis(HashMap<String , Object> reqHashMap) 
    {
    	return deliveryDao.insertTsis(reqHashMap);
    }
    
    public boolean insertTsis_210802(HashMap<String , Object> reqHashMap) 
    {
    	return deliveryDao.insertTsis_210802(reqHashMap);
    }
    
}
