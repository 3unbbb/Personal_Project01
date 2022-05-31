package com.eb.hrboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.board.db.BoardDTO;
import com.eb.hrboard.db.H_BoardDAO;
import com.eb.hrboard.db.H_BoardDTO;
import com.eb.member.db.MemberDAO;

public class H_BoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		System.out.println("M : BoardWriteAction-execute()호출");
		
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
		MemberDAO Mdao = new MemberDAO();
		BoardDTO Bdto  =  Mdao.getBoardMember(id);
			
		
		//전달된 정보를 저장(Board DTO)
		H_BoardDTO dto = new H_BoardDTO();
		dto.setContent(request.getParameter("content"));
		dto.setDepartment(request.getParameter("department"));
		dto.setId(request.getParameter("id"));
		dto.setSubject(request.getParameter("subject"));
		
		
		dto.setIp(request.getRemoteAddr());
		
		//Board DAO 객체 생성
		H_BoardDAO dao = new H_BoardDAO();

		
		
		//dao 동작실행 insertBoard(dto)
		dao.insertBoard(dto);
		
		//페이지 이동 -> 이동정보를 저장해서 컨트롤러 전달
		forward.setPath("./H_BoardList.hbo");
		forward.setRedirect(true);
		
		return forward;
		
	}

	
		
	
}
