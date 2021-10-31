package kr.code.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.code.common.CommonAction;

public class BoardCreateAction  implements CommonAction {

	@Override
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return CommonAction.root +"/jsp/board/board_write.jsp";
	}

}
