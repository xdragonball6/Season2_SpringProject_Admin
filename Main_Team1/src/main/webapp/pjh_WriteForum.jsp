<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰작성하기</title>
<link href="css/admin_kkg.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="aQnA_style.css">
<script type="text/javascript">
    function validateForumWriteForm() {
        var title = document.getElementsByName("ftitle")[0].value;
        var content = document.getElementsByName("fcontent")[0].value;

        if (title.trim() === "") {
            alert("제목을 입력하세요.");
            return false;
        }

        if (content.trim() === "") {
            alert("내용을 입력하세요.");
            return false;
        }

        return true;
    }
</script>
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

<h1>리뷰 작성</h1>
<form action="Aforumwrite.do" method="post" onsubmit="return validateForumWriteForm()">
			<input type="hidden" name="f_cid" value="${cid }">
    <table>
        <tr>
            <td>제목</td>
            <td><input type="text" name="ftitle" ></td>
        </tr>
        <tr>
            <td>작성자</td>
            <td><input type="text" name="cname" value="${param.cname }" readonly="readonly"></td>
        </tr>
        <tr>
            <td>상품코드</td>
            <td><input type="text" name="f_pid" value="${param.f_pid}" readonly="readonly"></td>
        </tr>
        <tr>
            <td colspan="2">내용</td>
        </tr>
    </table>
    <textarea name="fcontent" cols="35" rows="10"></textarea>
    <br/>
    <input type="submit" value="리뷰작성하기">
</form>
</div>
</main>
</div>
</div>
</body>
</html>