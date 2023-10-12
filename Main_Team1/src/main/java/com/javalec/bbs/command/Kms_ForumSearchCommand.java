package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Kms_WriteList_Dao;
import com.javalec.bbs.dto.Kms_WriteList_Dto;


public class Kms_ForumSearchCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int Ftype = Integer.parseInt(request.getParameter("ftype"));
		String content = request.getParameter("content");
		
		
		Kms_WriteList_Dao dao = new Kms_WriteList_Dao();
		ArrayList<Kms_WriteList_Dto> dtos = dao.forumsearch(Ftype,content);
		int listSize= dtos.size();
		request.setAttribute("RList", dtos);
		request.setAttribute("ftype", Ftype);
		request.setAttribute("ListSize", listSize);
	}

}
