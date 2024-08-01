<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%> --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%-- <%@include file="stepHeader.jsp" %> --%>


<section class="sidebar">
    <!-- Sidebar user panel -->
    <div class="user-panel">
        <div class="pull-left image">
            <img src="${pageContext.request.contextPath}/img/ico_admin.png" class="img-circle" alt="User Image" />
        </div>
        <div class="pull-left info">
            <p>안녕하세요<br> ${sessionScope.USERNAME} 님</p>

            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
    </div>
    
    <ul class="sidebar-menu">
    	<%
    		if (Integer.parseInt(GRADE) >= 9) {
    	%>
        <li class="treeview <c:if test="${navi eq 1 }"> active </c:if>">
            <a href="#">
                <i class="fa fa-envelope-o"></i>
                <span>티시스 임직원 관리</span>
                <i class="fa fa-angle-down pull-right"></i>
            </a>
            <ul class="treeview-menu">
				<li><a href="${pageContext.request.contextPath}/tsisRegisterList.do"><i class="fa fa-angle-double-right"></i>배송지 등록 내역(21.12)</a></li>
				<li><a href="${pageContext.request.contextPath}/tsisList.do"><i class="fa fa-angle-double-right"></i>임직원 목록(21.12)</a></li>
            </ul>
        </li>
        <%-- <li class="treeview <c:if test="${navi eq 2 }"> active </c:if>">
            <a href="#">
                <i class="fa fa-envelope-o"></i>
                <span>'21년 신입사원 공걔 채용 면접</span>
                <i class="fa fa-angle-down pull-right"></i>
            </a>
            <ul class="treeview-menu">
				<li><a href="${pageContext.request.contextPath}/registerList.do"><i class="fa fa-angle-double-right"></i>'21년 신입사원 공걔 채용 면접</a></li>
            </ul>
        </li> --%>
        <%-- <li class="treeview <c:if test="${navi eq 2 }"> active </c:if>">
            <a href="#">
                <i class="fa fa-envelope-o"></i>
                <span>티시스 협력직 관리</span>
                <i class="fa fa-angle-down pull-right"></i>
            </a>
            <ul class="treeview-menu">
				<li><a href="${pageContext.request.contextPath}/registerList.do"><i class="fa fa-angle-double-right"></i>협력직 배송지 등록 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/partnerList.do"><i class="fa fa-angle-double-right"></i>협력직 목록</a></li>
            </ul>
        </li> --%>
        <%
        	}
    		if (Integer.parseInt(GRADE) >= 8) {
        %>
        <li class="treeview <c:if test="${navi eq 2 }"> active </c:if>">
            <a href="#">
                <i class="fa fa-envelope-o"></i>
                <span>한옥카페 주문 현황</span>
                <i class="fa fa-angle-down pull-right"></i>
            </a>
            <ul class="treeview-menu">
				<li><a href="${pageContext.request.contextPath}/orderList.do"><i class="fa fa-angle-double-right"></i>한옥 카페 주문 현황</a></li>
            </ul>
        </li>
        <% 
        	}
    		if (Integer.parseInt(GRADE) >= 8) {
        %>
        <li class="treeview <c:if test="${navi eq 2 }"> active </c:if>">
            <a href="#">
                <i class="fa fa-envelope-o"></i>
                <span>간편식 주문 현황</span>
                <i class="fa fa-angle-down pull-right"></i>
            </a>
            <ul class="treeview-menu">
				<li><a href="${pageContext.request.contextPath}/saladOrderList.do"><i class="fa fa-angle-double-right"></i>간편식 주문 현황</a></li>
            </ul>
        </li>
        <% 
        	}
        %>
    </ul>
</section>
  <!-- /.sidebar -->