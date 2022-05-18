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
		

		
		return null;
	}

}
