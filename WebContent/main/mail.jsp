<%@page import="com.eb.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Editorial by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title></title>
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
<% String Email = (String)request.getAttribute("Em"); %>

	<!-- Content -->
		<section>
			<header class="main">
			<%-- <input type = "text" value = "<%=dto.getB_Id() %>" >
				<input type = "text" value = "<%=dto.getB_Company() %>" >
				<input type = "text" value = "<%=dto.getB_Department() %>" > --%>
				<h1>건의사항을 남겨주세요</h1>
				<h2>불편한 점이나 개선사항을 메일로 보내주세요!</h2>
				<h3></h3>
			</header>
			
	<form action ="mailSend" method = "post">
	
	<table>
			<tr>
				<td> 보내는 사람</td>
				<td><input type = "text" name = "sender" value=<%=Email %> readonly="readonly"></td>
				<td> 받는 사람</td>
				<td><input type = "text" name = "receiver" value="3unbbb@gmail.com" readonly="readonly"></td>
			</tr>


			<tr>
			<td> 제목</td>
			<td colspan = "3"><input type = "text" name = "subject" size ="5"></td>
			</tr>
			<tr>
			<td> 내용</td>
			<td colspan = "3"><textarea rows = "25" cols="27" name = "content"></textarea></td>
			</tr>
			<tr>
			<!-- <td> 파일</td>
			<td colspan = "3"><input type ="file" name="file"></td>
			</tr> -->
			
			
	</table>
		<div id ="table_write">
		<input type ="submit" value ="보내기" class ="btn">
		</div>
	</form>
		</section>

						</div>
					</div>

				<!-- Sidebar -->
						<jsp:include page="../inc/left.jsp"></jsp:include>
				<!-- Sidebar -->

				<!-- Footer -->
				
				</div>
			</div>

			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>