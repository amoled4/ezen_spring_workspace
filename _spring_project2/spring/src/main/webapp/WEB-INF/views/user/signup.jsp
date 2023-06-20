<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<div id="sign-wrap">
	<form action="/member/signup" method="post">
		
		<input type="text" name="id" placeholder="아이디" class="sign-input"> <br>
		<input type="password" name="pw" placeholder="비밀번호" class="sign-input"> <br>
		<input type="text" name="name" placeholder="이름" class="sign-input"> <br>
		<input type="text" name="email" placeholder="이메일" class="sign-input"> <br>
		<input type="text" name="home" placeholder="주소" class="sign-input"> <br>
		<input type="text" name="age" placeholder="나이" class="sign-input"> <br>

		<button type="submit" class="sign-btn">가입완료</button>
		<a href="/"><button type="button" class="sign-btn">취소</button></a>
	</form>
</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>