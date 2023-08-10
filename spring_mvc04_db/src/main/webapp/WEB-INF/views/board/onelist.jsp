<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#board table {
	    width:580px;
	    margin:0 auto;
	    margin-top:20px;
	    border:1px solid tomato;
	    border-collapse:collapse;
	    font-size:14px;
	    
	}
	
	#board table caption {
	    font-size:20px;
	    font-weight:bold;
	    margin-bottom:10px;
	}
	
	#board table th {
	    text-align:center;
	    border:1px solid tomato;
	    padding:4px 10px;
	}
	
	#board table td {
	    text-align:left;
	    border:1px solid tomato;
	    padding:4px 10px;
	}
	
	.no {width:15%}
	.title {width:30%}
	.writer {width:20%}
	.reg {width:20%}
	.hit {width:15%}
	.title{background:snow}
	.odd {background:snow}
	
	fieldset {
	 width: 580px;
    }
    input{
    	padding: 10px;
    }
    
    th{
    	background-color: snow;
    }
</style>
<script type="text/javascript">
	function list_go(f) {
		f.action="/board_list.do";
		f.submit();
	}
	function update_go(f) {
		f.action = "/board_updateForm.do";
		f.submit();
	}
	function delete_go(f) {
		f.action = "/board_deleteForm.do";
		f.submit();
	}
	
	function comment_go(f) {
		 // 유효성 검사
		if(f.writer.value.trim().length <=0){
			alert("이름을 입력해 주세요");
			f.writer.focus();
			return;
		}
		if(f.content.value.trim().length <=0){
			alert("내용을 입력해 주세요");
			f.content.focus();
			return;
		}
		f.action = "/comment_write.do";
		f.submit();
	}
	function comment_del(f) {
		f.action = "/comment_delete.do";
		f.submit();
	}
</script>
</head>
<body>
	<div id="board">
	<form method="post">
		<table summary="게시판 글쓰기">
			<caption>게시판 글쓰기</caption>
			<tbody>
				<tr>
					<th>제목</th>
					<td>${bovo.title}</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${bovo.writer}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><pre>${bovo.content}</pre></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<c:choose>
						<c:when test="${empty bovo.f_name}">
							<td><b>첨부 파일 없음</b></td>
						</c:when>
						<c:otherwise>
							<td><a href="/board_down.do?f_name=${bovo.f_name}"><img  src="/resources/images/${bovo.f_name}" style="80px"></a></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" value="${bovo.idx}" name="idx">
						<input type="hidden" value="${page}" name="page">
						<input style="background-color: snow; outline: none; border: 1px solid tomato;" type="button" value="수정" onclick="update_go(this.form)">
						<input style="background-color: snow; outline: none; border: 1px solid tomato;"  type="button" value="삭제" onclick="delete_go(this.form)">
						<input style="background-color: snow; outline: none; border: 1px solid tomato;"  type="button" value="목록" onclick="list_go(this.form)">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
	<%-- 댓글 입력 --%>
	<div style="padding:50px; width:580px; margin: auto; ">
		<form method="post">
			<fieldset>
				<p>이름 : <input type="text" name="writer" ></p>
				<p>내용 : <br>
					<textarea rows="4" cols="40" name="content"></textarea>
				 </p>
				 <input type="button" value="댓글저장" onclick="comment_go(this.form)">
				 <input type="hidden" name="b_idx" value="${bvo.b_idx}">
				 <input type="hidden" name="page" value="${page}">
			 </fieldset>
		</form>
	</div>
	<br><br><br>
	
	<%-- 댓글 출력 --%>
	<div style="display: table;" >
		<c:forEach var="k" items="${cList}">
		 <div style="border: 1px solid gray; width: 400px; margin: 20px; padding: 20px;">
		 	<form method="post">
		 		<p>이름 : ${k.writer}</p>
		 		<p>내용 : ${k.content }</p>
		 		<p>날짜 : ${k.write_date.substring(0,10)}</p>
		 		<%-- 실제로는 로그인 성공해야 지만 삭제번트이 보여야 한다. --%>
		 		<input type="button" value="댓글삭제" onclick="comment_del(this.form)">
		 		<input type="hidden" value="${k.c_idx}" name="c_idx">
		 		<input type="hidden" value="${k.b_idx}" name="b_idx">
		 		<input type="hidden" name="page" value="${page}">
		 	</form>
		 </div>
		</c:forEach>
	</div>
</body>
</html>

