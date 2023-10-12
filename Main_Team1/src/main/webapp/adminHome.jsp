<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 홈 화면</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>


<!-- 프로젝트내 js 파일  -->
<script src="js/admin_kkg.js" type="text/javascript"></script>

<!--  bootsstrap link -->

<link href="css/admin_kkg.css" rel="stylesheet">
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

<!-- 여기서부터 복사하시면 됩니다~~~~~~~~~!!!! -->

<jsp:include page="admin_01_header.jsp" />


</head>

<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-1">
				<jsp:include page="admin_01_sidebar.jsp" />
			</div>

			<div class="col-md-10" style="margin-left: 12%; margin-top: 30px;">
				<main class="ms-sm-auto px-md-4">

					<!-- 요기서부터 본문 내용 입력하면 됩니다아~~!!!!!  하단에  </div> 및 </main> 자리 맞춰서 넣는거만 기억하면 됩니다.-->

							<div>
								<h2>매출 관리</h2>
							</div>
							<div class="content">


								<div class="array">
									<div class="card shadw-lg"
										style="width: 500px; height: 300px; background-color: #F7F7F7;">
										<h5 class="card-title">월간 매출/판매 현황</h5>
										<canvas id="SaleChart_month"></canvas>
										<div class="card-body">
											<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
										</div>
									</div>
								</div>




								<!-- 							<div class="array">
								<div class="card" style="width: 350px; height: 300px;">
								    <h5 class="card-title"> 주간 매출/판매 현황</h5>
								  <canvas id="SaleChart_weekly"></canvas>
								  <div class="card-body">
									<a href="#" class="btn btn-primary">Go somewhere</a>
								  </div>
								</div>
							</div> -->


								<div class="array">
									<div class="card shadow-lg"
										style="width: 500px; height: 300px; background-color: #F7F7F7;">
										<h5 class="card-title">일일 매출/판매 현황</h5>
										<canvas id="SaleChart_daily"></canvas>
										<!-- <div class="card-body">
									<a href="#" class="btn btn-primary">Go somewhere</a>
								  </div> -->

									</div>
								</div>

							</div>
							<!-- content  -->
							<br />
							<hr />
							<br />
							<div>
								<h2>회원 관리</h2>
							</div>
							<div class="content">


								<div class="array">
									<div class="card shadow-lg"
										style="width: 500px; height: 300px; background-color: #F7F7F7;">
										<h5 class="card-title">일일 신규 가입자수/탈퇴자수</h5>
										<canvas id="NewScriberChart_daily"></canvas>
										<div class="card-body">
											<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
										</div>
									</div>
								</div>
								
								
								<div class="array">
									<div class="card shadow-lg"
										style="width: 500px; height: 300px; background-color: #F7F7F7; ">
										
										<h5 class="card-title" >재고 부족 제품</h5>
										
										<table style="margin-left: 20px">
										<tr>
										<th>No.</th><th>분류</th><th>제품번호</th><th>제품</th><th>재고</th>
										</tr>
									<c:forEach items="${OutofStocks}" var="product">
										<tr>
										<td>${product.seq}</td>
										<td>${product.categoryId}</td>
										<td>${product.pid}</td>
										<td>${product.pname}</td>
										<td>${product.pstock}</td>
										</tr>
									</c:forEach>


								</table>
										<div class="card-body">
											<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
										</div>
									</div>
								</div>
								
								
							</div>
							<br />
							<hr />
							<br />


<%-- 							<div>
								<p>받아온 data 검증하기 위한 파트</p>
								<p>
									날짜 :${requestScope.dailyDate} <input type="hidden"
										id="dailyDate" value="${requestScope.dailyDate}">
								</p>
								<p>
									주문 :${requestScope.dailyOrder} <input type="hidden"
										id="dailyOrder" value="${requestScope.dailyOrder}">
								</p>
								<p>
									매출 :${requestScope.dailySale} <input type="hidden"
										id="dailySale" value="${requestScope.dailySale}">
								</p>
								<p>
									신규 :${requestScope.dailyNS} <input type="hidden" id="dailyNS"
										value="${requestScope.dailyNS}">
								</p>

								<p>
									월별 :${requestScope.monthlyMonth} <input type="hidden"
										id="monthlyMonth" value="${requestScope.monthlyMonth}">
								</p>
								<p>
									주문 :${requestScope.monthlyOrder} <input type="hidden"
										id="MonthlyOrder" value="${requestScope.monthlyOrder}">
								</p>
								<p>
									매출 :${requestScope.monthlySale} <input type="hidden"
										id="MonthlySale" value="${requestScope.monthlySale}">
								</p>
							</div> --%>


				</main>
			</div>
		</div>
	</div>



	<!-- SaleChart_daily 그리기   -->
	<script>
	   /* 데일리 매출/결재건수 차트 */
	
	var dsContext = document.getElementById('SaleChart_daily');
    DoubleLineChart(dsContext, ${requestScope.dailyDate},'매출', ${requestScope.dailySale},'결재건' ,${requestScope.dailyOrder});

    /* monthly 매출/결재건수 차트 */
    var msContext = document.getElementById('SaleChart_month');
    DoubleLineChart(msContext, ${requestScope.monthlyMonth},'매출',${requestScope.monthlySale},'결재건', ${requestScope.monthlyOrder});

    /* 신규 가입자수 차트 */
    var DNSContext = document.getElementById('NewScriberChart_daily');
    SingleBarChart(DNSContext, ${requestScope.dailyDate}, '신규가입자' ,${requestScope.dailyNS});
	</script>




	<script>
        // 스크롤 이벤트 리스너
        window.addEventListener('scroll', function() {
            var header = document.querySelector('header');

            if (window.scrollY > 0) {
                header.classList.add('scrolled');
            } else {
                header.classList.remove('scrolled');
            }
        });
    </script>


</body>
</html>