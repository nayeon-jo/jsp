<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%
	String str="쿠키정보입니다";
	String userName="쿠키";
	
	Cookie cookie1=new Cookie("userName",userName);
	cookie1.setComment("사용자이름");
	
	//쿠키 유효시간 기본단위는 초
	//쿠키삭제기능은 따로 없고, 유효기간을 0으로 저장하면 삭제와 같다
	cookie1.setMaxAge(60*60*24); 	//하루
	
	//쿠키의 value는 무조건 String
	Cookie cookie2=new Cookie("userAge","30");
	cookie1.setComment("사용자이름");
	cookie1.setMaxAge(60*60*24);
	
	response.addCookie(cookie1);
	response.addCookie(cookie2);
%>
<%
	//쿠키는 배열타임으로 저장되어있음
	Cookie[] cookies=request.getCookies();

	if (cookies==null){
		out.print("<html><body></body><script>alert('쿠키없음'); </script></html>");
	}
	
	for (int i=0; i<cookies.length; i++){
		if(cookies[i].getName().equals("userAge")){
			cookies[i].setMaxAge(0);	//쿠키 삭제 -시간을 0으로 하면 삭제와 같다
			//쿠키 업데이트
			response.addCookie(cookies[i]);	//다시 입력
			break;
		}
	}
%>
</body>
</html>