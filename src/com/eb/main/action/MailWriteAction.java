package com.eb.main.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.member.db.MemberDAO;

public class MailWriteAction implements Action {

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
		
		//id에 해당하는 정보 가지고 오기 getMember(id)
		
		String Em = dao.getEmail(id);
		request.setAttribute("Em", Em);
		
		forward.setPath("./main/mail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
