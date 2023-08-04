<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방 명 록</title>
<style type="text/css">
a {
	text-decoration: none;
}

table {
	width: 600px;
	border-collapse: collapse;
	text-align: center;
}

table, th, td {
	border: 1px solid tomato;
	padding: 3px
}

div {
	width: 600px;
	margin: auto;
	text-align: center;
}
</style>
<script type="text/javascript">
	function delete_go(f) {		
			var chk = confirm("정말 삭제할까요?");
			if (chk) {				
				f.action = "/guestbook2_deleteOk.do";
				f.submit();
			} else {
				history.go(-1);
			}
	}
</script>
</head>
<body>
	${alertScript}
	<div>
		<h2>방명록 : 작성화면</h2>
		<hr />
		<p>
			[<a href="/guestbook2_list.do">목록으로 이동</a>]
		</p>
		<form>
			<table>
				<tr align="center">
					<td bgcolor="lightyellow">비밀번호</td>
					<td><input type="password" name="pwd" size="20" /></td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2"><input type="hidden" name="idx"
							value="${gvo.idx }"> <input type="button" value="삭제"
							onclick="delete_go(this.form)" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="취소" /></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>