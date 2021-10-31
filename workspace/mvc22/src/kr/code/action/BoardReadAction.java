package kr.code.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.code.common.CommonAction;
import kr.code.service.BoardService;
import kr.code.vo.BoardVO;

public class BoardReadAction implements CommonAction {

	@Override
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//db로부터 게시판리스트 정보를 가져올 서비스 단....
		BoardService service = new BoardService();
	
		try{
			
			int boardId  = Integer.parseInt(request.getParameter("boardId"));
				
			// 게시판 리스트 가져오기
			BoardVO  readVO  = service.getReadBoard(boardId);
			
			// 객체저장
			request.setAttribute("vo", readVO);

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return CommonAction.root +"/jsp/board/board_read.jsp";
	}


}
