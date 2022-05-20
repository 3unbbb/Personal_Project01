package com.eb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eb.board.db.BoardDAO;
import com.eb.board.db.BoardDTO;

public class BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			System.out.println("DAO : BoardUpdateAction-execute()호출");
			
			int num = Integer.parseInt(request.getParameter("num"));
			String pageNum = request.getParameter("pageNum");
			
			//전달된 정보를 저장(Board DTO)
			BoardDTO dto = new BoardDTO();

			dto.setContent(request.getParameter("content"));
			dto.setSubject(request.getParameter("subject"));
			dto.setFile(request.getParameter("file"));

			
			System.out.println(request.getParameter("b_id"));
			System.out.println(request.getParameter("subject"));
			
			dto.setIp(request.getRemoteAddr());
			System.out.println("M : "+ dto);
			
			//Board DAO 객체 생성
			BoardDAO dao = new BoardDAO();
			
			//dao 동작실행 insertBoard(dto)
			dao.updateBoard(num, dto);
			
			//페이지 이동
			ActionForward forward = new ActionForward();
			forward.setPath("./BoardList.bo");
			forward.setRedirect(true);
			
			
		return forward;
	}

}
