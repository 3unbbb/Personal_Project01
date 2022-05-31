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
		<h1>갤러리 게시판</h1>
		<table id="notice">

		 <c:set var="size" value="${galleryList.size() }"/>
		 <c:set var="col" value="4"/>
		 <c:set var="row" value="${(size/col + (size%col>0? 1:0)) }"/>
		 <c:set var="galleryCnt" value="0"/>

		<c:set var="list" value="${galleryList }" />
		
   		 <c:forEach begin="1" end="${row }" step="1">
			<tr>
			<c:forEach begin="1" end="${col }" step="1">
				<c:if test="${size > galleryCnt }">
				<td>
					<img  src="./gallery_upload/${list[galleryCnt].image.split(',')[0] }"
						 width ="160" height="160" onclick="href"> <br>
					<a href ="./GalleryContents.ga?num=${list[galleryCnt].num}">${list[galleryCnt].subject }</a>
				</td>
				</c:if>
			<c:set var="galleryCnt" value="${galleryCnt+1 }" />
			</c:forEach>
		</tr>
		</c:forEach>			
	</table>
		
		<div id="page_control">
		<%
		if(result != 0) {
			
		if(startPage > pageBlock){
		%>
		<a href="./GalleryList.bo?pageNum=<%=startPage-pageBlock%>">[이전]</a>
		<% 
		}
		
		for(int i = startPage;i<=endPage;i++){
			%>
				<a href="./GalleryList.bo?pageNum=<%=i%>">[<%=i %>]</a>
			<%
		}
		if(endPage > pageCount){
			%>
			<a href="./GalleryList.bo?pageNum=<%=startPage+pageBlock%>">[다음]</a>
			<% 	
			
		}
	}
	%>
	
	</div>
		
	</article>
	<section class="wirte_search">
    <input type = "button" value="write" onclick=" location.href ='./GalleryWrite.ga';">
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