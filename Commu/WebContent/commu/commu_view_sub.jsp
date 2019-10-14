<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 오류 페이지 정의  -->
<%@ page errorPage="commu_error.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시글 수정 등록</title>
	<link rel="stylesheet" href="commu_css" type="text/css" media="screen" />

	<script type="text/javascript">

		// window.load는 페이지 로딩 후 실행
		window.onload = function() {
			var action = document.form1.action.value;

			if(action=="edit") {
				document.getElementById("insert").disabled=true;
				// document.getElementById("update").disabled=false;
				// document.getElementById("delete").disabled=false;
			} else if(action=="add") {
				// document.getElementById("insert").disabled=false;
				document.getElementById("update").disabled=true;
				document.getElementById("delete").disabled=true;
			}
		} 
	
		function insertcheck() {
			// post방식
			document.form1.action.value="insert";
			document.form1.submit();
		}
	
		function updatecheck() {
			// post방식
			document.form1.action.value="go_update";
			document.form1.submit();
		}
	
		function deletecheck() {
			result = confirm("정말로 삭제하시겠습니까 ?");
		
			if(result == true){
				
				// post방식
				document.form1.action.value="delete";
				document.form1.submit();
			}
			else
				return;
		}
		
		function backcheck() {
			// post방식
			document.form1.action.value="list";
			document.form1.submit();
			// history.go(-1);
			
		}
		
	</script>

</head>

<body>

<div align="center">
	<H2>게시글 수정</H2>
	<HR>

	<!-- 계좌이체 등록용 -->
	<form name="form1" method="post" action="CommuController" enctype="multipart/form-data">

		<input type="hidden" name="action" value=${action}>
		<input type="hidden" name="id" value=${commuDTO.id}>
		
		<table>
			<thead>
			<tr>
				<th colspan="3">글 보기</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>제목</td>
				<td>${commuDTO.writehead}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${commuDTO.writer}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${commuDTO.writetext}</td>
			</tr>		
			<tr>
				<td colspan=2 align=center>
					<!--  <input type="button" id="insert" value="작성" onClick="insertcheck()">-->
					<input type="button" id="update" value="수정" onClick="updatecheck()">
					<input type="button" id="delete" value="삭제" onClick="deletecheck()">
					<input type="button" id="back" value="돌아가기" onClick="backcheck()">
				</td>
			</tr>
		</table>
	</form>
	</div>


</body>
</html>