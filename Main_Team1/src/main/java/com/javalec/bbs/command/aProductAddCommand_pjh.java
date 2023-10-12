package com.javalec.bbs.command;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Product_Dao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class aProductAddCommand_pjh implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
//		String directory = "/Volumes/Data/jihwan/Main_Team1/src/main/webapp/image/";
		String directory = request.getServletContext().getRealPath("/image/");
		System.out.println(directory);
        int maxSize = 1024 * 1024 * 100;
        String encoding = "UTF-8";
        request.setCharacterEncoding("utf-8");
        MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
        
        String fileName = multipartRequest.getOriginalFileName("file");
        String fileName1 = multipartRequest.getOriginalFileName("file1");
        String fileName2 = multipartRequest.getOriginalFileName("file2");
        String fileRealName = multipartRequest.getFilesystemName("file");
        String fileRealName1 = multipartRequest.getFilesystemName("file1");
        String fileRealName2 = multipartRequest.getFilesystemName("file2");
        String filepath1="../webapp/imag/";
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        String extension1 = fileName1.substring(fileName1.lastIndexOf(".")+1);
        String extension2 = fileName2.substring(fileName2.lastIndexOf(".")+1);
        String pname = multipartRequest.getParameter("pname");
        String pcategory = multipartRequest.getParameter("pcategory");
        String pstock = multipartRequest.getParameter("pstock");
        String pprice = multipartRequest.getParameter("pprice");
        String pcontent = multipartRequest.getParameter("pcontent");
        pcontent = pcontent.replaceAll("<p>", "").replaceAll("</p>", "");

        if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg")) {
            File file = new File(directory + fileRealName);
            file.delete();
            request.setAttribute("uploadError", "업로드 할 수 없는 확장자입니다.");
        } else {
        	 LocalDateTime now = LocalDateTime.now();
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
             String timeStamp = now.format(formatter);

             String newFileName = timeStamp + "." + extension;
             String newFileName1 = timeStamp + "." + extension1;
             String newFileName2 = timeStamp + "." + extension2;
             String filePath = directory + newFileName; // 파일 경로 생성
             int result = new Admin_Product_Dao().saveProduct(pname, pprice, pstock, pcontent, pcategory, newFileName, newFileName1, newFileName2);
             if (result == -1) {
                 System.out.println("업로드에 실패했습니다.");
             } else {
                 request.setAttribute("fileName", fileName);
                 request.setAttribute("fileRealName", fileRealName);
                 request.setAttribute("fileDirectory", filePath);
                 request.setAttribute("filepath1", filepath1);
                 
                 String updatedFileName = new Admin_Product_Dao().getUpdatedFileName();
                 String updateContentFileName1 = new Admin_Product_Dao().getUpdatedContentFileName1();
                 String updateContentFileName2 = new Admin_Product_Dao().getUpdatedContentFileName2();
                 
                 // 파일 이름 변경 실행
                 renameImageFile(directory, fileRealName, updatedFileName);
                 renameImageFile(directory, fileRealName1, updateContentFileName1);
                 renameImageFile(directory, fileRealName2, updateContentFileName2);
             }
         }
     }

     public void renameImageFile(String directory, String oldFileName, String newFileName) {
         File oldFile = new File(directory, oldFileName);
         File newFile = new File(directory, newFileName);
         System.out.println(directory);
         boolean success = oldFile.renameTo(newFile);
         if (success) {
             System.out.println("이미지 파일 이름 변경 완료");
         } else {
             System.out.println("이미지 파일 이름 변경 실패");
         }
     }
}