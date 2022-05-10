package com.eb.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet{
	
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("BoardFrontController - do Process() 호출");
		System.out.println("GET/POST방식 모두 처리");
		
		
	// 1. 가상주소 계산//
		System.out.println("C : 1. 가상주소 계산 시작");
		
		//가상주소 가져오기
		String requestURI = request.getRequestURI();
		System.out.println("C : requestURI(가상주소) - " +requestURI);
		
		//프로젝트명 가져오기
		String ctxPath = request.getContextPath();
		System.out.println("C : ctxPath(프로젝트명) - " + ctxPath);
		
		//가상주소 계산(가상주소 - 프로젝트명)
		String command = requestURI.substring(ctxPath.length());
		System.out.println("C : command(가상주소-프로젝트명) -" + command);
		
		System.out.println("C : 1. 가상주소 계산 끝");
	// 1. 가상주소 계산//
		
	//2. 가상주소 매핑//
	
		System.out.println("C : 가상주소 매핑시작");
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/BoardWrite.bo")){
			System.out.println("C : /BoardWrite.bo 호출");
			System.out.println("C : DB사용 X, 정보입력 페이지(view)");
			
			//페이지 이동 객체 생성
			forward = new ActionForward();
			forward.setPath("./center/boardWrite.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/BoardWriteAction.bo")){
			System.out.println("C : /BoardWriteAction.bo호출");
			System.out.println("C : DB사용 O, 페이지 이동");
			
			action = new BoardWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		System.out.println("C : 가상주소 매핑끝");
	//2. 가상주소 매핑//
		
	//3. 페이지 이동//	
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
	//3. 페이지 이동//	
		
		
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" BoardFrontController - doGET() 호출");
		doProcess(request, response);
		// TODO Auto-generated method stub
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" BoardFrontController - doPOST() 호출");
		doProcess(request, response);
		// TODO Auto-generated method stub
	}
	
	
	
	//
	
	
	
}
