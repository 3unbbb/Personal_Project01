<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%	String sessionId = (String)session.getAttribute("id");
	
		if(sessionId == null){
			 %><script>
			 alert('로그인 후 사용가능합니다.');
			 location.href='./Login.mm';</script><%
		}
	
	%>

<%



	//다운로드 할 파일
	String fileName = request.getParameter("fileName");

	//파일 주소
	String savePath = "upload";
	
	//업로드 된 폴더 접근
	ServletContext ctx = getServletContext();
	String downLoadPath = ctx.getRealPath(savePath);
	
	
	//다운로드할 파일의 경로
	String downFilePath = downLoadPath + "\\" + fileName;
	System.out.println("파일 경로" + downFilePath);
	
	byte[] b = new byte[4096];
	FileInputStream fis = new FileInputStream(downFilePath);
	
	String MimeType = ctx.getMimeType(downFilePath);
	
	System.out.println("MimeType : " + MimeType);
	
	if(MimeType == null){
		MimeType = "applicaion/octect=stream";
	}
	
	response.setContentType(MimeType);
	
	 String agent = request.getHeader("User-Agent");
     boolean ieBrowser  = (agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1);
     
     if(ieBrowser){
   	  // ie : 다운로드시 한글 깨짐, 공백문자 표시 +
   	  
   	 fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
   	  
     }else{
   	  // 그외 나머지 브라우저 : 인코딩처리 (한글처리)
   	  
   	  fileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
   	  
     }
	
  // 모든 파일이 다운로드 형태로 실행설정
     response.setHeader("Content-Disposition", "attachment; filename="+fileName);
     
     
     out.clear();
     out = pageContext.pushBody(); 
     
     
     // 출력 스트림 객체
     
     ServletOutputStream out2 = response.getOutputStream();
     
     int data = 0;
     
     // 파일을 배열의 크기만큼씩 읽어오기 / 파일이 끝날때까지
     while( (data = fis.read(b,0,b.length)) != -1 ){
   	     out2.write(b,0,data);
     }
     // 배열에 공백을 채우기
     out2.flush();
     out2.close();
     fis.close(); 
	
%>

</body>
</html>