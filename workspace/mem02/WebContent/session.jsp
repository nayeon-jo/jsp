<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
 <h1>세션 테스트</h1>
 <div>
  <button type="button" onclick="goPage();">페이지 이동</button>
 </div>
 <%
 	session.setAttribute("info","사용자정보");	//세션 저장
 
 	//세션 유효시간 설정 -별도 지정이 아닌 전체 적용 -초 단위
 	session.setMaxInactiveInterval(1800);	//30분 적용
 	//무한정 주고싶을때는 -1을 넣으면 된다.
 %>
</body>
	
<script>
 function goPage(){
	 location.href='session_result.jsp'; 	//페이지 이동
 }
</script>
</html>
