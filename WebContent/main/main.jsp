<%@page import="org.eclipse.jdt.internal.compiler.ast.PrefixExpression"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
	Editorial by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>

	<head>
		<title>Editorial by HTML5 UP</title>
		
		
		
		</script>	
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
	
	<!-- news -->
	<section id="banner">
		<div class="content">
			<header width ="50%">
				<h1>Main Page!</h1>
				<ul class="actions">
					<li><a href="./BoardList.bo" class="button big">Learn More</a></li>
				</ul>	
			</header>
		</div>
		

		<span class="image object">
		<h2>오늘의 인기글</h2>
		<table>
		 <c:forEach var = "dto" items="${boardList }">
			<tr>
				<td class="left" >${dto.subject }</td>
   				</tr>
			</c:forEach>
		
		</table>
		</span>

	</section>
	<!-- news -->
								
		
	
								

	<!-- Footer -->
		<footer id="footer">
			<p class="copyright">&copy; Untitled. All rights reserved. Demo Images: <a href="https://unsplash.com">Unsplash</a>. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
		</footer>

	</div>
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