package com.eb.gallery.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.gallery.db.GalleryDAO;



public class GalleryDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("DAO : GalleryDelteAction-execute() 실행");
		
		
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
		GalleryDAO dao = new GalleryDAO();
		dao.deleteGallery(num);
		
		
		out.println("<script language='javascript'>");
		out.println("alert('게시글이 삭제되었습니다.')");
		out.println("location.href='./GalleryList.ga';");
		out.println("</script>");

		out.flush();
		
		
		//페이지 이동
		forward.setPath("./GalleryList.ga");
		forward.setRedirect(true);
		return null;
		

	}

}