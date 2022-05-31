package com.eb.hrboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.hrboard.db.H_BoardDAO;
import com.eb.hrboard.db.H_BoardDTO;



public class H_BoardContentAction implements Action {

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
		out.println("location.href='./Login.mm';");
		out.println("</script>");

		out.flush();
		
		
		}
			
		//받아올정보(num, pageNum)
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
			
		//db사용 o, 
		H_BoardDAO dao = new H_BoardDAO();
		dao.updateReadCount(num);
		
		//글 번호에 해당하는 글 정보 가져오기
		H_BoardDTO dto = dao.getBoard(num);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		

		forward.setPath("./hrBoard/hr_board_content.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
