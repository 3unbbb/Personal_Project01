package com.eb.purboard.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.member.db.MemberDAO;
import com.eb.purboard.db.U_BoardDAO;


public class U_BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("M : /U_BoardListAction-execute()호출");
		
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
		
		//전달받은 정보 X
		
		//DB 사용 ㅇ => DAO
				
		U_BoardDAO dao = new U_BoardDAO();
		
		int result = dao.getBoardCount();
		
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
		
		List boardList = null;
		if(result>0){
			boardList = dao.getBoardList(startRow, pageSize);
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
			
		MemberDAO Mdao = new MemberDAO();
		String De =  (String)Mdao.getP_BoardMember(id);
			
		request.setAttribute("De", De);
		
		//request영역에 글 정보 저장
		
		request.setAttribute("boardList", boardList);
		
		//request영역에 페이지 넘버 정보 저장해서 list.jsp페이지로 이동
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		
		//페이지이동(./center/U_Board_list.jsp)
		forward.setPath("./purBoard/pur_board_list.jsp");
		forward.setRedirect(false);

		
		
		return forward;
	}

}
