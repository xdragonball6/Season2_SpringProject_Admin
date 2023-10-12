package com.javalec.bbs.dto;

public class HomeDto {

	int pid;
	int bid;
	String pname;
	String pfilename;
	String pcontent;
	int pprice;
	String c_name;
	int bqty;
	
	
	public HomeDto() {
		// TODO Auto-generated constructor stub
	}

	public HomeDto(int pid, String pname, String pfilename, String pcontent, int pprice, String c_name) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pfilename = pfilename;
		this.pcontent = pcontent;
		this.pprice = pprice;
		this.c_name = c_name;
	}



	public HomeDto(int pid, int bid, String pname, String pfilename, String pcontent, int pprice, int bqty) {
		super();
		this.pid = pid;
		this.bid = bid;
		this.pname = pname;
		this.pfilename = pfilename;
		this.pcontent = pcontent;
		this.pprice = pprice;
		this.bqty = bqty;
	}

	
	
	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getBqty() {
		return bqty;
	}

	public void setBqty(int bqty) {
		this.bqty = bqty;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
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


	public String getPfilename() {
		return pfilename;
	}


	public void setPfilename(String pfilename) {
		this.pfilename = pfilename;
	}


	public String getPcontent() {
		return pcontent;
	}


	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}


	public String getC_name() {
		return c_name;
	}


	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	
	
}
