package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.HomeDto;
import com.javalec.bbs.dto.MDto;

public class HomeDao {

	
	DataSource dataSource;
	public HomeDao() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 상품이미지 가져오기
	public ArrayList<HomeDto> list() {
		ArrayList<HomeDto> list = new ArrayList<HomeDto>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select p.pid, p.pname, p.pfilename, p.pcontent, p.pprice, c.c_name "
							+ "from product p, category c where p.pcategory = c.c_num";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int pid = rs.getInt(1);
				String pname = rs.getString(2);
				String filename = rs.getString(3);
				String pcontent = rs.getString(4);
				int pprice = rs.getInt(5);
				String c_name = rs.getString(6);
				
				String pfilename = "image/" + filename;
				HomeDto dto = new HomeDto(pid, pname, pfilename, pcontent, pprice, c_name);
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
		
	}

	// 상품별 이미지 가져오기
	public ArrayList<HomeDto> lamplist(int c_cnum) {
		ArrayList<HomeDto> list = new ArrayList<HomeDto>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select p.pid, p.pname, p.pfilename, p.pcontent, p.pprice, c.c_name "
							+ "from product p, category c where p.pcategory = c.c_num and c.c_num = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, c_cnum);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int pid = rs.getInt(1);
				String pname = rs.getString(2);
				String filename = rs.getString(3);
				String pcontent = rs.getString(4);
				int pprice = rs.getInt(5);
				String c_name = rs.getString(6);
				
				String pfilename = "image/" + filename;
				HomeDto dto = new HomeDto(pid, pname, pfilename, pcontent, pprice, c_name);
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
		
	}
	
	// 장바구니
	public ArrayList<HomeDto> cartlist(String cid) {
		ArrayList<HomeDto> list = new ArrayList<HomeDto>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select p.pid, b.bid, p.pname, p.pfilename, p.pcontent, p.pprice, b.bqty "
							+ "from product p, basket b where b.b_pid = p.pid and b.b_cid = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int pid = rs.getInt(1);
				int bid = rs.getInt(2);
				String pname = rs.getString(3);
				String filename = rs.getString(4);
				String pcontent = rs.getString(5);
				int pprice = rs.getInt(6);
				int bqty = rs.getInt(7);
				
				
				String pfilename = "image/" + filename;
				HomeDto dto = new HomeDto(pid, bid, pname, pfilename, pcontent, pprice, bqty);
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
		
	}
	
}// end

