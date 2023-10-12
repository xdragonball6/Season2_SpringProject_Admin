package com.javalec.bbs.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class AdminExtra_Dto_kkg {

	
	
	
	
	
	//field
	
	
	Timestamp timestamp ;
	int sales ;
	Date date;
	int ordercount; //주문횟
	String month;
	
	int pageNum;
	int seq ;
	String cid ;
	String cname ;
	String cphone ;
	String cbirthdate ;
	String cgender ;
	String caddress ;
	Date cinsertdate ;
	
	int maxPage;
	int custnum;
	
	
	int categoryId;
	String categoryName;
	int orders; // 판매량
	
	
	int pid;
	int pstock;
	
	String gender;
	String pname;
	//constructor
	
	
	
	
	
	public AdminExtra_Dto_kkg(Date date, int sales) {
		super();
		this.date = date;
		this.sales = sales;
	}




	
	







	public AdminExtra_Dto_kkg(int seq, int categoryId, int pid, String pname, int pstock) {
		super();
		this.seq = seq;
		this.categoryId = categoryId;
		this.pid = pid;
		this.pname = pname;
		this.pstock = pstock;
	}













	public AdminExtra_Dto_kkg(String pname, int orders) {
		super();
		this.pname = pname;
		this.orders = orders;
	}
























	public AdminExtra_Dto_kkg(String categoryName, String gender, int orders, int sales) {
		super();
		this.categoryName = categoryName;
		this.gender = gender;
		this.orders = orders;
		this.sales = sales;
	}



	public AdminExtra_Dto_kkg(int seq, String gender, int orders, int sales) {
		super();
		this.seq = seq;
		this.gender = gender;
		this.orders = orders;
		this.sales = sales;
	}









	public AdminExtra_Dto_kkg(int seq, int categoryId, String categoryName, int orders, int sales) {
		super();
		this.seq = seq;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.orders = orders;
		this.sales = sales;
	}













	public AdminExtra_Dto_kkg(int maxPage, int custnum) {
		super();
		this.maxPage = maxPage;
		this.custnum = custnum;
	}






	public AdminExtra_Dto_kkg(int pageNum, int seq, String cid, String cname, String cphone, String cbirthdate, String cgender,
			String caddress, Date cinsertdate) {
		super();
		this.pageNum = pageNum;
		this.seq = seq;
		this.cid = cid;
		this.cname = cname;
		this.cphone = cphone;
		this.cbirthdate = cbirthdate;
		this.cgender = cgender;
		this.caddress = caddress;
		this.cinsertdate = cinsertdate;
	}



	public AdminExtra_Dto_kkg(Date date, int sales, int ordercount) {
		super();
		this.date = date;
		this.sales = sales;
		this.ordercount = ordercount;
	}
	
	
	
	public AdminExtra_Dto_kkg(String month, int sales, int ordercount) {
		super();
		this.month = month;
		this.sales = sales;
		this.ordercount = ordercount;
	}

	
	
	

	
	//getter & setter



	public Timestamp getTimestamp() {
		return timestamp;
	}



	public int getSeq() {
		return seq;
	}



	public void setSeq(int seq) {
		this.seq = seq;
	}



	public String getCid() {
		return cid;
	}



	public void setCid(String cid) {
		this.cid = cid;
	}



	public String getCname() {
		return cname;
	}



	public void setCname(String cname) {
		this.cname = cname;
	}



	public String getCphone() {
		return cphone;
	}



	public void setCphone(String cphone) {
		this.cphone = cphone;
	}



	public String getCbirthdate() {
		return cbirthdate;
	}



	public void setCbirthdate(String cbirthdate) {
		this.cbirthdate = cbirthdate;
	}



	public String getCgender() {
		return cgender;
	}



	public void setCgender(String cgender) {
		this.cgender = cgender;
	}



	public String getCaddress() {
		return caddress;
	}



	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}



	public Date getCinsertdate() {
		return cinsertdate;
	}



	public void setCinsertdate(Date cinsertdate) {
		this.cinsertdate = cinsertdate;
	}



	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}



	public int getSales() {
		return sales;
	}



	public void setSales(int sales) {
		this.sales = sales;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}




	public int getOrdercount() {
		return ordercount;
	}




	public void setOrdercount(int ordercount) {
		this.ordercount = ordercount;
	}



	public String getMonth() {
		return month;
	}



	public void setMonth(String month) {
		this.month = month;
	}
	
	
	
	
	public int getPageNum() {
		return pageNum;
	}



	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}






	public int getMaxPage() {
		return maxPage;
	}






	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}






	public int getCustnum() {
		return custnum;
	}






	public void setCustnum(int custnum) {
		this.custnum = custnum;
	}
	
	public int getCategoryId() {
		return categoryId;
	}






	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}






	public String getCategoryName() {
		return categoryName;
	}






	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}






	public int getorders() {
		return orders;
	}






	public void setorders(int orders) {
		this.orders = orders;
	}













	public String getGender() {
		return gender;
	}













	public void setGender(String gender) {
		this.gender = gender;
	}













	public int getPid() {
		return pid;
	}













	public void setPid(int pid) {
		this.pid = pid;
	}













	public String getPname() {
		return pname;
	}













	public void setPname(String pname) {
		this.pname = pname;
	}
	

	public int getOrders() {
		return orders;
	}













	public void setOrders(int orders) {
		this.orders = orders;
	}













	public int getPstock() {
		return pstock;
	}













	public void setPstock(int pstock) {
		this.pstock = pstock;
	}


	
	
	
}
