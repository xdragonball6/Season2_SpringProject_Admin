package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Pjh_WriteList_Dao;

public class Pjh_ANoticeModify_Command implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int ftype = Integer.parseInt(request.getParameter("ftype"));
		int nid = Integer.parseInt(request.getParameter("nid"));
		System.out.println(nid);
		request.setAttribute("nid", nid);
		String ntitle = request.getParameter("ntitle");
		String ncontent = request.getParameter("ncontent");
		Pjh_WriteList_Dao dao = new Pjh_WriteList_Dao();
		dao.modify(ntitle, ncontent, nid);
		request.setAttribute("ftype", ftype);
	}

}
