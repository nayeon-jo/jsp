package kr.code.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.code.common.CommonAction;
import kr.code.service.BoardService;
import kr.code.vo.BoardVO;

public class BoardListAction implements CommonAction{

	@Override
	public String excuteAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardService service = new BoardService();
		
		try {
			//게시판 리스트 가져오기
			List<BoardVO> list = service.getBoardList();
			//경과를 리퀘스으테 담기
		
			request.setAttribute("totalCount", list.size());
			request.setAttribute("boardList",list);
		
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return CommonAction.root +"/jsp/board/board_list.jsp";
	}

}
