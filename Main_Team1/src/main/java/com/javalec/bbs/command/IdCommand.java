package com.javalec.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.MDao;

public class IdCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cname = request.getParameter("cname");
		String cphone = request.getParameter("cphone");
		
		MDao dao = new MDao();
		String result = dao.Id(cname, cphone);
		System.out.println(result);
		request.setAttribute("id", result);
	}

}
