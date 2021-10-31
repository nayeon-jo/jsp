<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>

 .read_table table{
	min-width: 1260px;
	border-collapse: collapse;
	font-size: 12px; /* 글꼴 크기 */
	line-height: 24px; /* 줄 간격 */
}

 .read_table table td,th {
	border: #d3d3d3 solid 1px; /* 경계선 색상 스타일 굵기 */
	padding: 5px; /* 안쪽 여백 */
}

 .read_table th {
	background: yellowgreen; /* 배경색 */
}

img {
	width: 220px; /* 이미지 너비(가로) */
	height: 300px; /* 이미지 높이(세로) */
}

a {
	text-decoration: none; /* 링크 밑줄 없애기 */
	color: black; /* 글 색상 */
}

a:HOVER {
	text-decoration: underline; /* 밑줄 */
	color: green; /* 글 색상 */
}


</style>
<% String root = request.getContextPath(); %>

	<div  id="wrap" align="center">
		<h1>게시글 내용</h1>
		<form action="" method="post" id="frm">
			<input type="hidden" name="boardId" id="boardId" value="${vo.boardId}">
			<table  class="read_table">
				<tr>
					<td>
						<table>
							<tr>
								<th style="width: 80px">제목</th>
								<td>
								  <input type="text" name="title" id="title"
        	                              style="width:400px;hegith:20px;font-size:15px;" value="${vo.boardTitle}"> 
								</td>
							</tr>
							<tr>
								<th style="width: 80px">파일</th>
								<td>
								  <input type="file" name="upfile" id="upfile">
								</td>
							</tr>
							<tr>
								<th>내  용</th>
								<td>
								  <textarea rows="14" cols="150" id="contents" name="contents">${vo.boardContents}</textarea>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br>
			 <input type="hidden" name="code" id="code" value="${vo.boardId}">
			<input type="button" onclick="javascript:updatePds();"   value="수정">&nbsp;&nbsp;
			<input type="button" 	value="취소" onclick="goDetail();">
		</form>
	</div>
	<script>
			
	function updatePds(){
		 var form = document.querySelector('#frm'); 
		 
		 $.ajax(
				 {
					 url : getUrl('board/modify.ajax'),
					 type: 'post',
					 dataType: 'json',
					 cache : false,
					 mimeType : 'multipart/form-data',  //파일전송할때 쓰는타입!
					 contentType : false,  //기존 데이터전송양식을 쓰지말아!..
					 processData : false,  // 기존 ajax 가 데이터 날리는 방식 못쓰게함. - 파일전송일 경우.
					 data: new FormData(form) //form 객체 자체는 배열로구성..그래서 첫번째꺼 쓴다고 하면딤.
		         }
		 ).done(function(data){
			 console.log(data);
			 if(data.resultCode == 200){
				alert('게시글이 수정되었습니다.');
			}else {
				alert('게시글이 수정이 실패하였습니다.');
			}
			
			//등록 이후 게시판 리스트화면으로 전환 
			movePage('board');
		
		  }).fail(function(error) {
			  alert('게시글이 수정이 실패하였습니다.');
			  movePage('board');
		  });
	 
	}
			
	
	 function goDetail() {
		 var boardId  = $("#boardId").val();			
		   $("#right").load( contextUri('board/read.do?boardId='+ boardId), null)
	   }
	
	 
	 
	 //url 만들어주는 펑션 
	 function getUrl(target) {
		 return '<%=root%>/'+target;
	 }
	 
	
	</script>
