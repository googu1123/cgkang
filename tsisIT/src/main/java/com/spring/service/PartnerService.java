package com.spring.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.PartnerDao;


@Service("PartnerService")
public class PartnerService 
{
    @Resource(name="PartnerDao")
    public PartnerDao partnerDao;
    
    public int getRegTotalCount(HashMap<String , Object> reqHashMap) 
    {
        return partnerDao.getRegTotalCount(reqHashMap);
    }
    
    public int getPartnerTotalCount(HashMap<String , Object> reqHashMap) 
    {
        return partnerDao.getPartnerTotalCount(reqHashMap);
    }
    
    public List<HashMap<String, Object>> getRegList(HashMap<String , Object> reqHashMap) 
    {
        return partnerDao.getRegList(reqHashMap);
    }
    
    public List<HashMap<String, Object>> getPartnerList(HashMap<String , Object> reqHashMap) 
    {
        return partnerDao.getPartnerList(reqHashMap);
    }

}
