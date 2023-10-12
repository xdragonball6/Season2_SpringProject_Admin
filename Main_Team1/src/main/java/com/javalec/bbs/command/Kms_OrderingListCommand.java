package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Kms_UserOrderingLIst_Dao;
import com.javalec.bbs.dto.Kms_OrderingList_Dto;

public class Kms_OrderingListCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String cid = request.getParameter("cid");
		
		Kms_UserOrderingLIst_Dao dao = new Kms_UserOrderingLIst_Dao();
		ArrayList<Kms_OrderingList_Dto> dtos = dao.orderinglist(cid);
		request.setAttribute("Olist", dtos);
		
	}

}
