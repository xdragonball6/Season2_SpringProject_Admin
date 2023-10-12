package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.dao.MDao;
import com.javalec.bbs.dto.DetailsDto;

public class DetailsCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		request.setCharacterEncoding("utf-8");
		String cid = (String)session.getAttribute("cid");
		String cname = (String)session.getAttribute("Cname");
		String cphone = (String)session.getAttribute("Cphone");
		
		
		MDao dao = new MDao();
		ArrayList<DetailsDto> dtos = dao.details(cid, cname, cphone);
		
		request.setAttribute("detail", dtos);
	}

}
