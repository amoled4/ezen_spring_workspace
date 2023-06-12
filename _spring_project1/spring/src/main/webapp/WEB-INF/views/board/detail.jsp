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
			<td>${board.title }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.writer }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.read_count }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.context }</td>
		</tr>
	</table>
	<c:if test="${ses.id == board.writer }">
	<a href="/board/modify?bno=${board.bno }"><button>수정</button></a>
	<a href="/board/remove?bno=${board.bno }"><button>삭제</button></a>
	</c:if>
	<a href="/board/list"><button>목록</button></a>
	
	<!-- 댓글 작성 라인 -->
	<div>
	<p>
		<span id="cmtWriter">${ses.id }</span>
		<textarea rows="3" cols="50" id="cmtText" placeholder="댓글을 작성해 주세요."></textarea>
		<button type="button" id="cmtPostBtn">등록</button>
	</p>
	</div>
	<!-- 댓글 목록 라인 -->
	<div>
		<ul id="cmtListArea">
		<!-- li 하나가 하나의 댓글 객체 -->
			<li>
				<div>Writer
				<div>Content for Comment</div>
				</div>
				<span>reg_date</span>
			</li>
		</ul>
	</div>
	<script type="text/javascript">
		const bnoVal = `<c:out value="${board.bno}"/>`;
		console.log("bno : "+bnoVal);
	</script>
	<script type="text/javascript" src="/resources/js/boardComment.js"></script>
	<script type="text/javascript">
		getCommentList(bnoVal);
	</script>
</body>
</html>