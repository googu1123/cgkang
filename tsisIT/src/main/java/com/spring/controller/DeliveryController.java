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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.service.DeliveryService;
import com.spring.util.CommonUtil;
 
@Controller
public class DeliveryController 
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    DeliveryService DeliveryService;
    
    
    @RequestMapping(value = "tsis_corona.do")
	public String tsis_coroan(HttpServletRequest req , HttpServletResponse res) 
	{
		log.info(">>> tsis_coroan.do");
		return "tsis/tsis_registerForm";
	}
    
    @RequestMapping(value = "tsis_registerForm.do")
	public String tsis_registerForm(HttpServletRequest req , HttpServletResponse res) 
	{
		log.info(">>> tsis_registerForm.do");
		return "tsis/tsis_registerForm";
	}
    
    @RequestMapping(value = "tsis_registerForm_210802.do")
	public String tsis_registerForm_210802(HttpServletRequest req , HttpServletResponse res) 
	{
		log.info(">>> tsis_registerForm_210802.do");
		return "tsis/tsis_homecance_register";
	}
    
    @RequestMapping(value="tsis_search_id", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> search_id(@RequestBody String data) 
	{
		log.info(">>> tsis_search_id.do");
		String rt_code = "", rt_msg = "";
		
		log.info(">>>>>"+data.toString());
		
		
		try
		{
			JSONObject reqObject = new JSONObject(data.toString());
		    String s_id = reqObject.getString("s_id");

			HashMap<String, Object> reqHashMap = CommonUtil.jsonToHashMap(reqObject);
			
			if(DeliveryService.idCheck(reqHashMap) > 0 )
			{
				if(DeliveryService.selectCntTsis(reqHashMap) > 0)
				{
					Map<String, Object> map = new HashMap<String, Object>();
					map = DeliveryService.searchTsis(reqHashMap);
					
					log.info("r_name >>>" + map.get("r_name") );
					log.info("r_phone >>>" + map.get("r_phone") );
					log.info("r_post >>>" + map.get("r_post") );
					log.info("r_address >>>" + map.get("r_address") );
					log.info("r_detailAddress >>>" + map.get("r_detailAddress") );
					
					rt_code = "0000";
					rt_msg ="아래의 정보로 이미 배송 정보 등록 처리 되었습니다.\n"
							+"- 수신인 성함 : "+map.get("r_name")+"\n"
							+"- 수신인 전화번호 : "+map.get("r_phone")+"\n"
							+"- 수신인 주소 : "+map.get("r_post")+" " +map.get("r_address")+" "+map.get("r_detailAddress");
				}
				else
				{
					rt_code = "0000";
					rt_msg = "배송 정보 입력 가능합니다.";
				}
			}else{
				rt_code = "0000";
				rt_msg = s_id + " 은 등록된 티시스 사번이 아닙니다.\n"
						+ "티시스 사번을 확인 바랍니다.";
			}	
			
		}catch(Exception e){
			e.printStackTrace();
			
			rt_code = "0000";
			rt_msg =" 시스템에 오류가 발생하였습니다. \n관리자에게 문의 해주세요 .";
					
		}
		
		
		Map<String, Object> jsonObject = new HashMap<String, Object>();
		jsonObject.put("rt_code", rt_code);
	    jsonObject.put("rt_msg", rt_msg);
	    
	    log.debug("jsonObject >>> "+jsonObject.toString());
	    
	    return jsonObject;
	}
    
    @RequestMapping(value="tsis_search_id_210802", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> search_id_210802(@RequestBody String data) 
	{
		log.info(">>> tsis_search_id_210802.do");
		String rt_code = "", rt_msg = "";
		
		log.info(">>>>>"+data.toString());
		
		JSONObject reqObject = new JSONObject(data.toString());
	    String s_id = reqObject.getString("s_id");

		HashMap<String, Object> reqHashMap = CommonUtil.jsonToHashMap(reqObject);
		
		if(DeliveryService.idCheck_210802(reqHashMap) > 0 )
		{
			if(DeliveryService.selectCntTsis_210802(reqHashMap) > 0)
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map = DeliveryService.searchTsis_210802(reqHashMap);
				
				log.info("s_name >>>" + map.get("s_name") );
				log.info("r_gubun >>>" + map.get("r_gubun") );
				log.info("r_phone >>>" + map.get("r_phone") );
				log.info("r_post >>>" + map.get("r_post") );
				log.info("r_address >>>" + map.get("r_address") );
				log.info("r_detailAddress >>>" + map.get("r_detailAddress") );
				log.info("g_phone >>>" + map.get("g_phone") );
				
				rt_code = "0000";
				rt_msg ="아래의 정보로 이미 배송 정보 등록 처리 되었습니다.\n"
						+"- 수신인 성함 : "+map.get("s_name")+"\n"
						+"- 선택 와인 : "+map.get("r_gubun")+"\n"
						+"- 와인 수신인 연락처 : "+map.get("r_phone")+"\n"
						+"- 와인 수신인 주소 : "+map.get("r_post")+" " +map.get("r_address")+" "+map.get("r_detailAddress")+"\n"
						+"- 기프티콘 수신 연락처 : "+map.get("g_phone");
			}
			else
			{
				rt_code = "0000";
				rt_msg = "배송 정보 입력 가능합니다.";
			}
		}else{
			rt_code = "0000";
			rt_msg = reqHashMap.get("s_id") + " 은 등록된 티시스 사번이 아닙니다.\n"
					+ "티시스 사번을 확인 바랍니다.";
		}
		
		Map<String, Object> jsonObject = new HashMap<String, Object>();
		jsonObject.put("rt_code", rt_code);
	    jsonObject.put("rt_msg", rt_msg);
	    
	    log.debug("jsonObject >>> "+jsonObject.toString());
	    
	    return jsonObject;
	}
    
    
    @RequestMapping(value = "tsis_save.do")
	public @ResponseBody Map<String, Object> tsis_save(HttpServletRequest req , HttpServletResponse res) 
	{
		log.info(">>> tsis_save.do");
		String rt_code = "", rt_msg = "";
		HashMap<String, Object> insertHashMap = CommonUtil.getReqHashMap(req);
		
		log.info("insertHashMap >>" + insertHashMap.toString());
		
		if(DeliveryService.idCheck(insertHashMap) > 0 )	
		{
			if(DeliveryService.selectCntTsis(insertHashMap) > 0){
				rt_code = "0707";
	    		rt_msg =insertHashMap.get("s_id")+ "은 이미 등록 처리 되었습니다.";
			}else{
				
				if(DeliveryService.insertTsis(insertHashMap))
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
		}else{
			rt_code = "0808";
			rt_msg = insertHashMap.get("s_id")+ " 은 등록된 사번이 아닙니다.\n사번 확인 바랍니다.";
		}
		
		Map<String, Object> jsonObject = new HashMap<String, Object>();
		jsonObject.put("rt_code", rt_code);
	    jsonObject.put("rt_msg", rt_msg);
	    
	    log.debug("jsonObject >>> "+jsonObject.toString());
	    
	    return jsonObject;
	}
    
    
    
    @RequestMapping(value = "registerForm.do")
	public String registerForm(HttpServletRequest req , HttpServletResponse res) 
	{
		log.info(">>> registerForm.do");
		return "register/registerForm";
	}
    
    @RequestMapping(value = "save.do")
	public @ResponseBody Map<String, Object> save(HttpServletRequest req , HttpServletResponse res) 
	{
		log.info(">>> save.do");
		String rt_code = "", rt_msg = "";
		HashMap<String, Object> insertHashMap = CommonUtil.getReqHashMap(req);
		
		log.info("insertHashMap >>" + insertHashMap.toString());
		
		if(DeliveryService.partnerCheck(insertHashMap) > 0 )	
		{
			if(DeliveryService.selectCnt(insertHashMap) > 0){
				rt_code = "0707";
	    		rt_msg =insertHashMap.get("s_phone")+ "은 이미 등록 처리 되었습니다.";
			}else{
				
				if(DeliveryService.insert(insertHashMap))
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
		}else{
			rt_code = "0808";
			rt_msg = insertHashMap.get("s_phone")+ " 은 등록된 핸드폰 번호가 아닙니다.\n핸드폰 번호 확인 바랍니다.";
		}
		
		Map<String, Object> jsonObject = new HashMap<String, Object>();
		jsonObject.put("rt_code", rt_code);
	    jsonObject.put("rt_msg", rt_msg);
	    
	    log.debug("jsonObject >>> "+jsonObject.toString());
	    
	    return jsonObject;
	}
    
    @RequestMapping(value="tsis_search", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> search(@RequestBody String data) 
	{
		log.info(">>> tsis_search.do");
		String rt_code = "", rt_msg = "";
		
		log.info(">>>>>"+data.toString());
		
		JSONObject reqObject = new JSONObject(data.toString());
	    String s_phone = reqObject.getString("s_phone");

		HashMap<String, Object> reqHashMap = CommonUtil.jsonToHashMap(reqObject);
		
		if(DeliveryService.partnerCheck(reqHashMap) > 0 )
		{
			if(DeliveryService.selectCnt(reqHashMap) > 0)
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map = DeliveryService.search(reqHashMap);
				
				log.info("r_name >>>" + map.get("r_name") );
				log.info("r_phone >>>" + map.get("r_phone") );
				log.info("r_post >>>" + map.get("r_post") );
				log.info("r_address >>>" + map.get("r_address") );
				log.info("r_detailAddress >>>" + map.get("r_detailAddress") );
				
				rt_code = "0000";
				rt_msg ="아래의 정보로 이미 배송 정보 등록 처리 되었습니다.\n"
						+"- 수신인 성함 : "+map.get("r_name")+"\n"
						+"- 수신인 전화번호 : "+map.get("r_phone")+"\n"
						+"- 수신인 주소 : "+map.get("r_post")+" " +map.get("r_address")+" "+map.get("r_detailAddress");
			}
			else
			{
				rt_code = "0000";
				rt_msg = "배송 정보 입력 가능합니다.";
			}
		}else{
			rt_code = "0000";
			rt_msg = s_phone + " 은 등록된 핸드폰 번호가 아닙니다.\n핸"
					+ "드폰 번호 확인 바랍니다.";
		}
		
		Map<String, Object> jsonObject = new HashMap<String, Object>();
		jsonObject.put("rt_code", rt_code);
	    jsonObject.put("rt_msg", rt_msg);
	    
	    log.debug("jsonObject >>> "+jsonObject.toString());
	    
	    return jsonObject;
	}
    
    
    // 21년 08월 2일 이벤트 //
    @RequestMapping(value = "tsis_save_210802.do")
	public @ResponseBody Map<String, Object> tsis_save_210802(HttpServletRequest req , HttpServletResponse res) 
	{
		log.info(">>> tsis_save_210802.do");
		String rt_code = "", rt_msg = "";
		HashMap<String, Object> insertHashMap = CommonUtil.getReqHashMap(req);
		
		log.info("insertHashMap >>" + insertHashMap.toString());
		
		if(DeliveryService.idCheck_210802(insertHashMap) > 0 )	
		{
			if(DeliveryService.selectCntTsis_210802(insertHashMap) > 0){
				rt_code = "0707";
	    		rt_msg =insertHashMap.get("s_id")+ "은 이미 등록 처리 되었습니다.";
			}else{
				
				if(DeliveryService.insertTsis_210802(insertHashMap))
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
		}else{
			rt_code = "0808";
			rt_msg = insertHashMap.get("s_id")+ " 은 등록된 사번이 아닙니다.\n사번 확인 바랍니다.";
		}
		
		Map<String, Object> jsonObject = new HashMap<String, Object>();
		jsonObject.put("rt_code", rt_code);
	    jsonObject.put("rt_msg", rt_msg);
	    
	    log.info("jsonObject >>> "+jsonObject.toString());
	    
	    return jsonObject;
	}
    
    
}
