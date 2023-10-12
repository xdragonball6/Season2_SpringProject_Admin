package com.javalec.bbs.dto;

import java.sql.Timestamp;

public class MyreviewDto {

	int fid;
	String f_cid;
	String f_aid;
	int f_pid;
	int ftype;
	int fref;
	int freforder;
	int fstep;
	String ftitle;
	String fcontent;
	Timestamp finsertdate;
	Timestamp fdeletedate;	
	int fmotherid;
	String ppfilename;
	String pname;
	String cname;
	
	public MyreviewDto() {
		// TODO Auto-generated constructor stub
	}
	
	public MyreviewDto(int fid, String f_cid, String f_aid, int f_pid, int ftype, int fref, int freforder, int fstep,
			String ftitle, String fcontent, Timestamp finsertdate, Timestamp fdeletedate, int fmotherid,
			String ppfilename, String pname, String cname) {
		super();
		this.fid = fid;
		this.f_cid = f_cid;
		this.f_aid = f_aid;
		this.f_pid = f_pid;
		this.ftype = ftype;
		this.fref = fref;
		this.freforder = freforder;
		this.fstep = fstep;
		this.ftitle = ftitle;
		this.fcontent = fcontent;
		this.finsertdate = finsertdate;
		this.fdeletedate = fdeletedate;
		this.fmotherid = fmotherid;
		this.ppfilename = ppfilename;
		this.pname = pname;
		this.cname = cname;
	}
	
	
	
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

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


	public int getF_pid() {
		return f_pid;
	}

	public void setF_pid(int f_pid) {
		this.f_pid = f_pid;
	}

	public String getPpfilename() {
		return ppfilename;
	}

	public void setPpfilename(String ppfilename) {
		this.ppfilename = ppfilename;
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



}
