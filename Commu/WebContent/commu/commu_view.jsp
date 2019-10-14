<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 오류 페이지 정의  -->
<%@ page errorPage="commu_error.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시글 등록</title>
	<link rel="stylesheet" href="commu.css" type="text/css" media="screen" />

	<script type="text/javascript">

		// window.load는 페이지 로딩 후 실행
		window.onload = function() {
			var action = document.form1.action.value;

			if(action=="go_update") {
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
			document.form1.action.value="update";
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
	<H2>게시글 등록</H2>
	<HR>

	<!-- 게시글 등록용 -->
	<form name="form1" method="post" action="CommuController">

		<input type="hidden" name="action" value=${action}>
		<input type="hidden" name="id" value=${commuDTO.id}>		
		
		<table>
			<tr>
				<th>제목</th>
				<td>
					<input type=text size=20 name=writehead value=${commuDTO.writehead}></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type=text size=20 name=writer value=${commuDTO.writer}></td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><input type=date size=10 name=writeDate value=${commuDTO.writeDate}></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea cols="28" rows="20" name=writetext class="noresize" >${commuDTO.writetext}</textarea></td>
			</tr>
			<tr>
				<td colspan=2 align=center>
					<input type="button" id="insert" value="작성" onClick="insertcheck()">
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