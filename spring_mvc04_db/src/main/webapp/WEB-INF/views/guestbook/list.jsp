<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
a {
	text-decoration: none;
}

table {
	width: 600px;
	border-collapse: collapse;
	text-align: center;
	margin: auto;
	padding: 3px;
}

table, th, td {
	border: 1px solid tomato;
	padding: 3px;
}

div {
	width: 600px; 
	text-align : center;
	margin: auto;
	}
	
#number {
	background-color: snow;
	}
</style>
</head>
<body>
<div>
	<h2>방명록 리스트</h2>
	<hr style="border: 1px solid tomato;">
		<p>[<a href="/guestbook_write.do">방명록 쓰기</a>]</p>
	<table>
		<thead>
			<tr style="background-color: snow;">
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty list}">
					<tr>
						<td colspan="4"><h2>원하는 정보가 존재하지 않습니다.</h2></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="k" items="${list}" varStatus="vs">
						<tr>
							<td id="number">${vs.count}</td>
							<td>${k.name}</td>
							<td><a href="guestbook_onelist.do?idx=${k.idx}">${k.subject}</a></td>
							<td>${k.regdate.substring(0, 10)}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	</div>
</body>
</html>