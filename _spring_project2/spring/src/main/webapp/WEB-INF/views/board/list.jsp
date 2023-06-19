<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
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
	
	<c:if test="${ph.prev }">
		<a href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">prev</a>
	</c:if>
	<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
		<a href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i } | </a>
	</c:forEach>
	<c:if test="${ph.next }">
		<a href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">next</a>
	</c:if>
	<br><br>
	
	<a href="/board/register"><button type="button">글작성</button></a>
	<a href="/"><button type="button">메인</button></a>
<jsp:include page="../layout/footer.jsp"></jsp:include>