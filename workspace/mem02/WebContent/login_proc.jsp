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
	<!--Userinfo userInfo=new Userinfo(); 와 같음-->
	<jsp:useBean id="userInfo" class="kr.code.vo.Userinfo" />
	<jsp:useBean id="loginInfo" class="kr.code.vo.Userinfo" />
	
	<% request.setCharacterEncoding("UTF-8"); %> <!--파라미터의 text 인코딩을 UTF-8 로 선언-> 한글꺠짐을 방지-->
	
	<!-- bean 객체의 setter를 이용하여 파라미터 받기 -->
	<!--이전 서버로 부터 전송된 데이터를 loginInfo를 통해서 받게 된다.
      객체의 setter 를 이용한 방법 ㅡ만약 parameter 를 받을 떄 파라메터의 key 값과 setter(또는 변수이름) 이 다를 경우에는, param 이라는 속성에 대입할 데이터의 key 이름을 써주면 된다.-->
	<jsp:setProperty property="userId" name="loginInfo"/>
	<jsp:setProperty property="userPw" name="loginInfo"/>
	
	<%	/*jsp에서 자바객체를 호출하는 방법 2가지 
		1. import를 통해 선언하여 쓰기
		2. javaBean 기능을 이용하기
			ㅡjsp태그 중 하나, 객체를 불러옴과 동시에 생성(Class a=new Class()와 같다)*/
		System.out.println(userInfo);
		Connection conn=null;	PreparedStatement pstmt=null;	ResultSet res=null;
		try{
			conn=DBconnection.getConnection();		//DB 연결
			StringBuilder sb=new StringBuilder();
			sb.append("select*			\n");
			sb.append("from user_info		\n");
			sb.append("where user_id=? 		\n");
			sb.append(" and user_pw =? 		\n");
			
			pstmt=conn.prepareStatement(sb.toString());		//DB연결되어있는 객체가 가진 문서에 sql을 써서 반환하라
			
			//파라미터의 데이터타입은 매칭할 칼럼의 데이터타입과 동일하게 준다
			pstmt.setString(1,loginInfo.getUserId());	pstmt.setString(2,loginInfo.getUserPw());
			
			res=pstmt.executeQuery();		//실행결과를 받는다
			String userName="";
			
			if(res!=null&&res.next()){
				//jsp:bean을 통해 등록되었기때문에 사용가능
				userInfo.setUserId(res.getString("user_id"));
				userInfo.setUserName(res.getString("user_name"));
				userInfo.setUserGender(res.getString("user_gender"));
				userInfo.setUserAge(res.getInt("user_age"));
				userInfo.setUserHobby(res.getString("user_hobby"));
				
				RequestDispatcher disp=request.getRequestDispatcher("main.jsp");
				request.setAttribute("msg","로그인되었습니다");		
				request.setAttribute("userInfo",userInfo);		//요청객체에 정보저장 map과같이 key,value쌍으로 저장
				disp.forward(request,response);	
			}else{
				response.sendRedirect("login.jsp");		//페이지를 단순히 이동시키는 기능, 데이터보존은 x(보존하는 방법이 있긴하지만...잘 안 쓴다.)
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
