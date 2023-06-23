<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 보기</title>
</head>
<body>
	<table>
		<tr>
			<th>제목</th>
			<td>${bvo.title }</td>
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
			<td>${bvo.content }</td>
		</tr>
	</table>
	<a href="/board/modify?bno=${bvo.bno }"><button type="button">수정</button></a>
	<a href="/board/remove?bno=${bvo.bno }"><button type="button">삭제</button></a>
	<a href="/board/list"><button type="button">목록</button></a>
</body>
</html>