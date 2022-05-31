package com.eb.hrboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.hrboard.db.H_BoardDAO;
import com.eb.hrboard.db.H_BoardDTO;

public class H_BoardReinsertAction implements Action {

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
	
		H_BoardDTO dto = new H_BoardDTO();
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
		H_BoardDAO dao = new H_BoardDAO();
		dao.boardReinsert(dto);
		
		forward.setPath("./H_BoardList.pbo");
		forward.setRedirect(true);
		
		return forward;
	}

}
