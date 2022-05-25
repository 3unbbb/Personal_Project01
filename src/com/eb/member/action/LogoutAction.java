package com.eb.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("M : LogoutAction-excute()실행)");
		
		//받아올 정보 = 세션정보
		HttpSession session = request.getSession();
		//세션 없애주기
		session.invalidate();

		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ActionForward forward= new ActionForward();
		
			
		out.println("<script language='javascript'>");
		out.println("alert('로그아웃되었습니다. 메인페이지로 이동합니다.')");
		out.println("location.href='./Main.ma';");
		out.println("</script>");

		out.flush();

		
		
		
		forward.setPath("./Main.ma");
		forward.setRedirect(true);
		
		return null;
	}

}
