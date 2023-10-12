package com.javalec.bbs.dto;

import java.sql.Timestamp;

public class Kms_OrderingList_Dto {

	int oid;
	String customer_cid;
	int product_pid;
	int oqty;
	int oprice;
	Timestamp odate;
	int odelivery;
	
	String pname;
	String cname;
	
	public Kms_OrderingList_Dto() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Kms_OrderingList_Dto(int oid, String customer_cid, int product_pid, int oqty, int oprice, Timestamp odate,
			int odelivery, 	String pname, String cname) {
		super();
		this.oid = oid;
		this.customer_cid = customer_cid;
		this.product_pid = product_pid;
		this.oqty = oqty;
		this.oprice = oprice;
		this.odate = odate;
		this.odelivery = odelivery;
		this.pname = pname;
		this.cname = cname;
	}



	public String getCname() {
		return cname;
	}



	public void setCname(String cname) {
		this.cname = cname;
	}



	public String getPname() {
		return pname;
	}



	public void setPname(String pname) {
		this.pname = pname;
	}



	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getCustomer_cid() {
		return customer_cid;
	}

	public void setCustomer_cid(String customer_cid) {
		this.customer_cid = customer_cid;
	}

	public int getProduct_pid() {
		return product_pid;
	}

	public void setProduct_pid(int product_pid) {
		this.product_pid = product_pid;
	}

	public int getOqty() {
		return oqty;
	}

	public void setOqty(int oqty) {
		this.oqty = oqty;
	}

	public int getOprice() {
		return oprice;
	}

	public void setOprice(int oprice) {
		this.oprice = oprice;
	}

	public Timestamp getOdate() {
		return odate;
	}

	public void setOdate(Timestamp odate) {
		this.odate = odate;
	}

	public int getOdelivery() {
		return odelivery;
	}

	public void setOdelivery(int odelivery) {
		this.odelivery = odelivery;
	}
	
	
}
