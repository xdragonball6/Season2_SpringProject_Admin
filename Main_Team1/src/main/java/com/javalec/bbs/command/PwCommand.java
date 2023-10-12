package com.javalec.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.MDao;

public class PwCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		
		MDao dao = new MDao();
		String result = dao.Pw(cid, cname);
		
		request.setAttribute("pw", result);
	}

}
