<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
 <%
 	request.setAttribute("msg", "elTest");
 	String msg=request.getAttribute("test").toString();
 	out.print(msg);
 %>
 <form action="el_result.jsp" method="get">
       <input name="wname" value="EL 테스트용 입니다.">
       <button type="submit">전송</button>
 </form>
</body>
</html>