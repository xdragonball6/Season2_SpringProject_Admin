package com.javalec.bbs.dto;

public class DetailsDto {

	String cname;
	String cphone;
	int qty;
	int price;
	String postnum;
	String address1;
	String address2;
	
	public DetailsDto() {
		// TODO Auto-generated constructor stub
	}

	public DetailsDto(String cname, String cphone, int qty, int price, String postnum, String address1,
			String address2) {
		super();
		this.cname = cname;
		this.cphone = cphone;
		this.qty = qty;
		this.price = price;
		this.postnum = postnum;
		this.address1 = address1;
		this.address2 = address2;
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

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPostnum() {
		return postnum;
	}

	public void setPostnum(String postnum) {
		this.postnum = postnum;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	
	
	
	
}
