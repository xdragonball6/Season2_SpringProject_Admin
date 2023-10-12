package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Kms_WriteList_Dao;

public class Pjh_CommentDeleteCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int fid = Integer.parseInt(request.getParameter("fid"));
		int page = Integer.parseInt(request.getParameter("page"));
		int ftype = Integer.parseInt(request.getParameter("ftype"));
		request.setAttribute("ftype", ftype);
		Kms_WriteList_Dao dao = new Kms_WriteList_Dao();
		dao.commentdelete(fid);
	}

}
