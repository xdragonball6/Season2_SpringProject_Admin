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

    td {
        text-align: left;
        padding: 10px;
    }

    td:first-child {
        text-align: left;
    }

</style>

<meta charset="UTF-8">
<title>구매내역</title>
</head>
<body>
		<figure class="text-center">
			<h3 class="text-dark">Little and Precious</h3>
			<h3>구매내역</h3>
		</figure>
	<div class="container d-flex justify-content-center align-items-center vh-100">
		<figure class="text-center">
		<table>
			<c:forEach items="${orderList}" var="dto" >
				<tr>
					<td>
			    		<img src="${dto.pfilename}" style="width: 100px; height: 100px; margin-bottom: 10px;" alt="..." />
			   		</td>
			   		<td>${dto.pname}<br/>
			    		${dto.pcontent} - ${dto.bqty}개<br/>
			    		<fmt:formatNumber value="${dto.price * dto.bqty}" pattern="#,##0원" />
			    	</td>
				</tr>
			</c:forEach>
		</table>
		<table>
				<c:set var="totalprice" value="0"></c:set>
			<c:forEach items="${orderList}" var="dto" >	
	  			<c:set var="subtotal" value="${dto.price * dto.bqty}"></c:set>
	  			<c:set var="totalprice" value="${totalprice + subtotal}"></c:set>
			</c:forEach>
			<c:forEach items="${detail}" var="dto" begin="0" end="0">
				<tr>	
					<td>이름 :</td>
					<td>${dto.cname }</td>
				</tr>
				<tr>
					<td>전화번호 :</td>
					<td>${dto.cphone }</td>
				</tr>
				<tr>
					<td>이메일(아이디) :</td>
					<td>${cid }</td>
				</tr>
				<tr>
					<td>우편번호 :</td>
					<td>${dto.postnum }</td>
				</tr>
				<tr>
					<td>주소 :</td>
					<td>${dto.address1 }</td>
				</tr>
				<tr>
					<td>상세주소 :</td>
					<td>${dto.address2 }</td>
				</tr>
				<tr>
					<td>배송메모 :</td>
					<td>${Memo }</td>
				</tr>
				<tr>
					<td>결제수단 :</td>
					<td>${Payment }</td>
				</tr>
				<tr>
				    <td>상품가격 :</td>
				    <td><fmt:formatNumber value="${totalprice}" pattern="#,##0원" /></td>
				</tr>
				<tr>
				    <td>배송비 :</td>
				    <td><fmt:formatNumber value="${totalprice >= 150000 ? 0 : 3000}" pattern="#,##0원" /></td>
				</tr>
				<tr>
				    <td>총 주문 금액 :</td>
				    <td><fmt:formatNumber value="${(totalprice) + (totalprice >= 150000 ? 0 : 3000)}" pattern="#,##0원" /></td>
				</tr>
			</c:forEach>

		</table>
		</figure>

	</div>
		<figure class="text-center">
			<button class="w-100 btn btn-lg btn-primary" onclick="home()">홈으로 가기</button>
		</figure>
	
	<script type="text/javascript">
		function home(){
			 window.location.href = "home.do";
		}
	</script>


	<%@ include file="bottom.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>