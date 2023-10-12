package com.javalec.bbs.command;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Dao_kkg;
import com.javalec.bbs.dao.ChartDao_kkg;
import com.javalec.bbs.dto.AdminExtra_Dto_kkg;
import com.javalec.bbs.dto.AdminOrdering_Dto_kkg;

public class showOrderListCommand_kkg implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 상단의 그래프를 가져오기 위한 작업 / 페이지 번호 확정 작업

		// x축 (날짜) 리스트 만들기.
		// String startDate ;
		// String endDate;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat tempFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Timestamp startday = null;
		Timestamp endday = null;
		int pageNum;

		if (request.getParameter("pageNum") == null) {

			pageNum = Integer.parseInt((request.getAttribute("pnum").toString()));

		} else {
			pageNum = Integer.parseInt((request.getParameter("pageNum")));

		}

		if (request.getParameter("startDate") == null) {

			startday = new Timestamp(((Date) request.getAttribute("startDate_chart")).getTime());
			endday = new Timestamp(((Date) request.getAttribute("endDate_chart")).getTime());
			startday.setHours(0);
			startday.setMinutes(0);
			startday.setSeconds(0);
			endday.setHours(0);
			endday.setMinutes(0);
			endday.setSeconds(0);

		} else {
			try {
				startday = new Timestamp(tempFormat.parse(request.getParameter("startDate") + " 00:00:00").getTime());
				endday = new Timestamp(tempFormat.parse(request.getParameter("endDate") + " 00:00:00").getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// String 배열로 바꿀거임. chart.js 에는 string 배열로 들어가야됨.

		// 그래프 그리기 위한 dao 불러오기

		ChartDao_kkg chartdao = new ChartDao_kkg();

		// 그래프 그리기 위한 데이터 셋팅하기.

		// 1번째 차트의 x 축을 위한 날짜데이터 및 월 데이터 셋팅하기
		// 날짜 데이터 셋팅하기.
		String cid = request.getParameter("cid"); // 일단 깊. 왜있는지 나도모름
		// 날짜 리스트 셋팅하기
		List<Date> dateList = getDateList(startday, endday); // method01. Timestamp 2개로 List<DATE> 만들어오기.
		List<String> dateListStr = new ArrayList<>();
		for (Date date : dateList) {
			String dateString = dateFormat.format(date);

			dateListStr.add("'" + dateString + "'");

		}

		// 월 데이터 셋팅하기
		List<String> monthListstr = getMonthList(startday, endday);

		// 1번째 차트 : 선형 그래프 의 y축 값들 셋팅하기
		// Dao에서 가져오기 위한 dto 배열
		ArrayList<AdminExtra_Dto_kkg> dailydtos = chartdao.dailyGraph(startday, endday); // (date, dailySale,
																							// dailyOrders) 순으로 저장되어잇음.
																							// Date date, int sales, int
																							// ordercount
		ArrayList<AdminExtra_Dto_kkg> monthlydtos = chartdao.monthlyGraph(startday, endday); // String month, int sales,
																								// int ordercount

		// chart.js 에서 쉽게 쓰려면 정수배열로 넘기는게 좋다.
		List<Integer> dailySaleList = getDailySaleList(dateList, dailydtos); // method02. dailySale 저장하기 위한 메소드 실행. 하단
		List<Integer> dailyOrderList = getDailyOrderList(dateList, dailydtos); // method 05. ordercount 가져오기위한 메소드.
		List<Integer> monthlySaleList = getMonthlySaleList(monthListstr, monthlydtos); // method03. monthlysale 저장하기 위한
		List<Integer> monthlyOrderList = getMonthlyOrderList(monthListstr, monthlydtos); // method06. monthlyOrder 저장하기

		// 차트 02_카테고리별 매출 비율 그래프 / 카테고리별 판매량 비율 분석 //

		ArrayList<AdminExtra_Dto_kkg> categoryDtos = chartdao.categoryChart(startday, endday); // int No , int 카테고리번호
																								// (c_num) , String
																								// c_name, int 판매량
																								// orders , int 매출액
																								// sales,
		List<String> catrgoryName = getCategoryName(categoryDtos);
		List<Integer> categoryOrders = getOrders(categoryDtos); // method 08
		List<Integer> categorySales = getSales(categoryDtos); // method 09

		// 차트 03_남녀별 매출 비율 그래프 / 남녀별 주문 건수 비율

		ArrayList<AdminExtra_Dto_kkg> genderDtos = chartdao.genderChart(startday, endday); // gender , categoryName ,
																							// orders , sales
		List<String> genderName = new ArrayList<>();
		genderName.add("'남자'");
		genderName.add("'여자'");
		List<Integer> genderOrders = getOrders(genderDtos); // method 08
		List<Integer> genderSales = getSales(genderDtos); // method 09

//		// 차트 04_카테고리별 남녀 구매량/주문 건수/매출액 차이
//		ArrayList<AdminExtra_Dto_kkg> gendercategoryDtos = chartdao.gendercategoryChart(startday, endday); // seq ,
//																											// categoryName
//																											// , orders
//																											// , sales
//		List<Integer> categoryGDOrders_male = new ArrayList<>(); // 남자들의 조명 -> 미니어쳐 -> 의자 주문수량
//		List<Integer> categoryGDSales_male = new ArrayList<>(); // 남자들의 조명 -> 미니어쳐 -> 의자 매출액
//		List<Integer> categoryGDOrders_female = new ArrayList<>(); // 남자들의 조명 -> 미니어쳐 -> 의자 주문수량
//		List<Integer> categoryGDSales_female = new ArrayList<>(); // 남자들의 조명 -> 미니어쳐 -> 의자 매출액
//
//		for (int i = 0; i < gendercategoryDtos.size(); i += 2) {
//			categoryGDOrders_male.add(gendercategoryDtos.get(i).getOrders());
//			categoryGDSales_male.add(gendercategoryDtos.get(i).getSales());
//		}
//		for (int i = 1; i < gendercategoryDtos.size(); i += 2) {
//			categoryGDOrders_female.add(gendercategoryDtos.get(i).getOrders());
//			categoryGDSales_female.add(gendercategoryDtos.get(i).getSales());
//		}

		// 차트_05_ 최대 매출 상품 top5 / 최대 판매량 상품 top5
		ArrayList<AdminExtra_Dto_kkg> MaxOrdersDtos = chartdao.maxOrdersChart(startday, endday); // pname, porders
		ArrayList<AdminExtra_Dto_kkg> MaxSalesDtos = chartdao.maxSalesChart(startday, endday); // pname, porders sales
																								// 이지만 dto 상으로는 orders 로
																								// 있다. 위에거랑 형식이 동일해서 그냥
																								// 놔두었음.

		List<String> maxPnameOrders = getPname(MaxOrdersDtos);
		List<Integer> maxOrders = getOrders(MaxOrdersDtos);

		List<String> MaxPnameSales = getPname(MaxSalesDtos);
		List<Integer> maxSales = getOrders(MaxSalesDtos);

		// 셋팅한값 어트리뷰트로 넘기기

		request.setAttribute("dateList", dateListStr);
		request.setAttribute("monthList", monthListstr);
		request.setAttribute("dailySales", dailySaleList);
		request.setAttribute("dailyOrders", dailyOrderList);
		request.setAttribute("monthlySales", monthlySaleList);
		request.setAttribute("monthlyOrders", monthlyOrderList);

		request.setAttribute("categoryList", catrgoryName);
		request.setAttribute("categoryOrders", categoryOrders);
		request.setAttribute("categorySales", categorySales);

		request.setAttribute("genderList", genderName);
		request.setAttribute("genderOrders", genderOrders);
		request.setAttribute("genderSales", genderSales);

//		request.setAttribute("categoryGenderOrders_male", categoryGDOrders_male);
//		request.setAttribute("categoryGenderSales_male", categoryGDSales_male);
//		request.setAttribute("categoryGenderOrders_female", categoryGDOrders_female);
//		request.setAttribute("categoryGenderSales_female", categoryGDSales_female);

		request.setAttribute("maxPnameOrders", maxPnameOrders);
		request.setAttribute("maxOrders", maxOrders);
		request.setAttribute("maxPnameSales", MaxPnameSales);
		request.setAttribute("maxSales", maxSales);

	} // end of execute

	private static List<Date> getDateList(Timestamp startday, Timestamp endday) {
		List<Date> dateList = new ArrayList<>();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startday);

		while (!calendar.getTime().after(endday)) {
			Date currentDate = new Date(calendar.getTimeInMillis());
			dateList.add(currentDate);
			calendar.add(Calendar.DATE, 1);
		}

		return dateList;
	}

	// -------------------------method02. DailySale 저장하기 getDailySaleList 메소드
	// 시작-------------------------------------------
	// -------------------------method02. g회원수 가져올때 사용해도
	// 됨-------------------------------------------

	private List<Integer> getDailySaleList(List<Date> dateList, ArrayList<AdminExtra_Dto_kkg> ddrs) {

		List<Integer> saleList = new ArrayList<>();

		// 날짜 별로 판매금액 가져오는 중. / 판매 없는 날은 0넣어야 함.

		// while 문을 돌려서, 매출액 리스트를 채울거임. while 문 조건은 다음과 같음.
		// 실제 기간내 모든 날짜가 담긴 리스트 : dateList 의 길이를 len 으로 지정함.
		// k는 dateList 내 날짜를 탐색할 번호임. 이 번호가 dateList의 길이보다 작아야함.(배열의 시작은 0이기 때문임)
		// j는 DB에서 받아온 Arraylist<dto> 안에 있는 날짜들을 탐색하는 번호임.
		// 판매가 없는 날은 포함되지 않았을 것이기에, 탐색번호를 j,k로 구분하였음.

		int j = 0;
		int k = 0;
		int len = dateList.size(); // 실제 기간내 모든 기간이

		// --------------while문 설명 : while문이 돌아가는 조건은 위에서 이야기하였음.----------------
		// try 문을 쓴 이유는 db에서 가져온 값에 null point exception 이 발생할 것이기 때문임.
		// 최근 몇일간 판매량이 없었다면,혹은 당일 판매량이 아직 없다면 null point exception이 발생할 수 밖에 없음.
		// 따라null point exception 이 발생하면, k값만 늘려서 반복문을 지속하기 위함. (근데 j값 늘려도 상관 없음. 어차피 db
		// 에서 가져온 데이터는 끝일거라서.)
		// try 문 DB 값의 날짜와, real 날짜 리스트의 값을 탐색함.

		// ------ try 안의 if 문 설---------
		// if 문안에서 위에 탐색한 두 날짜가 없으면, saleList에다가 0 을 추가함. ( 해당하는 Real_date에 해당하는 sale 값이
		// 없다는 소리기 때문)
		// DB 값의 날짜는 그대로 두고, real 날짜만 다음 날짜를 탐색해 오기 위해 k에만 1을 더해줌.

		// 위에 탐색한 두 날짜가 같으면 해당하는 db 값의 sale 데이터를 saleList에 추가함.
		// j와 k를 둘다 ++함.

		while (k < len) {

			try {
				// DB에서 가져온 목록에서 오늘 날짜의 날이 있는지 없는지 검증.
				Date DB_date = ddrs.get(j).getDate(); // DB 데이터의 날짜.
				Date Real_date = dateList.get(k); // 실제 있어야 하는 날짜.

//DateTimeFormatter 객체를 생성하고, 날짜 형식을 설정합니다.
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//날짜를 문자열로 변환합니다.

				if (dtf.format(DB_date.toLocalDate()).equals(dtf.format(Real_date.toLocalDate()))) {

					saleList.add(ddrs.get(j).getSales()); // 날짜가 있으면
					j++;
					k++;
				}

				else if (DB_date.compareTo(Real_date) > 0) {
					saleList.add(0); // 날짜가 없으면 (같지 않으면) 0 더하기.
					k++;

				} else {
					j++;
				}

			} catch (IndexOutOfBoundsException e) {
				saleList.add(0);
				// j++;
				k++;
			} // outofbound에러 처리 끝.
		} // daily sale을 위한 while 끝

		return saleList;

	} // -------------------------DailySale 저장하기 getDailySaleList
		// 끝-------------------------------------------

	// -------------------------method 03. TimeStamp 두개로 월 목록 뽑아내기.
	// ---------------------
	public static List<String> getMonthList(Timestamp startday, Timestamp endday) {
		List<String> yearMonthList = new ArrayList<>();
		SimpleDateFormat monthFormatter = new SimpleDateFormat("yyyy-MM");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startday);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		while (calendar.getTime().before(endday) || calendar.getTime().equals(endday)) {
			String monthString = monthFormatter.format(calendar.getTime());
			yearMonthList.add(monthString);
			calendar.add(Calendar.MONTH, 1);
		}

		return yearMonthList;
	} // 월 목록 뽑아내기 끝~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// -------------------------method04. monthlySale 저장하기 메소드
	// 시작-------------------------------------------

	private List<Integer> getMonthlySaleList(List<String> monthListStr, ArrayList<AdminExtra_Dto_kkg> mdrs) {

		List<Integer> saleList = new ArrayList<>();

		int j = 0;
		int k = 0;
		int len = monthListStr.size(); // 실제 기간내 모든 기간이

		while (k < len) {

			try {
				// DB에서 가져온 목록에서 오늘 날짜의 날이 있는지 없는지 검증.
				String DB_month = mdrs.get(j).getMonth(); // DB 데이터의 날짜.
				String Real_month = monthListStr.get(k); // 실제 있어야 하는 날짜.

				if (!DB_month.equals(Real_month)) {
					saleList.add(0); // 날짜가 없으면 (같지 않으면) 0 더하기.
					k++;
				} else {
					saleList.add(mdrs.get(j).getSales()); // 날짜가 있으면
					j++;
					k++;
				}
			} catch (IndexOutOfBoundsException e) {
				saleList.add(0);
				// j++;
				k++;
			} // outofbound에러 처리 끝.
		} // daily sale을 위한 while 끝

		return saleList;

	} // -------------------------monthlySale 저장하기
		// 끝-------------------------------------------

	// -------------------------method05.매출이 아닌 주문수량 으로
	// 바꾸었음.-------------------------------------------

	private List<Integer> getDailyOrderList(List<Date> dateList, ArrayList<AdminExtra_Dto_kkg> ddrs) {

		List<Integer> OrdercountList = new ArrayList<>();

		int j = 0;
		int k = 0;
		int len = dateList.size(); // 실제 기간내 모든 기간이

		// j와 k를 둘다 ++함.

		while (k < len) {

			try {
				// DB에서 가져온 목록에서 오늘 날짜의 날이 있는지 없는지 검증.
				Date DB_date = ddrs.get(j).getDate(); // DB 데이터의 날짜.
				Date Real_date = dateList.get(k); // 실제 있어야 하는 날짜.

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				// 날짜를 문자열로 변환합니다.

				if (dtf.format(DB_date.toLocalDate()).equals(dtf.format(Real_date.toLocalDate()))) {

					OrdercountList.add(ddrs.get(j).getOrdercount()); // 날짜가 있으면
					j++;
					k++;
				}

				else if (DB_date.compareTo(Real_date) > 0) {
					OrdercountList.add(0); // 날짜가 없으면 (같지 않으면) 0 더하기.
					k++;

				} else {
					j++;
				}

			} catch (IndexOutOfBoundsException e) {
				OrdercountList.add(0);
				// j++;
				k++;
			} // outofbound에러 처리 끝.
		} // daily sale을 위한 while 끝

		return OrdercountList;

	} // -------------------------DailySale 저장하기 getDailySaleList
		// 끝-------------------------------------------

	// -------------------------method06. monthlyOrdercount 저장하기 메소드
	// 시작-------------------------------------------

	private List<Integer> getMonthlyOrderList(List<String> monthListStr, ArrayList<AdminExtra_Dto_kkg> mdrs) {

		List<Integer> orderCountList = new ArrayList<>();

		int j = 0;
		int k = 0;
		int len = monthListStr.size(); // 실제 기간내 모든 기간이

		while (k < len) {

			try {
				// DB에서 가져온 목록에서 오늘 날짜의 날이 있는지 없는지 검증.
				String DB_month = mdrs.get(j).getMonth(); // DB 데이터의 날짜.
				String Real_month = monthListStr.get(k); // 실제 있어야 하는 날짜.

				if (!DB_month.equals(Real_month)) {
					orderCountList.add(0); // 날짜가 없으면 (같지 않으면) 0 더하기.
					k++;
				} else {
					orderCountList.add(mdrs.get(j).getOrdercount()); // 날짜가 있으면
					j++;
					k++;
				}
			} catch (IndexOutOfBoundsException e) {
				orderCountList.add(0);
				// j++;
				k++;
			} // outofbound에러 처리 끝.
		} // daily sale을 위한 while 끝

		return orderCountList;

	} // -------------------------monthlyOrder 저장하기
		// 끝-------------------------------------------

	// ------------method 07 : 가져온 dto 리스트에서 카테고리 이름만 빼서 배열로 반환하기 -----------
	private List<String> getCategoryName(ArrayList<AdminExtra_Dto_kkg> dtos) {
		List<String> categoryNames = new ArrayList<>();

		for (int i = 0; i < dtos.size(); i++) {
			categoryNames.add("'"+dtos.get(i).getCategoryName()+"'");

		}

		return categoryNames;

	}// ----------------- method 07 끝

	// ---------------method 08 : 가져온 dto 에서 orders 빼오기.

	private List<Integer> getOrders(ArrayList<AdminExtra_Dto_kkg> dtos) {
		List<Integer> Orders = new ArrayList<>();

		for (int i = 0; i < dtos.size(); i++) {
			Orders.add(dtos.get(i).getOrders());

		}

		return Orders;

	}// ----------------method 08 끝

	// ---------------method 09 : 가져온 dto 에서 Sales 빼오기.

	private List<Integer> getSales(ArrayList<AdminExtra_Dto_kkg> dtos) {
		List<Integer> Sales = new ArrayList<>();

		for (int i = 0; i < dtos.size(); i++) {
			Sales.add(dtos.get(i).getSales());

		}

		return Sales;

	}// ----------------method 09 끝

	// ------------method 10 : 가져온 dto 리스트에서 상품 이름만 빼서 배열로 반환하기 -----------
	private List<String> getPname(ArrayList<AdminExtra_Dto_kkg> dtos) {
		List<String> productName = new ArrayList<>();

		for (int i = 0; i < dtos.size(); i++) {
			productName.add("'" + dtos.get(i).getPname() + "'");

		}

		return productName;

	}// ----------------- method 10 끝

}// endGame
