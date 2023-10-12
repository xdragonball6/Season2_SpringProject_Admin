<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<link href="join.css" rel="stylesheet">
</head>
<body>
<div class="container">
	<main class="form-signin w-100 m-auto">
		<div class="container">
			<figure class="text-center">
				<h3 class="text-dark">Little and Precious</h3>
				<h3 class="text-dark">비밀번호 찾기</h3>
			</figure>
			
		</div>
	<form action="pindPwview.do" name="pindPw" method="post">
			<label>아이디</label>
				<input type="email" class="form-control" name="cid" id="cid" placeholder="name@example.com">
			<label>이름</label>
				<input type="text" class="form-control" name="cname" id="cname" placeholder="이름"><br/>
		<button class="w-100 btn btn-lg btn-primary" type="submit" onclick="checkForm(event)">비밀번호 찾기</button><br/><br/>
		<button class="w-100 btn btn-lg btn-primary" type="button" onclick="cancel()" >돌아가기</button>
	</form>
</main>
</div>

	<script type="text/javascript">
	function checkForm(event) {
		event.preventDefault();
		
		// 이름 정규식 패턴
		const namePattern = /^[가-힣]{2,}$/;
		
		// 전화번호 정규식 패턴
		const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
		
		const form = document.pindPw
		const email = form.cid.value;
		const name = form.cname.value;
		
		  // 이메일 입력값 검사
		  if (email.length == 0) {
		    alert("이메일 주소를 입력해주세요.");
		    return false;
		  }
		  if (!emailPattern.test(email)) {
		    alert("유효한 이메일 주소를 입력해주세요.");
		    return false;
		  }
	
		  // 이름 입력값 검사
		  if (name.length == 0) {
		    alert("이름을 입력해주세요.");
		    return false;
		  }
		  if (!namePattern.test(name)) {
		    alert("유효한 이름을 입력해주세요.");
		    return false;
		  }
		
		  form.submit()
	}

</script>


<script type="text/javascript">
	function cancel() {
	  window.location.href = "login.jsp";
	}
</script>
<%@ include file="bottom.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</body>
</html>