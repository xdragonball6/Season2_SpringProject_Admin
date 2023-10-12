package com.javalec.bbs.dto;

import java.sql.Timestamp;

public class Admin_Order_Dto_kkg {

	
	
	// 주문번호, 제품 아이디, 제품 이름,  회원 아이디, 회원 이름, 회원 주소, 회원 전화번호 
	int seq;
	int oid;
	int pid;
	String pname; 
	int oqty;
	int oprice;
	
	String cid;
	String cname;
	String caddress;
	String cphone;
	
	Timestamp odate;
	
	
	
	
	
	
	public Admin_Order_Dto_kkg(int seq, int oid, int pid, String pname, int oqty, int oprice, String cid, String cname,
			String caddress, String cphone, Timestamp odate) {
		super();
		this.seq = seq;
		this.oid = oid;
		this.pid = pid;
		this.pname = pname;
		this.oqty = oqty;
		this.oprice = oprice;
		this.cid = cid;
		this.cname = cname;
		this.caddress = caddress;
		this.cphone = cphone;
		this.odate = odate;
	}








	public int getOid() {
		return oid;
	}








	public void setOid(int oid) {
		this.oid = oid;
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








	public String getCaddress() {
		return caddress;
	}








	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}








	public String getCphone() {
		return cphone;
	}








	public void setCphone(String cphone) {
		this.cphone = cphone;
	}








	public int getSeq() {
		return seq;
	}








	public void setSeq(int seq) {
		this.seq = seq;
	}








	public Timestamp getOdate() {
		return odate;
	}








	public void setOdate(Timestamp odate) {
		this.odate = odate;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//endgame
