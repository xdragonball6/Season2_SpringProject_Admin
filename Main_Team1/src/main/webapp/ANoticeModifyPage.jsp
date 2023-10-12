<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
	<title>공지사항 수정</title>
	<link href="css/admin_kkg.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="aQnA_style.css">
	<style>
		body {
			font-family: Arial, sans-serif;
			background-color: #f8f8f8;
		}

		.container {
			max-width: 1200px;
			margin: 0 auto;
			padding: 20px;
		}

		h3 {
			color: #333;
			font-size: 24px;
			margin-top: 20px;
		}

		.form-table {
			width: 100%;
			border-collapse: collapse;
			margin-top: 20px;
		}

		.form-table td {
			padding: 10px;
		}

		.form-table td:first-child {
			width: 120px;
			font-weight: bold;
		}

		.form-table input[type="text"],
		.form-table textarea {
			width: 100%;
			padding: 8px;
			border: 1px solid #ccc;
			border-radius: 4px;
			box-sizing: border-box;
			font-size: 14px;
		}

		.form-table textarea {
			height: 150px;
		}

		.btn-group {
			margin-top: 20px;
			text-align: right;
		}

		.btn-group input[type="submit"] {
			background-color: #4CAF50;
			color: #fff;
			border: none;
			padding: 8px 16px;
			text-decoration: none;
			font-size: 14px;
			cursor: pointer;
			border-radius: 4px;
		}

		.btn-group input[type="submit"]:hover {
			background-color: #45a049;
		}
	</style>
	<!-- 여기서부터 복사하시면 됩니다~~~~~~~~~!!!! -->
	<jsp:include page="admin_01_header.jsp" />
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<jsp:include page="admin_01_sidebar.jsp" />
			</div>
			<div class="col-md-10" style="margin-left: 15%;">
				<main class="ms-sm-auto px-md-4">
					<!-- 요기서부터 본문 내용 입력하면 됩니다아~~!!!!!  하단에  </div> 및 </main> 자리 맞춰서 넣는거만 기억하면 됩니다.-->
					<form action="AnoticeModify.do" method="post">
						<table class="form-table">
							<tr>
								<td>제목</td>
								<td>
									<input type="hidden" name="nid" value="${nid}">
									<input type="hidden" name="ftype" value="${ftype}">
									<input type="text" name="ntitle" value="${ntitle}">
									</td>
							</tr>
							<tr>
								<td>작성자</td>
								<td>
									<input type="text" name="n_aid" value="${n_aid}" disabled/>
								</td>
							</tr>
							<tr>
								<td>입력날짜</td>
								<td>
									<input type="text" name="ninsertdate" value="${ninsertdate}" disabled/>
								</td>
							</tr>
							<tr>
								<td>내용</td>
								<td>
									<textarea name="ncontent">${ncontent}</textarea>
								</td>
							</tr>
						</table>
						<div class="btn-group">
							<input type="submit" value="저장">
							<input type="button" value="취소" onclick="history.back()">
						</div>
					</form>
				</main>
			</div>
		</div>
	</div>
</body>
</html>
