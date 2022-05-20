package com.eb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eb.board.db.BoardDAO;
import com.eb.board.db.BoardDTO;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("M : BoardModifyAction-execute()호출");
		
		//전달받은 정보 있음(글번호, 페이지 번호)
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		//db사용 0
		BoardDAO dao = new BoardDAO();
		
		BoardDTO dto = dao.modifyBoard(num);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		//페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./center//board_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
