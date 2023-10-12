package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Pjh_WriteList_Dao;

public class Pjh_CommentActionCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int f_pid = Integer.parseInt(request.getParameter("f_pid"));
		String ftitle = request.getParameter("ftitle");
		int fid = Integer.parseInt(request.getParameter("fid"));
		
		
		Pjh_WriteList_Dao dao = new Pjh_WriteList_Dao();
		dao.commentAction(f_pid, ftitle, fid);
	}

}
