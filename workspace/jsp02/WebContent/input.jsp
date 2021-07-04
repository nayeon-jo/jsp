<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<!-- css영역 -->
<!-- css작성시 #으로 시작하는 것은 id값에 속성을 부여하는것, 예를들면 #div_form-->
<!-- css작성시 .으로 시작하는 것은 class값에 속성을 부여하는것-->
<!-- class명으로 부여하는것이 더 좋다-->
 <style>
  div{
  width:400px;
  font-size:12pt;
  }
  button{
  font-size:12pt;
  }
  #div_form{
  background-color: #f8f8ff;
  }
  #div_button{
  margin-top:20px 0px 0px 0px; /*위 오른쪽 아래 좌 (시계방향)*/
  }
 </style>
</head>
<body>

 <form>
  <div id="div_form" style="height:100px">
   <div id="div_input">
 	이름: <input type="text" id='wname' name='wname'>
   </div>
   
   <div id="div_age">
    나이: <input type="text" id="age" name="age">
   </div>
    
   <div>
      성별:  <select id="gender" name="gender">
        		<option value="1"> 남자</option>
      			<option value="2"> 여자</option>
     		</select>
   </div>
    
   <div>
      취미:  
      <input type="checkbox" id="hobby1" name="hobby" value="등산">등산
      <input type="checkbox" id="hobby2" name="hobby" value="음악">음악
      <input type="checkbox" id="hobby3" name="hobby" value="영화">영화   	
   </div> 
  </div>
  
  <div id="div_button" align="center">
   <button type="submit">전송</button>
   <button type="reset">초기화</button>
  </div>
 </form>
 
</body>
</html>