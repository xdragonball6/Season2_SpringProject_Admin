package com.javalec.bbs.command;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.MDao;


/**
 * Servlet implementation class SelectionDeleteCommand
 */
@WebServlet("*.bi")
public class SelectionDeleteCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectionDeleteCommand() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionGo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionGo(request, response);
	}
	
	private void actionGo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String[] bid = request.getParameterValues("selectedBids");
	    System.out.println(bid.length);
		
		MDao dao = new MDao();
		boolean result = dao.selectionDelete(bid);
		
		if(result == true) {
			response.setStatus(HttpServletResponse.SC_OK);			
		}else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	     
	}
	

}
