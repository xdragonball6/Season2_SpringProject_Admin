<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<link href="join.css" rel="stylesheet">

</head>
<body>

<div class="container">
	<main class="form-signin w-100 m-auto">
		<div class="container">
			<figure class="text-center">
				<h3 class="text-dark">Little and Precious</h3>
				<h3 class="text-dark">회원가입</h3>
			</figure>
			
		</div>
		
		
			<form id="join-form" name="join" method="post">
				<label>아이디</label>
				<c:if test="${kakao != 0}">
					<div class="input-group">
						<input type="email" class="form-control" name="cid" id="cid" placeholder="name@example.com" value="${jid }">
						<input type="button" value="중복확인" name="emailcheak" onclick="checkDuplicate()">
					</div> 
				</c:if>
				<c:if test="${kakao == 0}">
					<div class="input-group">
						<input type="email" class="form-control" name="cid" id="cid" placeholder="name@example.com">
						<input type="button" value="중복확인" name="emailcheak" onclick="checkDuplicate()">
				</div> 
				</c:if>
				<div id="verificationDiv" style="display: none;">
 					<input type="text" class="form-control" name="verificationCodeCk" id="verificationCodeCk" placeholder="인증번호">
 					<input type="button" value="인증번호확인" name="codeCheck" onclick="checkVerification()">

				</div>
				<label>비밀번호</label>
					<input type="password" class="form-control" name="cpassword" id="cpassword" placeholder="비밀번호">
					<input type="password" class="form-control" name="passwordcheak" id="passwordcheak" placeholder="비밀번호 확인">
				<p class="text-end" id="passwordMatchMessage"style="color: red" ></p>
				<label>이름</label>
				<c:if test="${kakao != 0}">
					<input type="text" class="form-control" name="cname" id="cname" placeholder="이름" value="${jname }">
				</c:if>
				<c:if test="${kakao == 0}">
					<input type="text" class="form-control" name="cname" id="cname" placeholder="이름">
				</c:if>
				<label>전화번호</label>
					<input type="text" class="form-control" name="cphone" id="cphone" placeholder="휴대폰 번호 입력('-'포함해서 입력해주세요)">
				<label>성별</label><br/>
					  <input type="radio" name="cgender" value="male" > 남자<br/>
					  <input type="radio" name="cgender" value="female"> 여자<br/>
				<label>주소</label>
				<div class="input-group">
					<input type="text" class="form-control" name="cpostnum" id="sample6_postcode" placeholder="우편번호" readonly>
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
				</div>
				<br>
					<input type="text" class="form-control" name="caddress1" id="sample6_address" placeholder="주소" readonly><br>
					<input type="text" class="form-control" name="caddress2" id="sample6_detailAddress" placeholder="상세주소">
					<input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
				<label>생년월일</label>
					<input type="date" class="form-control" name="cbirth" id="myDateInput"><br/>
					<button class="w-100 btn btn-lg btn-primary" type="submit" onclick="checkForm(event)" >회원가입</button>
			
			</form>
	
	</main>
</div>
<%@ include file="bottom.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<!-- 오늘 날짜까지만 선택할수 있다 -->
<script type="text/javascript">

	const today = new Date();
	const year = today.getFullYear();
	let month = today.getMonth() + 1;
	let day = today.getDate();
	
	month = month < 10 ? '0' + month : month;
	day = day < 10 ? '0' + day : day;
	
	const maxDate = `${year}-${month}-${day}`;
	const dateInput = document.getElementById("myDateInput");
	
	dateInput.addEventListener("input", function() {
	  const selectedDate = new Date(this.value);
	  if (selectedDate > today) {
		alert("현재일 이후 날짜는 선택하실수 없습니다.")
	    this.value = maxDate;
	  }
	});

</script>

