package com.javalec.bbs.dto;

import java.sql.Timestamp;

public class Admin_WriteList_Dto {

	// Field
	String n_aid;
	String ntitle;
	String ncontent;
	Timestamp ninsertdate;
	
	
	
	
	// Constructor
	public Admin_WriteList_Dto(String n_aid, String ntitle, String ncontent, Timestamp ninsertdate) {
		super();
		this.n_aid = n_aid;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.ninsertdate = ninsertdate;
	}
	
	
	
	// Method
	public String getN_aid() {
		return n_aid;
	}
	public void setN_aid(String n_aid) {
		this.n_aid = n_aid;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public Timestamp getNinsertdate() {
		return ninsertdate;
	}
	public void setNinsertdate(Timestamp ninsertdate) {
		this.ninsertdate = ninsertdate;
	}
}
