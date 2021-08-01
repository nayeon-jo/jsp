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

@WebServlet("/board/del.do")
public class BoardDeleteServlet extends HttpServlet{
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
		request.setCharacterEncoding("utf-8"); 	//리퀘스트파라미터가 한글깨지는것을 방지
		int boardId=Integer.parseInt(request.getParameter("boardId"));
		
		Connection conn=null;	
		PreparedStatement pstmt=null;	
		int result=0;
		
		try {
			conn=DBconnection.getConnection(); //DB커넥션 맺기
			StringBuilder sb=new StringBuilder();	
			sb.append("delete from board_info where board_id=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, boardId);
			
			result=pstmt.executeUpdate(); 	//쿼리 실행
			
			if(result>0) {
				conn.commit();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconnection.closeConnection(conn);
			DBconnection.closeConnection(pstmt);
		}
		String nextPage=request.getContextPath()+"/board.do"; 	//새로 만들었으면 리스트페이지로 이동
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();	//결과를 사용하기위한 프린터 객체
		out.println("<html>");
		out.println("  <body>");
		out.println("  </body>");
		out.println("  <script>");
		
		if(result > 0) {
			out.println("  alert('삭제가 완료되었습니다');");
		}else {
			out.println("  alert('삭제가 실패되었습니다');");
		}
		
		out.println("      location.href='" + nextPage + "'");
		out.println("   </script>");
		out.println("</html>");
	}
}
