package com.javalec.bbs.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.MDao;

/**
 * Servlet implementation class memberDraw
 */
@WebServlet("*.dw")
public class memberDraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberDraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actiondw(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actiondw(request, response);
	}
	
	private void actiondw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String cid = request.getParameter("email");

	    MDao dao = new MDao();
	    boolean result = dao.memberDraw(cid);
	    
	    if(result == true) {
	    	response.setStatus(HttpServletResponse.SC_OK);
	    	response.getWriter().print("success");
	    } else {
		    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		    response.getWriter().print("failed");
	    }
	    

	}
	
	
	
	

}
