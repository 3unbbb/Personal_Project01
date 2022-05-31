package com.eb.purboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.board.db.BoardDTO;
import com.eb.member.db.MemberDAO;

public class U_BoardMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
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
		BoardDTO dto  =  dao.getBoardMember(id);
		
		System.out.println("M - dto : " + dto.getB_Department());
		System.out.println("M - 현재 글쓰기 아이디 : "+ dto.getB_Id());
		
		request.setAttribute("dto", dto);
		
		forward.setPath("./purBoard/pur_boardWrite.jsp");
		forward.setRedirect(false);

		
		return forward;
	
	}
}
