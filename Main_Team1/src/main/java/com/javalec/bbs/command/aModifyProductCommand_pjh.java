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

public class aModifyProductCommand_pjh implements MCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // TODO Auto-generated method stub
//		String directory = "/Volumes/Data/jihwan/Main_Team1/src/main/webapp/image/";
		String directory = request.getServletContext().getRealPath("/image/");
		System.out.println(directory);
        int maxSize = 1024 * 1024 * 100;
        String encoding = "UTF-8";
        request.setCharacterEncoding("utf-8");
        // MultipartRequest 객체 생성
        MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding,
                new DefaultFileRenamePolicy());

        // 새로운 파일 이름 변수 초기화
        String fileName = null;
        String contentfileName1 = null;
        String contentfileName2 = null;

        // 업로드된 파일 가져오기
        File file = multipartRequest.getFile("pfilename");
        File contentfile1 = multipartRequest.getFile("pcontentfilename1");
        File contentfile2 = multipartRequest.getFile("pcontentfilename2");

        String newFileName;
        String newContentFileName1;
        String newContentFileName2;
        if (file != null) {
            // 파일이 선택된 경우에만 처리
            String originalFileName = file.getName();
            String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String originalFileName1 = contentfile1.getName();
            String extension1 = originalFileName1.substring(originalFileName.lastIndexOf(".") + 1);
            String originalFileName2 = contentfile2.getName();
            String extension2 = originalFileName2.substring(originalFileName.lastIndexOf(".") + 1);
            String pid = multipartRequest.getParameter("pid");
            
            // 새로운 파일 이름 생성
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            String timeStamp = now.format(formatter);

            newFileName = pid + "_" + timeStamp + "." + extension;
            newContentFileName1 = "content1_" + pid + "_" + timeStamp +extension1;
            newContentFileName2 = "content2_" + pid + "_" + timeStamp +extension2;
            File newFile = new File(directory, newFileName);
            File newContentFile1 = new File(directory, newContentFileName1);
            File newContentFile2 = new File(directory, newContentFileName2);
            file.renameTo(newFile);
            contentfile1.renameTo(newContentFile1);
            contentfile2.renameTo(newContentFile2);

            // 원본 이미지 파일 삭제
            file.delete();
        } else {
            newFileName = "";
            newContentFileName1="";
            newContentFileName2="";
        }

        fileName = newFileName;
        contentfileName1 = newContentFileName1;
        contentfileName2 = newContentFileName2;
        // 폼 데이터 가져오기
        String pname = multipartRequest.getParameter("pname");
        int pstock = 0;
        String pstockStr = multipartRequest.getParameter("pstock");
        if (pstockStr != null && !pstockStr.isEmpty()) {
            pstock = Integer.parseInt(pstockStr);
        }
        String pcontent = multipartRequest.getParameter("pcontent");
        int pprice = 0;
        String ppriceStr = multipartRequest.getParameter("pprice");
        if (ppriceStr != null) {
            pprice = Integer.parseInt(ppriceStr);
        }
        int pid = 0;
        String pidStr = multipartRequest.getParameter("pid");
        if (pidStr != null) {
            pid = Integer.parseInt(pidStr);
        }

        // DAO 객체 생성 및 수정 메소드 호출
        Admin_Product_Dao dao = new Admin_Product_Dao();
        dao.modify(pid, pname, fileName, pcontent, contentfileName1, contentfileName2, pstock, pprice);
        String updatedFileName = new Admin_Product_Dao().getModifyFileName(pid);
        String updateContentFileName1 = new Admin_Product_Dao().getUpdatedContentFileName1();
        String updateContentFileName2 = new Admin_Product_Dao().getUpdatedContentFileName2();
        
        renameImageFile(directory, fileName, updatedFileName);
        System.out.println(fileName);
        System.out.println(updatedFileName);
        renameImageFile(directory, contentfileName1, updateContentFileName1);
        renameImageFile(directory, contentfileName2, updateContentFileName2);
    }

        public void renameImageFile(String directory, String oldFileName, String newFileName) {
            File oldFile = new File(directory, oldFileName);
            File newFile = new File(directory, newFileName);
            boolean success = oldFile.renameTo(newFile);
            if (success) {
                System.out.println("이미지 파일 이름 변경 완료");
            } else {
                System.out.println("이미지 파일 이름 변경 실패");
            }
        }
        
    }
