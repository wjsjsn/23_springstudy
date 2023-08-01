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
	function save_go(f) {
		// 자바 스크립트에서도 EL 사용 가능
		var k = "${gvo.pwd}";
		if(f.pwd.value == k){
		f.action="/guestbook_updateOk.do";
		f.submit();			
		}else {
			alert("비밀번호가 틀립니다.");
			f.pwd.value="";
			f.pwd.focus();
			return;
		}
	}
</script>
</head>
<body>
	<div>
		<h2>방명록 : 수정화면</h2>
		<hr />
		<p>[<a href="/guestbook_list.do">목록으로 이동</a>]</p>
		<form method="post">
			<table>
				<tr align="center">
					<td bgcolor="lavender">작성자</td>
					<td><input type="text" name="name" size ="20" value="${gvo.name}"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="lavender">제  목</td>
					<td><input type="text" name="subject" size ="20" value="${gvo.subject}"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="lavender">email</td>
					<td><input type="text" name="email" size ="20" value="${gvo.email}"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="lavender">비밀번호</td>
					<td><input type="password" name="pwd" size ="20"/></td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<textarea rows="10" cols="60" name="content" >${gvo.content}</textarea>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="hidden" value="updateOk.do" name="cmd">
							<input type="hidden" value="${gvo.idx}" name="idx">							
							<input type="button" value="저장" onclick="save_go(this.form)" />
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