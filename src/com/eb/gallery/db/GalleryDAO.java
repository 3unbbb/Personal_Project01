package com.eb.gallery.db;

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

public class GalleryDAO {
	//DB에 eb_gallery 테이블 관련된 모든 동작을 처리
	
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
	
	

	
	//insertGallery(bb) 시작
	public void insertGallery(GalleryDTO dto){
		int num = 0; //글번호 저장 변수
		try {
			
			//1. 디비연결
			con = getCon();
			
			//2. sql작성(글번호 계산) & pstmt 객체
			sql = "select max(num) from eb_gallery";
			pstmt = con.prepareStatement(sql);
			//3. sql 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()){ //max(num) 컬럼의 결과는 항상 존재함
				num = rs.getInt(1)+1; //index 0부터 시작하니까
				
			}
			
			System.out.println("DAO : 글번호" + num);
			
			//글쓰기
			//DB는 이미 연결 되어 있으니까 3.SQL 작성, pstmt 객체
			sql = "insert into eb_gallery(num,id,subject,content,"
					+"read_count,re_ref,re_lev,re_seq,date,ip,Image) "
					+"values(?,?,?,?,?,?,?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			
			pstmt.setInt(5, 0);
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);

			pstmt.setString(9, dto.getIp());
			pstmt.setString(10, dto.getImage());
			
			//4. sql 실행
			pstmt.executeUpdate();		//insert문은 executeUpdate 사용
			System.out.println("DAO : 이미지저장 완");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}
	//insertGallery(bb) 끝

	public int getGalleryCount() {
		int result = 0;
		
		try {
			//디비연결
			con = getCon();
			
			//sql
			sql = "select count(num) from eb_gallery";
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
	
	//getgalleryList();
	public ArrayList getGalleryList(){
		
		//글정보를 가져올 List 생성
		ArrayList galleryList = new ArrayList();
		
		try {
			
			//DB연결
			con = getCon();
			
			//sql & pstmt
			sql = "select * from eb_gallery order by num desc limit 0,5";
			pstmt = con.prepareStatement(sql);
			
			
			//sql 실행
			rs = pstmt.executeQuery();
			
			//데이터처리
			
			while(rs.next()){
				GalleryDTO dto = new GalleryDTO();
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setImage(rs.getString("image"));
				dto.setIp(rs.getString("ip"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setRead_count(rs.getInt("read_count"));
				dto.setSubject(rs.getString("subject"));
				
				galleryList.add(dto);
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return galleryList;
		
	}
	
	
	//getgalleryList();
	
	//getgalleryList(int startRow, int endRow);
	public ArrayList<GalleryDTO> getGalleryList(int startRow, int pageSize){
		
		//글정보를 가져올 List 생성
		ArrayList<GalleryDTO> galleryList = new ArrayList<GalleryDTO>();
		
		try {
			
			//DB연결
			con = getCon();
			
			//sql & pstmt
			sql = "select * from eb_gallery order by re_ref desc, re_seq asc limit ?,?";
			//limit ?,? >>>>> 데이터 가져올 때 시작& 끝
			pstmt = con.prepareStatement(sql);
			
			//???
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			
			//sql 실행
			rs = pstmt.executeQuery();
			
			//데이터처리
			
			while(rs.next()){
				GalleryDTO dto = new GalleryDTO();
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setImage(rs.getString("image"));
				dto.setIp(rs.getString("ip"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setRead_count(rs.getInt("read_count"));
				dto.setSubject(rs.getString("subject"));
				
				galleryList.add(dto);
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return galleryList;
		
	}
	
	
	//getgalleryList();
	
	//updateReadCount(num)
	public void updateReadCount(int num){
		
		try {
			con = getCon();
			
			sql = "update eb_gallery set read_count = read_count+1  where num = ?";
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
	
	//getGallery(num)
	public GalleryDTO getGallery(int num){
		GalleryDTO dto = null;
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			sql = "select * from eb_gallery where num = ?";
			pstmt = con.prepareStatement(sql);
			
			// ???
			pstmt.setInt(1, num);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()){
				dto = new GalleryDTO();
				
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setImage(rs.getString("Image"));
				dto.setIp(rs.getString("ip"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setRead_count(rs.getInt("read_count"));
				dto.setSubject(rs.getString("subject"));

				dto.setId(rs.getString("id"));
				

			}
			System.out.println(" DAO : 게시판 글 1개 저장완료 ");	
		
			} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	
		return dto;
	
	}
	//getGallery(num)
	
	//modifyGallery(num)
	public GalleryDTO modifyGallery(int num){
		GalleryDTO dto = null;
		try {
			//디비연결
			con=getCon();
			
			//sql & psmtm
			sql ="select * from eb_gallery where num=?";
			pstmt = con.prepareStatement(sql);
			
			//??
			pstmt.setInt(1, num);
			
			//쿼리 실행
			rs = pstmt.executeQuery();
			
			//데이터 처리
			if(rs.next()){
				dto = new GalleryDTO();
				
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setImage(rs.getString("image"));
				dto.setIp(rs.getString("ip"));
				dto.setNum(rs.getInt("num"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setRead_count(rs.getInt("read_count"));
				dto.setSubject(rs.getString("subject"));
				dto.setId(rs.getString("id"));
				
				
			}
			System.out.println("갤러리 "+num+"번 글 조회 완료");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return dto;
	}
	
	
	//modifyGallery(num)
	
	//updateGallery(num, dto)
	public void updateGallery(int num, GalleryDTO dto){
		try {
		
		//1. 디비연결
		con = getCon();
		
		sql = "update eb_gallery "
				+ "set subject =?, content=?, Image=? "
				+ "where num= ?";

		pstmt = con.prepareStatement(sql);
		

		pstmt.setString(1, dto.getSubject());
		pstmt.setString(2, dto.getContent());
		pstmt.setString(3, dto.getImage());
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
	//updateGallery(num, dto)
	
	//deleteGallery(num)
	public void deleteGallery(int num){
		
		try {
			con = getCon();
			
			sql="delete from eb_gallery where num=?";
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
	
	//deleteGallery(num)
	
	//galleryReinsert(num)
		public void galleryReinsert(GalleryDTO dto){
			
			int num=0;
			
			try {
				con = getCon();
				
				sql="select max(num) from eb_gallery";
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					num = rs.getInt(1)+1;
				
				}
				System.out.println("DAO : 답글의 번호 " + num);
				
				sql = "update eb_gallery set re_seq = re_seq + 1 "
						+ "where re_ref=? and re_seq>?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, dto.getRe_ref());
				pstmt.setInt(2, dto.getRe_seq());
				
				int check = pstmt.executeUpdate();
				
				System.out.println(check);
				
				if(check >0)
					System.out.println("DAO : 답글 순서 재배치 완");
					
					sql = "insert into eb_gallery(num,id,subject,content,"
							+"read_count,re_ref,re_lev,re_seq,date,ip,Image) "
							+"values(?,?,?,"
							+ "?,?,?,?,?,"
							+ "now(),?,?)";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, num);
					pstmt.setString(2, dto.getId());
					pstmt.setString(3, dto.getSubject());
					pstmt.setString(4, dto.getContent());
					pstmt.setInt(5, 0);
					pstmt.setInt(6, dto.getRe_ref());
					pstmt.setInt(7, dto.getRe_lev()+1);
					pstmt.setInt(8, dto.getRe_seq()+1);
					pstmt.setString(9, dto.getIp());
					pstmt.setString(10, dto.getImage());
					
					pstmt.executeUpdate();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
		}
	
	
	
	//galleryReinsert(num)
	
	
}
