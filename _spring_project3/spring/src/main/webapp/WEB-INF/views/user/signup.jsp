<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>

<div>
	<form action="/user/signup" method="post">
		<input type="text" name="id" placeholder="아이디"> <br>
		<input type="password" name="pw" placeholder="비밀번호"> <br>
		<input type="text" name="name" placeholder="이름"> <br>
		<input type="text" name="email" placeholder="이메일"> <br>
		<input type="text" name="home" placeholder="주소"> <br>
		<input type="text" name="age" placeholder="나이"> <br>
		<button>회원가입</button>
	</form>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>