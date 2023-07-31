<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>결과 보기</h2>
		<h3>
			<li>이름 : ${name}</li>
			<li>파일 이름(올릴 때 이름) : ${f_name}</li>
			<li>파일 이름(저장할 때 이름) : ${f_name2}</li>
			<li>파일 타입 : ${file_type}</li>
			<li>파일 사이즈 : ${size}KB</li>
			<li>파일 업로드 날짜 : ${lastday}</li>
			<img src="resources/images/${f_name2}" style="width: 100px;">
		</h3>
</body>
</html>