package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.service.UserService;
import com.spring.util.CommonUtil;
 
@Controller
public class UserController 
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "login.do")
	public String transcoder(HttpServletRequest req , HttpServletResponse res) 
	{
		log.info(">>> login.do");
		return "cms/login";
	}
    
    @RequestMapping(value="loginCheck.do")
    public String login(ModelMap model, HttpServletRequest request, HttpServletResponse response)
    {
    	log.info(">>>>>>>> loginCheck22222.do");
    	
    	String rt_code = ""; 
    	String rt_msg = "";
    	String rt_url = "";
    	
		try {
			
			HashMap<String, Object> reqHashMap = CommonUtil.getReqHashMap(request);

			log.info(">>>>>>>>> Login reqHashMap : " +  reqHashMap.toString());
				
			HashMap<String, Object> login_result = userService.userLogIn(reqHashMap);
			
			if(login_result != null)
			{
				if(login_result.get("pwd").equals((String)reqHashMap.get("pwd")))
				{
					rt_code = "0000";
					//rt_msg = "로그인 성공";
					
					int loginUpdate = userService.lastLoginUpdate(reqHashMap);
					
					log.info("update last login time " + Integer.toString(loginUpdate));
					
					request.getSession().setAttribute("USERID",login_result.get("userid"));
					request.getSession().setAttribute("USERNAME",login_result.get("username"));
					request.getSession().setAttribute("GRADE",login_result.get("grade"));
					request.getSession().setMaxInactiveInterval(300*60);  //300분
					
					log.info("userid : " + login_result.get("userid"));
					log.info("username : " + login_result.get("username"));
					log.info("grade : " + login_result.get("grade"));
					
					if (request.getSession().getAttribute("GRADE").toString().equals("8")){
						rt_url = "orderList.do";
					}else{
						rt_url = "tsisRegisterList.do";
					}
				}
				else
				{
					rt_code = "7777";
					rt_msg = "비밀번호가 올바르지 않습니다.";
					rt_url = "login.do";
				}
			}
			else
			{
				rt_code = "8888";
				rt_msg = "등록된 아이디가 아닙니다.";
				rt_url = "login.do";
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			rt_code = "9999";
			rt_msg = "로그인에 실패하였습니다. error: "+e.toString();
			rt_url = "login.do";
		}
		
		log.info("rt_msg >>>" + rt_msg);
		log.info("url >>>" + rt_url);
		
		model.addAttribute("message", rt_msg);
    	model.addAttribute("url", rt_url);
	    
	    return "common/callBackUrl";
    }
    
    @RequestMapping(value = "logout.do")
    public String logout(Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	request.getSession().setAttribute("USERID","");
    	request.getSession().setAttribute("USERNAME","");
    	request.getSession().setAttribute("GRADE","");
    	request.getSession().invalidate();
    	
    	//model.addAttribute("message", "로그아웃 처리 하였습니다.");
    	model.addAttribute("message", "");
    	model.addAttribute("url", "login.do");
    	
    	return "common/callBackUrl";
    	//return "redirect:/boardList.do";
    }
    
}
