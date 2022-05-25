<%@page import="com.eb.member.db.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		
		
		<script type="text/javascript">
			

		
			function checkLogin(){	
				
				
				if(document.infoForm.u_email.value == ""){
					alert('이메일을 입력해주세요');
					document.infoForm.u_email.focus();
					return false;
					
				} if(document.infoForm.u_company.value ==""){
					alert("회사를 입력해주세요");
					document.infoForm.u_company.focus();
					return false;
					
				} if(document.infoForm.u_department.value ==""){
					alert("부서를 입력해주세요");
					document.infoForm.u_department.focus();
					return false;
				}
	
			}
		</script>
		
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
		MemberDTO dto = (MemberDTO)request.getAttribute("dto");
	
	%>
	<%	String sessionId = (String)session.getAttribute("id");
	
		if(sessionId == null){
			 %><script>
			 alert('로그인 후 사용가능합니다.');
			 location.href='./Login.mm';</script><%
		}
	
	%>
	
	<!-- 게시판 -->
	<article>
		<h1>회원 리스트</h1>
		
	<form action="./MemberInfoUpdate.mm" method="post" name="infoForm" onsubmit="return checkLogin();">
		<table id="notice">
			<tr><th colspan ="2">회원 정보</th></tr>
			<tr>

				<td>id</td> 
				<td>${dto.getId() }</td>
			</tr>
			<tr>	
   				<td>name</td>
   				<td>${dto.getName() }</td>
			</tr>
			<tr>
   				<td>age</td>
   				<td>${dto.getAge() }</td>
   			</tr>
			<tr>
				<td>tel</td>
				<td>${dto.getTel() }</td>
			</tr>
			<tr>
				<td>email</td>
				<td><input type="text" name="u_email" value = "${dto.getEmail() }"></td>
			</tr>
			<tr>	
				<td>company</td>
				<td><input type="text" name="u_company" value = "${dto.getCompany() }"></td>
			</tr>
			<tr>	
				<td >department</td>
				<td><select name ="u_department">
							<option>---부서를 선택하세요---</option>
							<option value ="strategy">기획</option>
							<option value ="hr">인사</option>
							<option value ="account">재무/회계</option>
							<option value ="purchase">구매/자재</option>
							<option value ="production">생산</option>
							<option value ="IT">IT</option>
							<option value ="marketing">마케팅</option>
							</select>
							</td>
			</tr>

		</table>
		
		<input type = "submit" value = "수정하기" class="btn">
		
	</form>
	<form action="./MemberDelete.mm" method="post">
		<input type = "submit" value =" 탈퇴하기" class="btn">
	</form>
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