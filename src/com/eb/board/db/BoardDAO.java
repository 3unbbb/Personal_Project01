package com.eb.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	//DB연결 메서드 시작
	private Connection getCon() throws Exception{
		//1.2. DB연결
		
		//1. 프로젝트 정보를 초기화
		Context initCTX = new InitialContext();
		//2. 프로젝트에 저장된 리소스 정보 불러오기
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/board");
		//3. DB연결
		con = ds.getConnection();
		
		System.out.println("DAO : 디비연결 완(커넥션 풀)");
		System.out.println("DAO : "+ con);
		
		
		return con;
	}
	
	//DB연결 메서드 끝
	
	//DB 자원해제 메서드 시작
	public void closeDB(){
		
		try {
			if (rs !=null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
			System.out.println("DAO : 디비 연결 해제");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//DB 자원해제 메서드 끝
	
	//inSertBoard(bb) 시작
	public void insertBoard(BoardDTO bb){
		int num = 0; //글번호 저장 변수
		try {
			
			//1. 디비연결
			con = getCon();
			
			//2. sql작성(글번호 계산) & pstmt 객체
			sql = "select max(num) form eb_board";
			pstmt = con.prepareStatement(sql);
			//3. sql 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()){ //max(num) 컬럼의 결과는 항상 존재함
				num = rs.getInt(1)+1; //index 0부터 시작하니까
				
			}
			
			System.out.println("DAO : 글번호" + num);
			
			//글쓰기
			//DB는 이미 연결 되어 있으니까 3.SQL 작성, pstmt 객체
			sql = "insert into eb_board(num,id,company, department,subject,content,"
					+"readcount,re_ref,re_lev,re_seq,date,ip,file)"
					+"values(?,?,?,?,?,?,?,?,?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, bb.getId());
			pstmt.setString(3, bb.getCompany());
			pstmt.setString(4, bb.getDepartment());
			pstmt.setString(5, bb.getSubject());
			pstmt.setString(6, bb.getContent());
			
			pstmt.setInt(7, 0);
			pstmt.setInt(8, num);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);

			pstmt.setString(11, bb.getIp());
			pstmt.setString(12, bb.getFile());
			
			//4. sql 실행
			pstmt.executeUpdate();		//insert문은 executeUpdate 사용
			System.out.println("DAO : 글쓰기 완");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}
	//inSertBoard(bb) 끝
	
	
	
	
}
