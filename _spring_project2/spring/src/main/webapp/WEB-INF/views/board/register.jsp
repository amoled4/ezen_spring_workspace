<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<div id="regi-wrap">
<form action="/board/register" method="post" enctype="multipart/form-data">
<div id="table-wrap">
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
			<button type="button" id="trigger" class="regiBtn">찾아보기</button><br>
			<div id="fileZone">
				
			</div>
			</td>
		</tr>
	</table>
	
	<button id="regBtn" class="regiBtn">작성완료</button>
	<a href="/board/list"><button type="button" class="regiBtn">취소</button></a>
	</div>
	</form>
</div>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>