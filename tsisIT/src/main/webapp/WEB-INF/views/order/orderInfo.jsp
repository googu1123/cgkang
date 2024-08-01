<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="session.jsp" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%> --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var='orderInfo' value="${orderInfo}" />
<c:set var='orderCntMM' value="${orderCntMM}" />
<c:set var='maxCnt' value="${maxCnt}" />
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
			
			var orderNo = "${orderInfo.orderno}";
			if (orderNo===""){
				alert("등록된 주문 번호가 없습니다.\nQR 스캔 후 사용해 주세요.");
				location.href='orderIndex.do';
			}
		
			$(document).ready(function(){
				getMyOrderListMM('<%=USERID%>');
			});
		</script>
	</head>
	<body>
		<div class="wrap-page">
			<div class="wrap-user">
				<div class="user">
					<div class="name">${sessionScope.USERNAME}</div>
					<div class="num">${sessionScope.USERID}</div>
				</div>
				<a href="javascript:login.logout();" class="btn-logout"><img src="${pageContext.request.contextPath}/img/lounge/ic_logout.svg" alt="로그아웃" /></a>
			</div>
			<div class="box-blue btn">
				<h3>당월<br />사용<br />현황</h3>
				<div class="wrap-use"><span class="usenum">${orderCntMM.m_ordercnt_mm}</span>/${maxCnt}<span class="use">사용</span></div>
				<div class="more"><img src="${pageContext.request.contextPath}/img/lounge/ic_more.svg" alt="사용 내역 보기" /></div>
			</div>
			<div class="box-white">
				<p class="explain border-b p20">본 화면을 직원에게 보여주시고<br /><span class="highlight">즉시 음료를 주문하세요.</span></p>
				<div class="coupon-id num">NO. ${orderInfo.orderno}</div>
				<div class="wrap-coupon">
					<div class="box-coupon">
						<div class="coupon-num">
							<span class="num">${orderInfo.orderCnt}</span>
							<span class="txt">장</span>
						</div>
					</div>
				</div>
				<div class="date p20 num">${orderInfo.regdate}</div>
				<div class="wrap-inbox-btn">
					<button class="inbox-grey" style="cursor:pointer" onClick="location.href='orderIndex.do';">새로 주문하기</button>
				</div>
				<div class="wrap-warn">
					<img src="${pageContext.request.contextPath}/img/lounge/ic_warn.svg" alt="주의사항" />
					<p>직원 확인 전, 본 화면을 닫으시면<br />주문하실 수 없으니 유의해주세요!</p>
				</div>
			</div>
		</div>

		<!-- 사용내역 모달 -->
		<div class="wrap-layerpop modal">
			<div class="wrap-list">
				<span class="pop-close close"><img src="${pageContext.request.contextPath}/img/lounge/ic_close.svg" alt="닫기" /></span>

				<div class="pop-content">
					<h4>쿠폰 사용내역 조회</h4>
					<div class="header">
						<select id="yymm" onchange="javascript:getMyOrderListMM('<%=USERID%>');">
							<%
								for(int i=0; i<3;i++ ){
									Date nowTime = new Date();
									SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
									Calendar cal = Calendar.getInstance();
									
									if(i>0) cal.add(cal.MONTH, - i);
							%>
								<option value="<%=sf.format(cal.getTime()) %>"><%=sf.format(cal.getTime()) %></option>
							<%
								}
							%>
						</select>

						<div class="sum"><span class="num" id="YYMM_CNT">0</span>장 사용</div>
					</div>
					<!-- 리스트 -->
					<div class="con-list">
						<ul id="boardList"></ul>
					</div>
					<!-- //리스트 -->
				</div>
			</div>
		</div>
		<!-- //사용내역 모달 -->
		<script type="text/javascript">
			// Modal을 가져옵니다.
			var modals = document.getElementsByClassName('modal');
			// Modal을 띄우는 클래스 이름을 가져옵니다.
			var btns = document.getElementsByClassName('btn');
			// Modal을 닫는 close 클래스를 가져옵니다.
			var spanes = document.getElementsByClassName('close');
			var funcs = [];

			// Modal을 띄우고 닫는 클릭 이벤트를 정의한 함수
			function Modal(num) {
				return function () {
					// 해당 클래스의 내용을 클릭하면 Modal을 띄웁니다.
					btns[num].onclick = function () {
						modals[num].style.display = 'block';
						//console.log(num);
					};

					// <span> 태그(X 버튼)를 클릭하면 Modal이 닫습니다.
					spanes[num].onclick = function () {
						$("#yymm option:eq(0)").prop("selected", true);
						getMyOrderListMM('<%=USERID%>');
						modals[num].style.display = 'none';
					};
				};
			}

			// 원하는 Modal 수만큼 Modal 함수를 호출해서 funcs 함수에 정의합니다.
			for (var i = 0; i < btns.length; i++) {
				funcs[i] = Modal(i);
			}

			// 원하는 Modal 수만큼 funcs 함수를 호출합니다.
			for (var j = 0; j < btns.length; j++) {
				funcs[j]();
			}
		</script>
	</body>
</html>
