<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%
	String userId=request.getParameter("userId");
	String passwd=request.getParameter("userPw");

	out.print("아이디:"+userId+"   ");
	out.print("패스워드:"+passwd);
	
%>
<br>
<input type="text" name="userId" value=<%=userId%>>
</body>
</html>