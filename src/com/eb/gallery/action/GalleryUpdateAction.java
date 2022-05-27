package com.eb.gallery.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.gallery.db.GalleryDAO;
import com.eb.gallery.db.GalleryDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class GalleryUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("DAO : GalleryUpdateAction-execute()호출");
		
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
		
		//파일 폴더
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
				String path = request.getRealPath("/gallery_upload");
				System.out.println("M : "+ path);
						
				int maxSize = 5*1024*1024;
				MultipartRequest multi = new MultipartRequest(
					request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
								
					
				
				//전달된 정보를 저장(Board DTO)
				GalleryDTO dto = new GalleryDTO();
				dto.setContent(multi.getParameter("content"));
				dto.setId(id);
				dto.setSubject(multi.getParameter("subject"));
				
				
				String image = multi.getFilesystemName("file1")+","
						+multi.getFilesystemName("file2")+","
						+multi.getFilesystemName("file3")+","
						+multi.getFilesystemName("file4");
				
				dto.setImage(image);
				
			//	System.out.println(multi.getParameter("id"));
			//	System.out.println(multi.getParameter("subject"));
				
				dto.setIp(request.getRemoteAddr());
				//System.out.println("M : "+ dto);
				
				//Board DAO 객체 생성
				GalleryDAO dao = new GalleryDAO();
				
				//dao 동작실행 insertBoard(dto)
				dao.updateGallery(num, dto);
				
				//페이지 이동 -> 이동정보를 저장해서 컨트롤러 전달
				forward.setPath("./GalleryList.ga");
				forward.setRedirect(true);
				
				return forward;
			}

		}
