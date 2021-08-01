<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>홈페이지</title>
 <%@page import="kr.code.vo.Userinfo" %>
</head>
<body>
<% 
	Userinfo user=null;
	String root=request.getContextPath();

	//로그인 상태가 아니라면 userInfo 정보가 없을수 있으니 널체크를 꼭 해야한다
	if(session.getAttribute("userinfo")!=null){	
		user=(Userinfo)session.getAttribute("userinfo");
	}
	
	//로그인 상태가 아님
	if(user==null){
		response.sendRedirect(root+"/loginForm.do");
	}else{
		RequestDispatcher disp=request.getRequestDispatcher("/board.do");
		disp.forward(request,response);		//로그인 상태라면 게시판으로 이동
	}
%>
	
</body>
</html>