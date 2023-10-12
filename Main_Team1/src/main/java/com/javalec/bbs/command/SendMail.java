package com.javalec.bbs.command;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet("*.nu")
public class SendMail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SendMail() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        actionmail(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        actionmail(request, response);
    }

    private void actionmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
        final String username = "rkdeorb1478@gmail.com";
        final String password = "zulwebgvyrglhsmv";
        String from = "rkdeorb1478@gmail.com";
        String to = request.getParameter("email");
        String ecode = request.getParameter("sendCode");
        
        
        
        String host = "smtp.gmail.com";
        int port = 587;

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.3");
        properties.put("mail.smtp.ssl.ciphersuites", "TLS_AES_128_GCM_SHA256");


        Session session = Session.getInstance(properties, new Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Little and Precious 인증번호 입니다.");
            message.setText("인증번호 : " + ecode);

            Transport.send(message);
           
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

       

    }
}
