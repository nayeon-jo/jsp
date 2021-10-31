package kr.code.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.code.common.CommonAction;
import kr.code.vo.UserInfo;

public class LoginFormAction implements CommonAction{

	@Override
	public String excuteAction(HttpServletRequest request, 
			                   HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userInfo") == null) {
			request.setAttribute("loginOk", "No");
			request.setAttribute("userName", "");
		}else {
			UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
			request.setAttribute("loginOk", "OK");
			request.setAttribute("userName", userInfo.getUserName());
		}
		
		// TODO Auto-generated method stub
		return  CommonAction.root + "/jsp/login/loginForm.jsp";
	}

}
