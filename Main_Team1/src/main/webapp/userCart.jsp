<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }

    th {
        text-align: center;
        padding: 10px;
    }

    th:first-child {
        text-align: left;
        width: 50%;
    }

    td {
        text-align: center;
        padding: 10px;
    }

    td:first-child {
        text-align: left;
    }
	.subscript {
    	vertical-align: super;
    	font-size: smaller;
	}
  .center-align {
    display: flex;
    justify-content: center;
    align-items: center;
  }
</style>



<title>장바구니</title>
</head>
<body>
	
		<div class="container">
			<figure class="text">
				<h3 class="text-dark">장바구니</h3>
			</figure>
		</div>

<main>
<form action="" method="post">
    <table border="1">
        <tr>
            <th><input type="checkbox" id="headerCheckbox"  >&nbsp;&nbsp;&nbsp;&nbsp;상품 정보</th>
            <th>수량</th>
            <th>주문 금액</th>
            <th>배송 정보</th>
        </tr>
		<!-- 구매 리스트 사이즈  -->
		<c:set var="listSize" value="${fn:length(list)}" />


		<c:forEach items="${list}" var="dto" varStatus="status">
				
			  <tr>
			    <td><input type="checkbox" name="selectedItems" onchange="handleCheckboxChange(this)" value="${status.index + 1}" />
			    		&nbsp;&nbsp;&nbsp;&nbsp;<img src="${dto.pfilename}" style="width: 100px; height: 100px; margin-bottom: 10px;" alt="..." />
			    		&nbsp;&nbsp;&nbsp;&nbsp;${dto.pname}&nbsp;&nbsp;&nbsp;&nbsp;${dto.pcontent}
			    		<input type="hidden" name="selectedItems[]" value="${dto.bid }"></td>
			    <td>${dto.bqty}<br/><input type="hidden" value="${dto.pid }"> <button type="button" data-bs-toggle="modal" data-bs-target="#memberModal" onclick="chack1()">수량 변경 </button></td>
			    <td><fmt:formatNumber value="${dto.pprice * dto.bqty}" pattern="#,##0원" /></td>
			    <c:if test="${status.index == 0}">
			      	<td rowspan="${listSize}">배송료<br/>3,000원</td>
			    </c:if>
			  </tr>
		</c:forEach>

   	
    </table><br/>
    <button type="button" onclick="sendSelectedBids()">선택 상품 삭제</button>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <!-- <button>전체상품 삭제</button>  --> 
  
    <hr/>
    <label>총 주문 상품</label> ${listSize}<label>개</label>
    <hr/><br/>

		<figure class="text-center" style="display: flex; flex-direction: row; justify-content: center;">
		  	<div>
		    	<h3 id="myElement">0원</h3>
		    	<p>상품 금액</p>
		 	 </div>
		  	<div>
		    	<h3>&nbsp;&nbsp;&nbsp; + &nbsp;&nbsp;&nbsp;</h3>
		  	</div>
		  	<div>
		    	<h3 id="mydelivery">0원</h3>
		    	<p>배송비</p>
		  	</div>
		  	<div>
		    	<h3>&nbsp;&nbsp;&nbsp; = &nbsp;&nbsp;&nbsp;</h3>
		  	</div>
		  	<div>
		    	<h3 id="mytotal">0원</h3>
		    	<p>총 주문 금액</p>
		 	 </div>
		</figure>

    <br/>
    
    <hr/>
    <div class="container">
        <figure class="text-center">
  		  <button type="button" onclick="sendOrder()">주문하기</button><br/><br/>
  		  <a href="home.do">계속 쇼핑하기</a>
    	</figure>
    </div>
    
</form>    
</main>

<!-- 모달  -->
		<div class="modal" id="memberModal">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-body text-center">
		        <h5 class="modal-title fw-bold">상품 수량 변경</h5><br/>
					<input type="number">
		      </div>
		      <div class="modal-footer border-0">
		        <button type="button" class="btn btn-secondary rounded-pill" data-bs-dismiss="modal">취소</button>
		        <button type="button" class="btn btn-danger rounded-pill" onclick="qtyChange()">수량변경</button>
		      </div>
    		</div>
  		  </div>
		</div>

<script>
var totalprice = 0;
var totaldelivery = 0;
var deliveryFee = 0;
var selectedBids = [];

// 개별 선택한 값
function handleCheckboxChange(checkbox) {
	  var row = checkbox.parentNode.parentNode;
	  var price = row.querySelector('td:nth-child(3)').innerText;
	  price = parseInt(price.replace(/[^\d]+/g, ''));
	  var bidInput = row.querySelector('input[type="hidden"][name="selectedItems[]"]');
	  

	  
if (bidInput) {
	var bid = bidInput.value;
	  if (checkbox.checked) {
		  	selectedBids.push(bid); // 체크된 bid 값을 배열에 추가 
	    	totalprice += price;
		  	
	    if (totalprice >= 150000) {
	     	 deliveryFee = 0;
	      	 totaldelivery = totalprice;
	    } else {
	      	deliveryFee = 3000;
	      	totaldelivery = (totalprice + deliveryFee);
	    }
	    
	  } else {
		  var index = selectedBids.indexOf(bid);
		    if (index > -1) {
		      selectedBids.splice(index, 1); // 배열에서 해당 bid 값 제거
		  	}
		    
			totalprice -= price;
			if (totalprice < 150000) {
			
			
				if(totalprice == 0){
					deliveryFee = 0;
				}else{
					deliveryFee = 3000;
				}
			totaldelivery = (totalprice + deliveryFee);
			}else {
				deliveryFee = 0;
				totaldelivery = totalprice;
			} 
		}
	    
	    
	  }
	
	  console.log(selectedBids);
	  updateDisplayedValues();
	}

	// 전체 선택한 
