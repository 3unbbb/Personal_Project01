package com.eb.purboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.purboard.db.U_BoardDAO;
import com.eb.purboard.db.U_BoardDTO;



public class U_BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ActionForward forward = new ActionForward();
		if(id == null){
		out.println("<script language='javascript'>");
		out.println("alert('로그인이 필요한 서비스입니다.')");
		out.println("location.puref='./Login.mm';");
		out.println("</script>");

		out.flush();
		
		
		}
			
		//받아올정보(num, pageNum)
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
			
		//db사용 o, 
		U_BoardDAO dao = new U_BoardDAO();
		dao.updateReadCount(num);
		
		//글 번호에 해당하는 글 정보 가져오기
		U_BoardDTO dto = dao.getBoard(num);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		

		forward.setPath("./purBoard/pur_board_content.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
