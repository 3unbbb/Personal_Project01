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
					<h1>회원가입</h1>
				</header>

				<!-- Form -->
				<h3>Form</h3>

				<form method="post" action="./MemberInsertAction.mm">
					<div class="row gtr-uniform">
						<div class="col-6 col-12-xsmall">
							아이디  <input type="text" name="id"  ><br>
							비밀번호 <input type="password" name="pass"><br>
							이름 <input type="text" name="name" ><br>
							나이 <input type="text" name="age" ><br>
							전화번호 <input type="tel" name="tel"><br>
							이메일 <input type="email" name="email"><br>
							회사 <input type="text" name="company"><br>
							부서
							<select name ="department">
							<option>---부서를 선택하세요---</option>
							<option value ="strategy">기획</option>
							<option value ="hr">인사</option>
							<option value ="account">재무/회계</option>
							<option value ="purchase">구매/자재</option>
							<option value ="production">생산</option>
							<option value ="IT">IT</option>
							<option value ="marketing">마케팅</option>
							</select>
							<br>
							
							<input type ="submit" value ="회원가입하기">
						</div>
					</div>
				</form>

				
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