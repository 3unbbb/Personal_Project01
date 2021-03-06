package com.eb.planboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.board.db.BoardDAO;
import com.eb.palnboard.db.P_BoardDAO;

public class P_BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		System.out.println("DAO : BoardDelteAction-execute() 실행");
		
		
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
		
		//받아올 정보 : 글 번호
		int num = Integer.parseInt(request.getParameter("num"));
		
		//DB사용
		P_BoardDAO dao = new P_BoardDAO();
		dao.deleteP_Board(num);
		
		//페이지 이동
		forward.setPath("./P_BoardList.pbo");
		forward.setRedirect(true);
		
		
		return forward;
	}

}
