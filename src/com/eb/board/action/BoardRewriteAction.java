package com.eb.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.board.db.BoardDAO;
import com.eb.board.db.BoardDTO;
import com.eb.member.db.MemberDAO;

public class BoardRewriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			System.out.println("M : BoardRewriteAction-execute() 호출");
			
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			ActionForward forward = new ActionForward();
			if(id == null){
			out.println("<script language='javascript'>");
			out.println("alert('로그인이 필요한 서비스입니다.')");
			out.println("location.href='./Login.mm';");
			out.println("</script>");

			out.flush();
			
			
			}
			
			//받아올 정보 num, id
			int num = Integer.parseInt(request.getParameter("num"));
			
			MemberDAO dao = new MemberDAO();
			
			//id에 해당하는 정보 가지고 오기 getMember(id)
			BoardDTO dto = new BoardDTO();
			
			dto = dao.getBoardMember(id);
			
			System.out.println("M - dto : " + dto.getB_Company());
			System.out.println("M - 현재 글쓰기 아이디 : "+ dto.getB_Id());

			request.setAttribute("dto", dto);
			

			forward.setPath("./center/board_rewrite.jsp");
			forward.setRedirect(false);
							
		// TODO Auto-generated method stub
		return forward;
		
	}

}
