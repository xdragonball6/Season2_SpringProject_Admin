package com.javalec.bbs.command;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Pjh_WriteList_Dao;

public class Pjh_CheckCommentActionCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String[] fidList = request.getParameter("fidList").split(",");
		String[] pidList = request.getParameter("pidList").split(",");
		System.out.println(Arrays.toString(fidList));
		System.out.println(Arrays.toString(pidList));

		String ftitle = request.getParameter("content");
		Pjh_WriteList_Dao dao = new Pjh_WriteList_Dao();
		for (int i = 0; i < fidList.length; i++) {
		    int fid = Integer.parseInt(fidList[i].trim());
		    int pid = Integer.parseInt(pidList[i].trim());
		    System.out.println(fid);
		    System.out.println(pid);
		    dao.checkcommentAction(pid, ftitle, fid);
		}
	}
}