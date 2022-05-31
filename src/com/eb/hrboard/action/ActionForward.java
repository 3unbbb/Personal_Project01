package com.eb.hrboard.action;

public class ActionForward {
	//페이지 이동할 때 사용하는 객체
	
	private String path;		//이동 경로 저장 객체
	private boolean isRedirect; //이동 방식 저장 객체
	//true = 주소변환 o, 화면변환 o
	//false = 주소변환 x, 화면변환 o
	
	
	//alt shitf s +r
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	//alt shift s + s
	
	@Override
	public String toString() {
		return "ActionForward [path=" + path + ", isRedirect=" + isRedirect + "]";
	}

}
