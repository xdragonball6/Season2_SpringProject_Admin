package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.Kms_OrderingList_Dto;

public class Kms_UserOrderingLIst_Dao {
	
DataSource dataSource;
	
	public Kms_UserOrderingLIst_Dao() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Kms_OrderingList_Dto> orderinglist(String cid){
		ArrayList<Kms_OrderingList_Dto> dtos = new ArrayList<Kms_OrderingList_Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select o.*, p.pname, c.cname from ordering o, product p, customer c";
			String query1 = " where o.product_pid = p.pid and c.cid = o.customer_cid and customer_cid = ? order by oid desc";
			preparedStatement = connection.prepareStatement(query + query1);
			preparedStatement.setString(1, cid);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int oid = resultSet.getInt(1);
				String customer_cid = resultSet.getString(2);
				int product_pid = resultSet.getInt(3);
				int oqty = resultSet.getInt(4);
				int oprice = resultSet.getInt(5);
				Timestamp finsertdate = resultSet.getTimestamp(6);
				int odelivery = resultSet.getInt(7);
				String pname = resultSet.getString(8);
				String cname = resultSet.getString(9);
				
				
				
				
				Kms_OrderingList_Dto dto = new Kms_OrderingList_Dto(oid, customer_cid, product_pid, oqty, oprice, finsertdate, odelivery, pname, cname);
						
				dtos.add(dto);
				
			
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
		
	} // list
}
