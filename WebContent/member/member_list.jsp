<%@page import="com.eb.member.db.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
	Editorial by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Editorial by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="./assets/css/main.css" />
	</head>
	<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">


	<!-- Main -->
	<div id="main">
	<div class="inner">

	<!-- Header -->
	<jsp:include page="../inc/header.jsp"></jsp:include>
	<!-- Header -->
	
	<%
		List<MemberDTO> memberList = (List<MemberDTO>) request.getAttribute("memberList");
	
	%>
	


	<!-- 게시판 -->
	<article>
		<h1>회원 리스트</h1>
		<table id="notice">
			<tr>

				<th class="id">id</th>
 			 	<th class="pass">pass</th>
   				<th class="name">name</th>
   				<th class="age">age</th>
				<th class="tel">tel</th>
				<th class="email">email</th>
				<th class="company">company</th>
				<th class="department">department</th>
			</tr>

	<% for(int i=0;i<memberList.size();i++){
		
		MemberDTO dto = memberList.get(i);
		
	%>
		<tr>
			<td><%=dto.getId() %></td>
			<td><%=dto.getPass() %></td>
			<td><%=dto.getName() %></td>
			<td><%=dto.getAge() %></td>
			<td><%=dto.getTel() %></td>
			<td><%=dto.getEmail() %></td>
			<td><%=dto.getCompany() %></td>
			<td><%=dto.getDepartment() %></td>
				
   			</tr>
    
	<% }%>
    
		</table>

	</article>

	</div> 
	<!-- inner -->
	<!-- 게시판 -->

	</div>
	<!-- Sidebar -->
	<jsp:include page="../inc/left.jsp"></jsp:include>
	<!-- Sidebar -->
	
	</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>