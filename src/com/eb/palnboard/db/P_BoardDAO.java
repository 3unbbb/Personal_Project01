package com.eb.palnboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.eb.member.db.MemberDTO;
import com.mysql.cj.Session;

public class P_BoardDAO {
	//DB에 eb_plan_board 테이블 관련된 모든 동작을 처리
	
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
	
	

	
	//insertP_Board(bb) 시작
	public void insertP_Board(P_BoardDTO dto){
		int num = 0; //글번호 저장 변수
		try {
			
			//1. 디비연결
			con = getCon();
			
			//2. sql작성(글번호 계산) & pstmt 객체
			sql = "select max(num) from eb_plan_board";
			pstmt = con.prepareStatement(sql);
			//3. sql 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()){ //max(num) 컬럼의 결과는 항상 존재함
				num = rs.getInt(1)+1; //index 0부터 시작하니까
				
			}
			
			System.out.println("DAO : 글번호" + num);
			
			//글쓰기
			//DB는 이미 연결 되어 있으니까 3.SQL 작성, pstmt 객체
			sql = "insert into eb_plan_board(num,id,department,subject,content,"
					+"read_count,re_ref,re_lev,re_seq,date,ip) "
					+"values(?,?,?,?,?,?,?,?,?,now(),?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getDepartment());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());
			
			pstmt.setInt(6, 0);
			pstmt.setInt(7, num);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);

