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

@WebServlet("/board/write.do")
public class BoardCreateServlet extends HttpServlet{
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
		request.setCharacterEncoding("utf-8"); 		//리퀘스트 파라미터가 한글깨지는것을 방지
		
		String title=request.getParameter("title");
		String contents=request.getParameter("contents");
		
		Connection conn=null;	
		PreparedStatement pstmt=null;	
		int result=0;
		
		StringBuilder sb=new StringBuilder();
		
		try {
			conn=DBconnection.getConnection();	//커넥션 연결
			
			sb.append("INSERT INTO board_info(board_title,board_author,board_contents) \n");
			sb.append("		VALUES(?,?,?)	\n");	
			
			int dbIndex=1;		
			
			pstmt=conn.prepareStatement(sb.toString());		//문서에 쿼리를 작성하여 반환
			
			//물음표 내용(?,?,?)을 채우자
			pstmt.setString(dbIndex++, title);
			pstmt.setString(dbIndex++, "admin");
			pstmt.setString(dbIndex++, contents);
			
			result=pstmt.executeUpdate();	
			
			if(result>0) {	//결과가 참이라면 커밋
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
			out.println("  alert('수정이 완료되었습니다');");
		}else {
			out.println("  alert('수정이 실패되었습니다');");
		}
		
		out.println("      location.href='" + nextPage + "'");
		out.println("   </script>");
		out.println("</html>");
	}
}
