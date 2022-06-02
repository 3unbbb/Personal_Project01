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
		<script src="./js/jquery-3.6.0.js"></script>
		<script type="text/javascript">
  			$(document).ready(function(){


      //jtbc - 뉴스RSS
      $.ajax({
    	  url : "https://fs.jtbc.joins.com//RSS/newsflash.xml",
    	  success: function(data){
    		  
    		  // 뉴스 기사를 화면에 출력 + 기사클릭시 링크로 이동
    		  $(data).find('item').each(function(i){
    			  if(i == 5) return false;
    			  var title = $(this).find('title').text();
    			  var link = $(this).find('link').text();
    			  $('.flash').append("<a href='"+link+"'>"+title+"</a><br><br>");
    		  });
    		  
    		  
    	  }
      });
      
      $.ajax({
    	  url : "https://fs.jtbc.joins.com/RSS/economy.xml",
    	  success: function(data){
    		  
    		  // 뉴스 기사를 화면에 출력 + 기사클릭시 링크로 이동
    		  $(data).find('item').each(function(i){
    			  if(i == 5) return false;
    			  var title = $(this).find('title').text();
    			  var link = $(this).find('link').text();
    			  $('.economy').append("<a href='"+link+"'>"+title+"</a><br><br>");
    		  });
    		  
    		  
    	  }
      });
      
      $.ajax({
    	  url : "https://fs.jtbc.joins.com/RSS/entertainment.xml",
    	  success: function(data){
    		  
    		  // 뉴스 기사를 화면에 출력 + 기사클릭시 링크로 이동
    		  $(data).find('item').each(function(i){
    			  if(i == 5) return false;
    			  var title = $(this).find('title').text();
    			  var link = $(this).find('link').text();
    			  $('.entertainment').append("<a href='"+link+"'>"+title+"</a><br><br>");
    		  });
    		  
    		  
    	  }
      });

  });

</script>
		
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="./assets/css/main.css" />
		<style type="text/css">
		#news{
			display: inline-block;
			width : 33%;
		}
		</style>
		
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
	
		<div class="content">
			<header width ="50%">
			<br>
				<h1>Main Page!</h1>	
				<hr>
			</header>
		</div>
	<div>
		<h2>이번주 인기글</h2>
		<table>
		 <c:forEach var = "dto" items="${boardList }">
			<tr>
				<td class="left" >${dto.subject }</td>
   				</tr>
			</c:forEach>
		
		</table>
	</div>
	
	<!-- news -->
	<hr>
		<h1>Today's News!</h1>

		<div class="flash" id="news">
			<h2><속보></h2>
		</div>
	
		<div class="economy" id="news">
			<h2><경제></h2>
		</div>
	
		<div class="entertainment" id="news">
			<h2><연예></h2>
		</div>

	
								
		
	
								
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