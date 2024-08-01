package com.spring.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.util.HttpConnection;

 
@Controller
public class LoginController 
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value = "getGroupList.do" , produces = "application/text; charset=utf8")
    public @ResponseBody String getTsisRegisterList(Model model, 
    		@RequestBody String data,
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	String reglist = HttpConnection.PostData("https://www.t-ammo.com/IGWS/groupList_.json","");
		
    		
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("rtCode", "0000");
    	jsonObj.put("result", reglist);
    	
	    log.info("jsonObject >>> "+jsonObj.toString());
	    
	    String output = jsonObj.toString();
		
        return output;
    }
    
    @RequestMapping(value = "loginOk.do" , produces = "application/text; charset=utf8")
    public @ResponseBody String login(Model model, 
    		@RequestBody String data,
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    
    	System.out.println(">>>>>> loginOk.do");
    	
    	JSONObject req_jsonObj = new JSONObject(data);    	
    	
//    	System.out.println("CPCODE >>>>>>"+req_jsonObj.get("CPCODE"));
//    	System.out.println("ID >>>>>>"+req_jsonObj.get("ID"));
//    	System.out.println("PWD >>>>>>"+req_jsonObj.get("PWD"));
    	
    	String input = "CPCODE="+req_jsonObj.get("CPCODE")+"&ID="+req_jsonObj.get("ID")+"&PWD="+req_jsonObj.get("PWD");
    
    	String login_result = HttpConnection.PostData("https://www.t-ammo.com/IGWS/loginLegacy_.json",input);
		
    	System.out.println("login_result>>"+ login_result);
    		
    	JSONObject jsonObj = new JSONObject(login_result);
	    String output = jsonObj.toString();
		
        return output;
    }
    
}
