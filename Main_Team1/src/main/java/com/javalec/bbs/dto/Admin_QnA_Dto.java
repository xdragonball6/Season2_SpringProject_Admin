package com.javalec.bbs.dto;

import java.sql.Timestamp;

public class Admin_QnA_Dto {

	// Field
	
	int fid;
	String f_cid;
	String f_aid;
	int p_id;
	int ftype;
	int fref;
	int freforder;
	int fstep;
	String ftitle;
	String fcontent;
	Timestamp finsertdate;
	Timestamp fdeletedate;
	int fmotherid;
	int t_num;
	String t_name;
	
	int nid;
	String ntitle;
	String ncontent;
	Timestamp ninsertdate;
	
	// Constructor
	
	public Admin_QnA_Dto(int fid, String ftitle, String f_aid, String f_cid, Timestamp finsertdate, String t_name) {
		super();
		this.fid = fid;
		this.ftitle = ftitle;
		this.f_aid = f_aid;
		this.f_cid = f_cid;
		this.finsertdate = finsertdate;
		this.t_name = t_name;
	}


	
	
	
	public Admin_QnA_Dto(int nid, String ntitle, String ncontent, Timestamp ninsertdate) {
		super();
		this.nid = nid;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.ninsertdate = ninsertdate;
	}
	
	




	// Method
	public int getFid() {
		return fid;
	}


	public void setFid(int fid) {
		this.fid = fid;
	}


	public String getF_cid() {
		return f_cid;
	}


	public void setF_cid(String f_cid) {
		this.f_cid = f_cid;
	}


	public String getF_aid() {
		return f_aid;
	}


	public void setF_aid(String f_aid) {
		this.f_aid = f_aid;
	}


	public int getP_id() {
		return p_id;
	}


	public void setP_id(int p_id) {
		this.p_id = p_id;
	}


	public int getFtype() {
		return ftype;
	}


	public void setFtype(int ftype) {
		this.ftype = ftype;
	}


	public int getFref() {
		return fref;
	}


	public void setFref(int fref) {
		this.fref = fref;
	}


	public int getFreforder() {
		return freforder;
	}


	public void setFreforder(int freforder) {
		this.freforder = freforder;
	}


	public int getFstep() {
		return fstep;
	}


	public void setFstep(int fstep) {
		this.fstep = fstep;
	}


	public String getFtitle() {
		return ftitle;
	}


	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}


	public String getFcontent() {
		return fcontent;
	}


	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}


	public Timestamp getFinsertdate() {
		return finsertdate;
	}


	public void setFinsertdate(Timestamp finsertdate) {
		this.finsertdate = finsertdate;
	}


	public Timestamp getFdeletedate() {
		return fdeletedate;
	}


	public void setFdeletedate(Timestamp fdeletedate) {
		this.fdeletedate = fdeletedate;
	}


	public int getFmotherid() {
		return fmotherid;
	}


	public void setFmotherid(int fmotherid) {
		this.fmotherid = fmotherid;
	}


	public int getT_num() {
		return t_num;
	}


	public void setT_num(int t_num) {
		this.t_num = t_num;
	}


	public String getT_name() {
		return t_name;
	}


	public void setT_name(String t_name) {
		this.t_name = t_name;
	}





	public int getNid() {
		return nid;
	}
	
	
	
	
	
	public void setNid(int nid) {
		this.nid = nid;
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
