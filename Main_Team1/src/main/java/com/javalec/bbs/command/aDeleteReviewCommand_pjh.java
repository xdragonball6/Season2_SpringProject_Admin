package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Product_Dao;
import com.javalec.bbs.dao.Admin_Review_Dao;

public class aDeleteReviewCommand_pjh implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Admin_Review_Dao dao = new Admin_Review_Dao();
		String[] selectedItems = request.getParameterValues("fid");
		dao.delete(selectedItems);
	}

}
