<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매하기</title>
</head>
<body>
<!DOCTYPE html>
<html>
<head>
<title>구매 페이지</title>
</head>
<body>
<form action="/buy" method="post">
 <c:forEach items="${DetailedProduct}" var="dto">
<input type="text" name="productName" placeholder="상품 이름">
<td>${dto.pname}</td>
<input type="text" name="productPrice" placeholder="상품 가격">
  <td>${dto.pprice}</td>
<input type="text" name="name" placeholder="이름">
<input type="text" name="address" placeholder="주소">
<input type="text" name="phone" placeholder="전화번호">
<input type="submit" value="구매">
</c:forEach>
</form>

</body>
</html>
</body>
</html>