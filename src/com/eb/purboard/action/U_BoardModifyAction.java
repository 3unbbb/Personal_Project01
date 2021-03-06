package com.eb.purboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.purboard.db.U_BoardDAO;
import com.eb.purboard.db.U_BoardDTO;

public class U_BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("M : BoardModifyAction-execute()호출");
		
		
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
		
		//전달받은 정보 있음(글번호, 페이지 번호)
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		//db사용 0
		U_BoardDAO dao = new U_BoardDAO();
		
		U_BoardDTO dto = dao.modifyBoard(num);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		//페이지 이동
		forward.setPath("./purBoard/pur_board_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
