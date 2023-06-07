<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1>로그인</h1>
	<form action="/member/login" method="post">
		<table>
			<tr>
				<td><input type="text" name="id" placeholder="아이디"></td>
			</tr>
			<tr>
				<td><input type="password" name="pw" placeholder="비밀번호"></td>
			</tr>
		</table>
		<button>로그인</button>
		<a href="/"><button type="button">메인</button></a>
	</form>
</body>
</html>