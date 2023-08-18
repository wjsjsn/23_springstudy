<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weather</title>
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
	<table>
		<thead>
			<tr id="title">
				<td>시·도별</td><td>총인구 (명)</td><td>1차 접종 누계</td><td>1차 접종 퍼센트</td><td>2차 접종 누계</td><td>2차 접종 퍼센트</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="k"> 
				<tr>
					<td>${k.city}</td>
					<td><fmt:formatNumber value="${k.totalCount}" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${k.firstCount}" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${k.firstPercent}" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${k.secondCount}" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${k.secondPercent}" pattern="#,###.##" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>