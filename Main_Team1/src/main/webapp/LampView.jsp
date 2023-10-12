<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LAMP</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<main class="form-signin">
	<div class="container">
		<figure class="text-center">

            <h3 class="text-dark">LAMP</h3>
            <label>LAMP는 오직 조명의 역할에만 그치지 않습니다. </label>
            <label>이 제품은 공간을 더욱 특별하게 만들어주는 예술 작품과도 같습니다. 독창적인 디자인과 창의적인 형태는 공간의 시각적인 장식 요소로서도 탁월한 역할을 합니다. </label>
            <label>따라서 LAMP를 통해 당신의 공간은 오롯이 당신만의 특별한 세계로 탈바꿈할 것입니다. </label><br/><br/>
        </figure>
    </div>
<hr/><br/>

 <div class="container">
 <label>전체 상품</label>
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



<%@ include file="bottom.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>