package com.eb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eb.board.db.BoardDAO;
import com.eb.board.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		System.out.println("M : BoardWriteAction-execute()호출");
		
		//파일 폴더
		String path = request.getRealPath("/upload");
		System.out.println("M : "+ path);
				
		int maxSize = 5*1024*1024;
		MultipartRequest multi = new MultipartRequest(
			request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
						
			
		
		//전달된 정보를 저장(Board DTO)
		BoardDTO dto = new BoardDTO();
		dto.setB_Company(multi.getParameter("b_company"));
		dto.setContent(multi.getParameter("content"));
		dto.setB_Department(multi.getParameter("b_department"));
		dto.setB_Id(multi.getParameter("b_id"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setFile(multi.getFilesystemName("file"));
		
		System.out.println(multi.getParameter("b_id"));
		System.out.println(multi.getParameter("subject"));
		
		dto.setIp(request.getRemoteAddr());
		//System.out.println("M : "+ dto);
		
		//Board DAO 객체 생성
		BoardDAO dao = new BoardDAO();
		
		//dao 동작실행 insertBoard(dto)
		dao.insertBoard(dto);
		
		//페이지 이동 -> 이동정보를 저장해서 컨트롤러 전달
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
		
	}

	
		
	
}
