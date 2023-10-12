package com.javalec.bbs.dto;

import java.sql.Timestamp;

public class MDto {
	String cid;
	String cpassword;
	String cname;
	String cphone;
	String cbirthdate ;
	String cgender;
	String cpostnum;
	String caddress1;
	String caddress2;
	
	public MDto() {
		// TODO Auto-generated constructor stub
	}




	public MDto(String cid, String cpassword, String cname, String cphone, String cbirthdate, String cgender,
			String cpostnum, String caddress1, String caddress2) {
		super();
		this.cid = cid;
		this.cpassword = cpassword;
		this.cname = cname;
		this.cphone = cphone;
		this.cbirthdate = cbirthdate;
		this.cgender = cgender;
		this.cpostnum = cpostnum;
		this.caddress1 = caddress1;
		this.caddress2 = caddress2;
	}



	public MDto(String cid, String cname, String cphone, String cpostnum, String caddress1, String caddress2) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cphone = cphone;
		this.cpostnum = cpostnum;
		this.caddress1 = caddress1;
		this.caddress2 = caddress2;
	}




	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
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







	public String getCpostnum() {
		return cpostnum;
	}

	public void setCpostnum(String cpostnum) {
		this.cpostnum = cpostnum;
	}

	public String getCaddress1() {
		return caddress1;
	}

	public void setCaddress1(String caddress1) {
		this.caddress1 = caddress1;
	}

	public String getCaddress2() {
		return caddress2;
	}

	public void setCaddress2(String caddress2) {
		this.caddress2 = caddress2;
	}
	
	
	
	
	
}
