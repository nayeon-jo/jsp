<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
 <%
 //세션데이터를 반환하는 타입은 object 그래서 데이터를 꺼낼때 타입에 맞게 캐스팅해줘야한다
	String info="";
 	if(session.getAttribute("info")==null){
 		//out.print("<html><body></body><script>alert('미로그인 상태')</script></html>");
 		response.sendRedirect("login.jsp");
 	}else{
 		info=session.getAttribute("info").toString();
 	}
 %>
 <h1>환영합니다 <%=info %>님</h1>
</body>
</html>