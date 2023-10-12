<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>My Page</title>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="join.css" rel="stylesheet">


<style>
   .mb-3 {
	margin: 0 auto;
	width: 800px;
	margin-bottom: 20px;
	align-items: center;
	text-align: left;
	
  }
   .custom-nav {
	float: left;
	width: 25%;
    margin-right: 0px;
    
  }

  
</style>

</head>
<body>

<div class="container">
  <div class="row justify-content-center">
    <div class="col-4 custom-nav">
      <nav>
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link" href="mypageview.do">회원정보 수정</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="PwChange.jsp">비밀번호 변경</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="myreview.do?cid=${cid}">나의 리뷰</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="orderinglist.do?cid=${cid }">주문 목록</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#memberModal">회원 탈퇴</a>

          </li>
        </ul>
      </nav>
    </div>
    
    <div class="col-8">
      <main class="form-signin">
        <div class="container">
          <figure class="text-center">
            <h3 class="text-dark">Little and Precious</h3>
            <h3 class="text-dark">회원정보 수정</h3>
          </figure>
        </div>
		
		
			<form id="member-form" name="member" method="post" >
			<c:forEach items="${list }" var="dto">
			    <label>*수정을 원하시면 비밀번호를 입력해주세요!!</label><br/>
				<label>아이디</label>
				
					<input type="email" class="form-control" name="cid" id="cid" value="${dto.cid }"  readonly>
<!-- 					<input type="button" value="중복확인" name="emailcheak" onclick="checkDuplicate()"> -->
				
				<label>비밀번호</label>
				<div class="input-group">
					<input type="password" class="form-control" name="cpassword" id="cpassword" placeholder="비밀번호">
					<input type="button" value="확인" name="pwcheak" onclick="pwCk()">
				</div>

				<script type="text/javascript">
					function pwCk(){
						const cpassword = '${dto.cpassword}'
						const form = document.member
						const mpassword = form.cpassword.value
						
					    if (cpassword === mpassword) {
					        alert("비밀번호가 일치합니다.");
					        form.cpassword.readOnly = true;
					        form.cname.readOnly = false;
					        form.cphone.readOnly = false;
					        form.cgender.forEach((radio) => {
					            radio.disabled = false;
					        });
					        form.addrBtn.disabled = false;
					        form.caddress2.readOnly = false;
					        form.cbirthdate.readOnly = false;
					        form.memberUp.disabled = false;
					    } else {
					        alert("비밀번호가 일치하지 않습니다.");
					    }
					}
				
				</script>
				
				<label>이름</label>
					<input type="text" class="form-control" name="cname" id="cname" value="${dto.cname }" placeholder="이름" readonly>
				<label>전화번호</label>
					<input type="text" class="form-control" name="cphone" id="cphone" value="${dto.cphone }" placeholder="휴대폰 번호 입력('-'포함해서 입력해주세요)" readonly>
				<label>성별</label><br/>
					<input type="radio" name="cgender" value="male" ${dto.cgender == 'male' ? 'checked' : ''} disabled> 남자<br/>
					<input type="radio" name="cgender" value="female" ${dto.cgender == 'female' ? 'checked' : ''} disabled> 여자<br/>

				<label>주소</label>
				<div class="input-group">
					<input type="text" class="form-control" name="cpostnum" id="sample6_postcode" value="${dto.cpostnum }" placeholder="우편번호" readonly>
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" name="addrBtn" disabled>
				</div>
				<br>
					<input type="text" class="form-control" name="caddress1" id="sample6_address" value="${dto.caddress1 }" placeholder="주소" readonly><br>
					<input type="text" class="form-control" name="caddress2" id="sample6_detailAddress"value="${dto.caddress2 }" placeholder="상세주소" readonly>
					<input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
				<label>생년월일</label>
					<input type="date" class="form-control" name="cbirthdate" min="1930-01-01" max="2050-12-31" value="${dto.cbirthdate }" readonly><br/>
			</c:forEach>
					<button class="w-100 btn btn-lg btn-primary" name="memberUp" type="submit" onclick="checkForm(event)" disabled>확인</button>
			</form>
			
		<div class="modal" id="memberModal">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-body text-center">
		        <h5 class="modal-title fw-bold">회원 탈퇴</h5>
		        <p>회원 탈퇴를 진행하시면 아래와 같은 내용이 적용됩니다:</p>
		        <ul class="list-unstyled text-center">
		          <li><strong>계정과 관련된 모든 개인정보가 삭제됩니다.</strong></li>
		          <li><strong>저장된 데이터, 포스트, 댓글 등 모든 활동 내역이 삭제됩니다.</strong></li>
		          <li><strong>탈퇴 후에는 이전에 사용했던 계정으로 다시 로그인할 수 없습니다.</strong></li>
		        </ul>
		        <p>회원 탈퇴를 진행하시겠습니까?</p>
		      </div>
		      <div class="modal-footer border-0">
		        <button type="button" class="btn btn-secondary rounded-pill" data-bs-dismiss="modal">취소</button>
		        <button type="button" class="btn btn-danger rounded-pill" onclick="memberDraw()">회원 탈퇴</button>
		      </div>
    		</div>
  		  </div>
		</div>


		
		</main>
		</div>
	</div>
</div>



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

<script type="text/javascript">
<!-- 정규식 검사 -->

	
function checkForm(event) {
	event.preventDefault();

	
	// 비밀번호 정규식 패턴
	const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
	
	// 이름 정규식 패턴
	const namePattern = /^[가-힣]{2,}$/;
	
	// 전화번호 정규식 패턴
	const phonePattern = /^\d{3}-\d{3,4}-\d{4}$/;
	
	// 우편번호 정규식 패턴
	const postcodePattern = /^\d{5}$/;
	
	// 폼 내용 가져오기
	const form = document.member;
	const name = form.cname.value;
	const phone = form.cphone.value;
	const genderInputs = form.cgender;
	const cpostnum = form.cpostnum.value;
	const caddress1 = form.caddress1.value;
	const caddress2 = form.caddress2.value;
	const cbirth = form.cbirthdate.value;



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
	  const form = document.member;
	  const email = form.cid.value;
	  const password = form.cpassword.value;
	  const name = form.cname.value;
	  const phone = form.cphone.value;
	  const genderInputs = form.cgender.value;
	  const cpostnum = form.cpostnum.value;
	  const caddress1 = form.caddress1.value;
	  const caddress2 = form.caddress2.value;
	  const cbirth = form.cbirthdate.value;

	  var xhr = new XMLHttpRequest();
	  xhr.open("POST", "member.mb", true);
	  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  xhr.onreadystatechange = function() {
	    if (xhr.readyState === XMLHttpRequest.DONE) {
	      if (xhr.status === 200) {
	        alert("회원정보가 수정되었습니다.");
	        window.location.href = "mypageview.do";
	      } else {
	        alert("회원정보 수정 오류");
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

<script type="text/javascript">
function memberDraw() {
  const form = document.member;
  const email = form.cid.value;
  
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "memberDraw.dw", true); 
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
      if (xhr.readyState === XMLHttpRequest.DONE) {
        if (xhr.status === 200) {
          alert("회원 탈퇴 되었습니다.");
          window.location.href = "logout.do";
        } else {
          alert("회원탈퇴 오류");
        }
      }
    }
  };
  xhr.send("email=" + encodeURIComponent(email)); // 이메일 값을 요청에 포함시킵니다
}
</script>



 <%@ include file="bottom.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>

