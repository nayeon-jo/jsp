<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<!-- action은 전송될 jsp 경로를 기입, method는 전송방식 (get/post), id는 자바스크립트에서 form 호출 -->
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

<!-- 자바스크립트는 script 태그영역에서 -->
<script> 					
//jsp문서는 컴파일이 순차적으로 되기 때문에 스크립트도 순차적으로 읽게된다
//자바스크립트는
	function btnEvt(){
	//아이디와 패스워드 입력확인
	var userId=document.getElementById('userId');		//html태그의 id값으로 찾아 가져온다
	var passwd=document.querySelector('#userPw');		//쿼리셀렉터는 다양하게 찾아올 수 있기 때문에 어떤값으로 찾아오는건지 명시해야함, #이 붙는 경우는 id값으로 가져오겠다는 뜻
	
	if(userId.value.length==0){
		alert('아이디 입력:');
		userId.focus();
		return False;			//자바스크립트에서 리턴은 함수코드를 종료하기위해서도 쓴다 return 또는 return False
	}
	
	console.log('아이디 없을 경우 실행안됨');		//브라우저 개발자도구 콘솔창에 프린트
	
	if(userPw.value.length==0){
		alert('패스워드 입력:');
		userPw.focus();
		return False;
	}
	
	var form=document.querySelector('#frm');	//form객체 가져온다
	form.submit(); 	//폼 전송
}
</script>
</html>