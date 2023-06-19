<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<div id="regi-wrap">
<form action="/board/register" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" value="${ses.id }" readonly="readonly"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="30" cols="50" name="context"></textarea></td>
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
	<button id="regBtn">작성완료</button>
	<a href="/board/list"><button type="button">취소</button></a>
	</form>
</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>