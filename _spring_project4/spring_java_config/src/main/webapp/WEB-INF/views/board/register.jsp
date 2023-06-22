<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/board/register" method="post">
	title : <input type="text" name="title">
	writer : <input type="text" name="writer">
	content : <input type="text" name="content">
	<button>등록</button>
</form>
</body>
</html>