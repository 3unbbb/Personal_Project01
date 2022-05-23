package com.eb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.board.db.BoardDAO;
import com.eb.board.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		//받아올정보(num, pageNum)
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
			
		//db사용 o, 
		BoardDAO dao = new BoardDAO();
		dao.updateReadCount(num);
		
		//글 번호에 해당하는 글 정보 가져오기
		BoardDTO dto = dao.getBoard(num);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward= new ActionForward();
		forward.setPath("./center/board_content.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
