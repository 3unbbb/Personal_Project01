<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="./js/jquery-3.6.0.js"></script>
<script type="text/javascript">
  $(document).ready(function(){

      //jtbc - 뉴스RSS
      $.ajax({
    	  url : "https://fs.jtbc.joins.com//RSS/newsflash.xml",
    	  success: function(data){
    		  alert(" JTBC 접근 완료 ");
    		  
    		  // 뉴스 기사를 화면에 출력 + 기사클릭시 링크로 이동
    		  $(data).find('item').each(function(){
    			  
    			  var title = $(this).find('title').text();
    			  var link = $(this).find('link').text();
    			  
    			  $('body').append("<a href='"+link+"'>"+title+"</a><br><br>");
    			  
    		  });
    		  
    		  
    	  }
      });

  });

</script>


</head>
<body>
    <H1>ajaxRSS.html</H1>
</body>
</html>