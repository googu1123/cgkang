<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_recruit.css">
    <title>면접 일정 조회</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">
</head>
<body>
    <div class="wrap">
        <div class="intro-header">
            <img src="${pageContext.request.contextPath}/img/logo-tsis.svg">
            <h1>태광그룹 (주)티시스 신입사원 공개채용</h1>
            <h2>면접 일정 선택</h2>
        </div>
        <div class="content">
            <p>면접 일정 선택 후 변경이 불가하오니<br>신중하게 선택해 주시기 바랍니다.</p>
            <a href="tsis_recruit_select.do" class="btn-blue">면접 일정 선택</a> 
            <!-- <a href="#" onclick="f_close();" class="btn-blue">면접 일정 선택</a> -->
        </div>
    </div>
</body>
<script>
	function f_close(){
		alert('면접 일정 등록이 마감 되었습니다.');
	}
</script>
</html>