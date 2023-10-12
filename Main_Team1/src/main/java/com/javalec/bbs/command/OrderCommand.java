package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.dao.RDao;
import com.javalec.bbs.dto.PurchaseOrderDto;

public class OrderCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession(true);
//		String pidParameter = request.getParameter("pid");
//		int ppid = 0;
//		String ccid = (String)session.getAttribute("cid");
//		String oid = request.getParameter("oid");
//		String oqty = request.getParameter("oqty");
//		String oprice = request.getParameter("oprice");
//		String opostnum = request.getParameter("opostnum");
//		String oaddress1 = request.getParameter("oaddress1");
//		String oaddress2 = request.getParameter("oaddress2");
//		String odate = request.getParameter("odate");
//		String view;
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		int ppid = Integer.parseInt(request.getParameter("pid"));
		int bqty = Integer.parseInt(request.getParameter("qty"));
		String ccid = (String)session.getAttribute("cid");
		
		
		RDao dao = new RDao();
		ArrayList<PurchaseOrderDto> dtos = dao.orderList(ppid, bqty);
		request.setAttribute("orderList", dtos);
		
		
		ArrayList<PurchaseOrderDto> dtos2 = dao.list(ccid);
		request.setAttribute("list", dtos2);
		

//		orderList, pid, pfilename, pname, pcontent, bqty,
//		list cid, cname, cphone, cpostnum, caddress1,2

	}
}
