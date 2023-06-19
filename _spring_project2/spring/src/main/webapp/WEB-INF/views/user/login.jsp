<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>

	<form action="/member/login" method="post">
		<table>
			<tr>
				<td><input type="text" name="id" placeholder="아이디"></td>
			</tr>
			<tr>
				<td><input type="password" name="pw" placeholder="비밀번호"></td>
			</tr>
		</table>
		<button>로그인</button>
		<a href="/"><button type="button">메인</button></a>
	</form>
<jsp:include page="../layout/footer.jsp"></jsp:include>