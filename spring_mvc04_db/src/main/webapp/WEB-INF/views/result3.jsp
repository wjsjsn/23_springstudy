<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카카오 로그인</title>
<style type="text/css">
	
table {
    width: 600px;
    margin-top: 50px;
    border-collapse: collapse;
    border: 50px;
    margin: auto;
}
table, th, td {
    border: 1px solid tomato;
    text-align: center;
}

#title{
	background-color: snow;
}
</style>
</head>
<body>
	<div>
		<header>
			<h1>개인정보</h1>
		</header>
		<article>
			<h2>${nickname}</h2>
			<h2>${email}</h2>
			<img src="${profile_image}">
		</article>
	</div>
</body>
</html>