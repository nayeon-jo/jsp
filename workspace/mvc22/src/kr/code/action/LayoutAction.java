package kr.code.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.code.common.CommonAction;

public class LayoutAction implements CommonAction{

	@Override
	public String excuteAction(HttpServletRequest request, 
			                        HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return CommonAction.root +"/jsp/layout/layout.jsp";
	}

}
