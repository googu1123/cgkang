package com.spring.service;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.UserDao;


@Service("UserService")
public class UserService 
{
    @Resource(name="UserDao")
    private UserDao userDao;
    
    //로그인
    public HashMap<String , Object> userLogIn(HashMap<String , Object> reqHashMap) 
    {
    	return userDao.userLogin(reqHashMap);
    }
    
    //최종 로그인 시간 업데이트
    public int lastLoginUpdate(HashMap<String , Object> reqHashMap) 
    {
    	return userDao.lastLoginUpdate(reqHashMap);
    }
    
}
