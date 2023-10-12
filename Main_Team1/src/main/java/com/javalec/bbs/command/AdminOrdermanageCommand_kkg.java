package com.javalec.bbs.command;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Dao_kkg;
import com.javalec.bbs.dto.Admin_Order_Dto_kkg;

public class AdminOrdermanageCommand_kkg implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String endDay ;
		String cid ;
		Timestamp startDate;
		Timestamp endDate;
		Admin_Dao_kkg dao = new Admin_Dao_kkg();
		String startDay;
		
		if(request.getParameter("customerId")==null) {
			
			cid = "1";
		}else {
			
			cid = request.getParameter("customerId");

		}
		
		
		if(request.getParameter("startDate")==null) {
			System.out.println("null값 확정");
			
			startDay = "1900-01-01";
			startDate = Timestamp.valueOf("1900-01-01 00:00:00");
			
			System.out.println(startDate);
			endDate = Timestamp.valueOf(LocalDate.now().plusDays(1).atStartOfDay());
			LocalDate localDate = endDate.toLocalDateTime().toLocalDate();
	        endDay = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		}else {
			
			startDay = request.getParameter("startDate");
			endDay = request.getParameter("endDate");
			startDate = Timestamp.valueOf(request.getParameter("startDate")+" 00:00:00");
			endDate = Timestamp.valueOf(request.getParameter("endDate")+" 23:59:59");
			
		}
		
		
		
		
		
		ArrayList<Admin_Order_Dto_kkg> dtos = dao.getOrderlist(cid,startDate,endDate);
		
		int ListSize = dtos.size();
		
		request.setAttribute("orderList", dtos);
		request.setAttribute("ListSize", ListSize);
		request.setAttribute("customerId", cid);
		request.setAttribute("startDate", startDay);
		request.setAttribute("endDate", endDay);
	}

}
