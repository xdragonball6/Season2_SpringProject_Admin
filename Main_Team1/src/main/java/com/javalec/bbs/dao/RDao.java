package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.MyreviewDto;
import com.javalec.bbs.dto.PurchaseOrderDto;
import com.javalec.bbs.dto.RDto;

public class RDao {
	DataSource dataSource;
	
	public RDao() {
		// TODO Auto-generated constructor stub
	
	try {
		Context context = new InitialContext();
		dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	}

	//상품 상세 페이지
public ArrayList<RDto> DetailedProduct(int ppid){
	ArrayList<RDto> dtos = new ArrayList<RDto>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

try {
	connection = dataSource.getConnection(); // sql 연결
	String query = "select pid, pname, pfilename, pcontentfilename1, pcontentfilename2, pcontent, pprice, pstock from product where pid = ?";
	preparedStatement = connection.prepareStatement(query);
	preparedStatement.setInt(1, ppid);
	resultSet = preparedStatement.executeQuery();

	while(resultSet.next()) {
		int pid = resultSet.getInt("pid");
		String pname = resultSet.getString("pname");
		String filename = resultSet.getString("pfilename");
		String contentfilename1 = resultSet.getString("pcontentfilename1");
		String contentfilename2 = resultSet.getString("pcontentfilename2");
		String pcontent = resultSet.getString("pcontent");
		int pprice = resultSet.getInt("pprice");
		int pstock = resultSet.getInt("pstock");
		
		String pfilename = "image/" + filename; 	
		String pcontentfilename1 = "image/" + contentfilename1; 	
		String pcontentfilename2 = "image/" + contentfilename2; 	
		RDto dto = new RDto(pid, pname, pfilename, pcontentfilename1, pcontentfilename2, pcontent, pprice, pstock);
		dtos.add(dto);
		
	}
}catch (Exception e) {
	e.printStackTrace();
}finally {
	try {
		if(resultSet != null) resultSet.close();
		if(preparedStatement != null) preparedStatement.close();
		if(connection != null) connection.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
}

return dtos;



}
//공지사항 게시판
public ArrayList<RDto> Notice(){
	ArrayList<RDto> dtos = new ArrayList<RDto>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

try {
	connection = dataSource.getConnection(); // sql 연결
	String query = "select nid, n_aid, ntitle, ncontent, ninsertdate from notice";
	preparedStatement = connection.prepareStatement(query);
	resultSet = preparedStatement.executeQuery();

	while(resultSet.next()) {
		int nid = resultSet.getInt("nid");
		String n_aid = resultSet.getString("n_aid");
		String ntitle = resultSet.getString("ntitle");
		String ncontent = resultSet.getString("ncontent");
		String ninsertdate = resultSet.getString("ninsertdate");
		
		RDto dto = new RDto(nid, n_aid, ntitle, ncontent, ninsertdate);
		dtos.add(dto);
		
	}
}catch (Exception e) {
	e.printStackTrace();
}finally {
	try {
		if(resultSet != null) resultSet.close();
		if(preparedStatement != null) preparedStatement.close();
		if(connection != null) connection.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
}

return dtos;

}

//데이터 베이스 장바구니(basket) 입력
public boolean InsertCart(String cid, int pid, int qty) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	boolean result = false;
	
	try {
		connection = dataSource.getConnection(); // sql 연결
		String query = "INSERT INTO basket (b_cid, b_pid, bqty) VALUES (?, ?, ?)";
				       		
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, cid);
		preparedStatement.setInt(2, pid);
		preparedStatement.setInt(3, qty);
		
		preparedStatement.executeUpdate();
	
		result = true;

	
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(preparedStatement != null) preparedStatement.close();
			if(connection != null) connection.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	return result;
}
//데이터베이스 구매 목록입력
public ArrayList<PurchaseOrderDto> orderList(int ppid, int bbqty){
	ArrayList<PurchaseOrderDto> dtos = new ArrayList<PurchaseOrderDto>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	try {
		connection = dataSource.getConnection(); // sql 연결
		String query = "select pid, pfilename, pname, pprice, pcontent from product where pid = ?";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, ppid);
		
		resultSet = preparedStatement.executeQuery();

		while(resultSet.next()) {
			int pid = resultSet.getInt("pid");
			String filename = resultSet.getString("pfilename");
			String pname = resultSet.getString("pname");
			String pcontent = resultSet.getString("pcontent");
			int price = resultSet.getInt("pprice");
			int bqty = bbqty;
			String pfilename = "image/" + filename; 
			PurchaseOrderDto dto = new PurchaseOrderDto(pid, pfilename, pname, pcontent, price, bqty);
			dtos.add(dto);
			
		}
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(resultSet != null) resultSet.close();
			if(preparedStatement != null) preparedStatement.close();
			if(connection != null) connection.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	return dtos;

	}

public ArrayList<PurchaseOrderDto> list(String ccid){
	ArrayList<PurchaseOrderDto> dtos2 = new ArrayList<PurchaseOrderDto>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	try {
		connection = dataSource.getConnection(); // sql 연결
		String query = "select cid, cname, cphone, cpostnum, caddress1, caddress2 from customer where cid = ?";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, ccid);
		resultSet = preparedStatement.executeQuery();

		while(resultSet.next()) {
			String cid = resultSet.getString("cid");
			String cname = resultSet.getString("cname");
			String cphone = resultSet.getString("cphone");
			String cpostnum = resultSet.getString("cpostnum");
			String caddress1 = resultSet.getString("caddress1");
			String caddress2 = resultSet.getString("caddress2");
			
			PurchaseOrderDto dto = new PurchaseOrderDto(cid, cname, cphone, cpostnum, caddress1, caddress2);
			dtos2.add(dto);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(resultSet != null) resultSet.close();
			if(preparedStatement != null) preparedStatement.close();
			if(connection != null) connection.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	System.out.println("ccid: " + ccid);

	return dtos2;

	}



//마이 페이지 나의 리뷰 데이터 가져오기
public ArrayList<MyreviewDto> Myreview(String cid){
	ArrayList<MyreviewDto> dtos = new ArrayList<MyreviewDto>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	try {
		connection = dataSource.getConnection();
		String query = "select f.*, p.pfilename, p.pname, c.cname"
				+ " from forum f, product p, customer c";
		String query2 = " where p.pid = f.f_pid and c.cid = f.f_cid and ftype = 1 and f.f_cid = ? order by fref desc, freforder";
		
		preparedStatement = connection.prepareStatement(query + query2);
		preparedStatement.setString(1, cid);
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
			String ftitle = resultSet.getString(9);
			String fcontent = resultSet.getString(10);
			Timestamp finsertdate = resultSet.getTimestamp(11);
			Timestamp fdeletedate = resultSet.getTimestamp(12);
			int fmotherid = resultSet.getInt(13);
			String pfilename = resultSet.getString(14);
			String pname = resultSet.getString(15);
			String cname = resultSet.getString(16);
				
			String ppfilename = "image/" + pfilename;
			MyreviewDto dto = new MyreviewDto(fid, f_cid, f_aid, f_pid, ftype, 
			fref, freforder, fstep, ftitle, fcontent, finsertdate, fdeletedate, fmotherid, ppfilename, pname, cname);
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

}




