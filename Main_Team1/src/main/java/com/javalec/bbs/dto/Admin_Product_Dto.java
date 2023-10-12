package com.javalec.bbs.dto;

import java.sql.Timestamp;

public class Admin_Product_Dto {

	// Field
		String pfilename;
		String pname;
		int pid;
		int pprice;
		String pcontent;
		Timestamp pinsertdate;
		Timestamp pdeletedate;
		String c_name;
		int pstock;
		String pcontentfilename1;
		String pcontentfilename2;
		
		
		
		
		
		// Constructor
		
		
		
		
		
		
		public Admin_Product_Dto(String pfilename, String pname, int pprice, int pid) {
			super();
			this.pfilename = pfilename;
			this.pname = pname;
			this.pprice = pprice;
			this.pid = pid;
		}
		

		public Admin_Product_Dto(String pfilename, String pname, int pprice, int pid, int pstock, String pcontent, String pcontentfilename1, String pcontentfilename2, String c_name) {
			super();
			this.pfilename = pfilename;
			this.pname = pname;
			this.pprice = pprice;
			this.pid = pid;
			this.pstock = pstock;
			this.pcontent = pcontent;
			this.pcontentfilename1 = pcontentfilename1;
			this.pcontentfilename2 = pcontentfilename2;
			this.c_name = c_name;
		}
		
		public Admin_Product_Dto(String pfilename, String pname, int pprice, int pid, String c_name, String pcontent) {
			super();
			this.pfilename = pfilename;
			this.pname = pname;
			this.pprice = pprice;
			this.pid = pid;
			this.c_name = c_name;
			this.pcontent = pcontent;
		}
		
		public Admin_Product_Dto(String pfilename, String pname, int pprice, int pid, String c_name) {
			super();
			this.pfilename = pfilename;
			this.pname = pname;
			this.pprice = pprice;
			this.pid = pid;
			this.c_name = c_name;
		}
		
		public Admin_Product_Dto(String pfilename, String pname, int pprice, int pid, int pstock, String c_name) {
			super();
			this.pfilename = pfilename;
			this.pname = pname;
			this.pprice = pprice;
			this.pid = pid;
			this.pstock = pstock;
			this.c_name = c_name;
		}
		
		
		
		public Admin_Product_Dto(int pid, String pname, int pprice, String pcontent) {
			super();
			this.pid = pid;
			this.pname = pname;
			this.pprice = pprice;
			this.pcontent = pcontent;
		}

		// Method
		
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

		
		public String getC_name() {
			return c_name;
		}
		
		
		
		
		public void setC_name(String c_name) {
			this.c_name = c_name;
		}



		public int getPid() {
			return pid;
		}




		public void setPid(int pid) {
			this.pid = pid;
		}




		public int getPprice() {
			return pprice;
		}




		public void setPprice(int pprice) {
			this.pprice = pprice;
		}





		public String getPcontent() {
			return pcontent;
		}





		public void setPcontent(String pcontent) {
			this.pcontent = pcontent;
		}






		public Timestamp getPinsertdate() {
			return pinsertdate;
		}




		public void setPinsertdate(Timestamp pinsertdate) {
			this.pinsertdate = pinsertdate;
		}


		public Timestamp getPdeletedate() {
			return pdeletedate;
		}



		public void setPdeletedate(Timestamp pdeletedate) {
			this.pdeletedate = pdeletedate;
		}


		public int getPstock() {
			return pstock;
		}


		public void setPstock(int pstock) {
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

		
		
		
		
		
		
		
		
}

