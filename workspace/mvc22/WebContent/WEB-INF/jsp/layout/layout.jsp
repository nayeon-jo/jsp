<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<%
  String root = request.getContextPath();
%>
<style>
  
   body {
     text-align: center;
     min-width: 1260px;
   }
   
   header {
     background-color: #664B00;
     padding :10px;
   }
   
   nav {
     background-color: #FFE08C;
     min-width: 500px;
   }
   
   nav ul {
    postion : relative;
    margin : 0px;
    padding : 0px;
   }
   
   ul li{
     display: inline;
     padding-left: 30px;
     font-size: 20px;
     font-style : oblique;
     font-weight: bold;
   }
   
    footer {
      border: 1px solid black;
    }
    
    .leftArea {
    
    	float: left;
    	width: 15%;
    	height:600px;
    	overflow: hidden;
    }
    
    .rightArea {
    	width:85%;
    	height:600px;
    	overflow: hidden;
    }

</style>
</head>
<body>
  <div id="wrap">
     <header>
        <h1>홈페이지에 오신것을 환영합니다.</h1>
     </header>
     <nav>
        <ul>
          <li>
             <a href="javascript:void(0)" onclick="goHome();">HOME</a>
          </li>
           <li>
             <!-- <a href="javascript:void(0)" onclick="movePage('register');">회원가입</a> -->
          </li>
           <li>
             <a href="javascript:void(0)" onclick="movePage('board');">게시판</a>
          </li>
        </ul>
     </nav>
     <article>
        <div class="leftArea"  id="left"></div>
        <div class="rightArea" id="right"></div>
     </article>
     <div class="footer">밑단</div>
  </div>
</body>
<script>

	function onLoadPage(){
		//해당 아이디를 가진 div 에 원하는 jsp 파일을 삽입하는 기능
		// load(경로, 파라미터)  구조로 되어있으며
		// 파라미터가 없을 경우 null 을 넣어주면된다.
		$('#left').load(contextUri('login/loginForm.do'), null);
		$('#right').load(contextUri('view/main.jsp'), null);
	}


    $(document).ready(function(){
    	onLoadPage();
    })
    
    
     function movePage(type) {
    	
    	if(type == 'board') {
    		$('#right').load(contextUri('board/list.do'), null);
    	}else if(type == 'writeBoard') {
    		$('#right').load(contextUri('board/create.do'), null);
    	}
    	
    }
    
    
	function loadDetailPage(url, param){
		$("#right").load( url, param);
	}
	
	function reloadPage(url){
		$("#right").load( contextUri(url), null)
	}
	
	
	function fileDownLoad(fileName){
		location.href =contextUri("file/downLoadFile?fileName="+fileName);
	}
    
    function contextUri(uri){
		return '<%=root%>/'+uri;
    }

</script>
</html>