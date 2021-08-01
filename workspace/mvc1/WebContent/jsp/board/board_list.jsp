<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>
<%
	String root=request.getContextPath();
%>

<!-- css링크 걸기-->
<link href="<c:url value="/css/board.css"/>" rel="stylesheet" type="text/css"> 

</head>

<body>
  <div align="center" style="margin-top: 40px;font-size: 30px;">
  	<span>게시판</span>
  </div>
  <div align="center">
     <div align="right" style="width:650px;">
        <span>전체 글 :  ${totalCount} 건 </span>
     </div>
     <div class="container">
        <table class="b_table" style="width:650px">
          <thead>
	          <tr class="b_tr">
	            <th class="b_th" width="10%">글 번호</th>
	            <th class="b_th" width="45%">글 제목</th>
	            <th class="b_th" width="10%">저   자</th>
	            <th class="b_th" width="25%">작성날짜</th>
	            <th class="b_th" width="10%">조회 수</th>
	          </tr>
           </thead>
           <tbody>
             <c:if test="${boardList != null }">
          	   <c:forEach items="${boardList}" var="vo" varStatus="index">
          	      <tr class="b_tr">
          	    	 <td class="b_td">${vo.boardId}</td>
          	    	 <td class="b_td">
                        <a href="#" onclick="goDetail(${vo.boardId})">${vo.boardTitle}</a>
                      </td>
                      <td class="b_td">
                        ${vo.boardAuthor}
                      </td>
                      <td class="b_td">
                         ${vo.createDate}
                      </td>
                      <td class="b_td">
                         ${vo.boardCount}
                      </td>
          	      </tr>
          	    </c:forEach>
             </c:if>
            </tbody>
         </table>
      </div>
  </div>
  
  <div align="center" style="margin-top:10px;">
  	<div align="right" style="width:650px;">
  		<button id="writeBtn" onclick="writeBoard();" style="border-radius:10px; width:100px; background:#76D7EA">글올리기</button> 		
  	</div>
  </div>
</body>
<script>
	function writeBoard(){
		location.href = "<%=root%>/board/add/view.do"; 
	}
    function goDetail(boardId) {
	    location.href = "<%=root%>/board/detail.do?type=detail&boardId=" + boardId;
    }
</script>
</html>