<!-- 비밀번호 체크  -->
<script>
	// 비밀번호 입력란과 비밀번호 확인 입력란의 값을 실시간으로 비교하여 일치 여부를 체크하는 함수
	function checkPasswordMatch() {
		var password = document.getElementById("cpassword").value;
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
</script>

<!-- 주소 api  -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>



<!-- 이메일 중복체크 -->
<script type="text/javascript">
	var isEmailChecked = false;
	
	function checkDuplicate() {
	  // 중복 체크를 수행할 이메일 값을 가져옵니다
	  const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	  const form = document.join
	  const email = form.cid.value
	  if (!emailPattern.test(email)) {
		    alert("유효한 이메일 주소를 입력해주세요.");
		    return false;
		  }
	  // Ajax 요청을 보냅니다
	  var xhr = new XMLHttpRequest();
	  xhr.open("POST", "Email.ch", true); // 중복 체크를 수행할 URL을 지정합니다
	  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  xhr.onreadystatechange = function () {
	    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
	      // Ajax 요청이 성공적으로 완료되었을 때 수행할 동작을 작성합니다
	      var response = xhr.responseText;
	      // 중복 체크 결과에 따라 동작을 수행합니다
	      if (response === "duplicate") {
	        alert("중복된 이메일입니다.");   
	        isEmailChecked = false;
	        form.cid.focus();
	      } else {
	    	  alert("사용 가능한 이메일입니다.");
	    	  isEmailChecked = true;
	    	  AuthenticationNum()
	    	  document.getElementById("verificationDiv").style.display = "block";
	      }
	    }
	  };
	  xhr.send("email=" + encodeURIComponent(email)); // 이메일 값을 요청에 포함시킵니다
	  
	}
	
	var verificationCode = null
	function AuthenticationNum(){
		const form = document.join
		const email = form.cid.value

		  // Ajax 요청을 보냅니다
		  var xhr = new XMLHttpRequest();
		  xhr.open("POST", "join.nu", true); // 중복 체크를 수행할 URL을 지정합니다
		  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		  xhr.onreadystatechange = function() {
		    if (xhr.readyState === XMLHttpRequest.DONE) {
		      if (xhr.status === 200) {
		        alert("인증번호가 발송 되었습니다.");
		      } else {
		        alert("이메일 인증 오류");
		        form.cid.select();
		      }
		    }
		  };
		  
		  // POST 파라미터를 생성하여 전송합니다
		  verificationCode = generateVerificationCode();
		  var params = "email=" + encodeURIComponent(email)
		    + "&sendCode=" + verificationCode;
		  
		  xhr.send(params);
		
	}
	

	function generateVerificationCode() {
		  const codeLength = 6; // 인증 번호의 길이
		  let verificationCode = '';

		  for (let i = 0; i < codeLength; i++) {
		    verificationCode += Math.floor(Math.random() * 10); // 0부터 9까지의 랜덤한 숫자를 추가합니다.
		  }

		  return verificationCode;
	}
	
</script>
<!-- 인증번호 확인  -->	
<script type="text/javascript">

var verificationCodeCheck = false
function checkVerification(){
	const form = document.join
	const usercode = form.verificationCodeCk.value
	const code = verificationCode
	
	if(usercode != code){
		alert("인증번호가 일치하지 않습니다. 다시 입력해 주세요")
	}else{
		alert("인증번호가 일치합니다.")
		verificationCodeCheck = true;
	}
	
}


</script>	
	
