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
     <h1>게시글 내용 보기</h1>
     <form action="" method="post" id="frm">
        <input type="hidden" name="boardId" id="boardId" value="${boardVO.boardId}">
        <table class="read_table">
           <tbody>
        	  <tr>
        	     <th style="width: 20%">
        	        제목
        	     </th>
        	     <td style="width: 55%">
        	         <span style="font-size: 15px">${boardVO.boardTitle}</span>
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
        	             <textarea rows="14" cols="150">${boardVO.boardContents}</textarea>
        	         </div>
        	     </td>
        	  </tr>
           </tbody>
        </table>
        <div align="center">
              <button type="button" onclick="modifyBoard();">수정</button> &nbsp;&nbsp;
              <button type="button" onclick="deleteBoard();">삭제</button> &nbsp;&nbsp;
              <button type="button" onclick="goList();">목록</button>
        </div>
     </form>
  </div>
</body>

<script>
  function modifyBoard() {
	  var boardId = document.querySelector('#boardId').value;
	  location.href = "<%=root%>/board/detail.do?type=modify&boardId=" + boardId;
  }

  function goList (){ 
	  location.href = '<%=root%>/board.do'; 
  }
  
  function deleteBoard (){
	  var isConfirm = confirm('정말 삭제하시겠습니까 ?');
	  var boardId = document.querySelector('#boardId').value;
	 
	  if(isConfirm) {
		  location.href = "<%=root%>/board/del.do?boardId=" + boardId;
	  }  
  }
</script>
</html>