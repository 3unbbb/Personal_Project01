<%@page import="com.eb.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<!-- Header -->
	
	<%
	//보낸정보 받기
	BoardDTO dto =(BoardDTO)request.getAttribute("dto");
 
 	String pageNum = (String)request.getAttribute("pageNum");
	%>
	
	<input type = "hidden" value = "<%= session.getAttribute("id")%>">
	<!-- 게시판 -->
	<article>
		<h1>게시판</h1>
		<table id="notice">
			<tr>
				<td>글번호</td>
				<td>${dto.num }</td>
				<td>작성자</td>
				<td>${dto.b_Id }</td>
				<td>조회수</td>
				<td>${dto.read_count }</td>
				<td>작성일</td>
				<td>
					<fmt:formatDate value="${dto.date }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
 			 	<td>제목</td>
 			 	<td colspan="7">${dto.subject }</td>
			</tr>
			<tr>
				<td colspan = "8">${dto.content }</td>
			</tr>
			<tr>
				<td colspan = "8">${dto.file }</td>
			</tr>

	</table>
		
		
		
		
    <input type = "button" value="목록" onclick="location.href='./BoardList.bo?pageNum=<%=pageNum %>';">
    <input type = "button" value="답글" onclick="location.href='./BoardRewrite.bo?pageNum=<%=pageNum %>';">
    
    <input type = "hidden" value = <%=dto.getB_Id() %>>
    <input type = "hidden" value = <%=session.getAttribute("id") %>>
    <%
    if(session.getAttribute("id").equals(dto.getB_Id())){ %>
    <input type = "button" value="수정" onclick="location.href='./BoardUpdate.bo?pageNum=<%=pageNum %>';">
    <input type = "button" value="삭제" onclick="location.href='./BoardDelte.bo?pageNum=<%=pageNum %>';">
    
   <% } %>
    

	
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