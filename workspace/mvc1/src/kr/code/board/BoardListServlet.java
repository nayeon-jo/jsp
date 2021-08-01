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

@WebServlet("/board.do")
public class BoardListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn=null;	
		PreparedStatement pstmt=null;	
		ResultSet res=null;	
		
		List<BoardVO> boardList=new ArrayList<>();		//게시판 리스트를 담을 것을 생성
		
		try {
			conn=DBconnection.getConnection();			//DB 커넥션 가져오기 
			StringBuilder sb=new StringBuilder();		//sql담을 StringBuilder 선언
			
			sb.append("SELECT board_id, \n");
			sb.append("	board_title, \n");
			sb.append("	board_contents, \n");
			sb.append("	board_author,\n");
			sb.append("	board_count,\n");
			sb.append("	DATE_FORMAT(create_date,'%Y-%m-%d') AS create_date \n");
			sb.append("FROM board_info  \n");
			sb.append("ORDER BY board_id DESC  \n");
			
			pstmt=conn.prepareStatement(sb.toString());		//sql이 담긴 문서객체 만들기
			res=pstmt.executeQuery();		//문서를 실행해서 결과를 받아온다
			
			if(res!=null) {	//결과가 존재할 경우
				while(res.next()) {	//읽어들일 데이터가 있는 경우 루프가 돈다
					BoardVO boardVO=new BoardVO();	//저장할 게시판 객체를 생성
					
					boardVO.setBoardId(res.getInt("board_id"));
					boardVO.setBoardTitle(res.getString("board_title"));
					boardVO.setBoardContents(res.getString("board_contents"));
					boardVO.setBoardAuthor(res.getString("board_author"));
					boardVO.setBoardCount(res.getInt("board_count"));
					boardVO.setCreateDate(res.getString("create_date"));
					
					boardList.add(boardVO);	//리스트에 삽입
				}
				//request 객체에 저장
				request.setAttribute("boardList", boardList);
				request.setAttribute("totalCount", boardList.size());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconnection.closeConnection(conn);
			DBconnection.closeConnection(pstmt,res);
		}
		//페이지 이동
		RequestDispatcher disp=request.getRequestDispatcher("/jsp/board/board_list.jsp");
		disp.forward(request, response);
	}
}
