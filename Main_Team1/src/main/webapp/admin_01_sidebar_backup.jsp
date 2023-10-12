<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap.rtl.css" rel="stylesheet">
	<link href="css/bootstrap.rtl.min.css" rel="stylesheet">
	
	<link href="css/bootstrap-grid.css" rel="stylesheet">
	<link href="css/bootstrap-grid.min.css" rel="stylesheet">
	<link href="css/bootstrap-grid.rtl.css" rel="stylesheet">
	<link href="css/bootstrap-grid.min.rtl.css" rel="stylesheet">
	
	
	<link href="css/bootstrap-reboot.css" rel="stylesheet">
	<link href="css/bootstrap-reboot.min.css" rel="stylesheet">
	<link href="css/bootstrap-reboot.rtl.css" rel="stylesheet">
	<link href="css/bootstrap-reboot.rtl.min.css" rel="stylesheet">
	
	<link href="css/bootstrap-utilities.css" rel="stylesheet">
	<link href="css/bootstrap-utilities.min.css" rel="stylesheet">
	<link href="css/bootstrap-utilities.rtl.css" rel="stylesheet">
	<link href="css/bootstrap-utilities.rtl.min.css" rel="stylesheet">


</head>


<body>
<main class="d-flex flex-wrap">
	<div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 200px;" >

	    <ul class="nav nav-pills flex-column mb-auto">

	      <li class="nav-item">
	        <a href="adminHome.do" class="nav-link active" aria-current="page">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
	          adminHome.do
	        </a>
	      </li>
	      <li class="nav-item">
	      <c:set var="ftype" value="${param.ftype}" />
			    <a class="nav-link text-white" href="Awritelist.do?ftype=2">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#gird"></use></svg>
	          AQnA.do
	        </a>
	      </li>
	      <li class="nav-item">
	      <c:set var="ftype" value="${param.ftype}" />
			    <a class="nav-link text-white" href="Awritelist.do?ftype=1">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#gird"></use></svg>
	          AReview.do
	        </a>
	      </li>
	      <li>
	        <a href="AReviewList.do" class="nav-link text-white">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"></use></svg>
	          AReviewList.do
	        </a>
	      </li>
	      <li>
	        <a href="AreviewRate.do" class="nav-link text-white">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"></use></svg>
	          AreviewRate.do
	        </a>
	      </li>
	      <li>
	        <a href="APlist.do" class="nav-link text-white">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#gird"></use></svg>
	          APlist.do
	        </a>
	      </li>
	      <li>
	        <a href="APlist.do" class="nav-link text-white">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"></use></svg>
	          APlist.do
	        </a>
	      </li>
	      <li>
	        <a href="APinsert.do" class="nav-link text-white">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"></use></svg>
	          APinsert.do
	        </a>
	      </li>
	      <li>
	        <a href="APupdate.do" class="nav-link text-white">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"></use></svg>
	          APupdate.do
	        </a>
	      </li>
	      <li>
	        <a href="APdelete.do" class="nav-link text-white">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"></use></svg>
	          APdelete.do
	        </a>
	      </li>
	      <li>
	        <a href="AUserlist.do" class="nav-link text-white">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#grid"></use></svg>
	          AUserlist.do
	        </a>
	      </li>
	      <li>
	        <a href="Salemanage.do" class="nav-link text-white">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#grid"></use></svg>
	          Salemanage.do
	        </a>
	      </li>
	      <li>
	        <a href="Visitmanage.do" class="nav-link text-white">
	          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#grid"></use></svg>
	          Visitmanage.do
	        </a>
	      </li>
	    </ul>
	    
	    <hr>
	    <hr>
	    <div class="dropdown">
	      <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
	        <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
	        <strong>mdo</strong>
	      </a>
	      <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
	        <li><a class="dropdown-item" href="#">New project...</a></li>
	        <li><a class="dropdown-item" href="#">Settings</a></li>
	        <li><a class="dropdown-item" href="#">Profile</a></li>
	        <li><hr class="dropdown-divider"></li>
	        <li><a class="dropdown-item" href="#">Sign out</a></li>
	      </ul>
	    </div>
	  </div>
	  
	  <!-- div class="b-example-divider b-example-vr"></div> -->
	</main>
</body>
</html>