<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 결과</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<link href="join.css" rel="stylesheet">
</head>
<body>
<div class="container">
	<main class="form-signin w-100 m-auto">
		<div class="container">
			<figure class="text-center">
				<h3 class="text-dark">Little and Precious</h3>
				<h4 class="text-dark">비밀번호 찾기 결과</h4>
				<c:if test="${pw != null}">
 					 <label>귀하의 아이디는 ${pw} 입니다.</label>
 					 <button class="w-100 btn btn-lg btn-primary" type="button" onclick="id()">아이디 찾기</button><br/><br/>
					<button class="w-100 btn btn-lg btn-primary" type="button" onclick="cancel()" >돌아가기</button>
				</c:if>
				<c:if test="${pw == null}">
 					 <label>찾으시는 비밀번호가 없습니다.</label>
 					 <label>입력하신 정보를 다시 확인해주세요</label>
 					 <button class="w-100 btn btn-lg btn-primary" type="button" onclick="join()">회원가입 하기</button><br/><br/>
					<button class="w-100 btn btn-lg btn-primary" type="button" onclick="cancel()" >돌아가기</button>
				</c:if>				
			</figure>
			

		</div>
		
	</main>	
</div>
<script type="text/javascript">
	function cancel() {
	  window.location.href = "login.jsp";
	}
	
	function join() {
		  window.location.href = "join.jsp";
	}
	
	function id() {
		  window.location.href = "FindId.jsp";
	}

	
</script>


<%@ include file="bottom.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</body>
</html>