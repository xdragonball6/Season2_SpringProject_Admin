package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_QnA_Dao;
import com.javalec.bbs.dao.Kms_WriteList_Dao;
import com.javalec.bbs.dto.Admin_QnA_Dto;
import com.javalec.bbs.dto.Kms_WriteList_Dto;

public class Kms_WriteListCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int Ftype = Integer.parseInt(request.getParameter("ftype"));
		
		Kms_WriteList_Dao dao = new Kms_WriteList_Dao();
		ArrayList<Kms_WriteList_Dto> dtos = dao.list(Ftype);
		
		Admin_QnA_Dao daos = new Admin_QnA_Dao();
		ArrayList<Admin_QnA_Dto> dtos2 = daos.Noticelist();
		
		int listSize= dtos.size();
		request.setAttribute("ftype", Ftype);
		request.setAttribute("RList", dtos);
		request.setAttribute("noticelist", dtos2);
		request.setAttribute("ListSize", listSize);
	}

}
