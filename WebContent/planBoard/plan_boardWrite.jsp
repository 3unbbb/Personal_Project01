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
		<title>글쓰기</title>
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
<% //  BoardDTO dto = (BoardDTO)request.getAttribute("dto"); %>

<%	String sessionId = (String)session.getAttribute("id");
	
		if(sessionId == null){
			 %><script>
			 alert('로그인 후 사용가능합니다.');
			 location.href='./Login.mm';</script><%
		}
	
	%>

	<!-- Content -->
		<section>
			<header class="main">
			<%-- <input type = "text" value = "<%=dto.getB_Id() %>" >
				<input type = "text" value = "<%=dto.getB_Company() %>" >
				<input type = "text" value = "<%=dto.getB_Department() %>" > --%>
				<h1>글쓰기 페이지</h1>
			</header>
			
	<form action ="./P_BoardWriteAction.pbo?id=${dto.getId() }&department=${dto.getDepartment() }" method = "post">
	
	<table>
			<tr>
				<td> 글쓴이</td>
				<td><input type = "text" name = "id" value = "${dto.getId() }" disabled="disabled"></td>
			</tr>


			<tr>
			<td> 제목</td>
			<td colspan = "3"><input type = "text" name = "subject" size ="5"></td>
			</tr>
			<tr>
			<td> 내용</td>
			<td colspan = "3"><textarea rows = "25" cols="27" name = "content"></textarea></td>
			</tr>

			
			
	</table>
		<div id ="table_write">
		<input type ="submit" value ="쓰기" class ="btn">
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