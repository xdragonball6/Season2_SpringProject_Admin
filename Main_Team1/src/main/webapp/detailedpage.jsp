<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header.jsp" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<style>

.nav-linktwo {
  display: inline-block;
  padding: 15px 200px;
  background-color: black;
  margin-right: 20px;
  color: #fff;
  text-decoration: none;
  border-radius: 10px;
  margin-bottom: 20px;
  
}
.nav-linkthere{
 display: inline-block;
  padding: 15px 200px;
  background-color: black;
  margin-right: 20px;
  color: #fff;
  text-decoration: none;
  border-radius: 10px;
  margin-bottom: 20px;
  }

</style>

</head>
<script>
function updateTotalAmount() { // 수량을 늘렸을 때 db에 남아있는 갯수만큼만 가능
	  var quantity = parseInt(document.getElementById('quantity').value);
	  var price = parseInt(document.getElementById('price').innerText);
	  var maxQuantity = 30; // 최대 수량 설정
	  if (quantity > maxQuantity) {
	    quantity = maxQuantity; // 최대 수량을 초과하는 경우 최대 수량으로 설정
	    document.getElementById('quantity').value = maxQuantity; // 수량 입력 필드에 최대 수량 설정
	    alert('최대 수량을 초과할 수 없습니다.');
	  }
	  var totalAmount = quantity * price;
	  document.getElementById('total-amount').innerText = totalAmount;
	  var quantity = document.getElementById("quantity").value;
	}
function updateQty() {
	  var quantityField = document.getElementById('quantity');
	  var cartLink = document.getElementById('cartLink');
	  var purchaseLink = document.getElementById('purchaseLink');
	  
	  var quantity = parseInt(quantityField.value);
	  
	  var originalCartHref = cartLink.getAttribute('href');
	  var updatedCartHref = originalCartHref.replace(/qty=\d+/, 'qty=' + quantity);
	  cartLink.setAttribute('href', updatedCartHref);
	  
	  var originalPurchaseHref = purchaseLink.getAttribute('href');
	  var updatedPurchaseHref = originalPurchaseHref.replace(/qty=\d+/, 'qty=' + quantity);
	  purchaseLink.setAttribute('href', updatedPurchaseHref);
	}

productImage.innerHTML = '<img src="' + randomProduct.image + '" alt="' + randomProduct.name + '">';
productName.textContent = randomProduct.name;
productDescription.textContent = randomProduct.description;

</script>

<body>

<div class="container">
  <table class="product-table">
    <thead>
      <tr>
        <c:forEach items="${DetailedProduct}" var="dto">
          <td class="product-image">
            <img alt="상품 이미지" style="width: 500px; height: 500px;" src="${dto.pfilename}">
          </td>
          
         
       
          <td class="product-info">
          <table class="product-info-table" style="margin-left: 150px; margin-right: 0;">
              <tr>
              	<th>상품 이름</th>
              	<td>${dto.pname}</td>
              	</tr>
              	<tr>
                <th>상품 설명</th>
                <td>${dto.pcontent}</td>
              </tr>
              <tr>
                <th>상품 가격</th> 
                <td id="price">${dto.pprice}</td>
              </tr>
              <tr>
                <th>수량</th> 
            	<td>
            	<form action="userCart.jsp" method="get">
 						 <input type="hidden" name="pid" value="${dto.pid}">
 						 <input type="number" id="quantity" value="1" min="1" max="${dto.pstock}" onchange="updateTotalAmount()">
				</form>
       
   	
  			   <tr>
 				 <td colspan="2" class="purchase-details" >
   					 <div class="money">
   						<span>총 구매 금액: </span>
     					<span id="total-amount">${dto.pprice}</span><br><br><br>
     					
     						<a id="purchaseLink" class="nav-linkthere" href="OrderView.do?pid=${dto.pid }&qty=1" onclick="updateQty()" >구매하기</a>
   						
   						<a id="cartLink" class="nav-linktwo" href="productcart.do?pid=${dto.pid}&qty=1" onclick="updateQty()">장바구니</a>
					</div>
 				</td>
			</tr>

            </table>
          </td>
        </c:forEach>
      </tr>
    </thead>
  </table>
</div>
<p>추천상품</p>
<div id="productImageContainer">
  <img class="productImage" style="width: 200px; height: 200px;" alt="제품 사진">
  <img class="productImage" style="width: 200px; height: 200px;" alt="제품 사진">
  <img class="productImage" style="width: 200px; height: 200px;" alt="제품 사진">
  <img class="productImage" style="width: 200px; height: 200px;" alt="제품 사진">
  <img class="productImage" style="width: 200px; height: 200px;" alt="제품 사진">
  <img class="productImage" style="width: 200px; height: 200px;" alt="제품 사진">
</div>

<script>
// 이미지 파일의 배열을 생성합니다.
var imageFiles = [
	"image/조명06.png",
	"image/조명07.png",
	"image/조명08.png",
	"image/조명09.png",
	"image/조명01.png",
	"image/조명02.png",
	"image/조명03.png",
	"image/조명04.png",
	"image/조명05.png"
];



