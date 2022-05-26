package com.eb.gallery.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.gallery.db.GalleryDAO;
import com.eb.gallery.db.GalleryDTO;

public class GalleryContentAction implements Action {

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
		GalleryDAO dao = new GalleryDAO();
		dao.updateReadCount(num);
		
		//글 번호에 해당하는 글 정보 가져오기
		GalleryDTO dto = dao.getGallery(num);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		

		forward.setPath("./galleryBoard/gallery_content.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
