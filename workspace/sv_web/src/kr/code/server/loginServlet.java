package kr.code.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.code.db.DBconnection;
import kr.code.vo.Userinfo;


@WebServlet("/login.do")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	//내부에서만 쓰는 로직이라 private 선언
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String userPw=request.getParameter("userPw");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		
		Userinfo user=null;
		
		try {
			conn=DBconnection.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append("select*			\n");
			sb.append("from user_info		\n");
			sb.append("where user_id=? 		\n");
			sb.append(" and user_pw =? 		\n");
			
			pstmt=conn.prepareStatement(sb.toString());	//sql을 담은 문서객체를 반환해준다
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			
			res=pstmt.executeQuery();	//문서를 실행하여 결과를 받는다 -select만 가능			
			
			if(res!=null&&res.next()) {
				user=new Userinfo();
				user.setUserId(res.getString("user_id"));
				user.setUserName(res.getString("user_name"));
				user.setUserGender(res.getString("user_gender"));
				user.setUserAge(res.getInt("user_age"));
				user.setUserHobby(res.getString("user_hobby"));
			}		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconnection.closeConnection(pstmt,res);
		}
		
		
		//예외처리(trycatch문)밖으로 빼서 처리한다. DB가 에러난 경우는 해당 로직을 타지않을 수 있기 때문에.
		if(user==null) {	//로그인이 안된 것
			response.sendRedirect("login_fail.jsp");
		}else {
			HttpSession ss=request.getSession();	//로그인 되었으니 사용자 정보를 세션에 저장
			ss.setAttribute("userInfo", user);
			
			RequestDispatcher disp=request.getRequestDispatcher("main.jsp");
			disp.forward(request, response);
		}
	}

}
