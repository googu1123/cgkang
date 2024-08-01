<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%> --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="request"/>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="Pragma" content="no-cache"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/style_lounge.css'/>"/>
<script type="text/javascript">
//공용 static 변수
var CONTEXT_PATH = "${CONTEXT_PATH}";
var NATIVE_NONE = -1;
var NATIVE_ANDROID = 1;
var NATIVE_IOS = 2;
var TEST_ANDROID = 3;
var TEST_IOS = 4;
var userAgent = navigator.userAgent;
function getNativeFlag() {
	if (navigator.userAgent.toLowerCase().indexOf("sawoo_android") > -1 ) {
		return NATIVE_ANDROID;
	}
		
	if (userAgent.toLowerCase().indexOf("sawoo_ios") > -1) {
		return NATIVE_IOS;
	}

	if (navigator.userAgent.toLowerCase().indexOf("debug_android") > -1  || navigator.userAgent.toLowerCase().indexOf("debug_ios") > -1 ) {
		return TEST_ANDROID;
	}
	
	
	if(/SHW-M/i.test(navigator.userAgent)){ //갤탭일때
		return NATIVE_ANDROID;
	}
	
    if(navigator.userAgent.toLowerCase().indexOf('android') != -1) {
    	return NATIVE_ANDROID;
    } else if(navigator.userAgent.toLowerCase().indexOf('deviceandroid') != -1)	{
    	return NATIVE_ANDROID;
    } else if(navigator.userAgent.toLowerCase().indexOf('iphone') != -1) {
    	return NATIVE_IOS;
    } else if(navigator.userAgent.toLowerCase().indexOf('ipad') != -1) {
    	return NATIVE_IOS;
    } else if(navigator.userAgent.toLowerCase().indexOf('ipod') != -1) {
    	return NATIVE_IOS;
    } else if(navigator.userAgent.toLowerCase().indexOf('ios') != -1) {
    	return NATIVE_IOS;
    }
	
	return NATIVE_NONE;
}
if(getNativeFlag() == NATIVE_ANDROID || getNativeFlag() == NATIVE_IOS || getNativeFlag() == TEST_ANDROID){
	//정상
	//alert("userAgent : " + navigator.userAgent.toLowerCase());
} else {
	//location.replace("https://www.heungkukfire.co.kr/MAW/main1.do");
}
</script>
<script type="text/javascript" src="<c:url value='/js/jquery-3.5.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/order/common/common.Util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/order/common/appInterface.js'/>"></script>
