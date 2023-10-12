<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 상품</title>
</head>
<body>
	<div class="container">
		<figure class="text-center">
			<h3 class="text-dark">Little and Precious</h3>
			<h3 class="text-dark">검색 결과</h3>
		</figure>
		<hr/>
		</div>
 	<form action="search.do" method="post">
		 <input type="search" name="search">
		 <input type="submit" value="검색" >
	 </form>
   	<div class="row my-2">
  	  <c:forEach items="${searchlist }" var="dto">
	  	  <div class="col-md-4 text-center">
		  	  <a class="nav-link" href="detailedpage.do?pid=${dto.pid }">
				<img src="${dto.pfilename }" style="width: 400px; height: 400px; margin-bottom: 10px;"  alt="..."><br/>
				    <p class="text-dark" style="margin: 0;">${dto.pname }</p>
				   	<p class="text-dark" style="margin: 0;"><fmt:formatNumber value="${dto.pprice}" pattern="#,##0원" /></p>
			  </a>
	      </div>
  	  </c:forEach>
 	</div>




<!-- 하단  -->
<%@ include file="bottom.jsp" %>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>