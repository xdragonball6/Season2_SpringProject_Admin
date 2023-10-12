
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	
	
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
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
	
</head>

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


			/* 페이징 처리를 위한 버튼 */
			
			
			/* 수정/삭제 버튼과, 결재내역 보는 버튼을 위 */
			/* 수정/삭제 버튼 : 이건 팝업창 띄워서 수정하는 방식으로 구현하자.*/
			function updateInfo(cid){
				
			}
			/* 결재,주문내역 보기 : 이건 mvc 모델로 보내서 바꾸기 */
		
			
			
</script>




<body data-new-gr-c-s-check-loaded="14.1111.0 data-gr-ext-installed">
	<div class="container_mk">
		<div class="headbar">
			<jsp:include page="admin_01_header.jsp" />
		</div>
		<div class="sidebar">
			<jsp:include page="admin_01_sidebar.jsp" />
		</div>
	</div> 

<!--  ************* Headbar && Sidebar 종료 ************* -->

	<div class="container-fluid">
		<div class="row" style="margin-left : 70px; padding : 10px">
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div class="chartjs-size-monitor">
					<div class="chartjs-size-monitor-expand">
						<div class=""></div>
					</div>
					<div class="chartjs-size-monitor-shrink">
						<div class=""></div>
					</div>
				</div>
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">	매출/결재 관리</h1>
					<div>
					
						<form action="AUserlist.do" name="date" method="post">
							<input type="text" name ="startDate" id= "startDate" placeholder="시작일" autocomplete="off">
	 						<input type="text" name ="endDate" id="endDate" placeholder="종료일" autocomplete="off" >
	 						<input type="text">
	 						
	 						<input type="hidden" name ="pageNum" value="${userList[0].pageNum}">
 	 						<input type="button"  value="확 인" onclick="checkDate()">
						</form>
						
					</div>


					<div class="btn-toolbar mb-2 mb-md-0">
						<div class="btn-group me-2">
							<button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
							<button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
						</div>
						<button type="button"
							class="btn btn-sm btn-outline-secondary dropdown-toggle">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
								viewBox="0 0 24 24" fill="none" stroke="currentColor"
								stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
								class="feather feather-calendar align-text-bottom"
								aria-hidden="true">
								<rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
								<line x1="16" y1="2" x2="16" y2="6"></line>
								<line x1="8" y1="2" x2="8" y2="6"></line>
								<line x1="3" y1="10" x2="21" y2="10"></line></svg>
							This week
						</button>
					</div>
					<!-- 여기까지가 버튼 과 관련된 부분 이아래가 그래프다 -->
				</div>
				<div>
				<span>신규 가입자 현황</span>
				</div>
				<div>

				<canvas class="my-4 w-100 chartjs-render-monitor" id="userChart" width="500" height="300" 
						style="display: block; height: 275px; width: 652px;"></canvas>
				</div>
				<hr>
				
<%-- 검증을 위한 파트.	<div>
				
				<p>데이터 확인 하는 파트</p>
				<p>저장된 페이지 번호 :  ${userList[0].pageNum}</p>
				<p>저장된 시작 날 :${dateList[0]}</p>
				
				<p>저장된 마지말  : ${dateList[fn:length(dateList)-1]} </p>
				<p>날짜 배열의 길이 :${fn:length(dateList)} </p>
				
				<hr>
				
				</div>
--%>			
				<div>
					<h2>회원 목록  </h2>     
				</div>
				<div>
					<span style="font-size: 5; text-align: right">총 회원 수: ${usernum}</span>
				</div>
				<div class="table-responsive">
					<table class="table table-striped table-sm">
						<thead>
							<tr>
								<th scope="col">page</th>
								<th scope="col">#</th>
								<th scope="col">이름</th>
								<th scope="col">ID</th>
								<th scope="col">생년월일</th>
								<th scope="col">성별</th>
								<th scope="col">주소</th>
								<th scope="col">연락처</th>
								<th scope="col">가입일</th>
								<!-- <th scope="col">정보 수정/삭제</th> -->
								<th scope="col">결재내역 보기</th>

							</tr>
						</thead>
						<tbody>

							
							<c:forEach items="${userList}" var="customer">
								<tr>
									<td>${customer.pageNum}</td>
									<td>${customer.seq}</td>
									<td>${customer.cname}</td>
									<td>${customer.cid}</td>
									<td>${customer.cbirthdate}</td>
									<td>${customer.cgender}</td>
									<td>${customer.caddress}</td>
									<td>${customer.cphone}</td>
									<td>${customer.cinsertdate}</td>
								<%-- 	<td><form  name="updateInfo"><button onclick="updateInfo(${customer.cid})">수정/삭제</button></form></td> --%>
									<td><form action="showOderList.do" name="showOlist"> <input type="hidden" name="cid" value="${customer.cid}"><input type="submit" value="구매내역" size="30"></form></td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
					<div align="center">
						<form action="AUserlist.do" name="page" method="get">
						<c:set var="count" value="${maxPage}" />
							<input type="hidden" name="startDate" id="startDate" value="${dateList[0]}">
							<input type="hidden" name="endDate" id="endDate" value="${dateList[fn:length(dateList)-1]}">
						<c:forEach begin="1" end="${count}" var="i">
							<input type="submit" name="pageNum" value='${i}'>  &nbsp; 
						</c:forEach>
						</form>
					</div>
						
				</div>
			</main>
		</div>
	</div>

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
	    var userContext = document.getElementById('userChart');
		SingleBarChart(userContext, ${requestScope.dailyDate}, '신규가입자' ,${requestScope.dailyNS});  
    </script>
    
    
    
    
	<script>


	
	
	
	</script>

</body>
</html>