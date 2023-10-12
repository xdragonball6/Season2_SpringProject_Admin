package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Pjh_ANoticeModifyPage_Command implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int ftype = Integer.parseInt(request.getParameter("ftype"));
		int nid = Integer.parseInt(request.getParameter("nid"));
		System.out.println(nid);
		String n_aid = request.getParameter("n_aid");
		String ntitle = request.getParameter("ntitle");
		String ncontent = request.getParameter("ncontent");
		String ninsertdate = request.getParameter("ninsertdate");
		request.setAttribute("ftype", ftype);
		request.setAttribute("n_aid", n_aid);
		request.setAttribute("ntitle", ntitle);
		request.setAttribute("nid", nid);
		request.setAttribute("ninsertdate", ninsertdate);
		request.setAttribute("ncontent", ncontent);
		request.setAttribute("nid", nid);
	}

}
