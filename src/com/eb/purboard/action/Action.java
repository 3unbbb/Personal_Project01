package com.eb.purboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	//호출할 때 request, response 정보를 필요로 하고,
	//처리동작 후 ActionForward(페이지 이동객체) 리턴
	
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

}
