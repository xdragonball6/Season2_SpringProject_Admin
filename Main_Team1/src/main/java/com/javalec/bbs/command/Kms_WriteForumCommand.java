package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Kms_WriteList_Dao;

public class Kms_WriteForumCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String f_cid = request.getParameter("f_cid");
		int f_pid = Integer.parseInt(request.getParameter("f_pid"));
		String ftitle = request.getParameter("ftitle");
		String fcontent = request.getParameter("fcontent");	
		
		Kms_WriteList_Dao dao = new Kms_WriteList_Dao();
		dao.forumAction(f_cid, f_pid, ftitle, fcontent);
	
	}

}
