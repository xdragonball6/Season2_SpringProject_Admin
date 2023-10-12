<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<link href="css/admin_kkg.css" rel="stylesheet">
  <meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
    <title>상품 추가하기</title>
<!-- 여기서부터 복사하시면 됩니다~~~~~~~~~!!!! -->

<jsp:include page="admin_01_header.jsp" />


</head>

<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<jsp:include page="admin_01_sidebar.jsp" />
			</div>

			<div class="col-md-10" style="margin-left: 15%;">
				<main class="ms-sm-auto px-md-4">

					<!-- 요기서부터 본문 내용 입력하면 됩니다아~~!!!!!  하단에  </div> 및 </main> 자리 맞춰서 넣는거만 기억하면 됩니다.-->

		<h3>상품 추가하기</h3>
		<form action="AProductInsert.do" method="post"
			enctype="multipart/form-data">
			<table>
				<tr>
					<th>상품 설정</th>
				</tr>
				<tr>
					<td>이름</td>
				</tr>
				<tr>
					<td><input type="text" name="pname"></td>
				</tr>
				<tr>
					<td>카테고리</td>
				</tr>
				<tr>
					<td><input type="text" name="pcategory"></td>
				</tr>
				<tr>
				<tr>
					<td>가격</td>
				</tr>
				<tr>
					<td><input type="text" name="pprice"></td>
				</tr>
				<tr>
					<td>대표 이미지</td>
				</tr>
				<tr>
					<td><input type="file" name="file"></td>
				</tr>
				<tr>
					<td>수량</td>
				</tr>
				<tr>
					<td><input type="text" name="pstock"></td>
				</tr>
				<tr>
				<td>상세 설명</td>
				</tr>
				<tr>
				<td><textarea id="content" name="pcontent"></textarea></td>
				<td><input type="file" name="file1"><br/>
					<input type="file" name="file2"></td>
				</tr>
			</table>
			<input type="submit" value="업로드">
		</form>
		</main>
		</div>
		</div>
		</div>
</body>
</html>