package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Pjh_WriteList_Dao;
import com.javalec.bbs.dto.Admin_WriteList_Dto;

public class Pjh_ANoticeViewCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int nid = Integer.parseInt(request.getParameter("nid"));
		int ftype= Integer.parseInt(request.getParameter("ftype"));
		System.out.println(nid);
		Pjh_WriteList_Dao dao = new Pjh_WriteList_Dao();
		Admin_WriteList_Dto dto=dao.noticeView(nid);
		request.setAttribute("noticeview", dto);
		request.setAttribute("nid", nid);
		request.setAttribute("ftype", ftype);
	}

}
