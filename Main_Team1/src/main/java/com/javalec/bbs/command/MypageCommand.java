package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.dao.MDao;
import com.javalec.bbs.dto.MDto;

public class MypageCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String cid = (String)session.getAttribute("cid");
		
		MDao dao = new MDao();
		ArrayList<MDto> dtos = dao.list(cid);
		
		request.setAttribute("list", dtos);
		
	}

}
