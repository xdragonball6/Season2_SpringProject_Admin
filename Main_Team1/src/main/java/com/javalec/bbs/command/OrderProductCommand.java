package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.dao.MDao;

public class OrderProductCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		request.setCharacterEncoding("utf-8");
		
		String[] bid = (String[]) session.getAttribute("bid");
		String cid = (String)session.getAttribute("cid");
		String[] pid = request.getParameterValues("pid[]");
		String[] qty = request.getParameterValues("qty[]");
		String[] price = request.getParameterValues("price[]");
		String postnum = request.getParameter("cpostnum");
		String address1 = request.getParameter("caddress1");
		String address2 = request.getParameter("caddress2");
		String memo = request.getParameter("memo");
		String payment = request.getParameter("payment");
		String cname = request.getParameter("cname");
		String cphone = request.getParameter("cphone");
		String view;
		session.setAttribute("Memo", memo);
		session.setAttribute("Payment", payment);
		session.setAttribute("Cname", cname);
		session.setAttribute("Cphone", cphone);

		
		MDao dao = new MDao();
		boolean result = dao.order(bid, cid, pid, qty, price, postnum, address1, address2);
		
		if(result == true) {
			view = "details.do";
		}else {
			view = "OrderView.jsp";
		}
		
		request.setAttribute("view", view);
		
	}

}
