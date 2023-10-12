<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style>

	.table-container {
	  display: flex;
	  justify-content: center;
	  align-items: flex-start;
	}


    td {
        text-align: left;
        padding: 30px;
    }




</style>


<meta charset="UTF-8">
<title>구매</title>
</head>
<body>
	

<div class="container">
<div class="row">

  <div class="col-md-6">
    <h3>구매 상품</h3>
    <table style="margin-bottom: 20px;" border="1">
      <c:forEach items="${orderList}" var="dto" varStatus="status">
        <tr>
          <td>
            <img src="${dto.pfilename}" style="width: 100px; height: 100px; margin-bottom: 10px;" alt="..." />
          </td>
          <td style="width: 75%">${dto.pname}<br/>
            ${dto.pcontent} - ${dto.bqty}개<br/>
            <fmt:formatNumber value="${dto.price * dto.bqty}" pattern="#,##0원" />
          </td>
        </tr>
      </c:forEach> 	
    </table>
   </div>


   <div class="col-md-6">
    <form action="orderProduct.do" name="orderForm" id="orderForm" method="post" onsubmit="return check()">
      <h3>주문자 정보</h3>
      <c:forEach items="${list}" var="dto">
        <c:set var="name" value="${dto.cname }"></c:set>
        <c:set var="phone" value="${dto.cphone }"></c:set>
        <c:set var="id" value="${dto.cid }"></c:set>
        <c:set var="postnum" value="${dto.cpostnum  }"></c:set>
        <c:set var="address1" value="${dto.caddress1 }"></c:set>
        <c:set var="address2" value="${dto.caddress2}"></c:set>
        <input type="checkbox" id="sameInfoCheckbox" name="user"> 주문자 정보와 동일<br/>
        <div id="additionalInfo">
          <input type="text" class="form-control" name="cname" id="cname" size="20" value="${dto.cname }" placeholder="이름"><br/>
          <input type="text" class="form-control" name="cphone" id="cphone" size="20" value="${dto.cphone }" placeholder="전화번호" ><br/>
          <input type="email" class="form-control" name="cid" id="cid" size="42" value="${dto.cid }" placeholder="아이디(이메일)" readonly><br/>
          <input type="text"  class="form-control" name="cpostnum" id="sample6_postcode" placeholder="우편번호" value="${dto.cpostnum }" readonly>
          <input type="button" class="form-control" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br/>
          <input type="text"  class="form-control" name="caddress1" id="sample6_address" placeholder="주소" value="${dto.caddress1 }" readonly><br>
          <input type="text"  class="form-control" name="caddress2" id="sample6_detailAddress" placeholder="상세주소" value="${dto.caddress2 }"><br/>
          <input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
        </div>
        <br>
        배송메모<br>
        <select name="memo" class="form-control">
          <option>배송메모를 선택해주세요.</option>		
          <option>배송 전에 미리 연락 바랍니다.</option>		
          <option>부재시 경비실에 맡겨주세요.</option>		
          <option>부재시 전화나 문자를 남겨주세요.</option>		
          <option>직접입력</option>		
        </select>
      </c:forEach>
      <br/><br/>
      <h3>주문 요약</h3>
      <c:set var="totalprice" value="0"></c:set>
      <c:forEach items="${orderList}" var="dto" varStatus="status">	
        <input type="hidden" name="pid[]" value="${dto.pid }">
        <input type="hidden" name="qty[]" value="${dto.bqty }">
        <input type="hidden" name="price[]" value="${dto.price }">
        <c:set var="subtotal" value="${dto.price * dto.bqty}"></c:set>
        <c:set var="totalprice" value="${totalprice + subtotal}"></c:set>
      </c:forEach>
		  <div class="form-group">
		    <label for="productPrice">상품가격 :</label>
		    <input type="text" class="form-control" value="<fmt:formatNumber value='${totalprice}' pattern='#,##0원' />" readonly>
		  </div>
		  <div class="form-group">
		    <label for="deliveryFee">배송비 :</label>
		    <input type="text" class="form-control" value="<fmt:formatNumber value='${totalprice >= 150000 ? 0 : 3000}' pattern='#,##0원' />" readonly>
		  </div>
		  <div class="form-group">
		    <label for="totalAmount">총 주문 금액 :</label>
		    <input type="text" class="form-control" value="<fmt:formatNumber value='${totalprice + (totalprice >= 150000 ? 0 : 3000)}' pattern='#,##0원' />" readonly>
		  </div>
      <br/>
      <h3>결제수단</h3>
      <input type="radio" name="payment" checked="checked" value="신용카드"> 신용카드 
      <input type="radio" name="payment" value="무통장입금" > 무통장입금 
      <input type="radio" name="payment" value="카카오페이" > 카카오페이 
      <input type="radio" name="payment" value="삼성페이" > 삼성페이 
      <br/><br/>
      <input type="submit" class="form-control" value="구매하기">
    </form>
  </div>
  </div>
