<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link href="${path }/resources/CSS/header.css" rel="stylesheet"/>
<link href="${path }/resources/CSS/all.css" rel="stylesheet"/>
<link href="${path }/resources/CSS/home.css" rel="stylesheet"/>
<link href="${path }/resources/CSS/footer.css" rel="stylesheet"/>
<link href="${path }/resources/CSS/login.css" rel="stylesheet"/>
<link href="${path }/resources/CSS/register.css" rel="stylesheet"/>
<link href="${path }/resources/CSS/signup.css" rel="stylesheet"/>
<link href="${path }/resources/CSS/list.css" rel="stylesheet"/>
<link href="${path }/resources/CSS/signupCompl.css" rel="stylesheet"/>
<link href="${path }/resources/CSS/detail.css" rel="stylesheet"/>
<link href="${path }/resources/CSS/modify.css" rel="stylesheet"/>

</head>
<body>
<div id="box">
<div id="left">
<a href="/">
<span class="material-symbols-outlined">
volume_up
</span>
<span id="mainText">수다의 민족</span></a>
</div>
<div id="right">
<c:if test="${ses.id == null }">
<a href="/member/login"><button type="button" class="headerBtn">로그인</button></a>
<a href="/member/signup"><button type="button" class="headerBtn">회원가입</button></a>
</c:if>
<c:if test="${ses.id != null }">
<span>${ses.name }(${ses.id }) 님 환영합니다.</span>
<a href="/member/logout"><button type="button" class="headerBtn">로그아웃</button></a>
</c:if>
</div>
</div>