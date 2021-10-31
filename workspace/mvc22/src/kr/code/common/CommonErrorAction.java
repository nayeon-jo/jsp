package kr.code.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 사용자가 요청한 주소를 못찾을 경우
 * 실행할 액션 
 * @author ITSC
 *
 */
public class CommonErrorAction implements CommonAction{

	@Override
	public String excuteAction(HttpServletRequest request,
			             HttpServletResponse response) throws Exception {
		return  CommonAction.root + "/jsp/error/err404.jsp";
	}

}
