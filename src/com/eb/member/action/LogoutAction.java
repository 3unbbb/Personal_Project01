package com.eb.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//받아올 정보 = 세션정보
		HttpSession session = request.getSession();
		//세션 없애주기
		session.removeAttribute("id");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		
		out.println("<script>");
		out.println("alert('로그아웃 하셨습니다. 메인페이지로 이동합니다.');");
		out.println("</script>");
		
		
		
		ActionForward forward= new ActionForward();
		forward.setPath("./main/main.jsp");
		forward.setRedirect(true);
		
		return forward;
	}

}
