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
				<h3 class="text-dark">주문목록</h3>
			</figure>
		</div>

<main>
    <table border="1">
        <tr>
            <th>상품 정보</th>
            <th>수량</th>
            <th>주문 금액</th>
            <th>주문 날짜</th>
            <th>배송 상태</th>
            <th>리뷰 쓰기</th>
        </tr>
		
		<c:set var="listSize" value="${fn:length(list)}" />


		<c:forEach items="${Olist}" var="dto" varStatus="status">
				
			  <tr>
			    <td>${dto.pname}</td>
			    <td>${dto.oqty}</td>
			    <td>${dto.oprice}</td>
			    <td>${dto.odate}</td>
			    <td>
			     <c:choose>
          			<c:when test="${dto.odelivery == 0}">배송 전</c:when>
          			<c:when test="${dto.odelivery == 1}">배송 중</c:when>
		          	<c:when test="${dto.odelivery == 2}">배송 완료</c:when>
		          	<c:when test="${dto.odelivery == 3}">배송 완료</c:when>
		          	<c:otherwise>작성완료</c:otherwise>
		        </c:choose></td>
		        <td>
		        	<c:choose>
			        <c:when test="${dto.odelivery == 3}">
			            작성완료
			        </c:when>
			        <c:otherwise>
            <c:if test="${dto.odelivery == 2}">
                <form action="ReviewWriteForum.do">
                    <input type="hidden" name="oid" value="${dto.oid}">
                    <input type="hidden" name="f_pid" value="${dto.product_pid}">
                    <input type="hidden" name="pname" value="${dto.pname}">
                    <input type="hidden" name="cname" value="${dto.cname}">
                    <input type="submit" value="리뷰쓰기">
                </form>
            </c:if>
        			</c:otherwise>
    				</c:choose>
				</td>
			  </tr>
		</c:forEach>
</table>
   	
    
      
</main>




	
	<!-- footer -->
	<%@ include file="bottom.jsp" %>
	

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>