<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CHAIR</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<main class="form-signin">
	<div class="container">
		<figure class="text-center">

            <h3 class="text-dark">CHAIR</h3>
            <label>의자는 단순히 앉는 도구가 아니라, 예술 작품으로서의 가치를 가지고 있습니다</label><br/>
            <label>그 특별한 디자인과 섬세한 장인 정신은 당신의 공간을 창조적인 공간으로 탈바꿈시키며, 모든 시선을 사로잡을 것입니다. </label><br/>
            <label>편안함과 스타일을 동시에 즐겨보세요. </label><br/><br/>
        </figure>
    </div>
<hr/><br/>

 <div class="container">
 <label>전체 상품</label>
   	<div class="row my-2">
  	  <c:forEach items="${list }" var="dto">
	  	  <div class="col-md-4 text-center" >
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