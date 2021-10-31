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
       	<input type="hidden" name="boardId" id="boardId" value="${vo.boardId}">
        <table class="read_table">
           <tbody>
        	  <tr>
        	     <th style="width: 20%">
        	        제목
        	     </th>
        	     <td style="width: 55%">
        	      ${vo.boardTitle}
        	     </td>
        	      <th style="width: 10%">
        	         작성일시
        	      </th>
        	      <td style="width: 15%">
        	        ${vo.createDate}
        	      </td>
        	  </tr>
        	  <tr>
        	   <th>첨부파일</th>
        	     <td colspan="3">
        	        <a href="javascript:void(0)" onclick="fileDownLoad('${vo.storedFileName}')">${vo.originFileName}</a>
        	     </td>
        	  </tr>
        	  <tr>
        	     <th>내용</th>
        	     <td colspan="3">
        	       ${vo.boardContents}
        	     </td>
        	  </tr>
           </tbody>
        </table>
       <div align="center" style="margin-top:30px;">
              <button type="button" onclick="updateBoard();">수정</button> &nbsp;&nbsp;
              <button type="button" onclick="deleteBoard();">삭제</button> &nbsp;&nbsp;
              <button type="button" onclick="movePage('board');">취소</button>
        </div>
     </form>
  </div>
</body>
<script>

function updateBoard(){
	 
	var boardId  = $("#boardId").val();				

	var param ={
			boardId:boardId
	};
	
	 loadDetailPage(contextUri("board/update.do"),  param);				
 
}
		

function deleteBoard(){
	var boardId  = $("#boardId").val();				

	var param ={
			boardId:boardId
	};
	
	if (confirm("정말 삭제하시겠습니까??") == true){    //확인
		loadDetailPage(contextUri("board/delete.do"),  param);		
	}
 }
</script>
</html>