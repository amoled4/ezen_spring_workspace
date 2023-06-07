<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${list }" var="list">
			<tr>
				<td>${list.bno }</td>
				<td><a href="/board/detail?bno=${list.bno }">${list.title }</a></td>
				<td>${list.writer }</td>
				<td>${list.reg_date }</td>
				<td>${list.read_count }</td>
			</tr>
		</c:forEach>
	</table>
	<a href="/board/register"><button type="button">글작성</button></a>
	<a href="/"><button type="button">메인</button></a>
</body>
</html>