<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 오류 페이지 정의  -->
<%@ page errorPage="commu_error.jsp" %>

<!-- taglib 지시어  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>커뮤니티 사이트</title>

	<link rel="stylesheet" href="commu.css" type="text/css" media="screen" />

	<script type="text/javascript">
		function retrivalcheck() {
			
			// get방식
			// document.location.href="addr_control.jsp?action=list";
			
			// post방식 디펄트가 list
			document.form1.action.value="list";
			document.form1.submit();
	
			
		}
	
		function addcheck() {
			
			// post방식
			document.form1.action.value="add";
			document.form1.submit();
		}

		
		 //function editcheck(id) {
		
			// get방식
			// document.location.href="addr_control.jsp?action=edit&id="+id;
			
			// post방식
			 //document.form1.action.value="edit";
			 //document.form1.id.value=id;
			 //document.form1.submit();	
		//}
		 
		 function viewcheck(id) {
			 document.form1.action.value="view";
			 document.form1.id.value=id;
			 document.form1.submit();
		 }		 
	</script>
</head>

<body>
	<div align=center>
	<H2>커뮤니티</H2>
	<HR>

	<!-- 조회폼 -->
	<!-- action의 실제 URL은 앞에 컨텍스트 Path와 jsp의 패키지가 추가됨 -->
	<form name="form1" method="post" action="CommuController">
		<input type="hidden" name="action" value="list">
		<input type="hidden" name="id" value=0>
		
		<table border=1>
			<tr>
				<th>ID</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일자</th>
			</tr>
			<tr>
				<td>공지</td>
				<td>안녕하세요.</td>
				<td>운영자</td>
				<td>2019-06-13</td>
			</tr>
			<tr>
				<td>공지</td>
				<td>안녕하세요.</td>
				<td>운영자</td>
				<td>2019-06-14</td>
			</tr>
			
			<c:forEach var="i" items="${commuList}">
				<tr>
					<td>${i.id}</td>
					<td><a href="javascript:viewcheck(${i.id})">${i.writehead}</a></td>
					<td>${i.writer}</td>
					<td>${i.writeDate}</td>
				</tr>
			</c:forEach>			
			<tr>
				<td colspan=5 align=right>
    				<input type="button" value="글쓰기" onClick="addcheck()">
    				<input type="button" value="새로고침" onClick="retrivalcheck()">
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>

</html>