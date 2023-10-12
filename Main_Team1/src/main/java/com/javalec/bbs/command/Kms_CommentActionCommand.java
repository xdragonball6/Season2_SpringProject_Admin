package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Kms_WriteList_Dao;

public class Kms_CommentActionCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String f_cid = request.getParameter("f_cid");
		int f_pid = Integer.parseInt(request.getParameter("f_pid"));
		String ftitle = request.getParameter("ftitle");
		int fid = Integer.parseInt(request.getParameter("fid"));
		int fsteporder = Integer.parseInt(request.getParameter("fsteporder"));
		
		
		Kms_WriteList_Dao dao = new Kms_WriteList_Dao();
		dao.commentAction(f_cid, f_pid, ftitle, fid,fsteporder);
		
	}

}
