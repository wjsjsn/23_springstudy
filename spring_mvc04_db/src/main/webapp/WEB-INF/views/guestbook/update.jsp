<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
	a { text-decoration: none;}
	table{width: 800px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid tomato; padding: 3px}
	div{width: 800px; margin:auto; text-align: center;}
	.note-btn-group{width: auto;}
	.note-toolbar{width: auto;}
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
		<hr style="border: 1px solid tomato;">
		<p>[<a href="/guestbook_list.do">목록으로 이동</a>]</p>
		<form method="post">
			<table>
				<tr align="center">
					<td bgcolor="snow">작성자</td>
					<td><input type="text" name="name" size ="20" value="${gvo.name}"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="snow">제  목</td>
					<td><input type="text" name="subject" size ="20" value="${gvo.subject}"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="snow">email</td>
					<td><input type="text" name="email" size ="20" value="${gvo.email}"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="snow">비밀번호</td>
					<td><input type="password" name="pwd" size ="20"/></td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<textarea rows="10" cols="60" name="content" id="content">${gvo.content}</textarea>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="hidden" value="${gvo.idx}" name="idx">							
							<input type="button" style="background-color: lightyellow"  value="저장" onclick="save_go(this.form)" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" style="background-color: lightyellow"  value="취소" />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" crossorigin="anonymous"></script>
    <script src="resources/js/summernote-lite.js"></script>
    <script src="resources/js/lang/summernote-ko-KR.js"></script>
    <script type="text/javascript">
    	$(function() {
			$('#content').summernote({
				lang:"ko-KR",
				height:300,
				focus:true,
				 callbacks:{
					onImageUpload : function(files, editor) {
						for (var i = 0; i < files.length; i++) {
							sendImage(files[i], editor)
						}
					}
				} 
			});
		});
    	
    	function sendImage(file, editor) {
			var frm = new FormData();
			frm.append("s_file", file);
			$.ajax({
				url : "/saveImg.do",
				data : frm,
				type : "post",
				contentType : false,
				processData : false,
				dataType : "json",
				cache : false,
			}).done(function(data){
				var path = data.path; // 이미지가 저장된 경로
				var fname = data.fname; // 파일명
				$("#content").summernote("editor.insertImage", path + "/" + fname);
			});
		}
    </script>
</body>
</html>