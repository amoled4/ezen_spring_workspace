<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>
</head>
<body>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="bvo" items="${list }">
		<tr>
			<td>${bvo.bno }</td>
			<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
			<td>${bvo.writer }</td>
			<td>${bvo.readCount }</td>
		</tr>
		</c:forEach>
	</table>
	<a href="/"><button type="button">메인</button></a>
	<a href="/board/register"><button type="button">글작성</button></a>
</body>
</html>