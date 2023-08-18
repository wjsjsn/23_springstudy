<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function result_go(f) {
		f.countnum.value = f.amount.value;
		f.action = "/result4.do";
		f.submit();
	}
</script>
</head>
<body>
	<%-- 
		트랜잭션 : 데이터 베이스를 처리할 때 여러 개의 작업을 하나의 작업 단위로 묶어서 처리하는 것
		         더 이상 쪼갤 수 없는 업무 처리의 최소 단위
		         전체가 수행되거나 전체가 수행되지 않아야 함(all or nothing)
		         일부라도 오류가 발생하면 전체가 다 롤백해야 함
		         
		root-context.xml에 spring transaction manager를 설정해야됨
		
		create table card(
			customerId varchar(4000),
			amount varchar(4000)
			);
			
		create table ticket(
			customerId varchar(4000),
			countnum   varchar(4000) check(countnum <5) 
        	);
        	
        	티켓 구매 수를 5개 미만으로 구매해야 함
        	5개 이상 구매 시 트랜잭션 처리
	 --%>
	<h2> 결제 하기 </h2>
    <form method="post">
        <p> 고객 ID : <input type="text" name="customerId"></p>
        <p> 티켓 구매수 : <input type="number" name="amount"></p>
        <input type="hidden" name="countnum" value="">
        <input type="button" value="구매하기" onclick="result_go(this.form)">
        <input type="reset" value="취소하기">
    </form>
</body>
</html>