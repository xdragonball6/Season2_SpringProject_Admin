package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.dao.HomeDao;
import com.javalec.bbs.dto.HomeDto;

public class CartCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		
		String cid = (String)session.getAttribute("cid");
		
		HomeDao dao = new HomeDao();
		ArrayList<HomeDto> list = dao.cartlist(cid);
		
		request.setAttribute("list", list);
		
	}

}
