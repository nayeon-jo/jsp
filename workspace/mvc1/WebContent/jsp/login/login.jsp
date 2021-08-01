<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<style>
	.container{
	position:absolute;
	left:35%;
	top:50%;
	}
	.loginform{
	width:300px;
	}
	input{
	width:100%;
	height:20px;
	}
	.btn{
	margin-top:20px;
	}
	button{
	width:100%;
	height:50px;
	font-size:14px;
	border-radius:10px;
	color:#fff;
	background-color:#000;
	}
</style>
<%
	String path=request.getContextPath();
%>
</head>

<body>
 <div class="container">
	<form action="<%=path %>/login.do" method="post" id="frm">
		<div class="loginform">
			<div class="id"><input type="text" id="userId" name="userId" placeholder="ID"></div>
			<div class="pass"><input type="password" id="userPw" name="userPw" placeholder="PwD"></div>
			<div class="btn"><button type="button" id="login">로그인</button></div>
		</div>
	</form>
 </div>
</body>

<script>
	//2. 이벤트 정의 함수
	function evtInit(){//로그인버튼 이벤트 부여	
		//로그인 버튼 객체 가져오기
		loginBtn=document.querySelector('#login');
		
		//로그인버튼 이벤트 부여
		loginBtn.addEventListener('click',submitEvent);
		
		//엔터키 로그인 이벤트 부여
		var passwd=document.querySelector('#userPw');
		//keydown 이벤트 부여 -키가 눌리면 발생
		passwd.addEventListener('keydown', enterEvent);
	}
	
	//로그인 실행 함수
	function submitEvent(){
		var userId=document.querySelector('#userId');
		var userPw=document.querySelector('#userPw');
		
		//유효성 체크
		if(userId.value.length==0){
			alert('아이디를 입력');
			userId.focus();
			return false;
		}
		if(userPw.value.length==0){
			alert('패스워드를 입력');
			userPw.focus();
			return false;
		}
		var frm=document.querySelector('#frm');		//form 객체 가져오기
		frm.submit();		//문서 전송
	}
	
	function enterEvent(e){	//e는 이벤트 객체를 반환
		if(e.keyCode==13){
			submitEvent();
		}
	}

  	//1. html 로드 후 자동실행되도록
  	(
  		function(){
  			//이벤트 발생을 위한 펑션 호출
  			evtInit();
  		}
  	)();	
</script>
</html>