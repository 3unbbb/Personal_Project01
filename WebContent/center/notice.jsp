<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
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
		<link rel="stylesheet" href="../assets/css/main.css" />
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
	
	<input type = "hidden" value = "<%= session.getAttribute("id")%>">
	<!-- 게시판 -->
	<article>
		<h1>게시판</h1>
		<table id="notice">
			<tr>
				<th class="tno">글번호</th>
 			 	<th class="ttitle">제목</th>
   				<th class="twrite">작성자</th>
   				<th class="tdate">작성일</th>
				<th class="tread">조회수</th>
			</tr>
    
			<tr>
				<td>15</td>
				<td class="left" >Vivanus viveer portitor commodo.</td>
   				<td>Host Admin</td>
   				<td>2012.11.06</td>
   				<td>15</td>
   			</tr>
		</table>
    <input type = "button" value="write" onclick=" location.href ='./BoardWrite.bo';">
					
	<!-- Search -->
	<section id="search" class="alt">
	<form method="post" action="#">
		<input type="button" value="search" onclick = "">
		<input type ="text" name = "serach" width ="5">
	</form>
	</section>
	<!-- Search -->
	
	
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