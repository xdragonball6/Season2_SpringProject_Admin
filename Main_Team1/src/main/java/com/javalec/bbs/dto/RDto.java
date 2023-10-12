package com.javalec.bbs.dto;

public class RDto {

	
	
	int pid;
	String pname;
    String pfilename;
    String pcontentfilename1;
    String pcontentfilename2;
    String pcontent;
    int pprice;
    
    String f_cid;
    int ftype;
    String ftitle;
    String fcontent;
    int pstock;
    
    int cid;
    String bqty;
    
    int nid;
    String ntitle;
    String ncontent;
    String n_aid;
    String ninsertdate;
    
    
    
    public RDto() {
		// TODO Auto-generated constructor stub
	}



	public RDto(int nid, String n_aid, String ntitle, String ncontent, String ninsertdate) {
		super();
		this.nid = nid;
		this.n_aid = n_aid;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.ninsertdate = ninsertdate;
	}



	public RDto(int pid, int cid, String bqty) {
		super();
		this.pid = pid;
		this.cid = cid;
		this.bqty = bqty;
	}



	public RDto(String f_cid, int ftype, String ftitle, String fcontent) {
		super();
		this.f_cid = f_cid;
		this.ftype = ftype;
		this.ftitle = ftitle;
		this.fcontent = fcontent;
	}

	

	
	public RDto(int pid, String pname, String pfilename, String pcontentfilename1, String pcontentfilename2,  String pcontent, int pprice, int pstock) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pfilename = pfilename;
		this.pcontentfilename1 = pcontentfilename1;
		this.pcontentfilename2 = pcontentfilename2;
		this.pcontent = pcontent;
		this.pprice = pprice;
		this.pstock = pstock;
	
	
	
	
	
	}
	
	public String getPcontentfilename1() {
		return pcontentfilename1;
	}



	public void setPcontentfilename1(String pcontentfilename1) {
		this.pcontentfilename1 = pcontentfilename1;
	}



	public String getPcontentfilename2() {
		return pcontentfilename2;
	}



	public void setPcontentfilename2(String pcontentfilename2) {
		this.pcontentfilename2 = pcontentfilename2;
	}



	public String getNtitle() {
		return ntitle;
	}



	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}



	public String getN_aid() {
		return n_aid;
	}



	public void setN_aid(String n_aid) {
		this.n_aid = n_aid;
	}



	public String getNinsertdate() {
		return ninsertdate;
	}



	public void setNinsertdate(String ninsertdate) {
		this.ninsertdate = ninsertdate;
	}



	public int getPstock() {
		return pstock;
	}



	public int getNid() {
		return nid;
	}



	public void setNid(int nid) {
		this.nid = nid;
	}



	public String getNtitel() {
		return ntitle;
	}



	public void setNtitel(String ntitel) {
		this.ntitle = ntitel;
	}



	public String getNcontent() {
		return ncontent;
	}



	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}



	public void setPstock(int pstock) {
		this.pstock = pstock;
	}



	public int getPid() {
		return pid;
	}



	public void setPid(int pid) {
		this.pid = pid;
	}



	public String getpname() {
		return pname;
	}



	public void setpname(String pname) {
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



	public int getPprice() {
		return pprice;
	}



	public void setPprice(int pprice) {
		this.pprice = pprice;
	}



	public String getF_cid() {
		return f_cid;
	}



	public void setF_cid(String f_cid) {
		this.f_cid = f_cid;
	}






	public int getFtype() {
		return ftype;
	}



	public void setFtype(int ftype) {
		this.ftype = ftype;
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

	
	public String getPname() {
		return pname;
	}
	
	
	
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	
	
	public int getCid() {
		return cid;
	}
	
	
	
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	
	
	public String getBqty() {
		return bqty;
	}
	
	
	
	public void setBqty(String bqty) {
		this.bqty = bqty;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

