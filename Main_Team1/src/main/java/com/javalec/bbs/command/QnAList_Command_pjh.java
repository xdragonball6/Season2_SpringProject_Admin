package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_QnA_Dao;
import com.javalec.bbs.dto.Admin_QnA_Dto;

public class QnAList_Command_pjh implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Admin_QnA_Dao dao = new Admin_QnA_Dao();
		ArrayList<Admin_QnA_Dto> dtos = dao.list();
		ArrayList<Admin_QnA_Dto> dtos2 = dao.Noticelist();
		int listSize= dtos.size();
		for (Admin_QnA_Dto dto : dtos) {
		int fid=dto.getFid();
		}
		request.setAttribute("list", dtos);
		request.setAttribute("noticelist", dtos2);
		request.setAttribute("listSize", listSize);
	}

}
