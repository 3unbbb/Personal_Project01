package com.eb.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			System.out.println("M : MemberDeleteAction-execute() 호출");
			
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			
			MemberDAO dao = new MemberDAO();
			
			dao.memberDelete(id);
			
			session.invalidate();
		
			ActionForward forward = new ActionForward();
			forward.setPath("./Main.ma");
			forward.setRedirect(true);
			
		return forward;
	}

}