</div>


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
      
	 // 체크박스 요소를 가져옵니다.
    const checkbox = document.getElementById('sameInfoCheckbox');

    // 텍스트 필드 요소들을 가져옵니다.
    const cnameField = document.getElementById('cname');
    const cphoneField = document.getElementById('cphone');
    const cpostnumField = document.getElementById('sample6_postcode');
    const caddress1Field = document.getElementById('sample6_address');
    const caddress2Field = document.getElementById('sample6_detailAddress');

    // 체크박스의 상태가 변경될 때마다 실행되는 함수를 정의합니다.
    function toggleAdditionalInfo() {
        // 체크박스가 체크되어 있는지 확인합니다.
        if (checkbox.checked) {
            // 체크박스가 체크되어 있다면 데이터를 채웁니다.
            cnameField.value = "${name }";
            cphoneField.value = "${phone }";
            cpostnumField.value = "${postnum }";
            caddress1Field.value = "${address1 }";
            caddress2Field.value = "${address2 }";
        } else {
            // 체크박스가 체크되어 있지 않다면 데이터를 비웁니다.
            cnameField.value = "";
            cphoneField.value = "";
            cpostnumField.value = "";
            caddress1Field.value = "";
            caddress2Field.value = "";
        }
    }

    // 체크박스의 상태가 변경될 때마다 toggleAdditionalInfo 함수를 호출합니다.
    checkbox.addEventListener('change', toggleAdditionalInfo);

    // 페이지 로드 시 체크박스의 초기 상태에 따라 toggleAdditionalInfo 함수를 호출하여 데이터를 채울지 비울지 결정합니다.
    toggleAdditionalInfo();

    function check() {
    	event.preventDefault();
    	const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    	
    	// 이름 정규식 패턴
    	const namePattern = /^[가-힣]{2,}$/;
    	
    	// 전화번호 정규식 패턴
    	const phonePattern = /^\d{3}-\d{3,4}-\d{4}$/;
    	
    	// 우편번호 정규식 패턴
    	const postcodePattern = /^\d{5}$/;
    	
    	// 폼 내용 가져오기
    	const form = document.orderForm;
    	const email = form.cid.value;
    	const name = form.cname.value;
    	const phone = form.cphone.value;
    	const cpostnum = form.cpostnum.value;
    	const caddress1 = form.caddress1.value;
    	const caddress2 = form.caddress2.value;
 

 
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

    	  // 우편번호 입력값 검사
    	  if (cpostnum.length == 0) {
    	    alert("우편번호를 입력해주세요.");
    	    return false;
    	  }
    	  if (!postcodePattern.test(cpostnum)) {
    	    alert("유효한 우편번호를 입력해주세요.");
    	    return false;
    	  }


    	  
    	 
    	 var confirmed = confirm("구매하시겠습니까?");
    		  
    	 if (confirmed) {
    		 document.getElementById("orderForm").submit();
    	} else {
    		return false
    	}
    		

		  
    	  
    	}


	


</script>




	<!-- footer -->
	<%@ include file="bottom.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>