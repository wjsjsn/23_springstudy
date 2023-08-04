<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방 명 록 </title>
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
	a { text-decoration: none;}
	table{width: 600px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid tomato; padding: 3px;}
	div{width: 600px; margin:auto; text-align: center;}
	.note-btn-group{width: auto;}
	.note-toolbar{width: auto;}
</style>
<script type="text/javascript">
	function save_go(f) {
		if(f.name.value.trim().length <= 0){
			alert("이름을 입력하세요.");
			f.m_id.focus();
			return;
		}
		
		if(f.subject.value.trim().length <= 0){
			alert("제목을 입력하세요.");
			f.m_pw.focus();
			return;
		}
		
		if(f.content.value.trim().length <= 0){
			alert("내용을 입력하세요.");
			f.m_name.focus();
			return;
		}
		
		if(f.email.value.trim().length <= 0){
			alert("이메일을 입력하세요.");
			f.m_name.focus();
			return;
		}
		
		if(f.pwd.value.trim().length <= 0){
			alert("비밀번호를 입력하세요.");
			f.m_name.focus();
			return;
		}
		
		f.action="/guestbook2_writeOk.do";
		f.submit();
	}
</script>
</head>
<body>
	<div>
		<h2>방명록 : 작성화면</h2>
		<hr />
		<p>[<a href="/guestbook2_list.do">목록으로 이동</a>]</p>
		<!-- 파일 첨부를 하려면 -->
		<form method="post" enctype="multipart/form-data">
			<table>
				<tr align="center">
					<td bgcolor="lightyellow">작성자</td>
					<td><input type="text" name="name" size ="20"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="lightyellow">제  목</td>
					<td><input type="text" name="subject" size ="20"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="lightyellow">email</td>
					<td><input type="text" name="email" size ="20"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="lightyellow">비밀번호</td>
					<td><input type="password" name="pwd" size ="20"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="lightyellow">첨부파일</td>
					<td><input type="file" name="file" size ="20"/></td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<textarea rows="10" cols="60" name="content" id="content"></textarea>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="button" value="저장" onclick="save_go(this.form)" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="취소" />
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