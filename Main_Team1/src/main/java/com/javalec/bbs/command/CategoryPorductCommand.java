package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.HomeDao;
import com.javalec.bbs.dto.HomeDto;

public class CategoryPorductCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int c_num = Integer.parseInt(request.getParameter("num"));
		String view;
		
		if(c_num == 0) {
			view = "LampView.jsp";
		}else if(c_num ==1) {
			view = "MinatureView.jsp";
		}else {
			view = "ChairView.jsp";
		}
		HomeDao dao = new HomeDao();
		ArrayList<HomeDto> list = dao.lamplist(c_num);
		
		request.setAttribute("list", list);
		request.setAttribute("view", view);
	}

}
