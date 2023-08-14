<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	margin: 10px auto;
	width: 600;
	border-collapse: collapse;
	font-size: 12pt;
	border-color: gray;
}

table, th, td {
	border: 1px solid gray;
}
</style>
</head>
<body>
<jsp:include page="top.jsp" />
<hr>
	<table>
		<thead>
			<tr bgcolor="lightyellow">
				<th width="10%">제품번호</th>
				<th width="10%">이미지</th>
				<th width="35%">제품명</th>
				<th width="20%">제품가격</th>
				<th width="25%">비고</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty list}">
					<tr>
						<td colspan="5"><h2>제품 준비 중입니다.</h2></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="k" items="${list}">
						<tr>
							<td>${k.p_num}</td>
							<td><img src="/resources/images/${k.p_image_s}" width="100"></td>
							<td><a href="/shop_onelist.do?idx=${k.idx}">${k.p_name}</a></td>
							<td>할인가 : <fmt:formatNumber value="${k.p_saleprice}" pattern="#,##0"/>원<br>
												<font color="tomato">(할인율 : ${k.getPercent()}%)</font>
							</td>
							<td>시중 가격 : <fmt:formatNumber value="${k.p_price}" pattern="#,##0"/>원<br></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
	</tbody>
	</table>
</body>
</html>