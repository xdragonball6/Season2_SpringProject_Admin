<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>편집하기</title>
	</head>
	<body>
	<c:forEach items="${list}" var="dto">
	<table border="1">
		<tr>
			<td>상품번호</td>
			<td>${dto.f_pid}</td>
			<td>상품가격</td>
			<td>${dto.pprice}</td>
		</tr>
		<tr>
			<td>종류</td>
			<td>${dto.categoryName}</td>
			<td>모델명</td>
			<td>${dto.pname}</td>
		</tr>
	</table>
	<p>상세 설명</p>
	<p>${dto.pcontent}</p>
	</c:forEach>
</body>
</html>