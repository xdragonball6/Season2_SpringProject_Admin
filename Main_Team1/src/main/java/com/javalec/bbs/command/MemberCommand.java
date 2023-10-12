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
 * Servlet implementation class member
 */
@WebServlet("*.mb")
public class MemberCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberCommand() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionmb(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionmb(request, response);
	}

	private void actionmb(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		String cid = request.getParameter("email");
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
		boolean result = dao.memberUpdate(cid, cname, cphone, cbirth, gender, cpostnum, caddress1, caddress2);
	    

	    if (result == true) {
	        // 회원정보 수정
	    	response.setStatus(HttpServletResponse.SC_OK);
	    	response.getWriter().print("success");
	    	session.removeAttribute("name");
	    	session.setAttribute("name", cname);
	    } else {
	        // 회원가입 
		      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		      response.getWriter().print("failed");
	    }
	  
	}	
}
