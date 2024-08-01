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

<c:set var="orderCntMM" value="${orderCntMM}" />
<c:set var="maxCnt" value="${maxCnt}" />
<%-- 
<fmt:parseNumber var="orderCntMM" type="number" value="${orderCntMM}" />
<fmt:parseNumber var="maxCnt" type="number" value="${maxCnt}" />
--%>

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
				getMyOrderListMM('<%=USERID%>');
			});
			
			function session_check(callback){
				var json_data = "";
				var request = $.ajax({
                	url: "orderSession.do",
                    type: 'POST',
                    data: json_data,
                    dataType: "json",
                    processData: false,
                    contentType: false
                });
                
                request.done(function (data) // 전송 후, 결과 값 받아오는 부분
                {
                	if (data != null) {
                		var jsonObj = JSON.stringify(data);
                		var jsonData = JSON.parse(jsonObj);

                		if (jsonData.rt_code == '0000') {
                			//alert(jsonData.rt_msg);
                			console.info("success!!");
                			callback();
                		} else {
							alert('로그인 세션이 종료 되었습니다.\n로그인 후 이용해주세요.')                			
                            location.replace("orderLogin.do");
                			return false;
                		}
                	}
                });

                request.fail(function (jqXHR, textStatus) // 에러 발생
                {
                	alert("서버 통신 오류 발생!");
                });
				
			}
			
			function qr(){
				
				var maxCnt ="${maxCnt}";
				var orderCntMM ="${orderCntMM.m_ordercnt_mm}";
				var orderCnt = $("#order_cnt option:selected").val();
				
				console.log(parseInt(orderCntMM) + parseInt(orderCnt));
				console.log("maxCnt >>"+ parseInt(maxCnt));
				 
				var session_id = "<%=USERID%>";
				console.log("session_id >>"+ session_id);
				if (session_id=="" || session_id==null)
				{
					alert('로그인 후 사용하시기 바랍니다.');
					location.href = "orderLogin.do";
					return ;
				}
				else
				{
					if (parseInt(orderCntMM) + parseInt(orderCnt) > parseInt(maxCnt))
					{
						alert('월 20잔까지 사용 가능합니다.');	
						
					}else{
						
						var qr = JSON.stringify({"user_id":"<%=USERID%>","name":"<%=USERNAME%>","dept":"<%=USERDEPT%>","orderCnt":orderCnt});
						console.log("qr>>"+qr);
						appInterface.makeqr(qr); 
					}	
					
				}
				
			}
			
			
			function orderTimeCheck(){
				var json_data = "";
				var request = $.ajax({
                	url: "orderTimeCheck.do",
                    type: 'POST',
                    data: json_data,
                    dataType: "json",
                    processData: false,
                    contentType: false
                });
				
				request.done(function (data) // 전송 후, 결과 값 받아오는 부분
                {
                	if (data != null) {
                		var jsonObj = JSON.stringify(data);
                		var jsonData = JSON.parse(jsonObj);

                		if (jsonData.rt_code == '0000') {
                			//alert(jsonData.rt_msg);
                			console.info("success!!");
                			qr();
                			return true;
                		} else {
							alert(jsonData.rt_msg)                			
                            //location.replace("orderLogin.do");
                			return false;
                		}
                	}
                });

                request.fail(function (jqXHR, textStatus) // 에러 발생
                {
                	alert("서버 통신 오류 발생!");
                	return false;
                });
			}
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
				<h3>당월<br />사용<br />현황 </h3>
				<div class="wrap-use"><span class="usenum">${orderCntMM.m_ordercnt_mm}</span>/${maxCnt}<span class="use">사용</span></div>
				<div class="more"><img src="${pageContext.request.contextPath}/img/lounge/ic_more.svg" alt="사용 내역 보기" /></div>
			</div>
			<c:choose>  
				<c:when test="${orderCntMM.m_ordercnt_mm >= maxCnt}">
					<div class="box-white">
						<p class="explain pt30">이달에 발급된 쿠폰을<br />모두 사용했어요.</p>
						<div class="nocoupon align-c p40">
							<img src="${pageContext.request.contextPath}/img/lounge/img_nocoupon.svg" alt="쿠폰 소진" />
						</div>
						<div class="wrap-inbox-btn">
							<button class="inbox-blue" style="cursor:pointer" onclick="javascript:show_modal(0);">사용내역 보기</button>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="box-white">
						<p class="explain border-b p20">사용하실 쿠폰의 매수를 선택하고<br />QR생성 버튼을 눌러주세요.</p>
						<div class="box-select">
							<select id="order_cnt">
								<%
									for (int i=1; i<21;i++){
								%>
								<option value="<%=i%>" <%if(i==1){%>"selected"<%}%>><%=i%></option>
								<%
									}
								%>
								
							</select>
							<span class="txt">장</span>
						</div>
						<div class="wrap-inbox-btn">
							<button class="inbox-blue" style="cursor:pointer" onclick="javascript:session_check(orderTimeCheck);">QR 생성하기</button>
						</div>
					</div>	
				</c:otherwise>
			</c:choose>
			<div class="wrap-btn">
				<button class="outbox-blue btn">쿠폰 사용 방법</button>
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

		<!-- 사용방법 모달 -->
		<div class="wrap-layerpop modal">
			<div class="wrap-list">
				<span class="pop-close close"><img src="${pageContext.request.contextPath}/img/lounge/ic_close.svg" alt="닫기" /></span>

				<div class="pop-content">
					<h4>쿠폰 사용이 처음이세요?</h4>
					<div class="wrap-howto">
						<img src="${pageContext.request.contextPath}/img/lounge/img_howto.svg" alt="쿠폰 사용 방법" />
					</div>
				</div>
			</div>
		</div>
		<!-- //사용방법 모달 -->

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
				//console.log(num);
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
			
			function show_modal(num){
				var modals1 = document.getElementsByClassName('modal');
				modals1[num].style.display = 'block';
			}
			
		</script>
	</body>
</html>
