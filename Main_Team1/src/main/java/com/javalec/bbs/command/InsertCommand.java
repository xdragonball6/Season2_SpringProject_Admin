package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.dao.RDao;

public class InsertCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		int pid = Integer.parseInt(request.getParameter("pid"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		String cid = (String)session.getAttribute("cid");
		String view;
		
		RDao dao = new RDao();
		boolean result = dao.InsertCart(cid, pid, qty);
		
		if(result == true) {
			view = "cart.do";
			request.setAttribute("view",  view);
		}else {
			view = "detailedpage.do";
			request.setAttribute("view",  view);
		}
		
		
	}

}
