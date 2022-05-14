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
			public List<MemberDTO> getMemberList(){
				
				List<MemberDTO> memberList = new ArrayList<MemberDTO>();
				
				try {
					//디비연결
					con = getCon();
					
					//sql & pstmt
					sql = "select * from eb_member";
					pstmt = con.prepareStatement(sql);
					
					//sql 실행
					rs = pstmt.executeQuery();
					
					System.out.println("쿼리 실행완료");
					
					//5. 데이터 받아오기(db -> dto)
					while(rs.next()){
						MemberDTO dto = new MemberDTO();
						
						dto.setCompany(rs.getString("company"));
						dto.setAge(rs.getInt("age"));
						dto.setDepartment(rs.getString("department"));
						dto.setEmail(rs.getString("email"));
						dto.setId(rs.getString("id"));
						dto.setName(rs.getNString("name"));
						dto.setPass(rs.getString("pass"));
						dto.setTel(rs.getString("tel"));
						
						//dto에 저장한 정보 > Array에 담기 (arrayList 생성)
						memberList.add(dto);
					}
					
					System.out.println("DAO :(관리자)회원 목록 조회 성공");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					closeDB();
				}
				
				return memberList;
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
			
			
			
			
			
			
			
			
			
			
			
			
			
			
}
