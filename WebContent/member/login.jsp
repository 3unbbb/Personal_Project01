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
		<title>Elements - Editorial by HTML5 UP</title>
		
		
		<script type="text/javascript">
			
			function checkLogin(){	
				
				if(document.userInfo.id.value == ""){
					alert('아이디를 입력해주세요');
					document.userInfo.id.focus();
					return false;
					
				} if(document.userInfo.pass.value ==""){
					alert("비밀번호를 입력해주세요");
					document.userInfo.pass.focus();
					return false;
				}
	
			}
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
								
		<!-- Content -->
		<section>
			<header class="main">
				<h1>Login</h1>
			</header>

		<!-- Form -->

		<form method="post" action="./LoginAction.mm" name = "userInfo" onsubmit="return checkLogin();">
			<div class="row gtr-uniform">
				<div class="col-6 col-12-xsmall">
					<input type="text" name="id"   placeholder="아이디" /> <br>
					<input type="password" name="pass" placeholder="비밀번호" /> <br>
					<input type ="submit" value ="로그인">
					
				</div>
			</div>
		</form>
		
		
		
		<ul class="icons">
			<li><a href="./MemberInsert.mm"><span class= "button">회원가입</span></a></li>
		</ul>
		

			</section>

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