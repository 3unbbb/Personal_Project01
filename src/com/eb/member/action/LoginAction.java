package com.eb.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eb.member.db.MemberDAO;
import com.eb.member.db.MemberDTO;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			System.out.println("M : LoginAction-execute() 호출");
			
			//전달받은 정보 잇는가? ㅇㅇ 아이디, pw 입력받은거 => dto
			
			MemberDTO dto = new MemberDTO();
			dto.setId(request.getParameter("id"));
			dto.setPass(request.getParameter("pass"));
			
			
			
			//전달받은 정보 db와 비교 => dao 객체 사용
			MemberDAO dao = new MemberDAO();
			
			//로그인 동작
			int result = dao.login(dto);
			
			//result 결과에 따라 동작 설정
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			//System.out.println("M : result - " + result );
			ActionForward forward = new ActionForward();

			if(result == -1 ){	//비회원
				out.println("<script language='javascript'>");
				out.println("alert('로그인 정보가 잘못되었습니다.')");
				out.println("location.href='./Login.mm';");
				out.println("</script>");
				return null;
				
				
			}else if(result == 0){
				
				out.println("<script language='javascript'>");
				out.println("alert('로그인 정보가 없습니다.')");
				out.println("location.href='./Login.mm';");
				out.println("</script>");
				return null;
			}
			
			else{ //result == 1 \
				//세션 생셩 & 세션에 id 저장
				HttpSession session = request.getSession();
				session.setAttribute("id", dto.getId());
			
				//페이지 이동
				forward.setPath("./Main.ma");
				forward.setRedirect(true);
				return forward;
			
			}
		
	}

}
