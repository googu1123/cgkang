package com.spring.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CommonUtil 
{
	/**
	 * @Method Name : getRequestHashMap
	 * @Description : Request 받은 변수를 HashMap 으로 변환
	 * @History 	: 
	 */ 	
	
	public static String requestToJsonString(HttpServletRequest request)
	{
		String r = "";
		
		StringBuilder sb = new StringBuilder();
        BufferedReader br;
		try 
		{
			br = request.getReader();
			
			String str;
	        while( (str = br.readLine()) != null ){
	            sb.append(str);
	        }
	        r = sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	public static HashMap<String, Object> getReqHashMap(HttpServletRequest request)
	{
		HashMap<String, Object> reqHashMap = new HashMap<String, Object>();
		ArrayList<Object> list = null;
		int i;
		
		Enumeration<?> em = request.getParameterNames();
		
		while (em.hasMoreElements())
		{
			String name 	= (String)em.nextElement();
			String[] values = request.getParameterValues(name);
		
			if(values.length>1) 
			{
            	list = new ArrayList<Object>();
            	for(i=0;i<values.length;i++)
            	{
            		list.add(values[i]);
            		reqHashMap.put(name, list);
            	}
            }
			else 
			{
				reqHashMap.put(name, values[0]);
            }
		}
		
		return reqHashMap;
	}
	
	public static HashMap<String, Object> jsonToMap(JSONObject json) throws JSONException {
		HashMap<String, Object> retMap = new HashMap<String, Object>();

	    if(json != JSONObject.NULL) {
	        retMap = jsonToHashMap(json);
	    }
	    return retMap;
	}

	public static HashMap<String, Object> jsonToHashMap(JSONObject object) throws JSONException {
		HashMap<String, Object> map = new HashMap<String, Object>();

	    Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);

	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = jsonToHashMap((JSONObject) value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}

	public static List<Object> toList(JSONArray array) throws JSONException {
	    List<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.length(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = jsonToHashMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	}
	
	public static String decodeURI(String s, String enc)
	{
		String r = "";
		
		try {
            r = URLDecoder.decode(s, enc);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
		return r;
	}
	
	public static boolean isMobile(HttpServletRequest request)
	{
		
		String userAgent = request.getHeader("User-Agent").toLowerCase();
		boolean result  = false;
		 
		String[] browser = {"iphone","ipod","ipad","android","blackberry","windows ce","nokia","webos","opera mini","sonyericsson","opera mobi","iemobile", "windows phone"};
		for (int i = 0; i < browser.length; i++) {
		    if(userAgent.matches(".*"+browser[i]+".*")){
		        result = true;
		        break;
		    }
		}
		
		return result;
	}
	
	public static boolean useLoop(String[] arr, String targetValue) 
	{
		for(String s: arr)
		{
			if(s.equals(targetValue))
				return true;
		}
		return false;
	}
	
}
