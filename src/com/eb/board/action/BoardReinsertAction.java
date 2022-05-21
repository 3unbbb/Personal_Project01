package com.eb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eb.board.db.BoardDAO;

public class BoardReinsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("M : BoardReinsertAction.bo");
		
		//전달받은정보  num
		int num = Integer.parseInt(request.getParameter("num"));
		
		//db사용 o
		BoardDAO dao = new BoardDAO();
		dao.boardReinsert(num);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(false);
		
		return forward;
	}

}
