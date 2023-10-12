package com.javalec.bbs.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.MDao;


/**
 * Servlet implementation class JoinDbCommand
 */
@WebServlet("*.jn")
public class JoinDbCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinDbCommand() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String cid = request.getParameter("email");
		String cpassword = request.getParameter("password");
		String cname = request.getParameter("name");
		String cphone = request.getParameter("phone");
		String cbirth = request.getParameter("cbirth");
		String cgender = request.getParameter("genderInputs");
		String cpostnum = request.getParameter("cpostnum");
		String caddress1 = request.getParameter("caddress1");
		String caddress2 = request.getParameter("caddress2");
		int gender = 0;
		
		if(cgender.equals("male")) {
			gender = 1;
		}else {
			gender = 0;
		}


		MDao dao = new MDao();
		int result = dao.join(cid, cpassword, cname, cphone, cbirth, gender, cpostnum, caddress1, caddress2);
	    

	    if (result == 1) {
	        // 회원가입 성공
	    	response.setStatus(HttpServletResponse.SC_OK);
	    	response.getWriter().print("success");
	    } else {
	        // 회원가입 실패
		      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		      response.getWriter().print("Login failed");
	    }
	  
	}	
}
