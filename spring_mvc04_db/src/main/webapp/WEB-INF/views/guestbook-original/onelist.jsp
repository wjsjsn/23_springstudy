<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	a { text-decoration: none;}
	table{width: 600px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 600px; margin:auto; text-align: center;}
</style>
<script type="text/javascript">
	function edit_go(f) {
		f.action="/guestbook_update.do?idx=${gvo.idx}";
		f.submit();
	}
	
	function delete_go(f) {
		f.action="/guestbook_delete.do";
		f.submit();
	}
</script>
</head>
<body>
	<div>
		<h2>방명록 : 작성화면</h2>
		<hr />
		<p>[<a href="/guestbook_list.do">목록으로 이동</a>]</p>
		<form method="post">
			<table>
				<tr align="center">
					<td bgcolor="lavender">작성자</td>
					<td>${gvo.name}</td>
				</tr>
				<tr align="center">
					<td bgcolor="lavender">제  목</td>
					<td>${gvo.subject}</td>
				</tr>
				<tr align="center">
					<td bgcolor="lavender">email</td>
					<td>${gvo.email}</td>
				</tr>
				<tr align="center">
					<td colspan="2" style="text-align: left; padding-left: 10px; "><pre>${gvo.content}</pre>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
						<!-- 수정/삭제를 위해 idx 넘기기 -->
						<input type="hidden" name="idx" value="${gvo.idx}"> 
							<input type="button" value="수정" onclick="edit_go(this.form)" />
							<input type="button" value="삭제" onclick="delete_go(this.form)" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="취소" />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>