// 전체 선택한 항목 계산
function calculateTotalPrice() {
	  var checkboxes = document.querySelectorAll('td input[name="selectedItems"]');
	  totalprice = 0;
	  totaldelivery = 0;
	  selectedBids = [];
	
	  for (var i = 0; i < checkboxes.length; i++) {
	    if (checkboxes[i].checked) {
	      var row = checkboxes[i].parentNode.parentNode;
	      var price = row.querySelector('td:nth-child(3)').innerText;
	      price = parseInt(price.replace(/[^\d]+/g, ''));
	      var bidInput = row.querySelector('input[type="hidden"][name="selectedItems[]"]');
	      var bid = bidInput.value;
	
	      selectedBids.push(bid);
	      totalprice += price;
	
	      if (totalprice >= 150000) {
	        deliveryFee = 0;
	        totaldelivery = totalprice;
	      } else {
	        deliveryFee = 3000;
	        totaldelivery += price + deliveryFee;
	      }
	    }
	  }
	  console.log(selectedBids);
	  updateDisplayedValues();
	}


	// 화면에 보여주는 값
	function updateDisplayedValues() {
	  document.getElementById("myElement").textContent = totalprice.toLocaleString() + "원";
	  document.getElementById("mytotal").textContent = totaldelivery.toLocaleString() + "원";
	  document.getElementById("mydelivery").textContent = deliveryFee.toLocaleString() + "원";
	}

	// 전체선택
	function handleSelectAllCheckbox(checkbox) {
	  var checkboxes = document.querySelectorAll('td input[name="selectedItems"]');
	  for (var i = 0; i < checkboxes.length; i++) {
	    checkboxes[i].checked = checkbox.checked;
	  }
	  
	  calculateTotalPrice();
	}


	document.getElementById('headerCheckbox').addEventListener('change', function() {
	  handleSelectAllCheckbox(this);
	});
	
	// 데이터 보내기
	function sendSelectedBids() {
	  var selectedBidsString = selectedBids.join(",");
	  var url = "selectiondelete.bi?selectedBids=" + encodeURIComponent(selectedBidsString);
		
	  if(selectedBids.length == 0){  
		  alert("상품을 선택해주세요"); 
	  }else{
		  
	  var xhr = new XMLHttpRequest();
	  xhr.open('POST', url, true);
	  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  xhr.onreadystatechange = function() {
	    if (xhr.readyState === XMLHttpRequest.DONE) {
	      if (xhr.status === 200) {
	        console.log('선택된 bids를 서버로 전송했습니다.');
	        window.location.href = "cart.do";
	      } else {
	        console.error('서버로의 요청이 실패했습니다.');
	      }
	    }
	  };

	  xhr.send();
	  }
	}

	// 구매데이터 넘겨주기
function sendOrder() {
	  if (selectedBids.length === 0) {
	    alert("상품을 선택해주세요");
	  } else {
	    var url = "selectionOrder.od";
	    var params = "selectedBids=";
	    
	    for (var i = 0; i < selectedBids.length; i++) {
	      params += encodeURIComponent(selectedBids[i]);
	      
	      if (i !== selectedBids.length - 1) {
	        params += "&selectedBids=";
	      }
	    }
	    
	    var xhr = new XMLHttpRequest();
	    xhr.open('POST', url, true);
	    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	    xhr.onreadystatechange = function() {
	      if (xhr.readyState === 4) {
	        if (xhr.status === 200) {
	          alert("상품을 주문 하시겠습니까?");
	          window.location.href = "cartorder.do";
	        } else {
	          alert("주문 오류");
	        }
	      }
	    };
	
	    xhr.send(params);
	  }
}

var clickpid = null
function chack1(){
	  var button = event.target; // 클릭된 버튼 요소를 참조
	  var row = button.closest("tr");
	  clickpid = row.querySelector("td:nth-child(2) input[type='hidden']").value;

}
	
	
function qtyChange(){
	  var input = document.querySelector("#memberModal input[type='number']");

	  var quantity = input.value; 
	 
 	  var xhr = new XMLHttpRequest();
	  xhr.open("POST", "qtych.change", true);
	  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  xhr.onreadystatechange = function() {
	    if (xhr.readyState === XMLHttpRequest.DONE) {
	      if (xhr.status === 200) {
	        alert("수량이 변경되었습니다.");
	        window.location.href = "cart.do";
	      } else {
	        alert("수량변경오류");
	      }
	    }
	  };
	  
	  // POST 파라미터를 생성하여 전송합니다
	  var params = "qty=" + quantity
	  + "&pid=" + clickpid;
	  xhr.send(params); 
	  
	

}


</script>


	
	<!-- footer -->
	<%@ include file="bottom.jsp" %>
	

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>