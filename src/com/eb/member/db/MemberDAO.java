package com.eb.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.eb.board.db.BoardDTO;

public class MemberDAO {
	
	//디비에 연결된 eb_member 테이블과 관련된 모든 동작을 처리
	
	// 공통변수 선언
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String sql = "";

	// 디비연결 메서드
		private Connection getCon() throws Exception {
			// 1.2. 디비연결

			// 1) 프로젝트 정보를 초기화
			Context initCTX = new InitialContext();
			// 2) 프로젝트에 저장된 리소스 정보를 불러오기
			DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/board");
			// 3) 디비연결
			con = ds.getConnection();

			System.out.println(" DAO : 디비연결 성공(커넥션풀) ");
			System.out.println(" DAO : " + con);

			return con;
		}
		// 디비연결 메서드

		// 디비 자원해제 메서드
		public void closeDB() {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
				System.out.println(" DAO : 디비 연결 자원해제 ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 디비 자원해제 메서드
		
		//insertMember(MemberDTO dto)
		
		public void insertMember(MemberDTO dto){
			
			try {
				//1.2. 디비연결
				con = getCon();
				
				//sql 작성& pstmp
				sql = "insert into eb_member (id, pass, name, age, tel, "
						+ "email, company, department) "
						+ "values(?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				
				
				//???
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getPass());
				pstmt.setString(3, dto.getName());
				pstmt.setInt(4, dto.getAge());
				pstmt.setString(5, dto.getTel());
				pstmt.setString(6, dto.getEmail());
				pstmt.setString(7, dto.getCompany());
				pstmt.setString(8, dto.getDepartment());
				
				//sql 실행
				pstmt.executeUpdate();
				
				System.out.println("DAO : 회원가입 완료!");
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
		}
		
		//insertMember
		
		//getMemberList
			public MemberDTO getMemberInfo(String id){
				
				MemberDTO dto = null;
				
				try {
					//디비연결
					con = getCon();
					
					//sql & pstmt
					sql = "select * from eb_member where id=?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, id);
					
					//sql 실행
					rs = pstmt.executeQuery();
					
					//5. 데이터 받아오기(db -> dto)
					while(rs.next()){
						dto = new MemberDTO();
						
						dto.setCompany(rs.getString("company"));
						dto.setAge(rs.getInt("age"));
						dto.setDepartment(rs.getString("department"));
						dto.setEmail(rs.getString("email"));
						dto.setId(rs.getString("id"));
						dto.setName(rs.getNString("name"));
						dto.setPass(rs.getString("pass"));
						dto.setTel(rs.getString("tel"));
						
						
					}
					
					System.out.println("DAO :회원 정보 조회 완료");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					closeDB();
				}
				
				return dto;
			}
		
		//getMemberList
			
		//login()
		public int login(MemberDTO dto){
			int result = -1;
			
			
			try {
				//1.2. 디비연결
				con = getCon();
				
				//sql작성, pstmt
				
				sql = "select pass from eb_member where id=?";
				pstmt = con.prepareStatement(sql);
				
				//???
				pstmt.setString(1, dto.getId());
				
				//sql 실행
				rs = pstmt.executeQuery();
				
				//데이터 처리
				
				if(rs.next()){
					if(dto.getPass().equals(rs.getString("pass"))){
						result = 1;
						
					}else{
						result = 0; //비밀번호 다름
					}
				}else{
						result = -1; // 아이디에 해당하는 비밀번호가 없음
					}
				
				
				System.out.println("DAO : 로그인 확인 완료 result : "+ result);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return result;
		}
			
		//login()
			
		//getBoardMember()
		public BoardDTO getBoardMember(String id){
			BoardDTO dto = null;
			try {
				
				//db연결
				con = getCon();
				
				//sql , pstmt
				sql = "select * from eb_member "
						+ "where id = ?";
				
				pstmt = con.prepareStatement(sql);
				
				//???
				pstmt.setString(1, id);
				
				//쿼리 실행
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					dto = new BoardDTO();
					
					dto.setB_Company(rs.getString("company"));
					dto.setB_Id(rs.getString("id"));
					dto.setB_Department(rs.getString("department"));
				}
				
				System.out.println("DAO : 회원정보 1개 BoardDTO에 저장완료");
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
			return dto;
		}
	
	//getBoardMember()	
			
	//updateMember(company, department, email)		
		public void updateMember(String id, String company, String department, String email){
			
			try {
				con = getCon();
				
				sql="update eb_member set "
						+ "company =?, department=?, email=? "
						+ "where id=?";
						
				pstmt=con.prepareStatement(sql);
				
				pstmt.setString(1, company);
				pstmt.setString(2, department);
				pstmt.setString(3, email);
				pstmt.setString(4, id);
				
				pstmt.executeUpdate();
				
				System.out.println("DAO : 회원정보 수정 완료 (id : "+id+")");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
			
	//updateMember(company, department, email)		
			
			
			
			
			
			
			
}
