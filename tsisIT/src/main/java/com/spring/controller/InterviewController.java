package com.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import javax.xml.ws.Response;

import org.json.JSONObject;
//import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.spring.service.InterviewService;
import com.spring.util.CommonUtil;
import com.spring.common.BaseConstants;
import com.spring.util.DateUtil;
import com.spring.util.FileUtil;

@Controller
public class InterviewController 
{
	
	private static final Logger log = LoggerFactory.getLogger(InterviewController.class);
	
	@Autowired
	InterviewService interviewService;
	
//	@RequestMapping(value = "tsis_recruit_2021.do")
//	public String tsis_recruit_2021(HttpServletRequest req , HttpServletResponse res) 
//	{
//		log.info(">>> tsis_recruit_2021.do");
//		return "recruit/intro";
//	}
//	
//	@RequestMapping(value = "tsis_recruit.do")
//	public String intro(HttpServletRequest req , HttpServletResponse res) 
//	{
//		log.info(">>> tsis_recruit.do");
//		return "recruit/intro";
//	}
	
	@RequestMapping(value = "interview.do")
	public String interview(HttpServletRequest req , HttpServletResponse res) 
	{
		log.info(">>> interview.do");
		return "recruit/intro";
	}
	
	@RequestMapping(value = "tsis_recruit_select.do")
	public String select(HttpServletRequest req , HttpServletResponse res) 
	{
		log.info(">>> tsis_recruit_select.do");
		return "recruit/select";
	}
	
	
	@RequestMapping(value = "getInterviewList.do" , produces = "application/text; charset=utf8")
	//@RequestMapping(value = "getInterviewListGubun.do" , produces = "application/text; charset=utf8")
    public @ResponseBody String getTsisRegisterList(Model model, 
    		@RequestBody String data,
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	
    	JSONObject req_jsonObj = new JSONObject(data);    	
    	HashMap<String, Object> reqHashMap = CommonUtil.jsonToHashMap(req_jsonObj);
    	
    	log.info("interview_day >>>>>>"+reqHashMap.get("interview_day"));
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String reglist = "";
		try {
			//reglist = mapper.writeValueAsString(interviewService.getInterviewListGubun(reqHashMap));
			reglist = mapper.writeValueAsString(interviewService.getInterviewList(reqHashMap));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("rtCode", "0000");
    	jsonObj.put("reglist", reglist);
    	
	    log.info("jsonObject >>> "+jsonObj.toString());
	    
	    String output = jsonObj.toString();
		
        return output;
    }
	
	@RequestMapping(value = "tsis_recruit_form.do")
	public String vodPlay(Model model, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {    	
    	HashMap<String, Object> reqHashMap = CommonUtil.getReqHashMap(request);	
    	
    	String out_url ="";
    	log.info("tsis_recruit_form.do");
    	log.info("seq >>>>>>"+reqHashMap.get("seq"));
    	
    	if (reqHashMap.get("seq") == null)
    	{
    		//out_url ="recruit/tsis_recruit_select";
    		return "redirect:tsis_recruit_select.do";
    		
    	}else{
    		HashMap<String, Object> resHashMap = interviewService.getInterviewInfo(reqHashMap);
        	model.addAttribute("Info", resHashMap);
        	out_url ="recruit/info";
    	}
    	
    	
    	return out_url;
    }
	
	@RequestMapping(value = "tsis_recruit_save.do")
	public @ResponseBody Map<String, Object> tsis_recruit_save(HttpServletRequest req , HttpServletResponse res, SessionStatus status) 
	{
		log.info(">>> tsis_recruit_save.do");
		
		String rt_code = "", rt_msg = "";
		String interview_time_seq = "";
		
		HashMap<String, Object> insertHashMap = CommonUtil.getReqHashMap(req);
		
		log.info("insertHashMap >>" + insertHashMap.toString());
		
		//if(interviewService.phoneNumberCheck(insertHashMap)>0)
		HashMap<String, Object> resHashMap = interviewService.getPhoneNumberInfo(insertHashMap);
		
		if (resHashMap  != null)
		{
			interview_time_seq = insertHashMap.get("interview_time_seq").toString();
			
			log.info("resHashMap interview_time_seq >>"+ insertHashMap.get("interview_time_seq"));
			log.info("resHashMap >>"+ resHashMap.get("phone_number"));
			log.info("resHashMap >>"+ resHashMap.get("user_name"));
			//log.info("resHashMap >>"+ resHashMap.get("gubun"));  // A:티시스 IT, B: 티시스 건설
			
			//insertHashMap.put("gubun", resHashMap.get("gubun"));
				
			HashMap<String, Object> interviewHashMap = interviewService.getInterviewCheck(insertHashMap);
			
			//if(interviewService.selectCntPhone(insertHashMap) > 0)
			if(interviewHashMap != null)
			{
				rt_code = "0001";
	    		rt_msg =insertHashMap.get("phone")+ " 번호는 \n"+ interviewHashMap.get("interview_day")+ "일 "+interviewHashMap.get("timezone") + "~ 로 이미 등록 되어있습니다.\n" ;
			}
			else
			{
				int limit = 2;
				
				log.info("interview_time_seq >>>" + interview_time_seq);
				
				if (interview_time_seq.equals("22")){
					limit = 3;
				}
				
				log.info("limit >>>" + limit);
				
				
				
				//if(interviewService.selectCntTimeGubun(insertHashMap)>2)
				if(interviewService.selectCntTime(insertHashMap)>limit)
				{
					rt_code = "0002";
		    		//rt_msg ="해당 시간에 면접("+resHashMap.get("gubun")+") 인원이 초과 되었습니다.\n다른 시간을 선택해주세요.";
					rt_msg ="해당 시간에 면접 인원이 초과 되었습니다.\n다른 시간을 선택해주세요.";
				}
				else
				{
					if(interviewService.insert_interview(insertHashMap))
					{	
						rt_code = "0000";
						rt_msg = "정상처리 되었습니다.";
					}
					else
					{
						rt_code = "9999";
			    		rt_msg ="데이터 저장하는데 실패하였습니다";
					}
				}
			}
		}
		else
		{
			rt_code = "0003";
    		rt_msg =insertHashMap.get("phone")+ " 는 등록된 핸드폰 번호가 아닙니다.\n티시스 인사팀에 문의 바랍니다.";
		}
		
		
		
		Map<String, Object> jsonObject = new HashMap<String, Object>();
		jsonObject.put("rt_code", rt_code);
	    jsonObject.put("rt_msg", rt_msg);
	    
	    log.info("jsonObject >>> "+jsonObject.toString());
	    status.setComplete();
	    
	    return jsonObject;
	}
	
	
}
