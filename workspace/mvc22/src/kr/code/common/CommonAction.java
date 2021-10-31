package kr.code.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//모든 실행모듈이 상속할 인터페이스 
public interface CommonAction {
	public static final String root = "/WEB-INF";
	//실행 메서드 
	public String excuteAction(HttpServletRequest request,
			                   HttpServletResponse response) throws Exception;

}
