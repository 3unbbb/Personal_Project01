<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 	<header id="header">
		<a href="index.html" class="logo"><strong>Editorial</strong> by HTML5 UP</a>

	<% if(session.getAttribute("id") == null) {
		%>
	<ul class="icons">
		<li><a href="./Login.mm"><span class="button">로그인</span></a></li>
		<li><a href="./MemberInsert.mm"><span class= "button">회원가입</span></a></li>
	</ul>
	<%} else{ %>
		
		<ul class="icons">
			<li><a href="./Logout.mm"><span class="button">로그아웃</span></a></li>
		
		
		<%
		if(session.getAttribute("id").equals("admin")){
		%>
			<a href="./MemberList.mm"><span class="button">회원정보</span></a>
		</ul>
		<%}	
			%>	
		<% } %>
	</header>