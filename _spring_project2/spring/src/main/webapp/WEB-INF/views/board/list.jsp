<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<div id="list-wrap">
	<table id="list-table">
		<tr class="list-tr">
			<th class="list-th">번호</th>
			<th class="list-th">제목</th>
			<th class="list-th">작성자</th>
			<th class="list-th">작성일</th>
			<th class="list-th">조회수</th>
		</tr>
		<c:forEach items="${list }" var="list">
			<tr class="list-tr">
				<td class="list-td">${list.bno }</td>
				<td class="list-td"><a href="/board/detail?bno=${list.bno }">${list.title }</a></td>
				<td class="list-td">${list.writer }</td>
				<td class="list-td">${list.reg_date }</td>
				<td class="list-td">${list.read_count }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
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
	
	<a href="/board/register"><button type="button" class="list-regi">글작성</button></a>
	<br><br><br>
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
			<button type="submit" class="list-regi">검색</button>
			<a href="/board/list?pageNo=1&type=w&keyword=${ses.id }"><button type="button" class="list-regi">내가쓴글</button></a>
			<span>게시물 총 ${ph.totalCount }개</span>
	</form>
</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>