package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Review_Dao;

public class ADeleteReviewCommand2_pjh implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Admin_Review_Dao dao = new Admin_Review_Dao();
		String fid = request.getParameter("fid");
		int ftype = Integer.parseInt(request.getParameter("ftype"));
		dao.delete2(fid);
		request.setAttribute("ftype", ftype);
	}

}
