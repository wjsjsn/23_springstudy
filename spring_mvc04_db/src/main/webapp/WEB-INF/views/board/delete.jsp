<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#bbs table {
	    width:580px;
	    margin:0 auto;
	    margin-top:20px;
	    border:1px solid gray;
	    border-collapse:collapse;
	    font-size:14px;
	    
	}
	
	#bbs table caption {
	    font-size:20px;
	    font-weight:bold;
	    margin-bottom:10px;
	}
	
	#bbs table th {
	    text-align:center;
	    border:1px solid gray;
	    padding:4px 10px;
	}
	
	#bbs table td {
	    text-align:left;
	    border:1px solid gray;
	    padding:4px 10px;
	}
	
	.no {width:15%}
	.subject {width:30%}
	.writer {width:20%}
	.reg {width:20%}
	.hit {width:15%}
	.title{background:lightyellow}
	.odd {background:silver}
</style>
<script type="text/javascript">
	function delete_ok(f) {
			var chk = confirm("정말 삭제할까요?");
			if(chk){
				f.action="/board_deleteOk.do";
				f.submit();
			}else{
				history.go(-1);
			}
	}
	
	function list_go(f) {
		f.action="/board_list.do";
		f.submit();
	}
</script>
</head>
<body>
${alertScript}
	<div id="bbs">
	<form method="post">
		<table summary="게시판 삭제하기">
			<caption>게시판 삭제하기</caption>
			<tbody>
				<tr>
					<th style="width: 40%" class="title">비밀번호 확인</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" name="idx" value="${bovo.idx}">
						<input type="hidden" name="page" value="${page}">
						<input style="background-color: lightgray; outline: none; border: 1px solid gray;"  type="button" value="삭제" onclick="delete_ok(this.form)"/>
						<input style="background-color: lightgray; outline: none; border: 1px solid gray;"  type="button" value="목록" onclick="list_go(this.form)"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
</body>
</html>
