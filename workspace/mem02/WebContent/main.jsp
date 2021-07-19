<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<%@page import="kr.code.vo.Userinfo" %>
</head>
<body>
	<jsp:useBean id="userInfo" class="kr.code.vo.Userinfo" />
	<%
 	String msg=(String)request.getAttribute("msg");
	Userinfo user= (Userinfo)request.getAttribute("userInfo");
	%>
	
<span><%=msg %></span><br>

<div>
 <div>
	<table style="border:solid 1px;">
	 <tr>
	  <th>
	   아이디
	  </th>
	  <td>
	   <%=user.getUserId() %>
	  </td>
	 </tr>	
	 <tr>
	  <th>
	   이름
	  </th>
	  <td>
	   <%=user.getUserName() %>
	  </td>
	 </tr>			
	 <tr>
	  <th>
	   나이
	  </th>
	  <td>
	   <%=user.getUserAge() %>
	  </td>
	 </tr>		
	</table>
 </div>
</div>
</body>
</html>