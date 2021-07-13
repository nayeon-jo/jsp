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
		//자바스크립트는 변수 자체가 객체이다(x) 자바스크립트는 변수에 객체를 담을수 있다(o)
		var str='문자열';
		var num=10;		
		
		
		//------------함수의 타입------------
		function ff(a,b){	//파라미터있는 함수
			return a+b;		//리턴타입은 별도로 없어서, 리턴항목이 있으면, 그냥 리턴이 있는 함수가 된다.
		}
		ff();
		
		
		//------------함수선언방식 차이------------
		 //자바스크립트는 우측 값이 무엇이냐에 따라 변수의 타입이 달라진다.
		var func=function(){  //함수선언식
			alert('ttest');
		}
		func();
		
		function f(){	//함수표현식
			return 1;
		}
		
	</script>
</html>
