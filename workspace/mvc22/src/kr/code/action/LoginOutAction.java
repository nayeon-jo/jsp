package kr.code.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.code.common.CommonAction;

public class LoginOutAction implements CommonAction{

	@Override
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		//세션에 있는 정보를 지우면 로그아웃 
		if(session.getAttribute("userInfo") != null) {
			session.removeAttribute("userInfo");
			request.setAttribute("loginOK", "NO");
		}
		
		return CommonAction.root +"/jsp/layout/layout.jsp";
	}

}