<!-- 정규식 검사 -->
<script type="text/javascript">	
function checkForm(event) {
	event.preventDefault();
	const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	
	// 비밀번호 정규식 패턴
	const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
	
	// 이름 정규식 패턴
	const namePattern = /^[가-힣]{2,}$/;
	
	// 전화번호 정규식 패턴
	const phonePattern = /^\d{3}-\d{3,4}-\d{4}$/;
	
	// 우편번호 정규식 패턴
	const postcodePattern = /^\d{5}$/;
	
	// 폼 내용 가져오기
	const form = document.join;
	const email = form.cid.value;
	const password = form.cpassword.value;
	const passwordcheak = form.passwordcheak.value;
	const name = form.cname.value;
	const phone = form.cphone.value;
	const genderInputs = form.cgender;
	const cpostnum = form.cpostnum.value;
	const caddress1 = form.caddress1.value;
	const caddress2 = form.caddress2.value;
	const cbirth = form.cbirth.value;

	  // 이메일 입력값 검사
	  if (email.length == 0) {
	    alert("이메일 주소를 입력해주세요.");
	    return false;
	  }
	  if (!emailPattern.test(email)) {
	    alert("유효한 이메일 주소를 입력해주세요.");
	    return false;
	  }
	  if (!isEmailChecked) {
		    alert("이메일 중복 체크를 해주세요.");
		    return false;		  
	
	  }
	  if(!verificationCodeCheck){
		    alert("인증번호를 입력해주세요");
		    return false;	
	  }

	  // 비밀번호 입력값 검사
	  if (password.length == 0) {
	    alert("비밀번호를 입력해주세요.");
	    return false;
	  }
	  if (!passwordPattern.test(password)) {
	    alert("비밀번호는 영문자, 숫자를 포함하여 8자 이상으로 설정해주세요.");
	    return false;
	  }

	  // 비밀번호 확인 검사
	  if (passwordcheak.length == 0) {
	    alert("비밀번호 확인란을 입력해주세요.");
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

	  // 전화번호 입력값 검사
	  if (phone.length == 0) {
	    alert("전화번호를 입력해주세요.");
	    return false;
	  }
	  if (!phonePattern.test(phone)) {
	    alert("유효한 전화번호를 입력해주세요. (예: 010-1234-5678)");
	    return false;
	  }

	  // 성별 입력값 검사
	  var genderChecked = false;
	  for (var i = 0; i < genderInputs.length; i++) {
	    if (genderInputs[i].checked) {
	      genderChecked = true;
	      break;
	    }
	  }
	  if (!genderChecked) {
	    alert("성별을 선택해주세요.");
	    return false;
	  }

	  // 우편번호 입력값 검사
	  if (cpostnum.length == 0) {
	    alert("우편번호를 입력해주세요.");
	    return false;
	  }
	  if (!postcodePattern.test(cpostnum)) {
	    alert("유효한 우편번호를 입력해주세요.");
	    return false;
	  }

	  if (cbirth.length == 0) {
		    alert("생년월일을 입력해주세요");
		    return false;
		  }


	  submitForm()
	}

</script>

<script type="text/javascript">
function submitForm() {
	  const form = document.join;
	  const email = form.cid.value;
	  const password = form.cpassword.value;
	  const name = form.cname.value;
	  const phone = form.cphone.value;
	  const genderInputs = form.cgender.value;
	  const cpostnum = form.cpostnum.value;
	  const caddress1 = form.caddress1.value;
	  const caddress2 = form.caddress2.value;
	  const cbirth = form.cbirth.value;

	  var xhr = new XMLHttpRequest();
	  xhr.open("POST", "joinDB.jn", true);
	  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  xhr.onreadystatechange = function() {
	    if (xhr.readyState === XMLHttpRequest.DONE) {
	      if (xhr.status === 200) {
	        alert("회원가입을 축하드립니다. 로그인을 해주세요!");
	        window.location.href = "login.jsp";
	      } else {
	        alert("회원가입 오류");
	        form.cid.select();
	      }
	    }
	  };
	  
	  // POST 파라미터를 생성하여 전송합니다
	  var params = "email=" + encodeURIComponent(email)
	    + "&password=" + encodeURIComponent(password)
	    + "&name=" + encodeURIComponent(name)
	    + "&phone=" + encodeURIComponent(phone)
	    + "&genderInputs=" + encodeURIComponent(genderInputs)
	    + "&cpostnum=" + encodeURIComponent(cpostnum)
	    + "&caddress1=" + encodeURIComponent(caddress1)
	    + "&caddress2=" + encodeURIComponent(caddress2)
	    + "&cbirth=" + encodeURIComponent(cbirth);
	  
	  xhr.send(params);
	}


</script>





</body>
</html>
