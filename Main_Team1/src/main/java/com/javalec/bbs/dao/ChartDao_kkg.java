package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.AdminExtra_Dto_kkg;


public class ChartDao_kkg {
	
	DataSource dataSource;

	
	
	public ChartDao_kkg() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
	
	
	
	public ArrayList<AdminExtra_Dto_kkg> dailyGraph(Timestamp startday, Timestamp endday){
		ArrayList<AdminExtra_Dto_kkg> dtos= new ArrayList<AdminExtra_Dto_kkg>();
				
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		Date startdate = new Date(startday.getTime()); 
		Date enddate = new Date(endday.getTime()); 
		
		
		//가져오는 값들 int bid,  int sale, 
		try {
			connection =dataSource.getConnection();
			String sql_select = "select ROW_NUMBER() OVER (ORDER BY Date(odate)) AS No, sum(oprice) as sale, count(*) as ocount, Date(odate) as date ";
			String sql_from =	" from ordering";
			String sql_where = 	" where odate >= ? AND odate <= ? group by date(odate);";
			String sql_group = "";
			String sql = sql_select + sql_from + sql_where+sql_group;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, startday);
			preparedStatement.setTimestamp(2, endday);
			
			resultSet = preparedStatement.executeQuery();
			int i = 0;
			while(resultSet.next()) {
				
					//System.out.println("i값 : "+ i);
					//System.out.println("더하는 날짜 : "+oneday/(60*60*24*1000) );
				int dailySales = resultSet.getInt("sale");
				int dailyOrders = resultSet.getInt("ocount");
				Date date = resultSet.getDate("date");
				AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg (date, dailySales, dailyOrders);
				dtos.add(dto);
				
			i++;
			

			}
			connection.close();
		} catch (Exception e) {
				// TODO: handle exception
			System.out.println("실패");
			
				e.printStackTrace();
			}
		
