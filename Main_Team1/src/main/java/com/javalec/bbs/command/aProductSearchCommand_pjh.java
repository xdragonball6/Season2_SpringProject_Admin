package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Product_Dao;
import com.javalec.bbs.dto.Admin_Product_Dto;

public class aProductSearchCommand_pjh implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String list = request.getParameter("list");
		String query = request.getParameter("query");
		if (list.equals("pcategory")) {
		    // 단어와 인식값을 매핑하는 Map을 생성합니다.
		    Map<String, String> keywordMap = new HashMap<>();
		    keywordMap.put("조", "0");
		    keywordMap.put("명", "0");
		    keywordMap.put("미", "1");
		    keywordMap.put("니", "1");
		    keywordMap.put("어", "1");
		    keywordMap.put("쳐", "1");
		    keywordMap.put("의", "2");
		    keywordMap.put("자", "2");

		    // 입력된 쿼리를 한 글자씩 확인하고 해당하는 인식값을 설정합니다.
		    String pcategory = "";
		    for (int i = 0; i < query.length(); i++) {
		        String keyword = query.substring(i, i+1);
		        if (keywordMap.containsKey(keyword)) {
		            pcategory = keywordMap.get(keyword);
		            break;
		        }
		    }
		    
		    String uploadPath = "image/";
		    Admin_Product_Dao dao = new Admin_Product_Dao();
		    ArrayList<Admin_Product_Dto> dtos = dao.search1(list, pcategory);
		    
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
		} else if (list.equals("pname")) {
		    // pname에 대한 처리를 진행합니다.
		    String uploadPath = "image/";
		    Admin_Product_Dao dao = new Admin_Product_Dao();
		    ArrayList<Admin_Product_Dto> dtos = dao.search1(list, query);
		    
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
	}
