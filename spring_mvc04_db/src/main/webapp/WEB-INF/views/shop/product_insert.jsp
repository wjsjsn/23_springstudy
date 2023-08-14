<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	width: 400px;
	text-align: left;
	margin: auto;
}

table, tr, td {
	border: 1px solid black;
	padding: 3px;
	text-align: left;
}

div {
	width: 400px;
	margin: auto;
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="top.jsp" />
	<div>
		<form method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>분류</td>
					<td><input type="radio" value="1">컴퓨터 <input
						type="radio" value="2">가전제품 <input type="radio" value="3">스포츠
					</td>
				</tr>
				<tr>
					<td>제품번호</td>
					<td><input type="text"></td>
				</tr>
				<tr>
					<td>제품명</td>
					<td><input type="text"></td>
				</tr>
				<tr>
					<td>판매사</td>
					<td><input type="text"></td>
				</tr>
				<tr>
					<td>상품가격</td>
					<td><input type="text"></td>
				</tr>
				<tr>
					<td>할인가</td>
					<td><input type="text"></td>
				</tr>
				<tr>
					<td>상품이미지s</td>
					<td style="text-align: right;"><input type="button"
						value="찾아보기..."></td>
				</tr>
				<tr>
					<td>상품이미지L</td>
					<td style="text-align: right;"><input type="button"
						value="찾아보기..."></td>
				</tr>
				<tr>
					<td>상품내용</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="10" cols="40" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="상품등록"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>