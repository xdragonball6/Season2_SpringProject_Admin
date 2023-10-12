package com.javalec.bbs.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.dao.MDao;

/**
 * Servlet implementation class PwChangeCommand
 */
@WebServlet("*.cg")
public class PwChangeCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwChangeCommand() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actioncg(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actioncg(request, response);
	}
	
	private void actioncg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		String cpassword = request.getParameter("password");
		String newpassword = request.getParameter("newpassword");
		String cid = (String)session.getAttribute("cid");


		MDao dao = new MDao();
		boolean result = dao.pwchange(cid, cpassword, newpassword);
	    
		System.out.println(result);
	    if (result == true) {
	    	
	    	response.setStatus(HttpServletResponse.SC_OK);
	    	response.getWriter().print("success");
	    } else {

		      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		      response.getWriter().print("Login failed");
	    }
	  
	}	
}
