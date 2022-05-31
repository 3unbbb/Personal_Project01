package com.eb.hrboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.hrboard.db.H_BoardDAO;
import com.eb.hrboard.db.H_BoardDTO;

public class H_BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			System.out.println("DAO : BoardUpdateAction-execute()호출");
			
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
			
			int num = Integer.parseInt(request.getParameter("num"));
			String pageNum = request.getParameter("pageNum");
			
			//전달된 정보를 저장(Board DTO)
			H_BoardDTO dto = new H_BoardDTO();

			dto.setContent(request.getParameter("content"));
			dto.setSubject(request.getParameter("subject"));

			
			System.out.println(request.getParameter("id"));
			System.out.println(request.getParameter("subject"));
			
			dto.setIp(request.getRemoteAddr());
			System.out.println("M : "+ dto);
			
			//Board DAO 객체 생성
			H_BoardDAO dao = new H_BoardDAO();
			
			//dao 동작실행 insertBoard(dto)
			dao.updateBoard(num, dto);
			
			//페이지 이동
			forward.setPath("./H_BoardList.hbo");
			forward.setRedirect(true);
			
			
		return forward;
	}

}
