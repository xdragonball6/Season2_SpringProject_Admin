package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.HomeDao;
import com.javalec.bbs.dto.HomeDto;

public class HomePorductCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HomeDao dao = new HomeDao();
		ArrayList<HomeDto> list = dao.list();
		
		request.setAttribute("list", list);
		
	}

}