		return dtos;
	}
	
	
	
	
	
	public ArrayList<AdminExtra_Dto_kkg> monthlyGraph(Timestamp startday, Timestamp endday){
		
		ArrayList<AdminExtra_Dto_kkg> dtos= new ArrayList<AdminExtra_Dto_kkg>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		

		Date startdate = new Date(startday.getTime()); 
		Date enddate = new Date(endday.getTime()); 
		
		
		
		
		//가져오는 값들 int bid,  int sale, 
				try {
					connection =dataSource.getConnection();
					String sql_select = "select ROW_NUMBER() OVER (ORDER BY Date_format(odate,'%Y-%m')) AS No, sum(oprice) as sale, count(*) as ocount, Date_format(odate,'%Y-%m') as month ";
					String sql_from =	" from ordering";
					String sql_where = 	" where odate >= ? AND odate <= ? group by Date_format(odate,'%Y-%m')";
					String sql_group = "";
					String sql = sql_select + sql_from + sql_where+sql_group;
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setTimestamp(1, startday);
					preparedStatement.setTimestamp(2, endday);
					
					System.out.println(sql);
					resultSet = preparedStatement.executeQuery();
					int i = 0;
					while(resultSet.next()) {
						
						int monthlySales = resultSet.getInt("sale");
						int monthlyOrders = resultSet.getInt("ocount");
						String month = resultSet.getString("month");
						
							//System.out.println("날짜 : " +date);
							//System.out.println("매출 : "+ dailySales);
						AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg (month, monthlySales,monthlyOrders);
						dtos.add(dto);
						    
					System.out.println("완료된 month : "+month);
					System.out.println("완료된 월의 sale : "+ monthlySales);
					i++;
					}
					connection.close();
				} catch (Exception e) {
						// TODO: handle exception
					System.out.println("실패");

						e.printStackTrace();
					}
		
		
		
		
		
		return dtos;
	}
	

	
	// 신규가입자 현황 (Daily)
	
	public ArrayList<AdminExtra_Dto_kkg> dailyNSGraph(Timestamp startday, Timestamp endday){
		ArrayList<AdminExtra_Dto_kkg> dtos= new ArrayList<AdminExtra_Dto_kkg>();
				
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		
		Date startdate = new Date(startday.getTime()); 
			System.out.println("시작 날짜 : "+startdate);
		Date enddate = new Date(endday.getTime()); 
			System.out.println("마지막 날짜 : " + enddate);
		
		
		//가져오는 값들 int bid,  int sale, 
		try {
			connection =dataSource.getConnection();
			String sql_select = "select ROW_NUMBER() OVER (ORDER BY Date(cinsertdate)) AS No, count(*) as NScount,  cinsertdate as date ";
			String sql_from =	" from customer";
			String sql_where = 	" where cinsertdate >= ? ";
			String sql_group =  " group by date";
			String sql = sql_select + sql_from + sql_where+sql_group;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, startday);
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
		
			while(resultSet.next()) {
			
				int dailyNS = resultSet.getInt("NScount");
				
				Date date = resultSet.getDate("date");
				AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg (date, dailyNS);  //	dto 에는 오른쪽 명칭임. (Date date, int sales, int ordercount)
				dtos.add(dto);
				
		
			

			}
			connection.close();
		} catch (Exception e) {
				// TODO: handle exception
			System.out.println("실패");

				e.printStackTrace();
			}
		
		
		return dtos;
	} //dailyNSGraph 
	
	
	
	//adminuserlist.jsp 화면 하단의 유저 목록을 보여주기 위한 데이터 가져오기
	
	public ArrayList<AdminExtra_Dto_kkg> getUserList(int pageNum) {
		ArrayList<AdminExtra_Dto_kkg> userList = new ArrayList<>() ;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String subsql_select = "select * from ( ";
			String sql_select = "select  *, ROW_NUMBER() OVER (ORDER BY Date(cinsertdate)) AS No from customer ";
			String sql_where = " where cdeletedate is null ";
			String subsql_AS = " )as subquery where No <= ? and No > ? ";
			
			String sql = subsql_select+ sql_select +sql_where +subsql_AS;
			
			//String sql = 
			//		"select * from (select *, Row_NUMBER() OVER (ORDER BY Date(cinsertdate)) as No from customer where cdeletedate is null ) ";
			
			preparedStatement = connection.prepareStatement(sql);
			System.out.println("조건에 들어갈 5*pageNum : "+ pageNum*5);
			preparedStatement.setInt(1, pageNum*5);
			preparedStatement.setInt(2, (pageNum-1)*5);

			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int pagenum = pageNum;
				int seq = resultSet.getInt("No");
				String cid = resultSet.getString("cid");
				String cname = resultSet.getString("cname");
				String cphone = resultSet.getString("cphone");
				String cbirthdate = resultSet.getString("cbirthdate");
				String cgender = null;
				
				switch(resultSet.getInt("cgender")){
					case 0 :
						cgender = "여성";
					break;		
					
					case 1 :
						cgender = "남성";
					break;		
				}
				
				String caddress = "("+resultSet.getString("cpostnum")+") " + resultSet.getString("caddress1")+ resultSet.getString("caddress2");
				Date cinsertdate  = new Date(resultSet.getTimestamp("cinsertdate").getTime());
				
				AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg(pagenum, seq, cid, cname, cphone, cbirthdate, cgender, caddress, cinsertdate);
				userList.add(dto);
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		
		
		return userList;
	}//getUserList 끝
	
	
	public ArrayList<AdminExtra_Dto_kkg> getUserCount() {
		
		int a =0;
		int m =0;
		 ArrayList<AdminExtra_Dto_kkg> dtos = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		
		try {
			connection = dataSource.getConnection();
	
			String sql_select = "select count(*) as maxpage from customer ";
			String sql_where = " where cdeletedate is null ";
			
			String sql = sql_select +sql_where;
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				a = resultSet.getInt("maxpage");
			}
			connection.close();

		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		m= a/5 + 1;
		AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg(m, a);
		dtos.add(dto);
		return dtos ;
		
	}//end of getUserCount 
	
	
	
	public ArrayList<AdminExtra_Dto_kkg> categoryChart(Timestamp startday, Timestamp endday){
		ArrayList<AdminExtra_Dto_kkg> dtos= new ArrayList<AdminExtra_Dto_kkg>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		//가져오는 값들 int bid,  int sale, 
		try {
			connection =dataSource.getConnection();
			String sql_select = "select ROW_NUMBER() OVER (ORDER BY cg.c_num) as No, cg.c_num,cg.c_name, sum(o.oqty) as orders, sum(o.oprice) as sales ";
			String sql_from =	" from product as p , ordering as o, category as cg";
			String sql_where = 	" where p.pcategory = cg.c_num and o.o_pid=p.pid and o.odate >= ? and o.odate <= ? ";
			String sql_group =  " group by cg.c_num";
			String sql = sql_select + sql_from + sql_where+sql_group;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, startday);
			preparedStatement.setTimestamp(2, endday);
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
		
			while(resultSet.next()) {
				int seq = resultSet.getInt("No");
				int categoryId = resultSet.getInt("c_num");
				String categoryName = resultSet.getString("c_name");
				int orders = resultSet.getInt("orders");
				int sales = resultSet.getInt("sales");
				
				
				
				AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg (seq, categoryId, categoryName, orders, sales);  //	dto 에는 오른쪽 명칭임. (Date date, int sales, int ordercount)
				dtos.add(dto);
				
		
			

			}
			connection.close();
		} catch (Exception e) {
				// TODO: handle exception
			System.out.println("실패");

				e.printStackTrace();
			}
		

		Date startdate = new Date(startday.getTime()); 
		Date enddate = new Date(endday.getTime()); 
		
		return dtos;
		
	}// end of categoryChart
	
	
	
	public ArrayList<AdminExtra_Dto_kkg> gendercategoryChart(Timestamp startday,Timestamp endday){
		ArrayList<AdminExtra_Dto_kkg> dtos= new ArrayList<AdminExtra_Dto_kkg>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		//가져오는 값들 int bid,  int sale, 
		try {
			connection =dataSource.getConnection();
			String sql_select = "select g.g_name, cg.c_name,  sum(o.oqty) as orders, sum(o.oprice) as sales ";
			String sql_from =	" from customer as c, ordering as o , product as p, category as cg, gender as g ";
			String sql_where = 	" where o.o_cid = c.cid and c.cgender = g.g_num and o.o_pid = p. pid and p.pcategory = cg.c_num and o.odate >= ? and o.odate <= ? ";
			String sql_group =  " group by g.g_name, cg.c_num";
			String sql = sql_select + sql_from + sql_where+sql_group;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, startday);
			preparedStatement.setTimestamp(2, endday);
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
		
			while(resultSet.next()) {
				String gender = resultSet.getString("g_name");
				String categoryName = resultSet.getString("c_name");
				int orders = resultSet.getInt("orders");
				int sales = resultSet.getInt("sales");
				
				
				
				AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg ( categoryName, gender, orders, sales);  //	dto 에는 오른쪽 명칭임. (Date date, int sales, int ordercount)
				dtos.add(dto);
				
		
			

			}
			connection.close();
		} catch (Exception e) {
				// TODO: handle exception
			System.out.println("실패");

				e.printStackTrace();
			}
		

		Date startdate = new Date(startday.getTime()); 
			System.out.println("시작 날짜 : "+startdate);
		Date enddate = new Date(endday.getTime()); 
			System.out.println("마지막 날짜 : " + enddate);
		
		return dtos;
		
		
		
	}//end of gendercategoryChart
	
	
	public ArrayList<AdminExtra_Dto_kkg> genderChart(Timestamp startday,Timestamp endday){
		ArrayList<AdminExtra_Dto_kkg> dtos= new ArrayList<AdminExtra_Dto_kkg>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		//가져오는 값들 int bid,  int sale, 
		try {
			connection =dataSource.getConnection();
			String sql_select = "SELECT ROW_NUMBER() OVER (ORDER BY g.g_name) as No, g.g_name, SUM(o.oqty) AS orders, sum(o.oprice) AS sales ";
			String sql_from =	" FROM customer AS c, ordering AS o, gender AS g";
			String sql_where = 	" WHERE o.o_cid = c.cid AND c.cgender = g.g_num and o.odate >= ? and o.odate <= ? ";
			String sql_group =  " GROUP BY g.g_name";
			String sql = sql_select + sql_from + sql_where+sql_group;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, startday);
			preparedStatement.setTimestamp(2, endday);
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
		
			while(resultSet.next()) {
				int seq = resultSet.getInt("No");
				String gender = resultSet.getString("g_name");
				int orders = resultSet.getInt("orders");
				int sales = resultSet.getInt("sales");
				
				
				
				AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg (seq, gender, orders, sales);  //	dto 에는 오른쪽 명칭임. (Date date, int sales, int ordercount)
				dtos.add(dto);
				
		
			

			}
			connection.close();
		} catch (Exception e) {
				// TODO: handle exception
			System.out.println("실패");

				e.printStackTrace();
			}
		

		Date startdate = new Date(startday.getTime()); 
			System.out.println("시작 날짜 : "+startdate);
		Date enddate = new Date(endday.getTime()); 
			System.out.println("마지막 날짜 : " + enddate);
		
		return dtos;
		
		
		
	}//end of genderChart
	
	public ArrayList<AdminExtra_Dto_kkg> maxOrdersChart(Timestamp startday,Timestamp endday){
		ArrayList<AdminExtra_Dto_kkg> dtos= new ArrayList<AdminExtra_Dto_kkg>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		//가져오는 값들 int bid,  int sale, 
		try {
			connection =dataSource.getConnection();
			String sql_select = "Select p.pname, sum(o.oqty) as orders ";
			String sql_from =	" from ordering as o, product as p ";
			String sql_where = 	" where o.o_pid = p.pid and o.odate >= ? and o.odate <= ? ";
			String sql_group =  " group by p.pname";
			String sql_order =	" order by orders desc";
			String sql_Limit =	" Limit 5";
			String sql = sql_select + sql_from + sql_where+sql_group+sql_order+sql_Limit;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, startday);
			preparedStatement.setTimestamp(2, endday);
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
		
			while(resultSet.next()) {
				
				String pname = resultSet.getString("pname");
				int orders = resultSet.getInt("orders");
				
				
				
				
				AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg (pname, orders);  //	dto 에는 오른쪽 명칭임. (Date date, int sales, int ordercount)
				dtos.add(dto);
				
		
			

			}
			connection.close();
		} catch (Exception e) {
				// TODO: handle exception
			System.out.println("실패");

				e.printStackTrace();
			}
		

		Date startdate = new Date(startday.getTime()); 
			System.out.println("시작 날짜 : "+startdate);
		Date enddate = new Date(endday.getTime()); 
			System.out.println("마지막 날짜 : " + enddate);
		
		return dtos;
		
		
		
	}// end of maxOrdersChart 
	

	public ArrayList<AdminExtra_Dto_kkg> maxSalesChart(Timestamp startday,Timestamp endday){
		ArrayList<AdminExtra_Dto_kkg> dtos= new ArrayList<AdminExtra_Dto_kkg>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		//가져오는 값들 int bid,  int sale, 
		try {
			connection =dataSource.getConnection();
			String sql_select = "Select p.pname, sum(o.oprice) as sales ";
			String sql_from =	" from ordering as o, product as p ";
			String sql_where = 	" where o.o_pid = p.pid and o.odate >= ? and o.odate <= ? ";
			String sql_group =  " group by p.pname";
			String sql_order =	" order by sales desc";
			String sql_Limit =	" Limit 5";
			String sql = sql_select + sql_from + sql_where+sql_group+sql_order+sql_Limit;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, startday);
			preparedStatement.setTimestamp(2, endday);
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
		
			while(resultSet.next()) {
				
				String pname = resultSet.getString("pname");
				int sales = resultSet.getInt("sales");
				
				
				
				
				AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg (pname, sales);  //	dto 에는 오른쪽 명칭임. (Date date, int sales, int ordercount)
				dtos.add(dto);
				
		
			

			}
			connection.close();
		} catch (Exception e) {
				// TODO: handle exception
			System.out.println("실패");

				e.printStackTrace();
			}
		

		Date startdate = new Date(startday.getTime()); 
			System.out.println("시작 날짜 : "+startdate);
		Date enddate = new Date(endday.getTime()); 
			System.out.println("마지막 날짜 : " + enddate);
		
		return dtos;
		
		
		
	}// end of maxSalesChart
	
	
	
	
	
}//end Game now
