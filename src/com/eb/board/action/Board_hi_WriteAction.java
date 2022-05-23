package com.eb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eb.board.db.BoardDAO;
import com.eb.board.db.BoardDTO;

public class Board_hi_WriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("M : BoardWriteAction-execute()호출");
		
		
		
		//전달된 정보를 저장(Board DTO)
		BoardDTO dto = new BoardDTO();
		dto.setB_Company(request.getParameter("b_company"));
		dto.setContent(request.getParameter("content"));
		dto.setB_Department(request.getParameter("b_department"));
		dto.setB_Id(request.getParameter("b_id"));
		dto.setSubject(request.getParameter("subject"));
		
		System.out.println(request.getParameter("b_id"));
		System.out.println(request.getParameter("subject"));
		
		dto.setIp(request.getRemoteAddr());
		System.out.println("M : "+ dto);
		
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
