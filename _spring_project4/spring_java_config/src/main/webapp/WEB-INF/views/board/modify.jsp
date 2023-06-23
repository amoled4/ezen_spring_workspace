<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>
<form action="/board/modify?bno=${bvo.bno }" method="post">
	<table>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="${bvo.title }"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${bvo.writer }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${bvo.regAt }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${bvo.readCount }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><input type="text" name="content" value="${bvo.content }"></td>
		</tr>
	</table>
	<button>수정완료</button>
	<a href="/"><button type="button">취소</button></a>
	</form>
</body>
</html>