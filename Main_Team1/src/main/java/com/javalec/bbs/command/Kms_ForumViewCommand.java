package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Kms_WriteList_Dao;
import com.javalec.bbs.dto.Kms_WriteList_Dto;

public class Kms_ForumViewCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int fid = Integer.parseInt(request.getParameter("fid"));
		
		Kms_WriteList_Dao dao = new Kms_WriteList_Dao();
		Kms_WriteList_Dto dto = dao.ForumView(fid);
		request.setAttribute("forumView", dto);
		
		Kms_WriteList_Dao dao1 = new Kms_WriteList_Dao();
		ArrayList<Kms_WriteList_Dto> dtos1 = dao1.commentList(fid);
		request.setAttribute("Clist", dtos1);
		int listSize= dtos1.size();
		request.setAttribute("ListSize", listSize);
	}

}
