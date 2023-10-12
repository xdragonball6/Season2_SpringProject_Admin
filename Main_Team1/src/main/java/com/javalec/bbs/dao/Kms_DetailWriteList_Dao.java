package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.Kms_WriteList_Dto;

public class Kms_DetailWriteList_Dao {
	DataSource dataSource;
	
	public Kms_DetailWriteList_Dao() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public ArrayList<Kms_WriteList_Dto> QnADlist(int pid){
		ArrayList<Kms_WriteList_Dto> dtos = new ArrayList<Kms_WriteList_Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select f.*,p.pname,c.cname from forum f,product p, customer c";
			String query1 = " where p.pid = f.f_pid and c.cid = f.f_cid and ftype = 2 and f_pid = ? order by fref desc,freforder";
			preparedStatement = connection.prepareStatement(query + query1);
			preparedStatement.setInt(1, pid);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int fid = resultSet.getInt(1);
				String f_cid = resultSet.getString(2);
				String f_aid = resultSet.getString(3);
				int f_pid = resultSet.getInt(4);
				int ftype = resultSet.getInt(5);
				int fref = resultSet.getInt(6);
				int freforder = resultSet.getInt(7);
				int fstep = resultSet.getInt(8);
				int fsteporder = resultSet.getInt(9);
				String ftitle = resultSet.getString(10);
				String fcontent = resultSet.getString(11);
				Timestamp finsertdate = resultSet.getTimestamp(12);
				Timestamp fdeletedate = resultSet.getTimestamp(13);
				int fmotherid = resultSet.getInt(14);
				int fanswernum = resultSet.getInt(15);
				String pname = resultSet.getString(16);
				String cname = resultSet.getString(17);
				
				
				
				Kms_WriteList_Dto dto = new Kms_WriteList_Dto(fid, f_cid, f_aid, f_pid, ftype, fref, freforder,
						fstep, ftitle, fcontent, finsertdate, fdeletedate, fmotherid, fanswernum, fsteporder, pname, cname);
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
		
	} // QnA Detail list
	
	public ArrayList<Kms_WriteList_Dto> ReviewDlist(int pid){
		ArrayList<Kms_WriteList_Dto> dtos = new ArrayList<Kms_WriteList_Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select f.*,p.pname,c.cname from forum f,product p, customer c";
			String query1 = " where p.pid = f.f_pid and c.cid = f.f_cid and ftype = 1 and f_pid = ? order by fref desc,freforder";
			preparedStatement = connection.prepareStatement(query + query1);
			preparedStatement.setInt(1, pid);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int fid = resultSet.getInt(1);
				String f_cid = resultSet.getString(2);
				String f_aid = resultSet.getString(3);
				int f_pid = resultSet.getInt(4);
				int ftype = resultSet.getInt(5);
				int fref = resultSet.getInt(6);
				int freforder = resultSet.getInt(7);
				int fstep = resultSet.getInt(8);
				int fsteporder = resultSet.getInt(9);
				String ftitle = resultSet.getString(10);
				String fcontent = resultSet.getString(11);
				Timestamp finsertdate = resultSet.getTimestamp(12);
				Timestamp fdeletedate = resultSet.getTimestamp(13);
				int fmotherid = resultSet.getInt(14);
				int fanswernum = resultSet.getInt(15);
				String pname = resultSet.getString(16);
				String cname = resultSet.getString(17);
				
				
				
				Kms_WriteList_Dto dto = new Kms_WriteList_Dto(fid, f_cid, f_aid, f_pid, ftype, fref, freforder,
						fstep, ftitle, fcontent, finsertdate, fdeletedate, fmotherid, fanswernum, fsteporder, pname, cname);
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
		
	} // Review Detail list
}
