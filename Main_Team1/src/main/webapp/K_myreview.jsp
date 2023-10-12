<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- <style>
 
   .custom-nav {
	float: left;
	width: 25%;
    margin-right: 0px;
    
  }

  </style> -->
<meta charset="UTF-8">
<link rel="stylesheet" href="k_myreview.css">
 <link rel="icon" type="image/png" href="http://example.com/myicon.png"> 
<title>Insert title here</title>
</head>
<body>
<!-- <div class="container py-5">
  <header>
    <h1 class="text-center mb-3">회원정보변경</h1>
  </header>

  <div class="row">
    <div class="custom-nav">
      <nav>
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link" href="#">회원정보 수정</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">나의 리뷰</a>
          </li>
   
          <li class="nav-item">
            <a class="nav-link" href="#">배송 현황</a>
         
          </li>
        </ul>
      </nav>
    </div> -->
           <strong>나의 리뷰</strong>
            <p>본인이 작성한 리뷰 내용을 볼 수 있습니다.</p>
        </div>
        <div class="board_list_wrap">
            <div class="board_list">
                <div class="top">
                    <div class="f_cid">번호</div>
                    <div class="ftype">글쓴이</div>
                    <div class="title">제목</div>
                    <div class="fcontent">작성일</div>
               <!--      <div class="count">조회</div> -->
               <c:forEach items="${list }" var="dto">
      <div class="board_item">
        <div class="f_cid">${dto.f_cid}</div>
        <div class="ftype">${dto.ftype}</div>
        <div class="title">${dto.title}</div>
        <div class="fcontent">${dto.fcontent}</div>
      </div>
    </c:forEach>
                </div>
                </div>
</body>
</html>