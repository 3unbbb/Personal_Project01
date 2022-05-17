package com.eb.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eb.board.db.BoardDAO;
import com.eb.member.db.MemberDTO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("M : /BoardListAction-execute()호출");
		
		//전달받은 정보 없음
		
		//BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
		// 글 개수 확인 동작 실행 = getBoardCount();
		int result = dao.getBoardCount();
		System.out.println("M : 글 개수" + result);
		
		// 페이징 처리 1
		// 한 페이지에 보여줄 글의 개수
			int pageSize = 5;
			
		// 현 페이지 정보 계산하기
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null) {
					pageNum = "1"; // pageNum정보가 없을경우 항상 1페이지
				}

		// 페이지 시작행 계산 1, 11, 21, 31, 41,......
			int currentPage = Integer.parseInt(pageNum);

			int startRow = (currentPage - 1) * pageSize + 1;
		// 페이지 끝행 계산 10,20,30,40,....
			int endRow = currentPage * pageSize;
		
		//글이 있을 때 그 정보 전부를 가져오기
		//getBoardList();
		List boardList = null;
		if(result > 0){
			boardList = dao.getBoardList(startRow, pageSize); 
		}
		
		//reqeust 영역에 글 정보(list)저장
		request.setAttribute("memberList", memberList);
		
		
		// 연결된 뷰에 출력
		// 페이지 이동(./center/notice.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./center/BoardList.mm");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
