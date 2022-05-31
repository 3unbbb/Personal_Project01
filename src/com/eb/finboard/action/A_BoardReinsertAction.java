package com.eb.finboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.finboard.db.A_BoardDAO;
import com.eb.finboard.db.A_BoardDTO;

public class A_BoardReinsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("M : BoardReinsertAction-execute()호출");
		
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
		
		//전달받은정보  num, re_ref, lev, seq, name, pass subject, content
		//dto에 저장
	
		A_BoardDTO dto = new A_BoardDTO();
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setDepartment(request.getParameter("department"));
		dto.setId(id);
		dto.setContent(request.getParameter("content"));
		dto.setSubject(request.getParameter("subject"));
		dto.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		dto.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		dto.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		
		dto.setIp(request.getRemoteAddr());
		
		//db사용 o
		A_BoardDAO dao = new A_BoardDAO();
		dao.boardReinsert(dto);
		
		forward.setPath("./A_BoardList.abo");
		forward.setRedirect(true);
		
		return forward;
	}

}
