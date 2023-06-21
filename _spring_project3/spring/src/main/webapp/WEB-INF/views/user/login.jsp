<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<form action="/user/login" method="post">
<div>
	<input type="text" name="id" placeholder="아이디"> <br> 
	<input type="password" name="pw" placeholder="비밀번호"> <br> 
	<button>로그인</button>
</div>
</form>
<jsp:include page="../layout/footer.jsp"></jsp:include>