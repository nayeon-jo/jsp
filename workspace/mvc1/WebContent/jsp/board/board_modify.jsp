<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판 읽기</title>

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
     <h1>게시글 내용 수정</h1>
     <form action="" method="post" id="frm">
        <input type="hidden" name="boardId" id="boardId" value="${boardVO.boardId}">
        <table class="read_table">
           <tbody>
        	  <tr>
        	     <th style="width: 20%">
        	        제목
        	     </th>
        	     <td style="width: 55%">
        	        <input type="text" name="title" id="title"
        	            style="width:400px;hegith:20px;font-size:15px;" value="${boardVO.boardTitle}"> 
        	     </td>
        	      <th style="width: 10%">
        	         작성일시
        	      </th>
        	      <td style="width: 15%">
        	           <span style="font-size: 10px">${boardVO.createDate}</span>
        	      </td>
        	  </tr>
        	  <tr>
        	     <th style="width:80px">
        	        내용
        	     </th>
        	     <td colspan="3">
        	         <div style="heigth:200px;width:100%">
        	             <textarea rows="14" cols="150" id="contents" name="contents">${boardVO.boardContents}</textarea>
        	         </div>
        	     </td>
        	  </tr>
           </tbody>
        </table>
        <div align="center">
              <button type="button" onclick="modify();">수정</button> &nbsp;&nbsp;
              <button type="button" onclick="goList();">취소</button>
        </div>
     </form>
  </div>
</body>

<script>
 function modify() {
	 var form = document.querySelector('#frm');
	 form.action = '<%=root%>/modify/board.do';
	 
	 var title = document.querySelector('#title');
	 var contents = document.querySelector('#contents');
	 
	 console.log(title)
	 if(title.value.length == 0) {
		alert('제목을 입력해주십시오');
		title.focus();
		return false;
	 }
	 if(contents.value.length == 0) {
		alert('내용을 입력해주십시오');
		contents.focus();
		return false;
	 }
	 form.submit(); 
 }
 
 function goList (){ 
	 location.href = '<%=root%>/board.do'; 
 }
</script>
</html>