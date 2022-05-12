package com.eb.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eb.member.db.MemberDAO;
import com.eb.member.db.MemberDTO;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
			System.out.println("M : MemberListAction-execute() 호출");
			
			//받아올 정보 있는지?? 있음
			//어디서 받아와야하는지?? DB에서(DAO)
			//DAO 객체 만들기
			
			MemberDAO dao = new MemberDAO();
			//DAO 에서 정보 받아오기(List)
			List<MemberDTO> memberList = dao.getMemberList();
			
			//request영역에 저장(페이지 이동한 곳에서 출력할 정보저장)
			request.setAttribute("memberList", memberList);
			
			//페이지 이동(./member/member_list.jsp)
			ActionForward forward = new ActionForward();
			forward.setPath("./MemberList.mm");
			forward.setRedirect(false);
			
		return forward;
	}

}
