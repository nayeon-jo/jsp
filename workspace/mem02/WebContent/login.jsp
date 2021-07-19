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
</head>

<body>
 <div class="container">
	<form action="login_proc.jsp" method="post" id="frm">
		<div class="loginform">
			<div class="id"><input type="text" id="userId" name="userId" placeholder="ID"></div>
			<div class="pass"><input type="password" id="userPw" name="userPw" placeholder="PwD"></div>
			<div class="btn"><button type="button" id="login">로그인</button></div>
		</div>
	</form>
 </div>
</body>

<script>
  	function submEvent(){	//로그인이 실제로 발생하는 옵션
  		var userId=document.querySelector('#userId');		
  		var userPw=document.querySelector('#userPw');	
  		
  		if(userId.value.length==0){
  			alert('아이디를 입력:')
  			userId.focus();
  			return false;		//여기서 코드 끝
  		}
  		if(userPw.value.length==0){		//id와 pwd 검사는 연속성이 없기 때문에 if문을 각각 쓴다
  			alert('패스워드를 입력:')
  			userPw.focus();
  			return false;		//여기서 코드 끝
  		}
  		var frm=document.querySelector('#frm');	 //form객체 가져오기
  		frm.submit();		//문서 전송
  	}
	function enterEvent(event){		//이벤트가 발생하면 함수 호출시 event 객체를 파라미터로 준다
		var keyCode=event.keyCode;
		if(ketCode==13){
			submEvent();
		}
	}
	(function(){		//html이 로드된후 자동실행 
		var loginBtn=document.querySelector('#login');		//버튼이벤트를 부여
		loginBtn.addEventListener('click',submEvent);		//버튼객체에 이벤트를 부여
		var passwd=document.querySelector('#userPw');
		passwd.addEventListener('keydown',enterEvent);
	})();
</script>
</html>