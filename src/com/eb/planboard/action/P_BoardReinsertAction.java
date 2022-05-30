package com.eb.planboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.board.db.BoardDAO;
import com.eb.board.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class P_BoardReinsertAction implements Action {

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
		
		String path = request.getRealPath("/upload");
		System.out.println("M : "+ path);
				
		int maxSize = 5*1024*1024;
		MultipartRequest multi = new MultipartRequest(
			request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		BoardDTO dto = new BoardDTO();
		dto.setNum(Integer.parseInt(multi.getParameter("num")));
		dto.setB_Company(multi.getParameter("b_company"));
		dto.setB_Department(multi.getParameter("b_department"));
		dto.setB_Id(id);
		dto.setContent(multi.getParameter("content"));
		dto.setFile(multi.getFilesystemName("file"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setRe_ref(Integer.parseInt(multi.getParameter("re_ref")));
		dto.setRe_lev(Integer.parseInt(multi.getParameter("re_lev")));
		dto.setRe_seq(Integer.parseInt(multi.getParameter("re_seq")));
		
		dto.setIp(request.getRemoteAddr());
		
		//db사용 o
		BoardDAO dao = new BoardDAO();
		dao.boardReinsert(dto);
		
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}

}
