<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 보기</title>
</head>
<body>
<c:set var="board" value="${boardDTO.bvo }"></c:set>
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
			<td>
			<!-- file 표현 영역  -->
			<c:set var="fList" value="${boardDTO.fList }"></c:set>
			<ul>
				<c:forEach items="${fList }" var="fvo">
					<li>
						<c:choose>
							<c:when test="${fvo.file_type > 0 }">
								<img alt="없음" src="/upload/${fn:replace(fvo.save_dir,'\\', '/') }/${fvo.uuid}_${fvo.file_name}">
							</c:when>
							<c:otherwise>
								<div>
								<!-- 클립 모양 같은 파일 아이콘 모양 값을 넣을 수 있음 -->
								</div>
							</c:otherwise>
						</c:choose>
						<div>
							<div>${fvo.file_name }</div>
							${fvo.reg_date }
						</div>
						<span>${fvo.file_size }Byte</span>
					</li>
				</c:forEach>
			</ul>
			${board.context }
			</td>
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