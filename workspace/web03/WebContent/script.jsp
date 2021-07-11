<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<h1>스크립트예제</h1>
</body>

	<script>
		//변수 만들기
		var str='문자열';
		var num=10;		
		
		
		//함수의 타입
		function ff(a,b){	//파라미터있는 함수
			return a+b;		//리턴타입은 별도로 없어서, 리턴항목이 있으면, 그냥 리턴이 있는 함수가 된다.
		}
		ff();
		
		//함수선언방식 차이
		var func=function(){  //함수선언식
			alert('ttest');
		}
		func();
		
		function f(){	//함수표현식
			return 1;
		}
		
	</script>
</html>