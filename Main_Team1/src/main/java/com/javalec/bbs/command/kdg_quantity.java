package com.javalec.bbs.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.MDao;

/**
 * Servlet implementation class kdg_quantity
 */
@WebServlet("*.change")
public class kdg_quantity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public kdg_quantity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionjn(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionjn(request, response);
	}
	
	private void actionjn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수량변경
		request.setCharacterEncoding("utf-8");
		int pid = Integer.parseInt(request.getParameter("pid"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		
		MDao dao = new MDao();
	    boolean result = dao.qtyChange(pid, qty);

	    if(result == true) {
	    	response.setStatus(HttpServletResponse.SC_OK);
	    }else {
	    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    }

	  
	}	

}
