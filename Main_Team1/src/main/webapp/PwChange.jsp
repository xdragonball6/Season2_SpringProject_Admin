<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="join.css" rel="stylesheet">

</head>
<body>
<div class="container">
	<main class="form-signin w-100 m-auto">
		<div class="container">
			<figure class="text-center">
				<h3 class="text-dark">Little and Precious</h3>
				<h3 class="text-dark">비밀번호 변경</h3>
			</figure>
			
		</div>
		
		
			<form name="pwchange" method="post">
				<label>현재 비밀번호 입력</label>
					<input type="password" class="form-control" name="cpassword" id="cpassword" placeholder="현재 비밀번호"><br/>
				<label>새 비밀번호 입력</label>
					<input type="password" class="form-control" name="newpassword" id="newpassword" placeholder="세 비밀번호">
					<input type="password" class="form-control" name="passwordcheak" id="passwordcheak" placeholder="새 비밀번호 확인">
					<p class="text-end" id="passwordMatchMessage"style="color: red" ></p>

				<button class="w-100 btn btn-lg btn-primary" type="submit" onclick="checkForm(event)" >확인</button>
			
			</form>
	
	</main>
</div>


<script>
	// 비밀번호 입력란과 비밀번호 확인 입력란의 값을 실시간으로 비교하여 일치 여부를 체크하는 함수
	function checkPasswordMatch() {
		var password = document.getElementById("newpassword").value;
		var passwordcheak = document.getElementById("passwordcheak").value;
		var messageElement = document.getElementById("passwordMatchMessage");

		if (password === passwordcheak) {
			messageElement.innerHTML = "비밀번호가 일치합니다.";
		} else {
			messageElement.innerHTML = "비밀번호가 일치하지 않습니다.";
		}
	}

	// 비밀번호 확인 입력란의 값이 변경될 때마다 checkPasswordMatch 함수를 호출하여 비교 체크
	document.getElementById("passwordcheak").addEventListener("input", checkPasswordMatch);


function checkForm(event) {
	event.preventDefault();

	
	// 비밀번호 정규식 패턴
	const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
	
	
	// 폼 내용 가져오기
	const form = document.pwchange;
	const password = form.cpassword.value;
	const newpassword = form.newpassword.value;
	const passwordcheak = form.passwordcheak.value;

	  // 비밀번호 입력값 검사
	  if (password.length == 0) {
	    alert("비밀번호를 입력해주세요.");
	    return false;
	  }
	  if (!passwordPattern.test(password)) {
	    alert("비밀번호는 영문자, 숫자를 포함하여 8자 이상으로 설정해주세요.");
	    return false;
	  }
	  if (newpassword.length == 0) {
	    alert("비밀번호를 입력해주세요.");
	    return false;
	  }
	  if (!passwordPattern.test(newpassword)) {
	    alert("비밀번호는 영문자, 숫자를 포함하여 8자 이상으로 설정해주세요.");
	    return false;
	  }




	  submitForm()
	}

	
	function submitForm() {
		const form = document.pwchange;
		const password = form.cpassword.value;
		const newpassword = form.newpassword.value;
		
	  // Ajax 요청을 보냅니다
	  var xhr = new XMLHttpRequest();
	  xhr.open("POST", "pwchange.cg", true); // 중복 체크를 수행할 URL을 지정합니다
	  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  xhr.onreadystatechange = function () {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			 if (xhr.status === 200) {
			     alert("비밀번호가 변경되었습니다.");
			      window.location.href = "PwChange.jsp";
			  } else {
			      alert("비밀번호를 정확하게 입력해주세요");
			      window.location.href = "PwChange.jsp";
			  }
		}
	  };
	  
	  var params = "&password=" + encodeURIComponent(password)
	    + "&newpassword=" + encodeURIComponent(newpassword);
	  
	  xhr.send(params);
	  
	}
	
</script>

 <%@ include file="bottom.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>