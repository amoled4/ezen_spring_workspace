<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<div id="login-wrap">
	<form action="/member/login" method="post">
	<div id="input-wrap">
		<input type="text" name="id" placeholder="아이디" class="input-login"> <br>
		<input type="password" name="pw" placeholder="비밀번호" class="input-login">
	</div>
	<div id="btn-wrap">
		<button class="login-btn">로그인</button>
	</div>	
	</form>
</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>