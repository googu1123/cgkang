<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%> --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
	<head>
	<jsp:include page="head.jsp" />
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>t.라운지</title>
		<script type="text/javascript" src="<c:url value='/js/jquery.cookie.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/order/order.js'/>"></script>
		<script type="text/javascript">

	    $(document).ready(function(){
	    	
	    	var id_save = $.cookie('id_save')
	    	var id_value = $.cookie('id_value');
			console.log("id_save>>"+id_save);
			console.log("id_value>>"+id_value);
			
			if(id_save == "Y") 
			{
				$("#user_id").val(id_value);
				$("input:checkbox[id='chk']").prop("checked", true);

				var get = '{"callBack":"appInterface.autoLoginCall"}';
	        	appInterface.nativeGetInfo(get);
	        	
	    	} 
	    	
	        $("input").keydown(function(key) {
	        	if (key.keyCode == 13) {
	        		login.login();
	        	}
	        });
	    });
	   
	</script>
	</head>
	<body class="login">
		<div class="wrap-login">
			<p class="txt-login">서비스 이용을 위해<br />로그인이 필요합니다.</p>
				<input type="text" id="user_id" name="user_id" placeholder="그룹웨어 아이디" />
				<input type="password" id="user_pw" name="user_pw" placeholder="그룹웨어 패스워드" />
				<div class="save-id">
					<input type="checkbox" id="chk" />
					<label for="chk"><span class="id-chk">자동로그인</span></label>
				</div>
				<button id="loginbtn" class="login" style="cursor:pointer;" onClick="javascript:login.login();">로그인</button>
		</div>
	</body>
</html>
