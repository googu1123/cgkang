package com.spring.common;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class CryptoUtil {

	public static String hashSHA256(String str) {
		
		String def = "";
			
		if(str == null || str.trim().length() == 0) {
			return def;
		}
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			
			byte data[] = md.digest();
			 
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i <  data.length; i++) {
				sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		}
		catch (Exception e){
			return def;
		}
	}
	
	
	public final static String APP_AES128_KEY = "tsismobile!QAZ@#";  
	
	private static String byteArrayToHex(byte[] encrypted) {
	     
	    if(encrypted == null || encrypted.length == 0){ 
	        return "";
	    }
	     
	    StringBuffer sb = new StringBuffer(encrypted.length * 2);
	    String hexNumber;
	     
	    for(int x=0; x<encrypted.length; x++){
	        hexNumber = "0" + Integer.toHexString(0xff & encrypted[x]);
	        sb.append(hexNumber.substring(hexNumber.length() - 2));
	    }
	     
	    return sb.toString();
	}
	
	private static byte[] hexToByteArray(String hex) {
	     
	    if(hex == null || hex.length() == 0){
	        return null;
	    }
	     
	    //16진수 문자열을 byte로 변환
	    byte[] byteArray = new byte[hex.length() /2 ];
	     
	    for(int i=0; i<byteArray.length; i++){
	        byteArray[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2*i+2), 16);
	    }
	    return byteArray;
	}
	
	public static String encryptAES128(String str) {
		
		String def = "";
			
		if(str == null || str.trim().length() == 0) {
			return def;
		}
		
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(APP_AES128_KEY.getBytes(), "AES");
			
			Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
	         
	        byte[] encrypted = cipher.doFinal(str.getBytes());
	         
	        return byteArrayToHex(encrypted);
		}
		catch (Exception e){
			e.printStackTrace();
			return def;
		}
	}
	
	public static String decryptAES128(String str) {
		
		String def = "";
			
		if(str == null || str.trim().length() == 0) {
			return def;
		}
		
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(APP_AES128_KEY.getBytes(), "AES");
			
			Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
	         
	        byte[] original = cipher.doFinal(hexToByteArray(str));
	         
	        String originalStr = new String(original);
	         
	        return originalStr;
		}
		catch (Exception e){
			e.printStackTrace();
			return def;
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}
}