			pstmt.setString(10, dto.getIp());

			
			//4. sql 실행
			pstmt.executeUpdate();		//insert문은 executeUpdate 사용
			System.out.println("DAO : 글 DB저장 완");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}
	//insertP_Board(bb) 끝

	public int getP_BoardCount() {
		int result = 0;
		
		try {
			//디비연결
			con = getCon();
			
			//sql
			sql = "select count(num) from eb_plan_board";
			pstmt = con.prepareStatement(sql);
			
			//sql 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				result = rs.getInt(1);	//첫 번째 결과값을 result에 넣음 = count한 값을 넣음
				
			}
			
			System.out.println("DAO : 게시판 글 개수 " + result +"개");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
				
		return result;
	}
	
	//getboardList();
	public ArrayList getP_BoardList(){
		
		//글정보를 가져올 List 생성
		ArrayList boardList = new ArrayList();
		
		try {
			
			//DB연결
			con = getCon();
			
			//sql & pstmt
			sql = "select * from eb_plan_board order by num desc limit 0,5";
			pstmt = con.prepareStatement(sql);
			
			
			//sql 실행
			rs = pstmt.executeQuery();
			
			//데이터처리
			
			while(rs.next()){
				P_BoardDTO dto = new P_BoardDTO();
				dto.setDepartment(rs.getString("department"));
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setIp(rs.getString("ip"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setRead_count(rs.getInt("read_count"));
				dto.setSubject(rs.getString("subject"));
				
				boardList.add(dto);
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return boardList;
		
	}
	
	
	//getboardList();
	
	//getboardList(int startRow, int endRow);
	public ArrayList getP_BoardList(int startRow, int pageSize){
		
		//글정보를 가져올 List 생성
		ArrayList boardList = new ArrayList();
		
		try {
			
			//DB연결
			con = getCon();
			
			//sql & pstmt
			sql = "select * from eb_plan_board order by re_ref desc, re_seq asc limit ?,?";
			//limit ?,? >>>>> 데이터 가져올 때 시작& 끝
			pstmt = con.prepareStatement(sql);
			
			//???
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			
			//sql 실행
			rs = pstmt.executeQuery();
			
			//데이터처리
			
			while(rs.next()){
				P_BoardDTO dto = new P_BoardDTO();
				dto.setDepartment(rs.getString("department"));
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setIp(rs.getString("ip"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setRead_count(rs.getInt("read_count"));
				dto.setSubject(rs.getString("subject"));
				
				boardList.add(dto);
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return boardList;
		
	}
	
	
	//getboardList();
	
	//updateReadCount(num)
	public void updateReadCount(int num){
		
		try {
			con = getCon();
			
			sql = "update eb_plan_board set read_count = read_count+1  where num = ?";
			pstmt = con.prepareStatement(sql);
			
			//??
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			System.out.println("DAO : " + num + "번글 조회수 1 증가");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
	}
	
	//updateReadCount(num)
	
	//getP_Board(num)
	public P_BoardDTO getP_Board(int num){
		P_BoardDTO dto = null;
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			sql = "select * from eb_plan_board where num = ?";
			pstmt = con.prepareStatement(sql);
			
			// ???
			pstmt.setInt(1, num);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()){
				dto = new P_BoardDTO();
				
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setIp(rs.getString("ip"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setRead_count(rs.getInt("read_count"));
				dto.setSubject(rs.getString("subject"));
				dto.setDepartment(rs.getString("department"));
				dto.setId(rs.getString("id"));
				

				
			}
			System.out.println(" DAO : 게시판 글 1개 저장완료 ");	
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	
		return dto;
	
	}
	//getP_Board(num)
	
	//modifyP_Board(num)
	public P_BoardDTO modifyP_Board(int num){
		P_BoardDTO dto = null;
		try {
			//디비연결
			con=getCon();
			
			//sql & psmtm
			sql ="select * from eb_plan_board where num=?";
			pstmt = con.prepareStatement(sql);
			
			//??
			pstmt.setInt(1, num);
			
			//쿼리 실행
			rs = pstmt.executeQuery();
			
			//데이터 처리
			if(rs.next()){
				dto = new P_BoardDTO();
				
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setIp(rs.getString("ip"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setRead_count(rs.getInt("read_count"));
				dto.setSubject(rs.getString("subject"));
				dto.setDepartment(rs.getString("department"));
				dto.setId(rs.getString("id"));
				
				
			}
			System.out.println("게시판 "+num+"번 글 조회 완료");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return dto;
	}
	
	
	//modifyP_Board(num)
	
	//updateP_Board(num, dto)
	public void updateP_Board(int num, P_BoardDTO dto){
		try {
		
		//1. 디비연결
		con = getCon();
		
		sql = "update eb_plan_board "
				+ "set subject =?, content=? "
				+ "where num= ?";

		pstmt = con.prepareStatement(sql);
		

		pstmt.setString(1, dto.getSubject());
		pstmt.setString(2, dto.getContent());
		pstmt.setInt(3, num);
		
		//4. sql 실행
		pstmt.executeUpdate();		//insert문은 executeUpdate 사용
		System.out.println("DAO : 글 DB 수정본 저장 완");
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	
	}	
	//updateP_Board(num, dto)
	
	//deleteP_Board(num)
	public void deleteP_Board(int num){
		
		try {
			con = getCon();
			
			sql="delete from eb_plan_board where num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : "+num+"번 글 삭제 완료");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		
	}
	
	//deleteP_Board(num)
	
	//boardReinsert(num)
		public void boardReinsert(P_BoardDTO dto){
			
			int num=0;
			
			try {
				con = getCon();
				
				sql="select max(num) from eb_plan_board";
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					num = rs.getInt(1)+1;
				
				}
				System.out.println("DAO : 답글의 번호 " + num);
				
				sql = "update eb_plan_board set re_seq = re_seq + 1 "
						+ "where re_ref=? and re_seq>?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, dto.getRe_ref());
				pstmt.setInt(2, dto.getRe_seq());
				
				int check = pstmt.executeUpdate();
				
				System.out.println(check);
				
				if(check >0)
					System.out.println("DAO : 답글 순서 재배치 완");
					
					sql = "insert into eb_plan_board(num,id,department,subject,content,"
							+"read_count,re_ref,re_lev,re_seq,date,ip) "
							+"values(?,?,?,?,"
							+ "?,?,?,?,"
							+ "now(),?,?)";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, num);
					pstmt.setString(2, dto.getId());
					pstmt.setString(3, dto.getDepartment());
					pstmt.setString(4, dto.getSubject());
					pstmt.setString(5, dto.getContent());
					pstmt.setInt(6, 0);
					pstmt.setInt(7, dto.getRe_ref());
					pstmt.setInt(8, dto.getRe_lev()+1);
					pstmt.setInt(9, dto.getRe_seq()+1);
					pstmt.setString(10, dto.getIp());
					
					pstmt.executeUpdate();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
		}
	
	
	
	//boardReinsert(num)
	
	
}
