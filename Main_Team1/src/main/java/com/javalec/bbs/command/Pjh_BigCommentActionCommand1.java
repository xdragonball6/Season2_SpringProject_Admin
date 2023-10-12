package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Pjh_WriteList_Dao;

public class Pjh_BigCommentActionCommand1 implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int ftype = Integer.parseInt(request.getParameter("ftype"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		int fid = Integer.parseInt(request.getParameter("fid"));
		String f_cid = request.getParameter("f_cid");
		int f_pid = Integer.parseInt(request.getParameter("f_pid"));
		int fref = Integer.parseInt(request.getParameter("fref"));
		int fstep = Integer.parseInt(request.getParameter("fstep"));
		int fsteporder = Integer.parseInt(request.getParameter("fsteporder"));
		int freforder = Integer.parseInt(request.getParameter("freforder"));
		String ftitle = request.getParameter("ftitle");
		String fcontent = request.getParameter("fcontent");	
		int fmotherid = Integer.parseInt(request.getParameter("fmotherid"));
		int fanswernum = Integer.parseInt(request.getParameter("fanswernum"));
		
		
		Pjh_WriteList_Dao dao = new Pjh_WriteList_Dao();
		dao.bigCommentAction1(fid, f_cid, f_pid, fref, fstep, freforder, ftitle, fcontent, fmotherid, fanswernum, fsteporder);
	}

}
