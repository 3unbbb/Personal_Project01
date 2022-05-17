package com.eb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.board.db.BoardDAO;
import com.eb.board.db.BoardDTO;
import com.eb.member.db.MemberDAO;
import com.eb.member.db.MemberDTO;

public class BoardMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			System.out.println("M : BoardMemberAction-execute()호출");
			
			//전달받은 정보 = session의 아이디
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
						
			MemberDAO dao = new MemberDAO();
			
			//id에 해당하는 정보 가지고 오기 getMember(id)
			BoardDTO dto = new BoardDTO();
			
			dto = dao.getBoardMember(id);
			
			System.out.println("M - dto : " + dto.getB_Company());
			System.out.println("M - 현재 글쓰기 아이디 : "+ dto.getB_Id());

			request.setAttribute("dto", dto);
			

			ActionForward forward = new ActionForward();
			forward.setPath("./center/boardWrite.jsp");
			forward.setRedirect(false);
							
		// TODO Auto-generated method stub
		return forward;
	}

}
