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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.spring.service.TsisService;
import com.spring.util.CommonUtil;
import com.spring.common.BaseConstants;
import com.spring.util.DateUtil;
import com.spring.util.FileUtil;

@Controller
public class TsisController 
{
	
	private static final Logger log = LoggerFactory.getLogger(TsisController.class);
	
	@Autowired
    TsisService tsisService;
	
	@RequestMapping(value = "tsisRegisterList.do")
	public String tsisRegisterList(HttpServletRequest req , HttpServletResponse res) 
	{
		log.info(">>> tsisRegisterList.do");
		return "cms/tsisRegisterList";
	}
	
	@RequestMapping(value = "tsisList.do")
	public String tsisList(HttpServletRequest req , HttpServletResponse res) 
	{
		log.info(">>> tsisList.do");
		return "cms/tsisList";
	}
	
	@RequestMapping(value = "getTsisRegisterList.do" , produces = "application/text; charset=utf8")
    public @ResponseBody String getTsisRegisterList(Model model, 
    		@RequestBody String data,
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	
    	JSONObject req_jsonObj = new JSONObject(data);    	
    	HashMap<String, Object> reqHashMap = CommonUtil.jsonToHashMap(req_jsonObj);
    	
    	log.info("pageSize >>>>>>"+reqHashMap.get("pageSize"));
    	log.info("pageNo >>>>>>"+reqHashMap.get("pageNo"));
    	log.info("pageBlock >>>>>>"+reqHashMap.get("pageBlock"));
    	
    	int pageNum 	= (int) (reqHashMap.get("pageNo")==null?1:Integer.parseInt(reqHashMap.get("pageNo").toString()));
    	int pageSize 	= (int) (reqHashMap.get("pageSize")==null?10:Integer.parseInt(reqHashMap.get("pageSize").toString()));
    	int pageBlock 	= (int) (reqHashMap.get("pageBlock")==null?10:Integer.parseInt(reqHashMap.get("pageBlock").toString()));
    	
    	
    	int totalCount = tsisService.getTsisRegTotalCount(reqHashMap);
        int totalPage = (totalCount + (pageSize - 1)) / pageSize;
        
        if (totalPage==0) totalPage = 1;
        
        int startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
        int endPage = startPage + pageBlock - 1;
        if (endPage > totalPage) 
        { 
            endPage = totalPage;
        }

        int startNo = pageNum==1?0:(pageNum-1) * pageSize;
    	int endNo = pageSize;
    	
    	log.info("startPage>>>"+ startPage);
    	log.info("endPage>>>"+ endPage);
    	log.info("startNo >>>"+ startNo);
    	log.info("endNo >>>"+ endNo);
    	
    	reqHashMap.put("startNo",startNo);
    	reqHashMap.put("endNo",endNo);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String reglist = "";
		try {
			reglist = mapper.writeValueAsString(tsisService.getTsisRegList(reqHashMap));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("rtCode", "0000");
    	jsonObj.put("reglist", reglist);
    	jsonObj.put("pageNum", pageNum);
    	jsonObj.put("totalCount", totalCount);
    	
	    log.info("jsonObject >>> "+jsonObj.toString());
	    
	    String output = jsonObj.toString();
		
        return output;
    }
	
	@RequestMapping(value = "getTsisRegisterList_210802.do" , produces = "application/text; charset=utf8")
    public @ResponseBody String getTsisRegisterList_210802(Model model, 
    		@RequestBody String data,
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	
    	JSONObject req_jsonObj = new JSONObject(data);    	
    	HashMap<String, Object> reqHashMap = CommonUtil.jsonToHashMap(req_jsonObj);
    	
    	log.info("pageSize >>>>>>"+reqHashMap.get("pageSize"));
    	log.info("pageNo >>>>>>"+reqHashMap.get("pageNo"));
    	log.info("pageBlock >>>>>>"+reqHashMap.get("pageBlock"));
    	
    	int pageNum 	= (int) (reqHashMap.get("pageNo")==null?1:Integer.parseInt(reqHashMap.get("pageNo").toString()));
    	int pageSize 	= (int) (reqHashMap.get("pageSize")==null?10:Integer.parseInt(reqHashMap.get("pageSize").toString()));
    	int pageBlock 	= (int) (reqHashMap.get("pageBlock")==null?10:Integer.parseInt(reqHashMap.get("pageBlock").toString()));
    	
    	
    	int totalCount = tsisService.getTsisRegTotalCount_210802(reqHashMap);
        int totalPage = (totalCount + (pageSize - 1)) / pageSize;
        
        if (totalPage==0) totalPage = 1;
        
        int startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
        int endPage = startPage + pageBlock - 1;
        if (endPage > totalPage) 
        { 
            endPage = totalPage;
        }

        int startNo = pageNum==1?0:(pageNum-1) * pageSize;
    	int endNo = pageSize;
    	
    	log.info("startPage>>>"+ startPage);
    	log.info("endPage>>>"+ endPage);
    	log.info("startNo >>>"+ startNo);
    	log.info("endNo >>>"+ endNo);
    	
    	reqHashMap.put("startNo",startNo);
    	reqHashMap.put("endNo",endNo);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String reglist = "";
		try {
			reglist = mapper.writeValueAsString(tsisService.getTsisRegList_210802(reqHashMap));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("rtCode", "0000");
    	jsonObj.put("reglist", reglist);
    	jsonObj.put("pageNum", pageNum);
    	jsonObj.put("totalCount", totalCount);
    	
	    log.info("jsonObject >>> "+jsonObj.toString());
	    
	    String output = jsonObj.toString();
		
        return output;
    }
	
	
	@RequestMapping(value = "getTsisList.do" , produces = "application/text; charset=utf8")
    public @ResponseBody String getTsisList(Model model, 
    		@RequestBody String data,
    		HttpServletRequest request, 
    		HttpServletResponse response)
    {
    	
		log.info(">>>>>> getTsisList.do");
		
    	JSONObject req_jsonObj = new JSONObject(data);    	
    	HashMap<String, Object> reqHashMap = CommonUtil.jsonToHashMap(req_jsonObj);
    	
    	log.info("pageSize >>>>>>"+reqHashMap.get("pageSize"));
    	log.info("pageNo >>>>>>"+reqHashMap.get("pageNo"));
    	log.info("pageBlock >>>>>>"+reqHashMap.get("pageBlock"));
    	
    	int pageNum 	= (int) (reqHashMap.get("pageNo")==null?1:Integer.parseInt(reqHashMap.get("pageNo").toString()));
    	int pageSize 	= (int) (reqHashMap.get("pageSize")==null?10:Integer.parseInt(reqHashMap.get("pageSize").toString()));
    	int pageBlock 	= (int) (reqHashMap.get("pageBlock")==null?10:Integer.parseInt(reqHashMap.get("pageBlock").toString()));
    	
    	
    	int totalCount = tsisService.getTsisTotalCount(reqHashMap);
        int totalPage = (totalCount + (pageSize - 1)) / pageSize;
        
        if (totalPage==0) totalPage = 1;
        
        int startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
        int endPage = startPage + pageBlock - 1;
        if (endPage > totalPage) 
        { 
            endPage = totalPage;
        }

        int startNo = pageNum==1?0:(pageNum-1) * pageSize;
    	int endNo = pageSize;
    	
    	log.info("startPage>>>"+ startPage);
    	log.info("endPage>>>"+ endPage);
    	log.info("startNo >>>"+ startNo);
    	log.info("endNo >>>"+ endNo);
    	
    	reqHashMap.put("startNo",startNo);
    	reqHashMap.put("endNo",endNo);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String tsislist = "";
		try {
			tsislist = mapper.writeValueAsString(tsisService.getTsisList(reqHashMap));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("rtCode", "0000");
    	jsonObj.put("tsislist", tsislist);
    	jsonObj.put("pageNum", pageNum);
    	jsonObj.put("totalCount", totalCount);
    	
	    log.info("jsonObject >>> "+jsonObj.toString());
	    
	    String output = jsonObj.toString();
		
        return output;
    }
	
	
	
	
}
