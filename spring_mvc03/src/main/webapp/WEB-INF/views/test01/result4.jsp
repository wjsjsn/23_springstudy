<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>이미지 보기</h2>
	<h3>
		<li>이름 : ${name}</li>
		<li>파일 이름 : ${f_name}</li>
		<li>파일 타입 : ${f_type}</li>
		<li>파일 사이즈 : ${size}KB</li>
		<li>
			<a href="/down.do?f_name=${f_name}">
				<img src="resources/images/${f_name}" style="width: 300px;">
			</a>
		</li>	
	</h3>
</body>
</html>