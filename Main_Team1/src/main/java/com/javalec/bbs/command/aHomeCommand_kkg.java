package com.javalec.bbs.command;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Dao_kkg;
import com.javalec.bbs.dao.ChartDao_kkg;
import com.javalec.bbs.dto.AdminExtra_Dto_kkg;


public class aHomeCommand_kkg implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		

		
		
        //  *************** daily chart를 위한 데이터 가져오기************** 
	
		// ----------------- 차트를 그리기 위한 x축 (날짜) 리스트를 string 으로 만들어서 request 하기
		// 오늘날짜를 endday로 선언. (어제날짜까지의 매출을 구하기 위함.)
		// 시작날짜는 14일전을 startday로 선언. 오늘이 15일이면 5월1일이 시작날짜.지난 2주 (14일) + 오늘 데이터 까지 받아올거임.
		// 여기까지는 Timstamp형식임. (sql에 timestamp 형식으로 되어있어서임. 나중에 여유되면 그냥 DATE 형식으로 선언해도 될거도 같은데 일단 timestamp 형식으로 해서 구현을 1순위로 함.
		// List<Date> 형식으로 대상 날짜들을 만들었음.

		
        Date sqlDate = new Date(System.currentTimeMillis()); 
        Timestamp endday = new Timestamp(sqlDate.getTime());
        Calendar tempday = Calendar.getInstance();
        tempday.setTime(endday);
        tempday.add(Calendar.DAY_OF_MONTH, -14);
        Timestamp startday = new Timestamp(tempday.getTimeInMillis());
        List<Date> dateList = getDateList(startday, endday); //method01. Timestamp 2개로 List<DATE>  만들어오기.
        List<String> dateListStr = new ArrayList<>();
        
        // String 배열로 바꿀거임.
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");

        for (Date date : dateList) {
            String dateString = dateFormat.format(date);
            dateListStr.add("'"+dateString+"'");
        }

        
        
        
        //------------------------필요한 매출/주문건수 데이터 받아오기.-------------------
        //chartdao (차트를 그리기 위한 Dao)에서 필요한 매출액 혹은 주문건수를 받아옴.
        //받아온 자료들을 List<Integer> 형식으로 저장해야함. 
        //특별히 고려해야 할 상황1. 중간에 매출액 및 주문건수가 없는 날은 0으로 해야함.
        //특별히 고려해야 할 상황2. 최근 몇일동안 매출액 및 주문 건수가 없는 날은 위와 별도로 고려해야함.
        
        
        // dao 에서 data 받아오고, 넘겨줄 형식 List<integer> 선언하기
        ChartDao_kkg dao = new ChartDao_kkg(); 
        ArrayList<AdminExtra_Dto_kkg> ddrs = dao.dailyGraph(startday, endday);
        
        //System.out.println("*********** 체크해야됨  : dateList : "+ dateList);
        
        
        List<Integer> saleList = getDailySaleList(dateList, ddrs); //method02. dailySale 저장하기 위한 메소드 실행. 하단 function 기능에서 설명
        List<Integer> orderList = getDailyOrderList(dateList, ddrs); //method03. dailyOrders 저장하기 위한 메소드 실행. 

    	// daily 그래프 끝.
        
        
        
        
        
        //  *************** Monthly chart를 위한 데이터 가져오기************** 
        tempday.setTime(endday);
        tempday.add(Calendar.MONTH, -12);
        Timestamp mstartday = new Timestamp(tempday.getTimeInMillis());
        List<String> monthList =  getYearMonthList(endday, 12); //데이터 받아올떄 활용하기 위한 형식의 리스트
        List<String> monthListStr = new ArrayList<>(); 			// Script에서 오류 안일으키기 위한 형식의 리스트

        for (String month : monthList) {
            monthListStr.add("'"+month+"'");
        }
        
        
        
        ArrayList<AdminExtra_Dto_kkg> mdrs = dao.monthlyGraph(mstartday, endday);
        List<Integer> monthSaleList = getMonthlySaleList(monthList, mdrs);
        System.out.println("mdrs : "+ mdrs);
        List<Integer> monthOrderList = getMonthlyOrderList(monthList, mdrs);
      
        
        
        
        
        
        
        
        //  *************** 날짜별 신규 가입자 데이터 가져오기 (daily)************** 
        
       
        
        ArrayList<AdminExtra_Dto_kkg> DNrs = dao.dailyNSGraph(startday, endday); //Daily New subscriber
        System.out.println("DNrs 의 값 : " + DNrs.get(0).getDate());
        List<Integer> dailyNSList = getDailySaleList(dateList, DNrs); //method02. dailySale 랑 같이써도 상관 없음.
        //추후에 탈퇴자 내용포함 시킬 것.
        

        
        //  *************** 회원수 현황을 위한 데이터 가져오기************** 
        
        //시작일 기준 가입일자가 시작일 이전인 수를 카운 

        
        Admin_Dao_kkg dao_stocks = new Admin_Dao_kkg(); 
        ArrayList<AdminExtra_Dto_kkg> stocks = dao_stocks.getOutStocks();
        		
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        // 가져온 데이터들 request 에 셋팅하기
        
        //일별데이터
        request.setAttribute("dailyDate", dateListStr);
        request.setAttribute("dailySale", saleList);
        request.setAttribute("dailyOrder", orderList);

        //월별데이터
        request.setAttribute("monthlyMonth", monthListStr);
        request.setAttribute("monthlyOrder", monthOrderList);
        request.setAttribute("monthlySale", monthSaleList);
        
        //신규가입자 daily
        request.setAttribute("dailyNS", dailyNSList);
        
        request.setAttribute("OutofStocks", stocks);
        
        
        

	
	} //command 파트 끝. 이 아래는 기능부분 입니다.
	
	
	
	
	
	
	
	// ------------------- function -------------------
	
	
	// ----------------- method01. 실제로 JSP로 넘겨줄 Date 변수들의 리스트 목록 dateList 만들기. ----------------
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
	

	
	
	
	
	
	
    // -------------------------method02. DailySale 저장하기  getDailySaleList 메소드 시작-------------------------------------------
	// -------------------------method02. g회원수 가져올때 사용해도 됨-------------------------------------------
	
	private List<Integer> getDailySaleList(List<Date> dateList, ArrayList<AdminExtra_Dto_kkg> ddrs){
		
        List<Integer> saleList = new ArrayList<>();

        // 날짜 별로 판매금액 가져오는 중. / 판매 없는 날은 0넣어야 함.
   
        //while 문을 돌려서, 매출액 리스트를 채울거임. while 문 조건은 다음과 같음.
        //실제 기간내 모든 날짜가 담긴 리스트 : dateList 의 길이를 len 으로 지정함.
        //k는 dateList 내 날짜를 탐색할 번호임. 이 번호가 dateList의 길이보다 작아야함.(배열의 시작은 0이기 때문임)
        //j는 DB에서 받아온 Arraylist<dto> 안에 있는 날짜들을 탐색하는 번호임.
        //판매가 없는 날은 포함되지 않았을 것이기에, 탐색번호를 j,k로 구분하였음.
        
        int j = 0;
        int k = 0;
        int len = dateList.size(); //실제 기간내 모든 기간이 
        
        
        // --------------while문 설명 : while문이 돌아가는 조건은 위에서 이야기하였음.----------------
        // try 문을 쓴 이유는 db에서 가져온 값에 null point exception 이 발생할 것이기 때문임.
        // 최근 몇일간 판매량이 없었다면,혹은 당일 판매량이 아직 없다면 null point exception이 발생할 수 밖에 없음. 
        // 따라null point exception 이 발생하면, k값만 늘려서 반복문을 지속하기 위함. (근데 j값 늘려도 상관 없음. 어차피 db 에서 가져온 데이터는 끝일거라서.)
        // try 문 DB 값의 날짜와, real 날짜 리스트의 값을 탐색함.
        
        // ------ try 안의 if 문 설---------
        // if 문안에서 위에 탐색한 두 날짜가 없으면,  saleList에다가 0 을 추가함. ( 해당하는 Real_date에 해당하는 sale 값이 없다는 소리기 때문)
        // DB 값의 날짜는 그대로 두고, real 날짜만 다음 날짜를 탐색해 오기 위해 k에만 1을 더해줌.
        
        // 위에 탐색한 두 날짜가 같으면 해당하는 db 값의 sale 데이터를 saleList에 추가함.
        // j와 k를 둘다 ++함.
        
		while (k < len) {
//System.out.println("j 값 : " + j);
//System.out.println("k 값 : " + k);

			try {
				// DB에서 가져온 목록에서 오늘 날짜의 날이 있는지 없는지 검증.
				Date DB_date = ddrs.get(j).getDate(); // DB 데이터의 날짜.
				Date Real_date = dateList.get(k); // 실제 있어야 하는 날짜.
//System.out.println("DB 날짜 : " + DB_date);
//System.out.println("실제 날짜 : " + Real_date);
//System.out.println(DB_date.compareTo(Real_date));

//DateTimeFormatter 객체를 생성하고, 날짜 형식을 설정합니다.
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//날짜를 문자열로 변환합니다.


			if(dtf.format(DB_date.toLocalDate()).equals(dtf.format(Real_date.toLocalDate()))) {
				
				saleList.add(ddrs.get(j).getSales()); // 날짜가 있으면
			System.out.println("일치한 데이터베이스 날짜 : " + DB_date);
			System.out.println("일치한 실제날짜리스 날짜 : " + Real_date);
			System.out.println("일치한 날짜의 매출액 : " + ddrs.get(j).getSales());
				j++;
				k++;
			}

			else if (DB_date.compareTo(Real_date)>0) {
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
      
		
		
		
	}   // -------------------------DailySale 저장하기 getDailySaleList 끝-------------------------------------------
	
	
    // -------------------------method03. DailyOrder 저장하기  getDailyOrderList 메소드 시작-------------------------------------------
	
	private List<Integer> getDailyOrderList(List<Date> dateList, ArrayList<AdminExtra_Dto_kkg> ddrs){
		
        List<Integer> OrderList = new ArrayList<>();

        // 날짜 별로 판매금액 가져오는 중. / 판매 없는 날은 0넣어야 함.
   
        //while 문을 돌려서, 매출액 리스트를 채울거임. while 문 조건은 다음과 같음.
        //실제 기간내 모든 날짜가 담긴 리스트 : dateList 의 길이를 len 으로 지정함.
        //k는 dateList 내 날짜를 탐색할 번호임. 이 번호가 dateList의 길이보다 작아야함.(배열의 시작은 0이기 때문임)
        //j는 DB에서 받아온 Arraylist<dto> 안에 있는 날짜들을 탐색하는 번호임.
        //판매가 없는 날은 포함되지 않았을 것이기에, 탐색번호를 j,k로 구분하였음.
        
        int j = 0;
        int k = 0;
        int len = dateList.size(); //실제 기간내 모든 기간이 
        
        
        // --------------while문 설명 : while문이 돌아가는 조건은 위에서 이야기하였음.----------------
        // try 문을 쓴 이유는 db에서 가져온 값에 null point exception 이 발생할 것이기 때문임.
        // 최근 몇일간 판매량이 없었다면,혹은 당일 판매량이 아직 없다면 null point exception이 발생할 수 밖에 없음. 
        // 따라null point exception 이 발생하면, k값만 늘려서 반복문을 지속하기 위함. (근데 j값 늘려도 상관 없음. 어차피 db 에서 가져온 데이터는 끝일거라서.)
        // try 문 DB 값의 날짜와, real 날짜 리스트의 값을 탐색함.
        
        // ------ try 안의 if 문 설---------
        // if 문안에서 위에 탐색한 두 날짜가 없으면,  saleList에다가 0 을 추가함. ( 해당하는 Real_date에 해당하는 sale 값이 없다는 소리기 때문)
        // DB 값의 날짜는 그대로 두고, real 날짜만 다음 날짜를 탐색해 오기 위해 k에만 1을 더해줌.
        
        // 위에 탐색한 두 날짜가 같으면 해당하는 db 값의 sale 데이터를 saleList에 추가함.
        // j와 k를 둘다 ++함.
        
		while (k < len) {
//System.out.println("j 값 : " + j);
//System.out.println("k 값 : " + k);

			try {
				// DB에서 가져온 목록에서 오늘 날짜의 날이 있는지 없는지 검증.
				Date DB_date = ddrs.get(j).getDate(); // DB 데이터의 날짜.
				Date Real_date = dateList.get(k); // 실제 있어야 하는 날짜.
//System.out.println("DB 날짜 : " + DB_date);
//System.out.println("실제 날짜 : " + Real_date);

//DateTimeFormatter 객체를 생성하고, 날짜 형식을 설정합니다.
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//날짜를 문자열로 변환합니다.


			if(dtf.format(DB_date.toLocalDate()).equals(dtf.format(Real_date.toLocalDate()))) {
				
				OrderList.add(ddrs.get(j).getOrdercount()); // 날짜가 있으면
			System.out.println("일치한 데이터베이스 날짜 : " + DB_date);
			System.out.println("일치한 실제날짜리스 날짜 : " + Real_date);
			System.out.println("일치한 날짜의 주문수 : " + ddrs.get(j).getSales());
				j++;
				k++;
			}

			else if (DB_date.compareTo(Real_date)>0) {
				OrderList.add(0); // 날짜가 없으면 (같지 않으면) 0 더하기.
				k++;
			
			
			} else {
				
				j++;
			}
			} catch (IndexOutOfBoundsException e) {
				OrderList.add(0);
				// j++;
				k++;
			} // outofbound에러 처리 끝.
		} // daily sale을 위한 while 끝
        
		
		
		return OrderList;
      
		
		
		
	}   // -------------------------method04. 월별 리스트 만들 끝-------------------------------------------
	
	
	
    public static List<String> getYearMonthList(Timestamp endday, int numMonths) {
        List<String> yearMonthList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endday);
        calendar.add(Calendar.MONTH,-12);
   
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");

        // 현재 월부터 numMonths 개월 전까지 루프
        for (int i = 0; i < numMonths; i++) {
            Date currentDate = new Date(calendar.getTimeInMillis());
            String yearMonth = dateFormat.format(currentDate);
            yearMonthList.add(yearMonth);

            calendar.add(Calendar.MONTH, +1); // 이전 월로 이동
        }

        return yearMonthList;
    }
	
    
    
    
    // -------------------------method05. monthlySale 저장하기 메소드 시작-------------------------------------------
	
	private List<Integer> getMonthlySaleList(List<String> monthListStr, ArrayList<AdminExtra_Dto_kkg> mdrs){
		
        List<Integer> saleList = new ArrayList<>();

 
        
        int j = 0;
        int k = 0;
        int len = monthListStr.size(); //실제 기간내 모든 기간이 
        
        

        
		while (k < len) {
//System.out.println("j 값 : " + j);
//System.out.println("k 값 : " + k);

			try {
				// DB에서 가져온 목록에서 오늘 날짜의 날이 있는지 없는지 검증.
				String DB_month = mdrs.get(j).getMonth(); // DB 데이터의 날짜.
				String Real_month = monthListStr.get(k); // 실제 있어야 하는 날짜.
//System.out.println("DB Month : " + DB_month);
//System.out.println("실제 Month : " + Real_month);

				if (!DB_month.equals(Real_month)) {
					saleList.add(0); // 날짜가 없으면 (같지 않으면) 0 더하기.
					k++;
				} else {
					saleList.add(mdrs.get(j).getSales()); // 날짜가 있으면
//System.out.println("일치한 데이터베이스 month : " + DB_month);
//System.out.println("일치한 실제날짜리스 month : " + Real_month);
//System.out.println("일치한 월의 매출액 : " + mdrs.get(j).getSales());
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
      
		
		
		
	}   // -------------------------monthlySale 저장하기 끝-------------------------------------------
    
	
    // -------------------------method06. monthlySale 저장하기 메소드 시작-------------------------------------------
	
	private List<Integer> getMonthlyOrderList(List<String> monthListStr, ArrayList<AdminExtra_Dto_kkg> mdrs){
		
        List<Integer> orderList = new ArrayList<>();

 
        
        int j = 0;
        int k = 0;
        int len = monthListStr.size(); //실제 기간내 모든 기간이 
        
        

        
		while (k < len) {
//System.out.println("j 값 : " + j);
//System.out.println("k 값 : " + k);

			try {
				// DB에서 가져온 목록에서 오늘 날짜의 날이 있는지 없는지 검증.
				String DB_month = mdrs.get(j).getMonth(); // DB 데이터의 날짜.
				String Real_month = monthListStr.get(k); // 실제 있어야 하는 날짜.
//System.out.println("DB Month : " + DB_month);
//System.out.println("실제 Month : " + Real_month);

				if (!DB_month.equals(Real_month)) {
					orderList.add(0); // 날짜가 없으면 (같지 않으면) 0 더하기.
					k++;
				} else {
					orderList.add(mdrs.get(j).getOrdercount()); // 날짜가 있으면
//System.out.println("일치한 데이터베이스 month : " + DB_month);
//System.out.println("일치한 실제날짜리스 month : " + Real_month);
//System.out.println("일치한 월의 매출액 : " + mdrs.get(j).getOrdercount());
					j++;
					k++;
				}
			} catch (IndexOutOfBoundsException e) {
				orderList.add(0);
				// j++;
				k++;
			} // outofbound에러 처리 끝.
		} // daily sale을 위한 while 끝
        
		
		
		return orderList;
      
		
		
		
	}   // -------------------------monthlySale 저장하기 끝-------------------------------------------
	
	
	
	
	
	
	

} // END GAME NOW
