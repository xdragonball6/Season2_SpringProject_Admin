<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
        
    <title>리뷰 게시판</title>

</head>
<style>

</style>
<body>
    <h1>나의 리뷰</h1>
    <h4>리뷰 목록</h4>
    <table>
        <thead>
            <tr>
                  <th>상품이미지</th>
                  <th>번호</th>
            <th>작성자</th>
            <th>상품</th>
            <th>제목</th>
            <th>내용</th>
            <th>작성일</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${Myreview}" var="dto">
          <td class="product-image">
            <img alt="상품 이미지" style="width: 100px; height: 100px;" src="${dto.ppfilename}">
            </td>
                <td>${dto.fid}</td>
                <td>${dto.f_cid}</td>
                <td>${dto.pname}</td>
                <td>${dto.ftitle}</td>
                <td>${dto.fcontent}</td>
                <td>${dto.finsertdate}</td>
            </c:forEach>
        </tbody>
    </table>
    <%@ include file="bottom.jsp" %>
</body>
</html>