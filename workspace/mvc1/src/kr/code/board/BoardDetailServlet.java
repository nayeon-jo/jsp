package kr.code.board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.code.db.DBconnection;
import kr.code.vo.BoardVO;

@WebServlet("/board/detail.do")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		String id = request.getParameter("boardId");
		String type = request.getParameter("type");
	    
		int boardId = Integer.parseInt(id);		//DB 타입이 int형이니까 맞추려고 바꿈 
		
		try {
			conn = DBconnection.getConnection();		//DB 커넥션 가져오기 
			StringBuilder sb = new StringBuilder();		//sql 담을 StringBuilder 선언 
			
			if(type.equals("detail")) {
				sb.append(" UPDATE  board_info  \n");
				sb.append("set board_count = board_count +1  \n");
				sb.append("where board_id = ?  \n");
				
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setInt(1, boardId);
				
				//update / delete 는 성공여부만 알면된다.
				// return > 1 : 성공 , 0: 미실행, -1 : 실패
				int result = pstmt.executeUpdate();
				
				if(result > 0 ) { //update 쿼리가 정상동작했다면
					conn.commit(); // 실제 DB에 반영해라 
				}
				sb= new StringBuilder();		//쿼리 초기화를 위해서 빌더를 초기화
				DBconnection.closeConnection(pstmt);		//문서도 새로작성하기 위해서 기존문서를 닫는다.
			}
			sb.append("SELECT board_id,  \n");
			sb.append("       board_title,  \n");
			sb.append("       board_contents,  \n");
			sb.append("       DATE_FORMAT(create_date, '%Y-%m-%d') AS create_date  \n");
			sb.append("FROM board_info  \n");
			sb.append("WHERE board_id = ?  \n");
			
			pstmt = conn.prepareStatement(sb.toString());		//sql 이 담긴 문서객체 만들기
			pstmt.setInt(1, boardId);
			
			res = pstmt.executeQuery();		//문서를 실행해서 결과를 받아온다.
			
			if(res != null && res.next()) {		//결과가 존재할경우 
				BoardVO boardVO = new BoardVO();		// 저장할 게시판 객체를 생성 
					
				boardVO.setBoardId(res.getInt("board_id"));
				boardVO.setBoardTitle(res.getString("board_title"));
				boardVO.setBoardContents(res.getString("board_contents"));
				boardVO.setCreateDate(res.getString("create_date"));
				
				request.setAttribute("boardVO", boardVO);		//request 객체에 저장 
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBconnection.closeConnection(conn);
			DBconnection.closeConnection(pstmt, res);
		}
		String nextPage = "";
		
		if(type.equals("detail")) {
			nextPage = "/jsp/board/board_detail.jsp";
		}else if(type.equals("modify")) {
			nextPage = "/jsp/board/board_modify.jsp";
		}
		
		//페이지이동
		RequestDispatcher disp = request.getRequestDispatcher(nextPage);
		disp.forward(request, response);
	}
}