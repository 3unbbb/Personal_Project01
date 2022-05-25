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
		String company = (String)request.getParameter("u_company");
		String department = (String)request.getParameter("u_department");
		String email = (String)request.getParameter("u_email");
		String postcode = (String)request.getParameter("postcode");
		String address = (String)request.getParameter("address");
		String detailAddress = (String)request.getParameter("detailAddress");
		System.out.println("company :" + company );
		System.out.println("department :" + department );
		System.out.println("email :" + email );
		
		
		//db에 저장
		MemberDAO dao = new MemberDAO();
		
		dao.updateMember(id, company, department, email,postcode, address, detailAddress);
		
		//페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.ma");
		forward.setRedirect(true);
		
		return forward;
	}

}
