package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Product_Dao;
import com.javalec.bbs.dto.Admin_Product_Dto;

public class AProductSearchCommand2 implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String query = request.getParameter("query");
		Admin_Product_Dao dao = new Admin_Product_Dao();
		ArrayList<Admin_Product_Dto> dtos = dao.search2(query);
		String uploadPath = "image/";
		
		for (Admin_Product_Dto dto : dtos) {
	        String category = dto.getC_name();
	        String c_name;
	        switch (category) {
	            case "0":
	                c_name = "조명";
	                break;
	            case "1":
	                c_name = "미니어쳐";
	                break;
	            case "2":
	                c_name = "의자";
	                break;
	            default:
	                c_name = "";
	                break;
	        }
	        dto.setC_name(c_name);
		String fileName = dto.getPfilename();
	        String imagePath = uploadPath + fileName;
	        dto.setPfilename(imagePath);
	        int price = dto.getPprice();
	        System.out.println(dto.getC_name());
	    }
	    
	    request.setAttribute("list", dtos);
	}

}
