<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%> --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="sessionCk.jsp" %>


	<%
		if (GRADE.equals("9")){
   	%>
		<a href="tsisRegisterList.do" class="logo"> 티시스IT 사내 관리</a>
	<%}
		else if (GRADE.equals("8")){
	%>
		<a href="orderList.do" class="logo"> 한옥카페 주문 현황</a>
	<% } %>

<nav class="navbar navbar-static-top" role="navigation">
    <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </a>
    <div class="navbar-right">
        <ul class="nav navbar-nav">
            <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i>
                    <span>${sessionScope.USERNAME }<i class="caret"></i></span>
                </a>
                <ul class="dropdown-menu">
                    <!-- User image -->
                    <li class="user-header">
                        <img src="img/ico_admin.png" class="img-circle" alt="User Image" />
                        <p>
                            ${sessionScope.USERNAME }
                            <small>${sessionScope.USERNAME }</small>
                        </p>
                    </li>
                    <li class="user-footer">
                        <div class="pull-left">
                            <!-- <a href="#" class="btn btn-default btn-flat">프로필</a> -->
                        </div>
                        <div class="pull-right">
                            <a href="logout.do" class="btn btn-default btn-flat">로그아웃</a>
                        </div>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>