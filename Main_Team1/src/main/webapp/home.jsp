<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>
<!doctype html>
<html lang="ko">
<head>

    <title>메인 홈페이지 입니다</title>
    <!-- Bootstrap core CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

	<style>
	  .image {
	    width: 100%;
	    height: 500px;
	    object-fit: fill;
	  }
	  	html,
		body {
		  height: 100%;
		}
		
		body {
		  display: flex;
		  padding-top: 40px;
		  padding-bottom: 40px;
		}
		
		.form-signin {
		  max-width: 400px;
		  padding: 15px;
		}
			.form-control {
		  border: none;
		  border-bottom: 1px solid #ced4da;
		  border-radius: 0;
		}
	



	</style>


</head>
<body>

   <!-- 네버바 --> 

<main>

<!-- 배너부분  -->

  <section class="text-center container">
	<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
	  <div class="carousel-indicators">
	    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
	    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
	    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
	  </div>
	  <div class="carousel-inner">
	    <div class="carousel-item active" >	    	
	      <img src="image/room1.jpg" class="image" alt="...">
	    </div>
	  
	    <div class="carousel-item">
	      <img src="image/room2.jpg" class="image" alt="...">
	    </div>
	    <div class="carousel-item">
	      <img src="image/room4.jpg" class="image" alt="...">   
	    </div>
	  </div>
	  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Previous</span>
	  </button>
	  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Next</span>
	  </button>
	</div>
  </section>

<hr/>
<!-- 상품을 관리 -->

 <div class="container">
 <label>전체 상품</label><br/>
 		<form action="search.do" method="post">
		 	<input type="search" name="search">
		 	<input type="submit" value="검색" >
		 </form>
   	<div class="row my-2">
  	  <c:forEach items="${list }" var="dto">
	  	  <div class="col-md-4 text-center">
		  	  <a class="nav-link" href="detailedpage.do?pid=${dto.pid }">
					<img src="${dto.pfilename }" style="width: 400px; height: 400px; margin-bottom: 10px;"  alt="..."><br/>
				   	 <p class="text-dark" style="margin: 0;">${dto.pname }</p>
				   	 <p class="text-dark" style="margin: 0;"><fmt:formatNumber value="${dto.pprice}" pattern="#,##0원" /></p>
			  </a>
	      </div>
  	  </c:forEach>
 	</div>
</div>

</main>




<!-- 하단  -->
<%@ include file="bottom.jsp" %>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
      
</body>
</html>
