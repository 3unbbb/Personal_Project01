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
						아이디  <input type="text" name="id"  ><br>
						비밀번호 <input type="password" name="pass"><br>
						이름 <input type="text" name="name" ><br>
						나이 <input type="text" name="age" ><br>
						전화번호 <input type="tel" name="tel"><br>
						이메일 <input type="email" name="email"><br>
						주소
							<input type="text" id="postcode" name = "postcode" placeholder="우편번호" >
							<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
							<input type="text" id="address" name = "address" placeholder="주소"><br>
							<input type="text" id="detailAddress" name = "detailAddress" placeholder="상세주소">
							
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