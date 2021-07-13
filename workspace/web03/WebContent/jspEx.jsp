<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<h1>표현식 함수와 선언식 함수의 차이</h1>
</body>

<script type="text/javascript">
/*
    호이스팅 기술
    함수를 만들기전에 호출하고 그 후에 함수를 만들 경우, 
    스크립트에서 함수의 선언을 위로 끌어올려 호출전에 선언되게끔 하는 기술 
  */	
	f(10,20);
	function f(a,b){	//표현식 함수 -호이스팅 가능
		var sum=a+b;
		alert('합은'+sum);
	}
	
	var fuc=function(){ 	//선언식 함수 -호이스팅 가능
		alert('호이스팅 안됨');
	}
</script>
</html>
