<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../layout/header.jsp"></jsp:include>

<div>
	<form action="/user/modify" method="post">
	<c:forEach var="user" items="${UserVO }"></c:forEach>
		<input type="text" name="id" value="${user.id }" readonly="readonly"> <br>
		<input type="password" name="pw" value="${user.pw }"> <br>
		<input type="text" name="name" value="${user.name }"> <br>
		<input type="text" name="email" value="${user.email }"> <br>
		<input type="text" name="home" value="${user.home }"> <br>
		<input type="text" name="age" value="${user.age }"> <br>
		<button>회원정보 수정</button>
	</form>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>