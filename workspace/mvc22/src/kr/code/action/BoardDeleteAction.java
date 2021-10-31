package kr.code.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.code.common.CommonAction;
import kr.code.service.BoardService;

public class BoardDeleteAction implements CommonAction{

	@Override
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardService  boardService  = new BoardService();
		
		int boardId  = Integer.parseInt(request.getParameter("boardId"));
		
		int resultValue = boardService.deleteBoard(boardId);
		request.setAttribute("resultCode", resultValue);
		
		return CommonAction.root + "/jsp/board/delete_proc.jsp";
	}

	

}
