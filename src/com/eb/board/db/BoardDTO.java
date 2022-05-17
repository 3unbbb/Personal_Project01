package com.eb.board.db;

import java.sql.Date;

public class BoardDTO {
	//eb_board 테이블의정보를 저장/ 전송하는 객체
	
	private int num;
	private String b_id;
	private String subject;
	private String content;
	private String b_company;
	private String b_department;
	
	private int read_conunt;
	private int re_ref;
	private int re_lev;
	private int re_seq;
	
	private Date date;
	private String ip;
	private String file;
	
	public String getB_Company() {
		return b_company;
	}
	public void setB_Company(String company) {
		this.b_company = company;
	}
	public String getB_Department() {
		return b_department;
	}
	public void setB_Department(String department) {
		this.b_department = department;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getB_Id() {
		return b_id;
	}
	public void setB_Id(String id) {
		this.b_id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRead_conunt() {
		return read_conunt;
	}
	public void setRead_conunt(int read_conunt) {
		this.read_conunt = read_conunt;
	}
	public int getRe_ref() {
		return re_ref;
	}
	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}
	public int getRe_lev() {
		return re_lev;
	}
	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}
	public int getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	
	

}
