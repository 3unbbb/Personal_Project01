package com.eb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eb.board.db.BoardDAO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		System.out.println("DAO : BoardDelteAction-execute() 실행");
		
		//받아올 정보 : 글 번호
		int num = Integer.parseInt(request.getParameter("num"));
		
		//DB사용
		BoardDAO dao = new BoardDAO();
		dao.deleteBoard(num);
		
		//페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		
		return forward;
	}

}
