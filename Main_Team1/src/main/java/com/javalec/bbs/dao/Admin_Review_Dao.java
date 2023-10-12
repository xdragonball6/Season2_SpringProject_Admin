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

import com.javalec.bbs.dto.Admin_Product_Dto;
import com.javalec.bbs.dto.Admin_Review_Dto;

public class Admin_Review_Dao {
	
	
	// Field
	DataSource datasource;
	
	// Constructor
	public Admin_Review_Dao() {
		try {
			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// Method
	
	
	public ArrayList<Admin_Review_Dto> list() {
		ArrayList<Admin_Review_Dto> dtos = new ArrayList<Admin_Review_Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = datasource.getConnection();
			String query = "SELECT f.fid, f.f_cid, f.f_pid, f.finsertdate, c.cname, p.pname, p.pfilename, p.pprice, f.fcontent, f.ftitle, p.pcontent, p.pcategory"; 
			String query1 = " FROM forum f, product p, customer c WHERE f.f_cid=c.cid AND f.f_pid=p.pid AND f.ftype=1 AND f.fdeletedate IS NULL;";
			preparedStatement = connection.prepareStatement(query+query1);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int fid=resultSet.getInt(1);
				String f_cid=resultSet.getString(2);
				int f_pid=resultSet.getInt(3);
			    Timestamp finsertdate = resultSet.getTimestamp(4);
			    String cname  = resultSet.getString(5);
			    String pname = resultSet.getString(6);
			    String pfilename=resultSet.getString(7);
			    int pprice = resultSet.getInt(8);
			    String fcontent = resultSet.getString(9);
			    String ftitle= resultSet.getString(10);
			    String pcontent =resultSet.getString(11);
			    int pcategory = resultSet.getInt(12);
			    Admin_Review_Dto dto = new Admin_Review_Dto(fid, f_cid, f_pid, finsertdate, cname, pname, pfilename, pprice, fcontent, ftitle, pcontent, pcategory);
			    dtos.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet !=null) resultSet.close();
				if(preparedStatement !=null) preparedStatement.close();
				if(connection !=null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
		
	} // list
	
	public void delete(String[] fid) {
	    if (fid == null) {
	        // pid 배열이 null인 경우 처리
	        System.out.println("No products selected for deletion.");
	        return;
	    }
	    Connection connection = null;
	    PreparedStatement deleteInboundStatement = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        connection = datasource.getConnection();
	        // Delete from product table
	        String deleteProductQuery = "delete from product where fid = ?";
	        String deletedateInsert= "update forum set fdeletedate= now() where fid = ?";
	        preparedStatement = connection.prepareStatement(deletedateInsert);
	        for (int i = 0; i < fid.length; i++) {
	            preparedStatement.setInt(1, Integer.parseInt(fid[i]));
	            preparedStatement.executeUpdate();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (deleteInboundStatement != null)
	                deleteInboundStatement.close();
	            if (preparedStatement != null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void delete2(String fid) {
		  Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    
		    try {
		        connection = datasource.getConnection();
		        String query = "UPDATE forum SET fdeletedate = CURRENT_TIMESTAMP, ftitle = '삭제된 댓글입니다'";
		        String query1 = " WHERE fid = ?";
		        preparedStatement = connection.prepareStatement(query + query1);
		        
		        // fid 파라미터 설정
		        preparedStatement.setInt(1, Integer.parseInt(fid));
		        
		        preparedStatement.executeUpdate();
		            
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (preparedStatement != null)
		                preparedStatement.close();
		            if (connection != null)
		                connection.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}
	
	
	public ArrayList<Admin_Review_Dto> searchlist(String list, String query) {
		ArrayList<Admin_Review_Dto> dtos = new ArrayList<Admin_Review_Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = datasource.getConnection();
			String query1 = "SELECT f.fid, f_cid, f.f_pid, f.finsertdate, c.cname, p.pname, p.pfilename, p.pprice, f.fcontent, f.ftitle, p.pcontent, p.pcategory"; 
			String query2 = " FROM forum f, product p, customer c, type t WHERE f.f_cid=c.cid AND f.f_pid=p.pid AND f.ftype=1 AND f.fdeletedate IS NULL;";
			preparedStatement = connection.prepareStatement(query1+query2);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int fid=resultSet.getInt(1);
				String f_cid=resultSet.getString(2);
				int f_pid=resultSet.getInt(3);
			    Timestamp finsertdate = resultSet.getTimestamp(4);
			    String cname  = resultSet.getString(5);
			    String pname = resultSet.getString(6);
			    String pfilename=resultSet.getString(7);
			    int pprice = resultSet.getInt(8);
			    String fcontent = resultSet.getString(9);
			    String ftitle= resultSet.getString(10);
			    String pcontent =resultSet.getString(11);
			    int pcategory = resultSet.getInt(12);
			    Admin_Review_Dto dto = new Admin_Review_Dto(fid, f_cid, f_pid, finsertdate, cname, pname, pfilename, pprice, fcontent, ftitle, pcontent, pcategory);
			    dtos.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet !=null) resultSet.close();
				if(preparedStatement !=null) preparedStatement.close();
				if(connection !=null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
		
	} // list
	
	
	
	
	
	
	
	
	
	
}
