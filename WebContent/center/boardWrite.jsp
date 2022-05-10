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
		<jsp:include page="../inc/header.jsp"></jsp:include>
	<!-- Header -->

	<!-- Content -->
		<section>
			<header class="main">
				<h1>글쓰기 페이지</h1>
			</header>
	<form action ="./BoardWriteAction.bo" method = "post">		
	<table>
			<tr>
			<td> 글쓴이</td>
			<td><input type = "text" name = "id" size ="5"></td>
			</tr>
			<tr>
			<td> 회사</td>
			<td><input type = "text" name = "company" size ="5"></td>
			</tr>
			<tr>
			<td> 부서</td>
			<td><input type = "text" name = "department" size ="5"></td>
			</tr>
			<tr>
			<td> 제목</td>
			<td><input type = "text" name = "subject" size ="5"></td>
			</tr>
			<tr>
			<td> 내용</td>
			<td><textarea rows = "25" cols="27" name = "content"></textarea></td>
			</tr>
			<tr>
			<td> 사진</td>
			<td><input type ="file"></td>
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
				<footer id="footer">
					<p class="copyright">&copy; Untitled. All rights reserved. Demo Images: <a href="https://unsplash.com">Unsplash</a>. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
				</footer>

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