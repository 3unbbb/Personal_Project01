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
		<script src = "./js/jquery-3.6.0.js">	</script>
		<script type="text/javascript">
		
	$(function(){
		//모든 공백 체크 정규식
		var empJ = /\s/g;
		//아이디 정규식
		var idJ = /^[a-z0-9]{4,12}$/;
		// 비밀번호 정규식
		var passJ = /^[A-Za-z0-9]{4,12}$/; 
		// 이름 정규식
		var nameJ = /^[가-힣]{2,6}$/;
		// 이메일 검사 정규식
		var emailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		// 휴대폰 번호 정규식
		var telJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
		

		$("#id").blur(function() {
			if (idJ.test($(this).val())) {
					console.log(idJ.test($(this).val()));
					$("#id_check").text('');
			} else {
				$('#id_check').text('아이디는 영어소문자와 숫자로 이루어진 4~12자 입니다.');
				$('#id_check').css('color', 'red');
			}
		});
		
		$("#pass").blur(function() {
			if (passJ.test($(this).val())) {
					console.log(passJ.test($(this).val()));
					$("#pass_check").text('');
			} else {
				$('#pass_check').text('비밀번호는 영어 대,소문자와 숫자로 이루어진 4~12자 입니다.');
				$('#pass_check').css('color', 'red');
			}
		});
		
		$("#name").blur(function() {
			if (nameJ.test($(this).val())) {
					console.log(nameJ.test($(this).val()));
					$("#name_check").text('');
			} else {
				$('#name_check').text('이름을 확인해주세요');
				$('#name_check').css('color', 'red');
			}
		});
		
		$("#email").blur(function() {
			if (emailJ.test($(this).val())) {
					console.log(emailJ.test($(this).val()));
					$("#email_check").text('');
			} else {
				$('#email_check').text('이메일 형식으로 작성해주세요');
				$('#email_check').css('color', 'red');
			}
		});
		
		$("#tel").blur(function() {
			if (telJ.test($(this).val())) {
					console.log(telJ.test($(this).val()));
					$("#tel_check").text('');
			} else {
				$('#tel_check').text('-제외한 숫자로만 입력해 주세요');
				$('#tel_check').css('color', 'red');
			}
		});
		
		
	});
		
		
		
			function checkInsert(){	
				
				if(document.userInsert.id.value == ""){
					alert('아이디를 입력해주세요');
					document.userInsert.id.focus();
					return false;					
				} if(document.userInsert.pass.value ==""){
					alert("비밀번호를 입력해주세요");
					document.userInsert.pass.focus();
					return false;
				}if(document.userInsert.name.value ==""){
					alert("이름을 입력해주세요");
					document.userInsert.name.focus();
					return false;
				}if(document.userInsert.age.value ==""){
					alert("나이를 입력해주세요");
					document.userInsert.age.focus();
					return false;
				}if(document.userInsert.tel.value ==""){
					alert("전화번호를 입력해주세요");
					document.userInsert.tel.focus();
					return false;
				}if(document.userInsert.email.value ==""){
					alert("이메일을 입력해주세요");
					document.userInsert.email.focus();
					return false;
				}if(document.userInsert.company.value ==""){
					alert("회사를 입력해주세요");
					document.userInsert.company.focus();
					return false;
				}if(document.userInsert.postcode.value ==""){
					alert("우편번호를 입력해주세요");
					document.userInsert.company.focus();
					return false;
				}if(document.userInsert.address.value ==""){
					alert("주소를 입력해주세요");
					document.userInsert.company.focus();
					return false;
				}if(document.userInsert.detailAddress.value ==""){
					alert("상세주소를 입력해주세요");
					document.userInsert.company.focus();
					return false;
				} if(document.userInsert.pass2.value != document.userInsert.pass.value ){
					alert("비밀번호가 일치하지 않습니다.");
					document.userInsert.pass2.focus();
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
				<h1>회원가입</h1>
			</header>

		<!-- Form -->
			<h3>Form</h3>

			<form method="post" action="./MemberInsertAction.mm" name ="userInsert" onsubmit="return checkInsert();">
				<div class="row gtr-uniform">
					<div class="col-6 col-12-xsmall">
						아이디  <input type="text" name="id" id="id" >
						<div id="id_check" class="check"></div> <br>
						비밀번호 <input type="password" name="pass" id="pass">
						<div id="pass_check"></div><br>
						비밀번호  확인<input type="password" name="pass2" id="pass2">
						<div id="pass_check2"></div> <br>
						이름 <input type="text" name="name" id="name">
						<div id="name_check"></div><br>
						나이 <input type="number" name="age" id="age"><br>
						전화번호 <input type="tel" name="tel" id="tel">
						<div id="tel_check"></div><br>
						이메일 <input type="email" name="email" id ="email">
						<div id="email_check"></div><br>
						주소
							<input type="text" id="postcode" name = "postcode" id ="postcode" placeholder="우편번호" >
							<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
							<input type="text" id="address" name = "address" id ="address" placeholder="주소"><br>
							<input type="text" id="detailAddress" name = "detailAddress" id ="detailAddress" placeholder="상세주소">
							
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

						<br>	 
						회사 <input type="text" name="company">
						부서
							<select name ="department">
							<option>---부서를 선택하세요---</option>
							<option value ="strategy">기획</option>
							<option value ="hr">인사</option>
							<option value ="account">재무/회계</option>
							<option value ="purchase">구매/자재</option>
							</select>
							
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