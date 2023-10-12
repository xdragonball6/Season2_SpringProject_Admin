package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Review_Dao;
import com.javalec.bbs.dao.Pjh_WriteList_Dao;

public class Pjh_ANoticeDelete_Command implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Pjh_WriteList_Dao dao = new Pjh_WriteList_Dao();
		String nid = request.getParameter("nid");
		int ftype=Integer.parseInt(request.getParameter("ftype"));
		dao.delete(nid);
		request.setAttribute("ftype", ftype);
	}

}
