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

		System.out.println("DAO : /BoardListAction-execute()호출");
		
		//전달받은 정보 없음
		
		//BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
		// 글 개수 확인 동작 실행 = getBoardCount();
		dao.getBoardCount();
		
		
		//글이 있을 때 그 정보 전부를 가져오기
		//getBoardList();
		List<MemberDTO> memberList = dao.getBoardList();
		
		//reqeust 영역에 글 정보(list)저장
		request.setAttribute("memberList", memberList);
		
		
		// 연결된 뷰에 출력
		// 페이지 이동(./center/notice.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./center/MemberList.mm");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
