<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<%@page import="kr.code.db.DBconnection" %>
<%@page import="java.sql.Connection" %>

<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.PreparedStatement" %>
</head>
<body>
	<%
	//getParameter에서 쓰는 key값은 이전페이지에서 input태그의 name값으로 지정된다
		String userId=request.getParameter("userId");
		String userPw=request.getParameter("userPw");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		
		try{
			conn=DBconnection.getConnection();
		
			StringBuilder sb=new StringBuilder();
		
			sb.append("select*			\n");
			sb.append("from user_info		\n");
			sb.append("where user_id=? 		\n");
			sb.append(" and user_pw =? 		\n");
			
			pstmt=conn.prepareStatement(sb.toString());			//DB연결되어있는 객체가 가진 문서에 sql을 써서 변환하라
			
			//파라미터의 데이터타입은 매칭할 칼럼의 데이터타입과 동일하게
			pstmt.setString(1,userId);		//첫번째 파라미터
			pstmt.setString(2,userPw);		//두번째 파라미터
			
			//실행결과
			res=pstmt.executeQuery();
			String userName="";
			
			if(res!=null&&res.next()){
				RequestDispatcher disp=request.getRequestDispatcher("main.jsp");
				request.setAttribute("msg","로그인되었습니다");		//요청객체에 정보저장 map과같이 key,value쌍으로 저장
				disp.forward(request,response);
			}else{
				//페이지를 단순히 이동시키는 기능, 데이터보존은 x()
				response.sendRedirect("login.jsp");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBconnection.closeConnection(pstmt,res);
			DBconnection.closeConnection(conn);
		}
	%>
</body>
</html>