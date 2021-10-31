<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
  String root = request.getContextPath();
%>

<link href="<%=root%>/css/bbs1.css" rel="stylesheet" type="text/css">

<form action="<%=root%>/login/logOut.do" method="get" id="logOutForm"></form>
<!-- 로그인 화면 -->
<div id="login" style="display: none">
    <table class="log_table" >
       <tr class="log_tr">
          <td class="log_td">
              <fieldset class="log_field">
                  <legend class="log_le">회원 로그인</legend><br/>
                  <div class="log_div">
                      <input type="text" id="userId" name="userId" style="width:100%">
                  </div>
                  <div class="log_div">
                      <input type="password" id="userPw" name="userPw" style="width:100%">
                  </div>
                  <div class="log_div" align="center">
                      <a href="javascript:void(0)" onclick="login();">로그인</a>&nbsp;&nbsp;&nbsp;
                      <a href="javascript:void(0)" onclick="create();">회원가입</a>
                  </div>
              </fieldset>
          </td>
       </tr>
    </table>
</div>
<!-- 로그인이 되었을 경우  -->
<div id="do_login" style="display: none">
    <table class="log_table">
       <tr class="log_tr">
           <td class="log_td">
               <fieldset class="log_field">
                  <legend class="log_le">로그인 됨</legend>
                  <div class="log_div" id="loginUser"></div>
                  <div  class="log_div">
                     <a href="javascript:void(0)" onclick="logout();">로그아웃</a>
                  </div>
               </fieldset>
           </td>
       </tr>
    </table>
</div>
<script>

  function initPage() {
	  var isLogin = '${loginOk}';
	  
	  if(isLogin == 'OK') {
		  var userName = '${userName}';
		  doneLogin(userName);
	  }else {
		  $('#do_login').hide();
		  $('#login').show();
	  }
  }
  
  
  function validationCheck() {
	  //아이디 input 객체 가져오기
	  var userId = $('#userId');
	  var userPw = $('#userPw');
	  
	  if($.trim( userId.val() ).length == 0) {
		  alert('아이디를 입력하세요.');
		  userId.focus();
		  return false;
	  }
	  
	  if($.trim( userPw.val() ).length == 0) {
		  alert('패스워드를 입력하세요.');
		  userPw.focus();
		  return false;
	  }
	  
	  return true;
  }
  
  
  function login() {
	  
	  if( validationCheck() ) {
		  
		  //서버에 전송할 데이터 양식
		  // key, value 로 이루어지며 자바의 map 과 같은 형식 
		  var data = {
				  userId : $('#userId').val(),
				  userPw : $('#userPw').val()
		  }
		  /*
		    ajax 옵션 값의 의미
		     type :  전송방식 (GET / POST)
		     url : 데이터를 전송할 서블릿 주소 
		     data : 서버에 전송할 데이터 (map 구조)
		  */
		  $.ajax(
			{
				type:'post',
				url: '<%=root%>/login/loginProc.ajax',  
				data : data,   // 내가 보낼 데이터 파라미터 
				dataType: 'json'  // 내가 리턴받을 데이터 타입 
			}
			 //성공했을 경우 실행 
		  ).done(function(data){
			  // 브라우저 개발자도구의  콘솔창에서 확인 가능
			  console.log(data); 
			  
			  if(data.resultCode == 200) {
				  doneLogin(data.userName);
			  }else {
				  alert(data.resultMsg);
				  return false;
			  }
		
		  }).fail(function(error) {
			 //실패했을 경우 실행 
		  });
	  }
  }
  
  function doneLogin(userName) {
	  $('#do_login').show(); // 로그인 후 화면이 보여짐
	  $('#login').hide(); // 로그인전 화면은 숨겨짐 
	  
	  var span = $('<span></span>');
	  
	  span.css('width', '100%');
	  span.text('회원이름 :' + userName);
	  
	  $('#loginUser').empty(); // 기존내용 삭제
	  $('#loginUser').append(span);
  }

  
  function logout() {
	 location.href = '<%=root%>/login/logOut.do';
  }

  $(document).ready(function(){
	  initPage();
  });


</script>