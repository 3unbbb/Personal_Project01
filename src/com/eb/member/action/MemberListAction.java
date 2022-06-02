package com.eb.member.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.member.db.MemberDAO;
import com.eb.member.db.MemberDTO;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

			System.out.println("M : MemberListAction-execute() 호출");

			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			ActionForward forward = new ActionForward();
			if(id.equals("admin") == false){
			out.println("<script language='javascript'>");
			out.println("alert('관리자 권한이 없습니다.')");
			out.println("location.href='./Main.ma';");
			out.println("</script>");

			out.flush();
				
			}

			MemberDAO dao = new MemberDAO();
			//DAO 에서 정보 받아오기(List)
			List<MemberDTO> memberList = dao.getMemberList();

			//request영역에 저장(페이지 이동한 곳에서 출력할 정보저장)
			request.setAttribute("memberList", memberList);

			//페이지 이동(./member/member_list.jsp)
			forward.setPath("./member/member_list.jsp");
			forward.setRedirect(false);

		return forward;
	}

}