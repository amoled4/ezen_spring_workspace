<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>
	<c:set var="board" value="${boardDTO.bvo }" />
	<form action="/board/modify" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>번호</th>
				<td><input type="text" name="bno" value="${board.bno }"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${board.title }"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${board.writer }"
					readonly="readonly"></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><input type="text" name="writer"
					value="${board.read_count }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="30" name="context">${board.context }</textarea>
				</td>
			</tr>
			<tr>
				<th>파일</th>
				<td>
					<!-- file 표현 영역  --> 
					<c:set var="flist" value="${boardDTO.flist }"></c:set>
					<ul>
						<c:forEach items="${flist }" var="fvo">
							<li>
								<c:choose>
									<c:when test="${fvo.file_type > 0 }">
										<img alt="없음" src="/upload/${fn:replace(fvo.save_dir,'\\', '/') }/${fvo.uuid}_${fvo.file_name}">
										<button type="button" data-uuid="${fvo.uuid }" class="file-x">X</button>
									</c:when>
									<c:otherwise>
										<div>
											<!-- 클립 모양 같은 파일 아이콘 모양 값을 넣을 수 있음 -->
										</div>
									</c:otherwise>
								</c:choose>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr>
				<th>파일첨부</th>
				<td>
				<input type="file" id="file" name="files" multiple style="display:none">
				<button type="button" id="trigger">찾아보기</button><br>
				<div id="fileZone">
				
				</div>
				</td>
			</tr>
		</table>

		<button id="regBtn">수정완료</button>
		<a href="/board/remove?bno=${board.bno }"><button type="button">삭제</button></a>
		<a href="/board/list"><button type="button">취소</button></a>
	</form>
	<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
	<script type="text/javascript" src="/resources/js/boardModify.js"></script>
</body>
</html>