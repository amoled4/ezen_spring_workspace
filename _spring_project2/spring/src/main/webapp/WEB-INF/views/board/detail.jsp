<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../layout/header.jsp"></jsp:include>
<c:set var="board" value="${boardDTO.bvo }"></c:set>
<div id="detail-wrap">
	<c:if test="${ses.id == board.writer }">
	<a href="/board/modify?bno=${board.bno }"><button type="button" class="detail-btn">수정</button></a>
	<a href="/board/remove?bno=${board.bno }"><button type="button" class="detail-btn">삭제</button></a>
	</c:if>
	<a href="/board/list"><button type="button" class="detail-btn">목록</button></a>

	<table id="detail-table">
		<tr>
			<th class="detail-th">제목</th>
			<td class="detail-td">${board.title }</td>
		</tr>
		<tr>
			<th class="detail-th">작성자</th>
			<td class="detail-td">${board.writer }</td>
		</tr>
		<tr>
			<th class="detail-th">조회수</th>
			<td class="detail-td">${board.read_count }</td>
		</tr>
		<tr>
			<th class="detail-th">내용</th>
			<td class="detail-td">
			<c:set var="flist" value="${boardDTO.flist }"></c:set>
			<ul>
				<c:forEach items="${flist }" var="fvo">
					<li>
						<c:choose>
							<c:when test="${fvo.file_type > 0 }">
								<img alt="없음" src="/upload/${fn:replace(fvo.save_dir,'\\', '/') }/${fvo.uuid}_${fvo.file_name}">
							</c:when>
							<c:otherwise>
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
	<br><br><br>
	<div>
	<p>
		<span id="cmtWriter">${ses.id }</span>
		<textarea rows="3" cols="50" id="cmtText" placeholder="댓글을 작성해 주세요."></textarea>
		<button type="button" id="cmtPostBtn">등록</button>
	</p>
	</div>
	<br><br><br>
	<div>
		<ul id="cmtListArea">
			<li>
				<div>Writer
				<div>Content for Comment</div>
				</div>
				<span>reg_date</span>
			</li>
		</ul>
	</div>
</div>
	<script type="text/javascript">
		const bnoVal = `<c:out value="${board.bno}"/>`;
		console.log("bno : "+bnoVal);
	</script>
	<script type="text/javascript" src="/resources/js/boardComment.js"></script>
	<script type="text/javascript">
		getCommentList(bnoVal);
	</script>
<jsp:include page="../layout/footer.jsp"></jsp:include>