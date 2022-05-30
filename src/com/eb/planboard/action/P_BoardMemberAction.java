package com.eb.planboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.board.db.BoardDAO;
import com.eb.board.db.BoardDTO;
import com.eb.member.db.MemberDAO;
import com.eb.member.db.MemberDTO;
import com.eb.palnboard.db.P_BoardDTO;

public class P_BoardMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			System.out.println("M : BoardMemberAction-execute()호출");
			
			//전달받은 정보 = session의 아이디
			
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
						
			MemberDAO dao = new MemberDAO();
			
			//id에 해당하는 정보 가지고 오기 getMember(id)
			P_BoardDTO dto = new P_BoardDTO();
			
			dto = dao.getP_BoardMember(id);
			
			System.out.println("M - 현재 글쓰기 아이디 : "+ dto.getId());

			request.setAttribute("dto", dto);
			

			forward.setPath("./planBoard/plan_boardWrite.jsp");
			forward.setRedirect(false);
							
		// TODO Auto-generated method stub
		return forward;
	}

}
