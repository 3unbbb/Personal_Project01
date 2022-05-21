package com.eb.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.member.db.MemberDAO;

public class MemberInfoUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		//전달받은 정보 o
		String company = (String)request.getAttribute("company");
		String department = (String)request.getAttribute("department");
		String email = (String)request.getAttribute("email");
		
		
		//db에 저장
		MemberDAO dao = new MemberDAO();
		
		dao.updateMember(id, company, department, email);
		
		
		return null;
	}

}
