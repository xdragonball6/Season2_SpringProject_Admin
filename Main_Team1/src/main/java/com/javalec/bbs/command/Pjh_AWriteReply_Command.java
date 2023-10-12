package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Pjh_AWriteReply_Command implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int page = Integer.parseInt(request.getParameter("fid"));
		int fid = Integer.parseInt(request.getParameter("fid"));
		System.out.println(page);
		String ftype=request.getParameter("ftype");
		System.out.println(ftype);
		String cid=request.getParameter("f_cid");
		String pid=request.getParameter("f_pid");
		request.setAttribute("page", page);
		request.setAttribute("ftype", ftype);
		request.setAttribute("pid", pid);
		request.setAttribute("cid", cid);
		request.setAttribute("fid", fid);
	}

}
