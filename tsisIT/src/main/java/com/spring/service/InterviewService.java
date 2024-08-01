package com.spring.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.InterviewDao;;


@Service("InterviewService")
public class InterviewService 
{
    @Resource(name="InterviewDao")
    public InterviewDao interviewDao;
    
    public List<HashMap<String, Object>> getInterviewList(HashMap<String , Object> reqHashMap) 
    {
        return interviewDao.getInterviewList(reqHashMap);
    }
    
    public List<HashMap<String, Object>> getInterviewListGubun(HashMap<String , Object> reqHashMap) 
    {
        return interviewDao.getInterviewListGubun(reqHashMap);
    }
    
    public HashMap<String, Object> getInterviewInfo(HashMap<String , Object> reqHashMap) 
    {
    	return interviewDao.getInterviewInfo(reqHashMap);
    }
    
    public int selectCntPhone(HashMap<String , Object> reqHashMap) 
    {
    	return interviewDao.selectCntPhone(reqHashMap);
    }
    
    public HashMap<String, Object> getInterviewCheck(HashMap<String , Object> reqHashMap) 
    {
    	return interviewDao.getInterviewCheck(reqHashMap);
    }
    
    public int phoneNumberCheck(HashMap<String , Object> reqHashMap) 
    {
    	return interviewDao.phoneNumberCheck(reqHashMap);
    }
    
    public HashMap<String, Object> getPhoneNumberInfo(HashMap<String , Object> reqHashMap) 
    {
    	return interviewDao.getPhoneNumberInfo(reqHashMap);
    }
    
    public int selectCntTime(HashMap<String , Object> reqHashMap) 
    {
    	return interviewDao.selectCntTime(reqHashMap);
    }
    
    public int selectCntTimeGubun(HashMap<String , Object> reqHashMap) 
    {
    	return interviewDao.selectCntTimeGubun(reqHashMap);
    }
    
    public boolean insert_interview(HashMap<String , Object> reqHashMap) 
    {
    	return interviewDao.insert_interview(reqHashMap);
    }

}
