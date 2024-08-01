<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_recruit.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
  	<script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.min.js"></script>
    <title>면접자 정보 입력</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">
</head>
<body>
	<form method="post" id="mainform">
	<input type="hidden" name="interview_time_seq" id ="interview_time_seq" value="${Info.seq}">
    <div class="wrap">
        <div class="header">
            <img src="img/logo-tsis.svg">
            <h1>태광그룹 (주)티시스 신입사원 공개채용</h1>
            <h2>면접 일정 선택</h2>
        </div>
        <div class="content">
            <div class="book-time">
                <p>면접일정</p>
                ${fn:substring(Info.interview_day,0,4)}년
                ${fn:substring(Info.interview_day,5,7)}월
                ${fn:substring(Info.interview_day,8,10)}일 
                ${Info.timezone} ~
            </div>
            <input type="text" name="phone" id="phone" placeholder="휴대폰번호(-빼고 숫자만 입력)" onKeyup="inputTelNumber(this);" maxlength="13">
            <p class="txt">면접자 정보를 입력하시고 입력완료를 눌러주세요.</p>
        </div>
        <button type="button" id="btn_save">입력 완료</button>
    </div>
    </form>
</body>
<script type="text/javascript">
    function inputTelNumber(obj) {

      var number = obj.value.replace(/[^0-9]/g, "");
      var tel = "";

      // 서울 지역번호(02)가 들어오는 경우
      if (number.substring(0, 2).indexOf('02') == 0) {
        if (number.length < 3) {
          return number;
        } else if (number.length < 6) {
          tel += number.substr(0, 2);
          tel += "-";
          tel += number.substr(2);
        } else if (number.length < 10) {
          tel += number.substr(0, 2);
          tel += "-";
          tel += number.substr(2, 3);
          tel += "-";
          tel += number.substr(5);
        } else {
          tel += number.substr(0, 2);
          tel += "-";
          tel += number.substr(2, 4);
          tel += "-";
          tel += number.substr(6);
        }

        // 서울 지역번호(02)가 아닌경우
      } else {
        if (number.length < 4) {
          return number;
        } else if (number.length < 7) {
          tel += number.substr(0, 3);
          tel += "-";
          tel += number.substr(3);
        } else if (number.length < 11) {
          tel += number.substr(0, 3);
          tel += "-";
          tel += number.substr(3, 3);
          tel += "-";
          tel += number.substr(6);
        } else {
          tel += number.substr(0, 3);
          tel += "-";
          tel += number.substr(3, 4);
          tel += "-";
          tel += number.substr(7);
        }
      }

      obj.value = tel;
    }
    
    $(document).on('click', '#btn_save', function () {
    	
    	var msg = "면접 일정 : ${fn:substring(Info.interview_day,0,4)}년 ${fn:substring(Info.interview_day,5,7)}월 ${fn:substring(Info.interview_day,8,10)}일 ${Info.timezone} ~"
    				+"\n휴대폰 번호 : "+ $('#phone').val()
    	
		var form = new FormData(document.getElementById('mainform'));
    	
		if ($('#phone').val() == "") {
			alert("휴대폰 번호를 입력 해주세요.");
			$('#phone').focus();
			return false;
  		}
		
    	if (confirm(msg+"\n\n해당 정보를 등록 하시겠습니까!?")) 
      	{
			var request = $.ajax({
				url: "tsis_recruit_save.do",
	          	type: 'POST',
	          	data: form,
	          	dataType: "json",
	          	processData: false,
	          	contentType: false
			});

	        request.done(function (data) // 전송 후, 결과 값 받아오는 부분
	        {
				if (data != null) {
					var jsonObj = JSON.stringify(data);
					var jsonData = JSON.parse(jsonObj);
					
					console.info("jsonData:"+jsonData);
	              	
					if (jsonData.rt_code == '0000') {
						
						alert(msg +'\n\n정상적으로 면접 일정을 등록하였습니다.');
	                	console.info("success!!");
	
	                	$('form').each(function () {
	                  		//this.reset();
	                		location.href = "tsis_recruit_select.do";
	                		return false;
	                	});
					} else {
	                	//alert(jsonData.rt_msg + '('+ jsonData.rt_code +')');
	                	alert(jsonData.rt_msg);
	                	//alert('등록에 실패하였습니다.\n관리자에게 문의하세요. ['+jsonData.rt_msg+']');
	                	location.href = "tsis_recruit_select.do";
	                	return false;
					}
	
				}
			});
	
	        request.fail(function (jqXHR, textStatus) // 에러 발생
			{
				alert("서버 통신 오류 발생!");
				//location.href = "/";
			});
		}
	});
</script>
</html>
