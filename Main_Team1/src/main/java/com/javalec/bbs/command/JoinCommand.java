package com.javalec.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.MDao;

public class JoinCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String cid = request.getParameter("cid");
		String cpassword = request.getParameter("cpassword");
		String cname = request.getParameter("cname");
		String cphone = request.getParameter("cphone");
		String year = request.getParameter("birthYear");
		String month = request.getParameter("birthMonth");
		String day = request.getParameter("birthDay");
		String birth = year + "-"  + month + "-" + day;
		String cgender = request.getParameter("cgender");
		String cpostnum = request.getParameter("cpostnum");
		String caddress1 = request.getParameter("caddress1");
		String caddress2 = request.getParameter("caddress2");
		int gender;
		if(cgender.equals("male")) {
			gender = 1;
		}else {
			gender = 0;
		}

		
		
		MDao dao = new MDao();
		int result = dao.join(cid, cpassword, cname, cphone, birth, gender, cpostnum, caddress1, caddress2);
		System.out.println(result);
		request.setAttribute("join", result);
	
		
	}

}
