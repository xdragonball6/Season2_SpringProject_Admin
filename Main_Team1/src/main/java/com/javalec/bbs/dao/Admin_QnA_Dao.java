package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.Admin_Product_Dto;
import com.javalec.bbs.dto.Admin_QnA_Dto;

public class Admin_QnA_Dao {
	
		// Field
		DataSource datasource;
		
		
		// Constructor
		public Admin_QnA_Dao() {
			try {
				Context context = new InitialContext();
				datasource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		// Method
		public ArrayList<Admin_QnA_Dto> list() {
			ArrayList<Admin_QnA_Dto> dtos = new ArrayList<Admin_QnA_Dto>();
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = datasource.getConnection();
				String query = "SELECT f.fid, f.ftitle, f.f_aid, f.f_cid, f.finsertdate, t.t_name ";
				String query1 = " FROM forum f JOIN type t ON (f.ftype-1) = t.t_num where f.ftype=1";
				preparedStatement = connection.prepareStatement(query+query1);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					int fid=resultSet.getInt(1);
				    String ftitle = resultSet.getString(2);
				    String f_aid = resultSet.getString(3);
				    String f_cid = resultSet.getString(4);
				    Timestamp finsertdate = resultSet.getTimestamp(5);
				    String t_name=resultSet.getString(6);
				    Admin_QnA_Dto dto = new Admin_QnA_Dto(fid, ftitle, f_aid, f_cid, finsertdate, t_name);
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
		
		
		public int getTotalData() {
		    ArrayList<Admin_QnA_Dto> allData = list(); // 모든 데이터 조회
		    return allData.size();
		}
		
		public ArrayList<Admin_QnA_Dto> getDataInRange(int startIndex, int endIndex) {
			ArrayList<Admin_QnA_Dto> dtos = new ArrayList<Admin_QnA_Dto>();
			ArrayList<Admin_QnA_Dto> allData = list(); // 모든 데이터 조회
			
			int dataSize = allData.size();
			int start = Math.min(startIndex, dataSize); // 시작 인덱스를 데이터 사이즈 내에서 제한
			int end = Math.min(endIndex, dataSize); // 종료 인덱스를 데이터 사이즈 내에서 제한
			
			for (int i = start; i < end; i++) {
				dtos.add(allData.get(i));
			}
			
			return dtos;
		}
		
		
//		public int saveNotice(String ftitle, String fcontent) {
//			Connection connection = null;
//			PreparedStatement preparedStatement = null;
//
//			try{
//				connection = datasource.getConnection();
//				String query2= "insert into forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, ftitle, fcontent, finsertdate, fmotherid)";
//				String query3 = " select 'AdminOnly','admin', 1, 1, max(fref) + 1, 0, 0, ?, ?, now(),0 from forum";
//				preparedStatement = connection.prepareStatement(query2+query3);
//				preparedStatement.setString(1, ftitle);
//				preparedStatement.setString(2, fcontent);
//				return preparedStatement.executeUpdate();
//			}catch (Exception e) {
//				e.printStackTrace();
//			 } finally {
//			        // 리소스 해제 코드
//			        if (preparedStatement != null) {
//			            try {
//			                preparedStatement.close();
//			            } catch (Exception e) {
//			                e.printStackTrace();
//			            }
//			        }
//			        if (connection != null) {
//			            try {
//			                connection.close();
//			            } catch (Exception e) {
//			                e.printStackTrace();
//			            }
//			        }
//			    }
//			    return -1;
//			}
		
		
		public int saveNotice(String ftitle, String fcontent) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			try{
				connection = datasource.getConnection();
				String query2= "insert into notice (n_aid, ntitle, ncontent, ninsertdate)";
				String query3 = " select 'admin', ?, ?, now()";
				preparedStatement = connection.prepareStatement(query2+query3);
				preparedStatement.setString(1, ftitle);
				preparedStatement.setString(2, fcontent);
				return preparedStatement.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			 } finally {
			        // 리소스 해제 코드
			        if (preparedStatement != null) {
			            try {
			                preparedStatement.close();
			            } catch (Exception e) {
			                e.printStackTrace();
			            }
			        }
			        if (connection != null) {
			            try {
			                connection.close();
			            } catch (Exception e) {
			                e.printStackTrace();
			            }
			        }
			    }
			    return -1;
			}
		
		
		public ArrayList<Admin_QnA_Dto> Noticelist() {
			ArrayList<Admin_QnA_Dto> dtos = new ArrayList<Admin_QnA_Dto>();
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = datasource.getConnection();
				String query = "SELECT nid, ntitle, ncontent, ninsertdate";
				String query1 = " FROM notice where ndeletedate IS NULL";
				preparedStatement = connection.prepareStatement(query+query1);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					int nid = resultSet.getInt(1);
				    String ntitle = resultSet.getString(2);
				    String ncontent = resultSet.getString(3);
				    Timestamp ninsertdate = resultSet.getTimestamp(4);
				    Admin_QnA_Dto dto = new Admin_QnA_Dto(nid, ntitle, ncontent, ninsertdate);
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
