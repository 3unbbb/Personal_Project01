package com.eb.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet{

	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController - doProcess() 호출");
		System.out.println("GET/POST방식 모두 처리");
		
	///////1. 가상 주소 계산 시작//////////////////////////////////////
		System.out.println(" C : 1. 가상 주소 계산 시작");

		// 가상주소 가져오기
		String requestURI =	request.getRequestURI();
		System.out.println(" C : requestURI - "+requestURI);
		// 프로젝트명 가져오기
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - "+ctxPath);        
		// 가상주소 계산 (가상주소 - 프로젝트명)
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - "+command);


		System.out.println(" C : 1. 가상 주소 계산 끝\n");
	///////1. 가상 주소 계산 끝/////////////////////////////////////////
		
		
	///////2. 가상 주소 매핑 시작///////////////////////////////////////

		System.out.println(" C : 2. 가상 주소 매핑 시작 ");
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/MemberInsert.mm")){
			System.out.println("C : /MemberInsert.mm 호출");
		
			//DB사용 x, 페이지 이동 O
			
			forward = new ActionForward();
			forward.setPath("./member/member_insert.jsp");
			forward.setRedirect(false);
			
		}
		else if(command.equals("/MemberInsertAction.mm")){
			System.out.println("C : /MemberInsertAction.mm 호출");
			
			//MemberInsertAction 객체 생성
			action = new MemberInsertAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		else if(command.equals("/MemberList.mm")){
			System.out.println("C : /MemberList.mm 호출");
			//DB사용, VIEW 출력
			
			//MemberListAction 객체 생성
			action = new MemberListAction();
			
			try {
			forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		
		
		
		
		System.out.println(" C : 2. 가상 주소 매핑 끝\n ");
	///////2. 가상 주소 매핑 시작//////////////////////////////////////
		
	///////3.페이지 이동//////////////////////////////////////////////
		
		
		System.out.println(" C : 3. 페이지 이동 시작");
		if(forward != null){ // 페이지 이동정보가 있을때
			
			if(forward.isRedirect()){ // true
				System.out.println(" C : redirect방식, "+forward.getPath()+"로 이동");
				response.sendRedirect(forward.getPath());
				
			}else{ // false
				RequestDispatcher dis =
						 request.getRequestDispatcher(forward.getPath());
				
				System.out.println(" C : forward방식, "+forward.getPath()+"로 이동");
				dis.forward(request, response);				
			}			
			
		}
		System.out.println(" C : 3. 페이지 이동 끝\n ");		
		///////3.페이지 이동////////////////////////////////////////

		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController - doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController - doPost() 호출");
		doProcess(request, response);
	}
	
	

}
