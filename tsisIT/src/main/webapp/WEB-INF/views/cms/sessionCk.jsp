<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String USERID = (String) session.getAttribute("USERID");
	String USERNAME = (String) session.getAttribute("USERNAME");
	String GRADE = (String) session.getAttribute("GRADE");
	 
	//System.out.println("USERID >>> " + USERID);
	//System.out.println("USERNAME >>> " + USERNAME);
 	
	if(USERID==null || USERID.equals("")){
		response.sendRedirect("logout.do");
		return;
	}
%>