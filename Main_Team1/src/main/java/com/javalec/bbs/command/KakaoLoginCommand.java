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
 * Servlet implementation class KakaoLoginCommand
 */
@WebServlet("*.kao")
public class KakaoLoginCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakaoLoginCommand() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionKa(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionKa(request, response);
	}

	private void actionKa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		
	    String cid = request.getParameter("cid");
	    String cname = request.getParameter("cname");
	   
	   

		
	    MDao dao = new MDao();
	    int result = dao.kakaoLogin(cid);
	    
	    if (result == 2) {
	    	// 탈퇴한 회원
	    	response.setStatus(HttpServletResponse.SC_OK);	
	    	response.getWriter().print("mdraw"); // 탈퇴	
	    } else if(result == 1) {
	    	// 로그인 성공
	    	response.setStatus(HttpServletResponse.SC_OK);
	    	response.getWriter().print("success");	// 로그인
    		session.setAttribute("cid", cid);
    		session.setAttribute("name", cname);
	    } else if(result == 0) {
	    	// 회원가입이 안된 회원
	    	response.setStatus(HttpServletResponse.SC_OK);
	    	response.getWriter().print("join");	// 회원가입
    		session.setAttribute("jid", cid);
    		session.setAttribute("jname", cname);
	    }else {
	    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    }
	    
	}
	
	
}
