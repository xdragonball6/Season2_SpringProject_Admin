package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.dao.Kms_DetailWriteList_Dao;
import com.javalec.bbs.dao.RDao;
import com.javalec.bbs.dto.Kms_WriteList_Dto;
import com.javalec.bbs.dto.RDto;

public class DetailedCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		
		int pid = Integer.parseInt(request.getParameter("pid"));
				
		RDao dao = new RDao();
		ArrayList<RDto> dtos = dao.DetailedProduct(pid);
		System.out.println(dtos);
		request.setAttribute("DetailedProduct", dtos);
		
		Kms_DetailWriteList_Dao Qdao = new Kms_DetailWriteList_Dao();
		ArrayList<Kms_WriteList_Dto> Qdtos = Qdao.QnADlist(pid);
		request.setAttribute("Qdto", Qdtos);
		
		Kms_DetailWriteList_Dao Rdao = new Kms_DetailWriteList_Dao();
		ArrayList<Kms_WriteList_Dto> Rdtos = Rdao.QnADlist(pid);
		request.setAttribute("Rdto", Rdtos);
	}

}
