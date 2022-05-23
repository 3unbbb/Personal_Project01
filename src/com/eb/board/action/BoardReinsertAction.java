package com.eb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eb.board.db.BoardDAO;
import com.eb.board.db.BoardDTO;

public class BoardReinsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("M : BoardReinsertAction-execute()호출");
		
		//전달받은정보  num, re_ref, lev, seq, name, pass subject, content
		//dto에 저장
		BoardDTO dto = new BoardDTO();
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setB_Company(request.getParameter("b_company"));
		dto.setB_Department(request.getParameter("b_department"));
		dto.setB_Id(request.getParameter("id"));
		dto.setContent(request.getParameter("content"));
		dto.setFile(request.getParameter("file"));
		dto.setSubject(request.getParameter("subject"));
		dto.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		dto.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		dto.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		
		//db사용 o
		BoardDAO dao = new BoardDAO();
		dao.boardReinsert(dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
