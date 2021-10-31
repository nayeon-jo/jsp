<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>

<script>

	function resultFunc(){
		var resultCode =${resultCode};
		
		console.log("resultCode", resultCode);
		
		if(resultCode < 0){
			alert("삭제 실패");
		}else{
			alert("삭제 성공");
		}
		reloadPage('board/list.do');
		
	}
	 $(document).ready(resultFunc);

</script>