var selectedProductImage =  "image/조명05.jpg";
imageFiles = imageFiles.filter(function(image) {
  return image !== selectedProductImage;
});
function changeImages() {
  var productImages = document.getElementsByClassName("productImage");
  
  // 이미지를 중복되지 않도록 선택하기 위해 이미지 파일의 복사본을 생성합니다.
  var remainingImages = imageFiles.slice();
  
  // 각 이미지 요소에 대해 반복하여 랜덤한 이미지를 설정합니다.
  for (var i = 0; i < productImages.length; i++) {
    // 남은 이미지 파일 중에서 랜덤하게 인덱스를 선택합니다.
    var randomIndex = Math.floor(Math.random() * remainingImages.length);
    
    // 선택된 이미지 파일을 가져와 이미지 요소에 설정합니다.
    var randomImage = remainingImages[randomIndex];
    productImages[i].setAttribute("src", randomImage);
    
    // 이미 사용한 이미지를 복사본에서 제거합니다.
    remainingImages.splice(randomIndex, 1);
    
    // 남은 이미지가 없을 경우, 다시 모든 이미지 파일을 복사본에 추가합니다.
    if (remainingImages.length === 0) {
      remainingImages = imageFiles.slice();
      var selectedProductImage = "image/조명05.jpg";
      var productImages = document.getElementsByClassName("productImage");

      for (var i = 0; i < productImages.length; i++) {
        var image = productImages[i].getAttribute("src");

        if (image !== selectedProductImage) {
          var randomIndex = Math.floor(Math.random() * imageFiles.length);
          var randomImage = imageFiles[randomIndex];
          productImages[i].setAttribute("src", randomImage);
          imageFiles.splice(randomIndex, 1);
        } else {
          productImages[i].remove(); // 선택된 상품 이미지를 삭제합니다.
        }
      }
    }
  }
}

// 페이지 로드 시 이미지를 초기화합니다.
window.onload = changeImages;
</script>

 <div class="detailTab">
  <a href="#detailGoodsInfo" class="active">DETAIL PRODUCT</a>
  <a href="#detailReview">REVIEW BOARD</a>
  <a href="#detailQna">Q&amp;A BOARD</a>                   
</div>
<div style="padding-top: 10px;">
 <c:forEach items="${DetailedProduct}" var="dto">
  <table border="0">
    <tr>
      <td align="center">
        <img src="${dto.pcontentfilename1}" style="width: 500px; height: 500px;" align="absmiddle">
        <%-- <img src="${dto.pcontentfilename2}"> --%>
      </td>
    </tr>
  </table>
  </c:forEach>
</div>





<div id="detailGoodsInfo">상세 상품 내용</div>
<div id="detailReview">
		<table>
        		<tr>
            		<th style="background-color: lavender; color: purple;">ReviewNo.</th>
            		<th style="background-color: lavender;color: purple;">작성자</th>
            		<th style="background-color: lavender;color: purple;">제품</th>
            		<th style="background-color: lavender;color: purple;">제목</th>
            		<th style="background-color: lavender;color: purple;">내용</th>
            		<th style="background-color: lavender;color: purple;">작성일</th>
        	</tr>
        	<c:choose>
    <c:when test="${empty Rdto}">
        <tr>
            <td colspan="6">아직 게시물이 없습니다.</td>
        </tr>
    </c:when>
    <c:otherwise>
        	<c:forEach items="${Rdto}" var="dto">
            	<tr>
                <td><a href="ForumView.do?fid=${dto.fid}">${dto.fid}</a></td>
                <td>${dto.f_cid}</td>
                <td>${dto.f_pid}</td>
                <td>${dto.ftitle}</td>
                <td>${dto.fcontent}</td>
                <td>${dto.finsertdate}</td>
            	</tr>
        	</c:forEach>
        	 </c:otherwise>
</c:choose>
		</table>
	</div>

<div id="detailQna"><table>
        		<tr>
            		<th style="background-color: lavender; color: purple;">QnAno.</th>
            		<th style="background-color: lavender;color: purple;">작성자</th>
            		<th style="background-color: lavender;color: purple;">제품</th>
            		<th style="background-color: lavender;color: purple;">제목</th>
            		<th style="background-color: lavender;color: purple;">내용</th>
            		<th style="background-color: lavender;color: purple;">작성일</th>
        	</tr>
        	<c:choose>
    <c:when test="${empty Qdto}">
        <tr>
            <td colspan="6">아직 게시물이 없습니다.</td>
        </tr>
    </c:when>
    <c:otherwise>
        	<c:forEach items="${Qdto}" var="dto">
            	<tr>
                <td><a href="ForumView.do?fid=${dto.fid}">${dto.fid}</a></td>
                <td>${dto.f_cid}</td>
                <td>${dto.f_pid}</td>
                <td>${dto.ftitle}</td>
                <td>${dto.fcontent}</td>
                <td>${dto.finsertdate}</td>
            	</tr>
        </c:forEach>
         </c:otherwise>
</c:choose>
</table></div>

<c:forEach items="${DetailedProduct}" var="dto">
<form action="Kms_QnAWrite.jsp" method="get">
<input type="submit" value="질문작성하기">
<input type="hidden" name="cid" value="${cid }">
<input type="hidden" name="pid" value="${param.pid}">
<input type="hidden" name="pname" value="${dto.pname}">
</form>
</c:forEach>

<%@ include file="bottom.jsp" %>
</body>

</html>