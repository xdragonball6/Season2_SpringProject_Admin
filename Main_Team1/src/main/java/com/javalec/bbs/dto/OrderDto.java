package com.javalec.bbs.dto;

public class OrderDto {

	String cid;
	int pid;
	int bqty;
	int price;
    String pfilename;
    String pname;
    String pcontent;

	public OrderDto() {
		// TODO Auto-generated constructor stub
	}


	public OrderDto(String cid, int pid, int bqty, int price, String pfilename, String pname, String pcontent) {
		super();
		this.cid = cid;
		this.pid = pid;
		this.bqty = bqty;
		this.price = price;
		this.pfilename = pfilename;
		this.pname = pname;
		this.pcontent = pcontent;
	}




	public String getPfilename() {
		return pfilename;
	}


	public void setPfilename(String pfilename) {
		this.pfilename = pfilename;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}


	public String getPcontent() {
		return pcontent;
	}


	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}


	public String getCid() {
		return cid;
	}


	public void setCid(String cid) {
		this.cid = cid;
	}


	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	public int getBqty() {
		return bqty;
	}


	public void setBqty(int bqty) {
		this.bqty = bqty;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
	
	
}
