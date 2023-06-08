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
	<br>
	<!-- paging Line -->
	<!-- 이전페이지 -->
	<c:if test="${ph.prev }">
		<a href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">prev</a>
	</c:if>
	<!-- 컨트롤러에서 page 정보를 싣고 와야 함 -->
	<!-- startpage~endpage까지 숫자 반복 (foreach) -->
	<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
		<a href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i } | </a>
	</c:forEach>
	<!-- 다음페이지 -->
	<c:if test="${ph.next }">
		<a href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">next</a>
	</c:if>
	<br><br>
	<a href="/board/register"><button type="button">글작성</button></a>
	<a href="/"><button type="button">메인</button></a>
	
	<!-- 검색 라인 -->
	<form action="/board/list">
		<c:set value="${ph.pgvo.type }" var="typed"></c:set>
		<select name="type">
			<option ${type == null ? "selected":"" }> 선택 </option>
			<option value="t" ${typed eq "t" ? "selected":"" }>제목</option>
			<option value="w" ${typed eq "w" ? "selected":"" }>작성자</option>
			<option value="c" ${typed eq "c" ? "selected":"" }>내용</option>
			<option value="tc" ${typed eq "tc" ? "selected":"" }>제목+내용</option>
			<option value="tw" ${typed eq "tw" ? "selected":"" }>제목+작성자</option>
			<option value="wc" ${typed eq "wc" ? "selected":"" }>작성자+내용</option>
		</select>
			<input type="text" name="keyword" placeholder="검색">
			<input type="hidden" name="pageNo" value="${ph.pgvo.pageNo }">
			<input type="hidden" name="qty" value="${ph.pgvo.qty }">
			<button type="submit">검색</button>
			<a href="/board/list?pageNo=1&type=w&keyword=${ses.id }"><button type="button">내가쓴글</button></a>
	</form>
</body>
</html>