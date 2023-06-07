<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
</head>
<body>
<form action="/board/register" method="post">
	<table>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" value="${ses.id }" readonly="readonly"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="30" cols="50" name="context"></textarea></td>
		</tr>
	</table>
	<button>작성완료</button>
	<a href="/board/list"><button type="button">취소</button></a>
	</form>
</body>
</html>