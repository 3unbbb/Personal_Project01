package com.eb.board.db;

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
	
	

	
	//insertBoard(bb) 시작
	public void insertBoard(BoardDTO dto){
		int num = 0; //글번호 저장 변수
		try {
			
			//1. 디비연결
			con = getCon();
			
			//2. sql작성(글번호 계산) & pstmt 객체
			sql = "select max(num) from eb_board";
			pstmt = con.prepareStatement(sql);
			//3. sql 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()){ //max(num) 컬럼의 결과는 항상 존재함
				num = rs.getInt(1)+1; //index 0부터 시작하니까
				
			}
			
			System.out.println("DAO : 글번호" + num);
			
			//글쓰기
			//DB는 이미 연결 되어 있으니까 3.SQL 작성, pstmt 객체
			sql = "insert into eb_board(num,b_id,b_company,b_department,subject,content,"
					+"read_count,re_ref,re_lev,re_seq,date,ip,file) "
					+"values(?,?,?,?,?,?,?,?,?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getB_Id());
			pstmt.setString(3, dto.getB_Company());
			pstmt.setString(4, dto.getB_Department());
			pstmt.setString(5, dto.getSubject());
			pstmt.setString(6, dto.getContent());
			
			pstmt.setInt(7, 0);
			pstmt.setInt(8, num);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);

			pstmt.setString(11, dto.getIp());
			pstmt.setString(12, dto.getFile());
			
			//4. sql 실행
			pstmt.executeUpdate();		//insert문은 executeUpdate 사용
			System.out.println("DAO : 글 DB저장 완");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}
	//insertBoard(bb) 끝

	public int getBoardCount() {
		int result = 0;
		
		try {
			//디비연결
			con = getCon();
			
			//sql
			sql = "select count(num) from eb_board";
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
	public ArrayList getBoardList(){
		
		//글정보를 가져올 List 생성
		ArrayList boardList = new ArrayList();
		
		try {
			
			//DB연결
			con = getCon();
			
			//sql & pstmt
			sql = "select * from eb_board order by num desc limit 0,5";
			pstmt = con.prepareStatement(sql);
			
			
			//sql 실행
			rs = pstmt.executeQuery();
			
			//데이터처리
			
			while(rs.next()){
				BoardDTO dto = new BoardDTO();
				dto.setB_Company(rs.getString("b_company"));
				dto.setB_Department(rs.getString("b_department"));
				dto.setB_Id(rs.getString("b_id"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("file"));
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
	public ArrayList getBoardList(int startRow, int pageSize){
		
		//글정보를 가져올 List 생성
		ArrayList boardList = new ArrayList();
		
		try {
			
			//DB연결
			con = getCon();
			
			//sql & pstmt
			sql = "select * from eb_board order by re_ref desc, re_seq asc limit ?,?";
			//limit ?,? >>>>> 데이터 가져올 때 시작& 끝
			pstmt = con.prepareStatement(sql);
			
			//???
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			
			//sql 실행
			rs = pstmt.executeQuery();
			
			//데이터처리
			
			while(rs.next()){
				BoardDTO dto = new BoardDTO();
				dto.setB_Company(rs.getString("b_company"));
				dto.setB_Department(rs.getString("b_department"));
				dto.setB_Id(rs.getString("b_id"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("file"));
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
			
			sql = "update eb_board set read_count = read_count+1  where num = ?";
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
	
	//getBoard(num)
	public BoardDTO getBoard(int num){
		BoardDTO dto = null;
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			sql = "select * from eb_board where num = ?";
			pstmt = con.prepareStatement(sql);
			
			// ???
			pstmt.setInt(1, num);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()){
				dto = new BoardDTO();
				
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("file"));
				dto.setIp(rs.getString("ip"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setRead_count(rs.getInt("read_count"));
				dto.setSubject(rs.getString("subject"));
				dto.setB_Company(rs.getString("b_company"));
				dto.setB_Department(rs.getString("b_department"));
				dto.setB_Id(rs.getString("b_id"));
				
			}
			System.out.println(" DAO : 게시판 글 1개 저장완료 ");	
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	
		return dto;
	
	}
	//getBoard(num)
	
	//modifyBoard(num)
	public BoardDTO modifyBoard(int num){
		BoardDTO dto = null;
		try {
			//디비연결
			con=getCon();
			
			//sql & psmtm
			sql ="select * from eb_board where num=?";
			pstmt = con.prepareStatement(sql);
			
			//??
			pstmt.setInt(1, num);
			
			//쿼리 실행
			rs = pstmt.executeQuery();
			
			//데이터 처리
			if(rs.next()){
				dto = new BoardDTO();
				
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("file"));
				dto.setIp(rs.getString("ip"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setRead_count(rs.getInt("read_count"));
				dto.setSubject(rs.getString("subject"));
				dto.setB_Company(rs.getString("b_company"));
				dto.setB_Department(rs.getString("b_department"));
				dto.setB_Id(rs.getString("b_id"));
				
				
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
	
	
	//modifyBoard(num)
	
	//updateBoard(num, dto)
	public void updateBoard(int num, BoardDTO dto){
		try {
		
		//1. 디비연결
		con = getCon();
		
		sql = "update eb_board "
				+ "set subject =?, content=?, file=? "
				+ "where num= ?";

		pstmt = con.prepareStatement(sql);
		

		pstmt.setString(1, dto.getSubject());
		pstmt.setString(2, dto.getContent());
		pstmt.setString(3, dto.getFile());
		pstmt.setInt(4, num);
		
		//4. sql 실행
		pstmt.executeUpdate();		//insert문은 executeUpdate 사용
		System.out.println("DAO : 글 DB 수정본 저장 완");
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	
	}	
	//updateBoard(num, dto)
	
	//deleteBoard(num)
	public void deleteBoard(int num){
		
		try {
			con = getCon();
			
			sql="delete from eb_board where num=?";
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
	
	//deleteBoard(num)
	
	//boardReinsert(num)
		public BoardDTO boardReinsert(int num){
			BoardDTO dto = new BoardDTO();
			
			try {
				con = getCon();
				
				sql = "insert into eb_board(num,b_id,b_company,b_department,subject,content,"
						+"read_count,re_ref,re_lev,re_seq,date,ip,file) "
						+"values(?,?,?,?,?,?,?,?,?,?,now(),?,?)";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, num);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
			
			
			
			return dto;
		}
	
	
	
	//boardReinsert(num)
	
	
}
