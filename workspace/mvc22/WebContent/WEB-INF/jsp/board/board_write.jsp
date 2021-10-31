<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판 쓰기</title>

<style>
 .read_table table {
 
 	max-width :1260px;
 	min-width:600px;
 	border-collapse: collapse;
 	line-height: 24px;
 }
 
 .read_table table, td, th {
     border: #d3d3d3 solid 1px; /*색상, 외곽선형태, 두께 */
     padding: 5px;
 }
 
 .read_table th {
 	background: yellowgreen;
 }
</style>
<% String root = request.getContextPath(); %>
</head>
<body>
  <div align="center">
     <h1>게시글 내용 쓰기</h1>
     <form action="" method="post" id="frm">
        <table class="read_table">
           <tbody>
        	  <tr>
        	     <th style="width: 20%">
        	        제목
        	     </th>
        	     <td style="width: 55%">
        	        <input type="text" name="title" id="title"
        	            style="width:400px;hegith:20px;font-size:15px;" value=""> 
        	     </td>
        	      <th style="width: 10%">
        	         작성일시
        	      </th>
        	      <td style="width: 15%"></td>
        	  </tr>
        	  <tr>
        	   <th>첨부파일</th>
        	     <td colspan="3">
        	          <input type="file" name="upfile" id="upfile">
        	     </td>
        	  </tr>
        	  <tr>
        	     <th>내용</th>
        	     <td colspan="3">
        	         <div style="heigth:200px;width:100%">
        	             <textarea rows="14" cols="150" id="contents" name="contents"></textarea>
        	         </div>
        	     </td>
        	  </tr>
           </tbody>
        </table>
       <div align="center" style="margin-top:30px;">
              <button type="button" onclick="createEvt();">저장</button> &nbsp;&nbsp;
              <button type="button" onclick="goList();">취소</button>
        </div>
     </form>
  </div>
</body>
<script>

 function createEvt() { 
	 var form = document.querySelector('#frm'); 
	 var title = document.querySelector('#title');
	 var contents = document.querySelector('#contents');
	 
	 if(title.value.length == 0) {
		alert('제목을 입력해주십시오.');
		title.focus();
		return false;
	 }
	 
	 if(contents.value.length == 0) {
		alert('내용을 입력해주십시오.');
		contents.focus();
		return false;
	 }
	 
	 $.ajax(
			 {
				 url : getUrl('board/create.ajax'),
				 type: 'post',
				 dataType: 'json',
				 cache : false,
				 mimeType : 'multipart/form-data',  //파일전송할때 쓰는타입!
				 contentType : false,  //기존 데이터전송양식을 쓰지말아!..
				 processData : false,  // 기존 ajax 가 데이터 날리는 방식 못쓰게함. - 파일전송일 경우.
				 data: new FormData(form) //form 객체 자체는 배열로구성..그래서 첫번째꺼 쓴다고 하면딤.
	         }
	 ).done(function(data){
		 if(data.resultCode == 200){
			alert('게시글이 등록되었습니다.');
		}else {
			alert('게시글이 등록이 실패하였습니다.');
		}
		
		//등록 이후 게시판 리스트화면으로 전환 
		movePage('board');
	
	  }).fail(function(error) {
		  alert('게시글이 등록이 실패하였습니다.');
		  movePage('board');
	  });
	 
 }
 
 
 //url 만들어주는 펑션 
 function getUrl(target) {
	 return '<%=root%>/'+target;
 }
 
 

  function goList (){ 
	  movePage('board');
  }

</script>
</html>