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
	
	f(10,20);
	function f(a,b){	//표현식 함수
		var sum=a+b;
		alert('합은'+sum);
	}
	
	var fuc=function(){ //선언식 함수
		alert('호이스팅 안됨');
	}
</script>
</html>
