<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>계좌이체</title>
	<link rel="stylesheet" href="commu.css" type="text/css" media="screen" />
</head>

<body>
	<div align="center">
	<H2>등록 오류</H2>
	<HR>
	<table>
		<tr>
			<td>
				등록중 에러가 발생 했습니다.<BR>
				관리자에게 문의해 주세요..<BR>
				<HR>
				에러내용 : <%= exception%>
				<HR>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>