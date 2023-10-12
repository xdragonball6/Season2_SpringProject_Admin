package com.javalec.bbs.dto;

public class AdminOrdering_Dto_kkg {
	
	
	
	
	public AdminOrdering_Dto_kkg() {
		
	}
	
	
	
	int oid;
	String cid;
	int pid;
	int oqty;
	int oprice;
	String odate;
	
	
	
	
	
	//constructor
	
	
	public AdminOrdering_Dto_kkg(int oid, String cid, int pid, int oqty, int oprice, String odate) {
		super();
		this.oid = oid;
		this.cid = cid;
		this.pid = pid;
		this.oqty = oqty;
		this.oprice = oprice;
		this.odate = odate;
	}
	
	
	
	
	
	
	
	
	
	//getter & setter
	
	
	
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
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
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

} //end Game
