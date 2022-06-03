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
		<h1>회원 정보</h1>
		
	<form action="./MemberInfoUpdate.mm" method="post" name="infoForm" onsubmit="return checkLogin();">
		<table id="notice">
			
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
			<td>주소</td>
			<td>	<input type="text" id="postcode" name = "postcode" value="${dto.getPostcode()  }">
				<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="address" name = "address" value="${dto.getAddress() }"><br>
				<input type="text" id="detailAddress" name = "detailAddress" value="${dto.getDetailAddress() }">
				
				<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
				<script>
  									  function execDaumPostcode() {
   									     new daum.Postcode({
        								    oncomplete: function(data) {
               
              								var addr = ''; // 주소 변수
                							var extraAddr = ''; // 참고항목 변수

               								if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                   								 addr = data.roadAddress;
              								  } else { // 사용자가 지번 주소를 선택했을 경우(J)
                  								  addr = data.jibunAddress;
            								    }

							                document.getElementById('postcode').value = data.zonecode;
           								    document.getElementById("address").value = addr;
	
											document.getElementById("detailAddress").focus();
            							}
   							  		  }).open();
   								 	}
					</script>			
				
			</td>
			</tr>
			<tr>	
				<td >department</td>
				<td><select name ="u_department">
							<option>---부서를 선택하세요---</option>
							<option value ="strategy">기획</option>
							<option value ="hr">인사</option>
							<option value ="account">재무/회계</option>
							<option value ="purchase">구매/자재</option>
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