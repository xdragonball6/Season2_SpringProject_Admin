package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Kms_WriteList_Dao;
import com.javalec.bbs.dao.Pjh_WriteList_Dao;

public class Pjh_ReplyActionCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int page = Integer.parseInt(request.getParameter("fid"));
		int ftype = Integer.parseInt(request.getParameter("ftype"));
		int fid = Integer.parseInt(request.getParameter("fid"));
		String f_cid = request.getParameter("f_cid");
		int f_pid = Integer.parseInt(request.getParameter("f_pid"));
		int fref = Integer.parseInt(request.getParameter("fref"));
		int fstep = Integer.parseInt(request.getParameter("fstep"));
		int freforder = Integer.parseInt(request.getParameter("freforder"));
		String ftitle = request.getParameter("ftitle");
		String fcontent = request.getParameter("fcontent");	
		int fmotherid = Integer.parseInt(request.getParameter("fmotherid"));
		int fanswernum = Integer.parseInt(request.getParameter("fanswernum"));
//		int ftype=Integer.parseInt(request.getParameter("ftype"));
		
		Pjh_WriteList_Dao dao = new Pjh_WriteList_Dao();
		dao.replyAction(fid, f_cid, f_pid, fref, fstep, freforder, ftitle, fcontent, fmotherid, fanswernum);
	}

}
