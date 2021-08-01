package kr.code.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.code.db.DBconnection;

@WebServlet("/modify/board.do")
public class BoardUpdateServlet extends HttpServlet{
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
		request.setCharacterEncoding("utf-8"); //리퀘스트파라미터가 한글깨지는것을 방지
		
		String title=request.getParameter("title");
		String contents=request.getParameter("contents");
		int boardId=Integer.parseInt(request.getParameter("boardId"));
		
		Connection conn=null;	
		PreparedStatement pstmt=null;	
		int result=0;
		
		try {
			StringBuilder sb=new StringBuilder();	
			int dbIndex=1;
			
			sb.append("UPDATE board_info \n");
			sb.append("set board_title=?, \n");
			sb.append("	board_contents=? \n");
			sb.append("where board_id=? \n");
			
			conn=DBconnection.getConnection();
			pstmt=conn.prepareStatement(sb.toString());
			
			pstmt.setString(dbIndex++, title);
			pstmt.setString(dbIndex++, contents);
			pstmt.setInt(dbIndex++, boardId);
			
			result=pstmt.executeUpdate();
			
			if(result>0) { //성공했으면 실제 DB 반영
				conn.commit();
			}
		}catch(Exception e) {
			result=-1;
			e.printStackTrace();
		}finally {
			DBconnection.closeConnection(conn);
			DBconnection.closeConnection(pstmt);
		}
		String nextPage=request.getContextPath()+"/board/detail.do?type=detail&boardId="+boardId;
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();	//결과를 사용하기위한 프린터 객체
		
		out.println("<html>");
		out.println("  <body>");
		out.println("  </body>");
		out.println("  <script>");
		
		if(result > 0) {
			out.println("  alert('수정이 완료되었습니다');");
		}else {
			out.println("  alert('수정이 실패되었습니다');");
		}
		out.println("      location.href='" + nextPage + "'");
		out.println("   </script>");
		out.println("</html>");
	}
}
