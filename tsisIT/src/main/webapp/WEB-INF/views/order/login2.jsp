<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<meta charset="UTF-8">
<head>
<jsp:include page="/WEB-INF/views/order/head.jsp" />
<title>로그인</title>
<script type="text/javascript" src="<c:url value='/js/jquery.cookie.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/order/login.js'/>"></script>

<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/order/login.js"></script>
 --%>
<script type="text/javascript">

    $(document).ready(function(){
    	var id_save = $.cookie('id_save');
    	
    	if(id_save != "Y") {
    		appInterface.nativeGetInfo();
    		//login.autoLogin();
    	} 
    	
        $("input").keydown(function(key) {
        	if (key.keyCode == 13) {
        		login.login();
        	}
        });
    });
    
    function set(){
    	var set = '{"id":"28054926","pw":"testpwd"}';
    	appInterface.setinfo(set);
    };
    
	function get(){
		var get = '{"callBack":"appInterface.getInfo"}';
    	appInterface.nativeGetInfo(get);
    };
    
	function qr(){
		var qr ='{"orderCnt":"4","id":"28054926","name":"강창구","dept":"IT사업본부","team":"공통서비스팀","part":"통합서비스파트"}';
    	appInterface.makeqr(qr);
    };
    
    function del(){
    	appInterface.delinfo();
    };
</script>
</head>
<body>
    <div>
        <div class="content_login">
            <p class="hello">안녕하세요.</p>
            <p>원활한 서비스 이용을 위하여<br> 로그인이 필요합니다.</p>
            <div class="login_wrap">
                <p>
                    <label>사번</label>
                    <input id="user_id" name="user_id" type="text" value="">
                </p>
                <p>
                    <label>패스워드</label>
                    <input id="user_pw" name="user_pw" type="password" value="">
                </p>
                <p class="login_info">패스워드는 통합그룹웨어와 동일합니다.</p>
                <p class="auto">
                    <span>ID저장</span>
                    <label class="toggle-control">
                        <input id="id_save" name="id_save" type="checkbox" checked="checked">
                        <span class="control"></span>
                    </label>
                </p>
            </div>
        </div>
        <a href="javascript:login.login();" class="btn_login">로그인</a><br><br>
        <a href="javascript:set();" class="btn_login">SET</a><br><br>
        <a href="javascript:get();" class="btn_login">GET</a><br><br>
        <a href="javascript:del();" class="btn_login">DEL</a><br><br>
        <a href="javascript:qr();" class="btn_login">QR 호출</a><br><br>
    </div>

</body>

</html>