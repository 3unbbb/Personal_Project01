package com.eb.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	//DB에 eb_board 테이블 관련된 모든 동작을 처리
	
	//공통변수 선언
	
	private Connection con = null;  		//Connection import
	private PreparedStatement pstmt = null; //PreparedStatement import
	private ResultSet rs = null;			//ResultSet import
	private String sql = "";
	
	//DB연결 메서드
	private Connection getCon() throws Exception{
		//1.2. DB연결
		
		//1. 프로젝트 정보를 초기화
		Context initCTX = new InitialContext();
		//2. 프로젝트에 저장된 리소스 정보 불러오기
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/board");
		//3. DB연결
		con = ds.getConnection();
		
		System.out.println("DAO : 디비연결 성공(커넥션 풀)");
		System.out.println("DAO : "+ con);
		
		
		return con;
	}
	
	
	
	
}
