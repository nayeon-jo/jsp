<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<!-- action은 전송될 jsp 경로를 기입, method는 전송방식 (get/post), id는 자바스크립트에서 form을 호출하기 위해 지정 -->
 <form action="result.jsp" method="post" id="frm">
	<div>
	<div align="center" style="width:300px; height:50px">
		아이디:<input type="text" id="userId" name="userId" ><br/>
		패스워드:<input type="password" id="userPw" name="userPw" >
	</div>
	<div align="right" style="width:300px;">
		<button type="button" onclick="javascript:btnEvt();">로그인</button>
	</div>
	</div>
 </form>
</body>

<!-- 자바스크립트는 스크립트 태그 영역에서 코드를 써야한다 -->
<script> 					
//jsp문서는 컴파일이 순차적으로 되기 때문에 스크립트도 순차적으로 읽게된다
//자바스크립트는 함수 단위 언어이기 때문에 코드가 함수 안에 있을 경우에는 그 함수를 호출해야 실행하지만, 코드를 그냥 쓸 경우에는 자동적으로 읽히고 실행된다.
	function btnEvt(){
	//아이디와 패스워드 입력확인
	var userId=document.getElementById('userId');		//html태그의 id값으로 찾아 가져온다
	var passwd=document.querySelector('#userPw');		//쿼리셀렉터는 다양하게 엘리먼트(= html 태그)를 찾아올 수 있기 때문에 어떤값으로 찾아오는건지 명시해야함, #이 붙는 경우는 id값으로 가져오겠다는 뜻
	
	if(userId.value.length==0){
		alert('아이디 입력:');
		userId.focus();
		return False;		// 여기서 코드가 끝난다.
		//자바스크립트에서 return은 함수를 호출한 대상에게 값을 전닥해주기 위해서도 쓰지만, 함수코드진행을 종료하기위해서도 쓴다 (return 또는 return False)
	}
	
	console.log('아이디 없을 경우 실행안됨');		//브라우저 개발자도구 콘솔창에 프린트
	
	if(userPw.value.length==0){
		alert('패스워드 입력:');
		userPw.focus();
		return False;		// 여기서 코드가 끝난다.
	}
	
	var form=document.querySelector('#frm');	//form객체 가져온다
	form.submit(); 			//폼 전송
}
</script>
</html>
