package com.eb.purboard.action;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.ubo")
public class U_BoardFrontController extends HttpServlet{
	
	
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
	
		System.out.println("C : 2. 가상주소 매핑시작");
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/U_BoardWrite.ubo")){
			System.out.println("C : /U_BoardWrite.ubo 호출");
			System.out.println("C : DB사용 o, 정보입력 페이지(view)");
			
			action = new U_BoardMainAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}else if(command.equals("/U_BoardWriteAction.ubo")){
			System.out.println("C : /U_BoardWriteAction.ubo호출");
			System.out.println("C : DB사용 O, 페이지 이동");
			
			action = new U_BoardWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(command.equals("/U_BoardList.ubo")){
			System.out.println("C : /U_BoardList.ubo 호출");
			System.out.println("db사용 , 페이지 이동 ");
			//BoardListAction 객체 생성
			
			action = new U_BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}else if(command.equals("/U_BoardContent.ubo")){
			System.out.println("C : U_BoardContent.ubo 호출");
			
			action = new U_BoardContentAction();
			
			try {
				
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/U_BoardModify.ubo")){
			System.out.println("C : /U_BoardModify.ubo 호출");
			
			action = new U_BoardModifyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/U_BoardUpdateAction.ubo")){
			System.out.println("/C : U_BoardUpdateAction.ubo 호출");
			
			action = new U_BoardUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(command.equals("/U_BoardDelete.ubo")){
			System.out.println("C : /U_BoardDelete.ubo 호출");
			
			action = new U_BoardDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/U_BoardRewrite.ubo")){
			System.out.println("C : /U_BoardRewrite.ubo 호출");
			
			action = new U_BoardRewriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/U_BoardReinsertAction.ubo")){
			System.out.println("C :/U_BoardReWrtieAction.ubo 호출");
			
			action = new U_BoardReinsertAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		System.out.println("C : 2. 가상주소 매핑끝");
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
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" BoardFrontController - doPOST() 호출");
		doProcess(request, response);
	}
	
	
	
	//
	
	
	
}
