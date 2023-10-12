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
import com.javalec.bbs.dto.AdminOrdering_Dto_kkg;
import com.javalec.bbs.dto.Admin_Order_Dto_kkg;

public class Admin_Dao_kkg {

	DataSource dataSource;

	
	
	public Admin_Dao_kkg() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	} //연결 끝
	
	
	
	public  ArrayList<AdminExtra_Dto_kkg> getOutStocks() {
		
		 ArrayList<AdminExtra_Dto_kkg> dtos = new ArrayList<>();
		
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection =dataSource.getConnection();
				String sql_select = "select Row_Number() Over (Order by p.pstock) as No, p.pcategory as category,p.pid, p.pname, p.pstock ";
				String sql_from	 =	" from product as p";
				String sql_where = 	" where pstock <=10 and pdeletedate is null";
				String sql_group = " order by pstock";
				String sql = sql_select + sql_from + sql_where+sql_group;
				preparedStatement = connection.prepareStatement(sql);

				
				resultSet = preparedStatement.executeQuery();
				int i = 0;
				while(resultSet.next()) {
					
						//System.out.println("i값 : "+ i);
						//System.out.println("더하는 날짜 : "+oneday/(60*60*24*1000) );
					int seq = resultSet.getInt("No");
					int type = resultSet.getInt("category");
					int pid = resultSet.getInt("pid");
					String pname = resultSet.getString("pname");
					int pstock = resultSet.getInt("pstock");
					
					AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg (seq, type, pid, pname, pstock);
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
	
	
	public ArrayList<Admin_Order_Dto_kkg> getOrderlist(String customerid, Timestamp startDate, Timestamp endDate){
		 ArrayList<Admin_Order_Dto_kkg> dtos = new ArrayList<>();
			
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			
			try {
				connection =dataSource.getConnection();
				String sql_select = "select Row_Number() Over (Order by o.odate) as No, o.oid, o.o_pid, p.pname, o.oqty, o.oprice, o.o_cid, c.cname, CONCAT(c.cpostnum,' ', c.caddress1, c.caddress2) as caddress, c.cphone, o.odate ";
				String sql_from	 =	" from product as p, ordering as o, customer as c";
				
				String sql_group = "";
				String sql_where ="";
				
				if(customerid.equals("1")) {
					
					sql_where = 	" where p.pid = o.o_pid and c.cid=o.o_cid and o.odate >= ? and o.odate <= ? ";

					String sql = sql_select + sql_from + sql_where+sql_group;
					preparedStatement = connection.prepareStatement(sql);
					
					preparedStatement.setTimestamp(1, startDate);
					preparedStatement.setTimestamp(2, endDate);
					
				}else {
					sql_where = 	" where p.pid = o.o_pid and c.cid=o.o_cid and o.odate >= ? and o.odate <= ? and c.cid = ?";
					String sql = sql_select + sql_from + sql_where+sql_group;
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setTimestamp(1, startDate);
					preparedStatement.setTimestamp(2, endDate);
					preparedStatement.setString(3, customerid);
				}
				

				
				resultSet = preparedStatement.executeQuery();
				int i = 0;
				while(resultSet.next()) {
					

					int seq = resultSet.getInt("No");
					int oid = resultSet.getInt("oid");
					int pid = resultSet.getInt("o_pid");
					String pname = resultSet.getString("pname");
					int oqty = resultSet.getInt("oqty");
					int oprice = resultSet.getInt("oprice");
					String cid =resultSet.getString("o_cid");
					String cname =resultSet.getString("cname");
					String caddress =resultSet.getString("caddress");
					String cphone =resultSet.getString("cphone");
					Timestamp odate = resultSet.getTimestamp("odate");
					
					

					
					Admin_Order_Dto_kkg dto = new Admin_Order_Dto_kkg (seq, oid, pid, pname, oqty,oprice,cid,cname,caddress,cphone,odate);
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
	
	
	
	
	
	
	

}//end Game
