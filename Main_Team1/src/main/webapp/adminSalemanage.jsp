
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매출 관리</title>

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
<!-- 달력부분 -->

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">


<script type="text/javascript">
			

				
				/* 날짜 입력의 정규화를 위함 */
			function checkDate(){
				const regstartdate = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])$/;
				const regenddate = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])$/;	
					
				const formdate = document.date;
				const startDate = formdate.startDate.value;
				const endDate = formdate.endDate.value;
				
				
				if(!regstartdate.test(startDate)){
						alert("시작 날짜를 입력해 주세요.")
						formdate.startDate.select();
						return;
					}
					
				if(!regenddate.test(endDate)){
						alert("마지막 날짜를 입력해 주세요")
						formdate.endDate.select();
						return;
					}
				if (endDate < startDate) {
				    alert("종료일은 시작일보다 뒷날짜여야 합니다.");
				    formdate.endDate.select();
				    return;
				}
 
				formdate.submit();

			}



					
			
</script>

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



					<div
						class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
						<h1 class="h2">매출/결재 관리</h1>


						<div>

							<form action="Salemanage.do" name="date" method="post">
								<input type="text" name="startDate" id="startDate"
									placeholder="시작일" autocomplete="off"> <input
									type="text" name="endDate" id="endDate" placeholder="종료일"
									autocomplete="off"> <input type="button" value="확 인"
									onclick="checkDate()">
							</form>

						</div>


						<div class="btn-toolbar mb-2 mb-md-0">
							<div class="btn-group me-2"></div>
							<button type="button"
								class="btn btn-sm btn-outline-secondary dropdown-toggle" onclick="thisWeek()">

								This week</button>
						</div>
						<!-- 여기까지가 버튼 과 관련된 부분 이아래가 그래프다 -->
					</div>

					<div class="content">
						<div class="array">
							<div class="card shadw-lg"
								style="width: 500px; height: 500px; background-color: #F7F7F7; margin-bottom: 10px">
								<h5 class="card-title">일별 매출/주문 현황</h5>
								<canvas id="dailychart"></canvas>
								<div class="card-body">
									<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
								</div>
							</div>
						</div>
						<div class="array">


							<div class="card shadw-lg"
								style="width: 500px; height: 500px; background-color: #F7F7F7; margin-bottom: 10px">
								<h5 class="card-title">월별 매출/주문 현황</h5>
								<canvas id="monthlychart"></canvas>
								<div class="card-body">
									<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
								</div>
							</div>
						</div>
					</div>

					<div class="content">
						<div class="array">
							<div class="card shadw-lg"
								style="width: 500px; height: 500px; background-color: #F7F7F7; display: flex; justify-content: center; align-items: center; margin-bottom: 10px">
								<h5 class="card-title">기간내 카테고리별 매출 비교</h5>
								<div style="width: 70%; height: 70%;">
									<canvas id="categorychart_sale"></canvas>
								</div>

								<div class="card-body">
									<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
								</div>
							</div>
						</div>
						<div class="array">

							<div class="card shadw-lg"
								style="width: 500px; height: 500px; background-color: #F7F7F7; margin-bottom: 10px">
								<h5 class="card-title">기간내 카테고리별 판매량 비교</h5>
								<canvas id="categorychart_order"></canvas>
								<div class="card-body">
									<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
								</div>
							</div>
						</div>
					</div>

					<div class="content">
						<div class="array">
							<div class="card shadw-lg"
								style="width: 500px; height: 500px; background-color: #F7F7F7; display: flex; justify-content: center; align-items: center; margin-bottom: 10px">
								<h5 class="card-title">기간내 남녀 매출 비교</h5>
								<div style="width: 70%; height: 70%;">
									<canvas id="gender_sale"></canvas>
								</div>
								<div class="card-body">
									<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
								</div>
							</div>
						</div>
						<div class="array">
							<div class="card shadw-lg"
								style="width: 500px; height: 500px; background-color: #F7F7F7; display: flex; justify-content: center; align-items: center; margin-bottom: 10px">
								<h5 class="card-title">기간내 남녀 주문량 비교</h5>
								<div style="width: 70%; height: 70%;">
									<canvas id="gender_order"></canvas>
								</div>
								<div class="card-body">
									<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
								</div>
							</div>
						</div>
					</div>




					<%-- 				<div>
					<p>기간내 카테고리별 남녀 매출 현황</p>
					<canvas class="my-4 w-100 chartjs-render-monitor"
						id="categorygenderchart_sale" width="700" height="400"
						style="display: block; height: 275px; width: 500px;"></canvas>
				</div>

				<div>
					<p>기간내 카테고리별 남녀 주문량 현황</p>
					<canvas class="my-4 w-100 chartjs-render-monitor"
						id="categorygenderchart_order" width="400" height="300"
						style="display: block; height: 275px; width: 500px;"></canvas>
				</div>  --%>

					<div class="content">
					
						<div class="array">
							<div class="card shadw-lg"
								style="width: 500px; height: 500px; background-color: #F7F7F7; margin-bottom: 10px">
								<h5 class="card-title">기간내 판매량 Top 5</h5>
								<canvas id="orderTopChart"></canvas>
								<div class="card-body">
									<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
								</div>
							</div>
						</div>
						<div class="array">


							<div class="card shadw-lg"
								style="width: 500px; height: 500px; background-color: #F7F7F7; margin-bottom: 10px">
								<h5 class="card-title">기간내 매출액 Top 5</h5>
								<canvas id="saleTopChart"></canvas>
								<div class="card-body">
									<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
								</div>
							</div>
						</div>
					</div>



					<hr>

					<div>

						<p>데이터 확인 하는 파트</p>
						<p>시작 날 :${dateList[0]}</p>
						<p>마지말 : ${dateList[fn:length(dateList)-1]}</p>
						<p>시작 달 : ${monthList[0]}</p>
						<p>마지막 달 : ${monthList[fn:length(monthList)-1]}</p>
						<br />
						<p>대상 날짜 : ${requestScope.dateList}</p>
						<p>일일매출 : ${requestScope.dailySales}</p>
						<p>월 매출 : ${requestScope.monthlySales}</p>
						<p>일 주문 : ${requestScope.dailyOrders}</p>
						<p>월 주문 : ${requestScope.monthlyOrders}</p>
						<br />
						<p>카테고리리스트 : ${requestScope.categoryList}</p>
						<p>카테고리 매출 : ${requestScope.categorySales}</p>
						<p>카테고리 주문 : ${requestScope.categoryOrders}</p>
						<br />

						<p>성별 리스트 : ${requestScope.genderList}</p>
						<p>성별 매출 : ${requestScope.genderSales}</p>
						<p>성별 주문 : ${requestScope.genderOrders}</p>

						<br />

						<%-- 					<p>남성 카테고리 매출 : ${requestScope.categoryGDSales_male}</p>
					<p>남성 카테고리 주문 : ${requestScope.categoryGDOrders_male}</p>
					<p>여성 카테고리 매출: ${requestScope.categoryGDSales_female}</p>
					<p>여성 카테고리 주문: ${requestScope.categoryGDOrders_female}</p>
 --%>
						<br />

						<p>주문 탑 5 목록 : ${requestScope.maxPnameOrders}</p>
						<p>주문 탑 5 주문량 : ${requestScope.maxOrders}</p>
						<p>매출 탑 5 목록: ${requestScope.maxPnameSales}</p>
						<p>매출 탑 5 매출액: ${requestScope.maxSales}</p>



						<hr>

					</div>



				</main>
			</div>
		</div>
	</div>


	<script>
	
 	 	DoubleLineChart(document.getElementById('dailychart'),${requestScope.dateList},'매출액',${requestScope.dailySales},'결재건',${requestScope.dailyOrders});  
		DoubleLineChart(document.getElementById('monthlychart'), ${requestScope.monthList}, '매출액' ,${requestScope.monthlySales},'결재건',${requestScope.monthlyOrders});
    	PieChart("카테고리별 매출 비중 분석",document.getElementById('categorychart_sale'),${requestScope.categoryList},${requestScope.categorySales} );
		SingleBarChart(document.getElementById('categorychart_order'),${requestScope.categoryList},'판매량',${requestScope.categoryOrders})		;  
		
	/* 	DoublebarChart(document.getElementByUd('categorygenderchart_sale'),${requestScope.categoryGenderSales_male},${requestScope.categoryGenderSales_female});
		DoublebarChart(document.getElementByUd('categorygenderchart_order'),${requestScope.categoryGenderOrder_male},${requestScope.categoryGenderOrder_female}); */
		
		/*function PieChart(Title, context, xlabel, ydatas_01) {*/

     	PieChart("남녀 매출 비교",document.getElementById('gender_sale'),${requestScope.genderList},${requestScope.genderSales} );
    	PieChart("남녀 주문량 비교",document.getElementById('gender_order'),${requestScope.genderList},${requestScope.genderOrders} );

    	SingleBarChart(document.getElementById('orderTopChart'),${requestScope.maxPnameOrders},'판매량',${requestScope.maxOrders}); 
		SingleBarChart(document.getElementById('saleTopChart'),${requestScope.maxPnameSales},'매출액',${requestScope.maxSales});
		
		
		
	</script>

	<script>

	    $(function() {
	      $("#startDate").datepicker({
	    	  dateFormat : "yy-mm-dd"
	      });
	      
	      
	      
	      $("#endDate").datepicker({
	    		  dateFormat : "yy-mm-dd"
	    		  
	      
	      });
	    });
    </script>

	<script>
	
    function thisweek(){
    	
   	 // 오늘 날짜를 구합니다.
   	  var currentDate = new Date();

   	  // 1주일 전 날짜를 구하기 위해 JavaScript의 Date 메소드를 사용합니다.
   	  var startDate = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate() - 7);

   	  // startDate와 endDate 변수를 문자열로 변환합니다.
   	  var startDateStr = startDate.toISOString().slice(0, 10);
   	  var endDateStr = currentDate.toISOString().slice(0, 10);
		 
   	  // adminorder.do로 전송할 URL을 생성합니다.
   	  var url = "Salemanage.do?startDate=" + startDateStr + "&endDate=" + endDateStr;

   	  // 페이지 이동을 위해 location.href를 사용합니다.
   	  location.href = url;
   	
   }
	</script>



</body>
</html>