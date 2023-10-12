package com.javalec.bbs.dto;

import java.sql.Timestamp;

public class Admin_Review_Dto {

	
	// Field
	int fid;
	String f_cid;
	String f_aid;
	int f_pid;
	int ftype;
	int freforder;
	int fstep;
	String ftitle;
	String fcontent;
	int fmotherid;
	Timestamp finsertdate;
	String cid;
	String cname;
	String pname;
	String pfilename;
	int pprice;
	String pcontent;
	int pcategory;
	String t_name;
	String categoryName;
	private Admin_Product_Dto productInfo;
	// Constructor
	
	
	
	
	
	
	


	public Admin_Review_Dto(int fid, String f_cid, int f_pid, Timestamp finsertdate, String cname, String pname, String pfilename, int pprice, String fcontent, String ftitle, String pcontent, int pcategory) {
		super();
		this.fid = fid;
		this.f_cid = f_cid;
		this.f_pid=f_pid;
		this.finsertdate = finsertdate;
		this.cname = cname;
		this.pname = pname;
		this.pfilename = pfilename;
		this.pprice = pprice;
		this.fcontent=fcontent;
		this.ftitle=ftitle;
		this.pcontent=pcontent;
		this.pcategory=pcategory;
	}
	public Admin_Review_Dto(int fid, int f_pid, Timestamp finsertdate, String cname, String pname, String pfilename, int pprice, String fcontent, String ftitle, String pcontent, int pcategory) {
		super();
		this.fid = fid;
		this.f_pid=f_pid;
		this.finsertdate = finsertdate;
		this.cname = cname;
		this.pname = pname;
		this.pfilename = pfilename;
		this.pprice = pprice;
		this.fcontent=fcontent;
		this.ftitle=ftitle;
		this.pcontent=pcontent;
		this.pcategory=pcategory;
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

	public int getF_pid() {
		return f_pid;
	}

	public void setF_pid(int f_pid) {
		this.f_pid = f_pid;
	}

	public int getFtype() {
		return ftype;
	}

	public void setFtype(int ftype) {
		this.ftype = ftype;
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

	public int getFmotherid() {
		return fmotherid;
	}

	public void setFmotherid(int fmotherid) {
		this.fmotherid = fmotherid;
	}

	public Timestamp getFinsertdate() {
		return finsertdate;
	}

	public void setFinsertdate(Timestamp finsertdate) {
		this.finsertdate = finsertdate;
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

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}



	public int getPprice() {
		return pprice;
	}



	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	
	
	public Admin_Product_Dto getProductInfo() {
        return productInfo;
    }
	
	 public void setProductInfo(Admin_Product_Dto productInfo) {
	        this.productInfo = productInfo;
	 }



	public String getPcontent() {
		return pcontent;
	}



	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}



	public int getPcategory() {
		return pcategory;
	}



	public void setPcategory(int pcategory) {
		this.pcategory = pcategory;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
	
	
	
	 
	 
	 
	 
	 
	 
	 
	 
	
	
	
	
	
	
}
