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
	String pageNum = (String)request.getAttribute("pageNum");
	int result = (int)request.getAttribute("result");
	int pageCount = (int)request.getAttribute("pageCount");
	int pageBlock = (int)request.getAttribute("pageBlock");
	int startPage = (int)request.getAttribute("startPage");
	int endPage = (int)request.getAttribute("endPage");
	
	%>
	
	<input type = "hidden" value = "<%= session.getAttribute("id")%>">
	<!-- 게시판 -->
	<article>
		<h1>게시판</h1>
		<table id="notice">
			<tr>
				<th>글번호</th>
				<th>썸네일</th>
 			 	<th>제목</th>
   				<th>글쓴이</th>
   				<th>작성일</th>
				<th>조회수</th>
				
			</tr>
   		 <c:forEach var = "dto" items="${boardList }">
			<tr>
				<td>${dto.num }</td>
				<td class="left" >
				
					<c:if test="${dto.re_lev > 0 }">
		        		<img src="./images/level.gif" width="${dto.re_lev * 10 }">
		        		<img src="./images/re.png" width = "15">
		     	  </c:if> 
		     	  <img  src="./images/thumbnail.jpg" width ="40">
				<a href="./BoardContent.bo?num=${dto.num }&pageNum=<%=pageNum%>">
				${dto.subject }
				</a>
				</td>
				
   				<td>${dto.b_Id }</td>
   				<td>${dto.date }</td>
   				<td>${dto.read_count }</td>
   				
   			</tr>
			</c:forEach>
	</table>
		
		<div id="page_control">
		<%
		if(result != 0) {
			
		if(startPage > pageBlock){
		%>
		<a href="./BoardList.bo?pageNum=<%=startPage-pageBlock%>">[이전]</a>
		<% 
		}
		
		for(int i = startPage;i<=endPage;i++){
			%>
				<a href="./BoardList.bo?pageNum=<%=i%>">[<%=i %>]</a>
			<%
		}
		if(endPage > pageCount){
			%>
			<a href="./BoardList.bo?pageNum=<%=startPage+pageBlock%>">[다음]</a>
			<% 	
			
		}
	}
	%>
	
	</div>
		
	</article>
	<section class="wirte_search">
    <input type = "button" value="write" onclick=" location.href ='./BoardWrite.bo';">
    
	<form method="post" action="#">
		<input type="button" value="search" onclick = "">
		<input type ="text" name = "serach" width ="5">
	</form>
	</section>
	<!-- Search -->
	

	
	
	
	

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