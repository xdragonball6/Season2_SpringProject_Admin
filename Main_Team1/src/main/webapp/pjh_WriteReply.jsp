<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글 쓰기</title>
<link href="css/admin_kkg.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="aQnA_style.css">
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

<h1>답글 작성</h1>
<form action="Areplywrite.do" method="post">
<input type="hidden" name="page" value="${page}">
<input type="hidden" name="ftype" value="${ftype}">
<input type="hidden" name="fid" value="${fid}">
<input type="hidden" name="fref" value="3">
<input type="hidden" name="freforder" value="0">
<input type="hidden" name="fstep" value="0">
<input type="hidden" name="fmotherid" value="${fid}">
<input type="hidden" name="fanswernum" value="0">
    <table>
        <tr>
            <td>제목</td>
            <td><input type="text" name="ftitle" ></td>
        </tr>
        <tr>
            <td>작성자</td>
            <td><input type="text" name="f_cid" value="관리자" readonly="readonly"></td>
        </tr>
        <tr>
            <td>상품코드</td>
            <td><input type="text" name="f_pid" value="${pid}" readonly="readonly"></td>
        </tr>
        <tr>
            <td colspan="2">내용</td>
        </tr>
    </table>
    <textarea name="fcontent" cols="35" rows="10"></textarea>
    <br/>
    <input type="submit" value="답글달기">
</form>
<form action="AForumView.do?fid=${fid}&ftype=${ftype}" method="post">
<input type="submit" value="취소">
</form>
</div>
</main>
</div>
</div>
</body>
</html>