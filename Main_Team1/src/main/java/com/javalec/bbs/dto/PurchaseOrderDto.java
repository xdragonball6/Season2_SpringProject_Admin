package com.javalec.bbs.dto;

public class PurchaseOrderDto {

	int pid;
	String pfilename;
	String pname;
	String pcontent;
	int price;
	int bqty;
	
	String cid;
	String cname;
	String cphone;
	String cpostnum;
	String caddress1;
	String caddress2;
	
	
    public PurchaseOrderDto() {
		// TODO Auto-generated constructor stub
	}


	public PurchaseOrderDto(int pid, String pfilename, String pname, String pcontent, int price, int bqty) {
		super();
		this.pid = pid;
		this.pfilename = pfilename;
		this.pname = pname;
		this.pcontent = pcontent;
		this.bqty = bqty;
		this.price = price;
		
	}


	public PurchaseOrderDto(String cid, String cname, String cphone, String cpostnum, String caddress1,
			String caddress2) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cphone = cphone;
		this.cpostnum = cpostnum;
		this.caddress1 = caddress1;
		this.caddress2 = caddress2;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
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

	public int getBqty() {
		return bqty;
	}


	public void setBqty(int bqty) {
		this.bqty = bqty;
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

