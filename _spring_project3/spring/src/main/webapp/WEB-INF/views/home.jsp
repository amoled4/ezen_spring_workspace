<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<jsp:include page="./layout/header.jsp"></jsp:include>

<c:if test="#{ses.id == null }">
	<a href="/user/login"><button type="button">로그인</button></a>
	<a href="/user/signup"><button type="button">회원가입</button></a>
</c:if>

<c:if test="${ses.id != null }">
	${ses.name }(${ses.id }) 님 환영합니다.
	<a href="/user/logout"><button>로그아웃</button></a>
	<a href="/user/modify?id=${uvo.id }"><button>정보수정</button></a>
</c:if>








<jsp:include page="./layout/footer.jsp"></jsp:include>