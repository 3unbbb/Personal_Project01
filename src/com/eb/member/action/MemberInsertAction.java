package com.eb.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eb.member.db.MemberDAO;
import com.eb.member.db.MemberDTO;

public class MemberInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		System.out.println("M : MemberInsertAction-execute() 호출");
		
		//회원 추가
		
		//회원 정보 저장(파라메터) => MemberDTO
		//아이디, 비밀번호, 이름, 나이, 전화번호, 이메일, 회사, 부서(select)
		
		MemberDTO dto = new MemberDTO();
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		dto.setCompany(request.getParameter("company"));
		dto.setDepartment(request.getParameter("department"));
		dto.setEmail(request.getParameter("email"));
		dto.setId(request.getParameter("id"));
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setTel(request.getParameter("tel"));
		dto.setAddress(request.getParameter("address"));
		dto.setDetailAddress(request.getParameter("detailAddress"));
		dto.setPostcode(request.getParameter("postcode"));

		System.out.println("저장할 회원 정보 : " + dto);
		
		//DAO객체 생성 - 메서드 만들어서 등록하기(insertMember) =>DAO로가기
		
		MemberDAO dao = new MemberDAO();
		
		//회원가입 동작 실행
		int result = dao.insertMember(dto);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//System.out.println("M : result - " + result );
		ActionForward forward = new ActionForward();
		
		if(result == -1){
			
			out.println("<script language='javascript'>");
			out.println("alert('이미 존재하는 아이디입니다.')");
			out.println("location.href=history.go(-1);");
			out.println("</script>");
			return null;
		}else{
		
		//페이지 이동("./MemberList.mm")
			out.println("<script language='javascript'>");
			out.println("alert('회원가입이 완료되었습니다.')");
			out.println("location.href='Login.mm';");
			out.println("</script>");
		
		}
		return forward;
	}

}
