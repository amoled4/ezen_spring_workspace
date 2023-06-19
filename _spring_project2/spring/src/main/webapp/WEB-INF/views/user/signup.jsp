<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<form action="/member/signup" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pw"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="home"></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="text" name="age"></td>
			</tr>
		</table>
		<button type="submit">가입완료</button>
		<a href="/"><button type="button">취소</button></a>
	</form>
<jsp:include page="../layout/footer.jsp"></jsp:include>