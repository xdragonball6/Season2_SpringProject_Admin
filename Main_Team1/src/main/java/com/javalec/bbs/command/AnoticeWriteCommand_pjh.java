package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnoticeWriteCommand_pjh implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int ftype= Integer.parseInt(request.getParameter("ftype"));
		System.out.println(ftype);
		request.setAttribute("ftype", ftype);
	}

}
