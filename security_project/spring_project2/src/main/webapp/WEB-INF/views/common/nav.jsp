<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header class="p-3 bg-dark text-white">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
          <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
        </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="/" class="nav-link px-2 text-secondary">Home</a></li>
          <li><a href="/" class="nav-link px-2 text-white">Products</a></li>
          <li><a href="/board/list" class="nav-link px-2 text-white">Board</a></li>
          <li><a href="/board/register" class="nav-link px-2 text-white">BoardREG</a></li>
          <li>${principal}</li>
         <!-- 표현식이 지정한 권한에 맞을 때만 출력 -->
<sec:authorize access="isAuthenticated()">
	<!-- 현재 인증한 사용자의 정보를 가져오는 태그입니다. 스프링 Security 구조대로 짰다면 손쉽게 태그를 이용해 가져올 수 있습니다. 아래와 같이 프로퍼티를 principal로 주고 변수에 담아주면 자바 코드에서 getProperty()를 실행한것과 동일합니다. 즉 사용자 정보를 담고 있는 UserDetails 객체가 반환됩니다. -->
<sec:authentication property="principal.mvo.email" var="authEmail"/>
<sec:authentication property="principal.mvo.nickName" var="authNick"/>
<sec:authentication property="principal.mvo.authList" var="auths"/>
<c:choose>
	<c:when test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
		<li><a href="/member/list"
		 class="nav-link px-2 text-white">${authNick }(${authEmail })</a></li>
	</c:when>
	<c:otherwise>
		<li><a href="/member/detail?email=${authEmail }"
		 class="nav-link px-2 text-white">${authNick }(${authEmail })</a></li>
	</c:otherwise>
</c:choose>
		<li><a href="" id="logoutLink" class="nav-link px-2 text-white">Logout</a></li>
		<form action="/member/logout" method="post" id="logoutForm">
			<input type="hidden" name="email" value="${authEmail }">
		</form>
</sec:authorize>   
<sec:authorize access="isAnonymous()">       
          <li><a href="/member/register" class="nav-link px-2 text-white">Sign Up</a></li>
          <li><a href="/member/login" class="nav-link px-2 text-white">Log In</a></li>
</sec:authorize>
        </ul>
      </div>
    </div>
  </header>
<script>
	document.getElementById('logoutLink').addEventListener('click', (e) => {
		e.preventDefault();
		document.getElementById('logoutForm').submit();
	});
</script>  