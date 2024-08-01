package com.spring.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.TsisDao;


@Service("TsisService")
public class TsisService 
{
    @Resource(name="TsisDao")
    public TsisDao tsisDao;
    
    public int getTsisRegTotalCount(HashMap<String , Object> reqHashMap) 
    {
        return tsisDao.getTsisRegTotalCount(reqHashMap);
    }
    
    public int getTsisRegTotalCount_210802(HashMap<String , Object> reqHashMap) 
    {
        return tsisDao.getTsisRegTotalCount_210802(reqHashMap);
    }
    
    public int getTsisTotalCount(HashMap<String , Object> reqHashMap) 
    {
        return tsisDao.getTsisTotalCount(reqHashMap);
    }
    
    public List<HashMap<String, Object>> getTsisRegList(HashMap<String , Object> reqHashMap) 
    {
        return tsisDao.getTsisRegList(reqHashMap);
    }
    
    public List<HashMap<String, Object>> getTsisRegList_210802(HashMap<String , Object> reqHashMap) 
    {
        return tsisDao.getTsisRegList_210802(reqHashMap);
    }
    
    public List<HashMap<String, Object>> getTsisList(HashMap<String , Object> reqHashMap) 
    {
        return tsisDao.getTsisList(reqHashMap);
    }

}
