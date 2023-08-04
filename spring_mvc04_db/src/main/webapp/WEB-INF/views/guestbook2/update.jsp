<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방 명 록 </title>
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
	a { text-decoration: none;}
	table{width: 800px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 800px; margin:auto; text-align: center;}
</style>
<script type="text/javascript">
	function save_go(f) {
			f.action="/guestbook2_updateOk.do";
			f.submit();
	}
</script>
</head>
<body>
	${alertScript}
	<div>
		<h2>방명록 : 수정화면</h2>
		<hr />
		<p>[<a href="/guestbook2_list.do">목록으로 이동</a>]</p>
		<!-- 파일 첨부를 하려면 -->
		<form method="post" enctype="multipart/form-data">
			<table>
				<tr align="center">
					<td bgcolor="lightyellow">작성자</td>
					<td><input type="text" name="name" value="${gvo.name }" size ="20"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="lightyellow">제  목</td>
					<td><input type="text" name="subject"  value="${gvo.subject }" size ="20"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="lightyellow">email</td>
					<td><input type="text" name="email"  value="${gvo.email }" size ="20"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="lightyellow">비밀번호</td>
					<td><input type="password" name="pwd" size ="20"/></td>
				</tr>
				<tr align="center">
					<td bgcolor="lightyellow">첨부파일</td>
					<c:choose>
						<c:when test="${empty gvo.f_name }">
							<td><input type="file" name="file"><b>이전 파일 없음</b></td>		
							<input type="hidden" name="f_name" value="${gvo.f_name }">
						
						</c:when>
						<c:otherwise>
							<td><input type="file" name="file"><b>이전 파일명 : (${gvo.f_name })</b></td>
								<input type="hidden" name="f_name" value="${gvo.f_name }">
						</c:otherwise>
					</c:choose>
				</tr>
				<tr align="center">
					<td colspan="2">
						<textarea rows="10" cols="60" name="content" id="content">${gvo.content }</textarea>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="hidden" value="${gvo.idx}" name="idx">			
							<input type="button" value="수정" onclick="save_go(this.form)" />
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