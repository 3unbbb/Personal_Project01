package com.eb.gallery.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eb.gallery.db.GalleryDAO;


public class GalleryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("M : /GalleryListAction-execute()호출");
		
		//전달받은 정보 X
		
		//DB 사용 ㅇ => DAO
		
		GalleryDAO dao = new GalleryDAO();
		
		int result = dao.getGalleryCount();
		
		//글이 있을 때 글 정보 전부를 가져오기(List로)
		//페이징처리
		//한 페이지에 보여줄 글의 개수 : 5
		int pageSize=5;
		
		//현 페이지 정보 계산하기
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage - 1) * pageSize +1;
		int endRow = currentPage * pageSize;
		//////////////////////////////////////////////////////
		
		List galleryList = null;
		
		if(result>0){
			galleryList = dao.getGalleryList(startRow, pageSize);
		}
		
		//전체 페이지 수
		int pageCount = result / pageSize + (result % pageSize == 0? 0 : 1);
		
		//한 화면에 보여줄 페이지 블럭의 수
		int pageBlock = 3;
		
		//페이지 블럭의 시작번호 1~10 > 1, 11~20 > 11
		int startPage = ((currentPage - 1)/pageBlock)* pageBlock + 1;
		
		// 페이지 블럭의 끝번호
		int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) {
				endPage = pageCount;
			}
		
		
		
		//request영역에 글 정보 저장
		
		request.setAttribute("galleryList", galleryList);
		
		//request영역에 페이지 넘버 정보 저장해서 list.jsp페이지로 이동
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		
		//페이지이동(./center/board_list.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./galleryBoard/gallery_list.jsp");
		forward.setRedirect(false);

		
		
		return forward;
	}

}
