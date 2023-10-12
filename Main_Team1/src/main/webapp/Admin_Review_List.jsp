<%@ page import="com.javalec.bbs.dao.Admin_Product_Dao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.File"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/admin_kkg.css" rel="stylesheet">
<!-- CSS 파일 추가 -->
<link href="css/modal.css" rel="stylesheet">
  
<meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
<title>리뷰 목록</title>
<script src="js/modal.js"></script>
<script>
var fidList = '${fidList}'.split(',');
var pidList = '${pidList}'.split(',');

function selectAll() {
    var checkboxes = document.getElementsByName('selectedItems');
    var selectAllCheckbox = document.getElementById('selectAllCheckbox');

    for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = selectAllCheckbox.checked;
    }
}


function deleteSelectedItems() {
    var checkboxes = document.getElementsByName('selectedItems');
    var selectedItems = [];

    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            selectedItems.push(checkboxes[i].value);
        }
    }

    if (selectedItems.length === 0) {
        alert('삭제할 상품을 선택해주세요.');
        return;
    }

    // 선택된 상품들의 값을 'fid'라는 이름으로 서버로 전송
    var form = document.createElement('form');
    form.method = 'POST';
    form.action = 'deleteReview.do';

    for (var i = 0; i < selectedItems.length; i++) {
        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'fid';
        input.value = selectedItems[i];
        form.appendChild(input);
    }

    document.body.appendChild(form);
    form.submit();

    return false; // 폼 제출 중지
}

function openCommentModal() {
    var checkboxes = document.getElementsByName('selectedItems');
    var selectedItems = [];
    var selectedPids = [];

    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            selectedItems.push(checkboxes[i].value);
            selectedPids.push(checkboxes[i].dataset.pid);
        }
    }

    if (selectedItems.length === 0) {
        alert('댓글을 작성할 상품을 선택해주세요.');
        return;
    }

    var modalContent = document.getElementById('commentModalContent');
    var modal = document.getElementById('commentModal');

    // 선택된 fid들을 모달창 내부의 숨은 필드에 설정
    var fidInput = modalContent.querySelector('input[name="fidList"]');
    fidInput.value = selectedItems.join(',');

    // 선택된 f_pid들을 모달창 내부의 숨은 필드에 설정
    var pidInput = modalContent.querySelector('input[name="pidList"]');
    pidInput.value = selectedPids.join(',');

    // 모달창을 보여줌
    modal.style.display = 'block';
}

function closeCommentModal() {
    var modalContent = document.getElementById('commentModalContent');
    var modal = document.getElementById('commentModal');

    // 모달창 내부의 숨은 필드를 제거
    modalContent.innerHTML = '';

    // 모달창을 닫음
    modal.style.display = 'none';
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
<div id="commentModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeCommentModal()">&times;</span>
        <div id="commentModalContent">
            <!-- 텍스트 입력 폼 및 저장 버튼 -->
            <form action="Acheckcommentwrite.do" method="post">
                 <input type="hidden" name="fidList" value="${fidList}">
        		 <input type="hidden" name="pidList" value="${pidList}">
                <textarea name="content" rows="4" cols="50"></textarea>
                <input type="submit" value="저장">
            </form>
        </div>
    </div>
</div>

		<h3>리뷰 목록</h3>
		 <input type="checkbox" id="selectAllCheckbox" onchange="selectAll()">
			<input type="button" value="삭제" onclick="deleteSelectedItems()">
			<input type="button" value="댓글" onclick="openCommentModal()">
			<table border="1">
    <tr>
        <th></th>
        <th>ID</th>
        <th>작성일</th>
        <th>고객정보</th>
        <th>상품</th>
        <th>리뷰 보기</th>
        <th>리뷰 상세하게 보기</th>
    </tr>
  <c:forEach items="${reviewlist}" var="dto">
    <tr>
        <td><input type="checkbox" name="selectedItems" value="${dto.fid}" data-pid="${dto.f_pid}"></td>
        <td>${dto.fid}</td>
        <td>${dto.finsertdate}</td>
        <td>${dto.cname}</td>
        <td><a href="#" onclick="openModal('${dto.fid}')"> <img src="${dto.pfilename}" alt="Product Image" width="100" /></a></td>
        <td><a href="#" onclick="openModal2('${dto.fid}')">${dto.ftitle}</a></td>
        <td>
            <form action="AForumView.do?fid=${dto.fid}&ftype=1" method="post">
                <input type="hidden" name="fid" value="${dto.fid}">
                <input type="submit" name="action" value="페이지로 이동">
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="7">
            <div id="productInfo-${dto.fid}" style="display: none;">
                <h3>제품 정보</h3>
                <p>주요정보</p>
                <p>상품사진</p>
                <a href="productInformation.do?query=${dto.pname}">
                    <img src="${dto.pfilename}" alt="Product Image" width="100" />
                </a>
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
            </div>
            <div id="reviewInfo-${dto.fid}" style="display: none;">
                <h3>리뷰</h3>
                <table border="1">
                    <tr>
                        <td>작성자</td>
                        <td>${dto.cname}</td>
                    </tr>
                    <tr>
                        <td>제목</td>
                        <td>${dto.ftitle}</td>
                    </tr>
                    <tr>
                        <td>작성일</td>
                        <td>${dto.finsertdate}</td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td>${dto.fcontent}</td>
                    </tr>
                </table>
            </div>
        </td>
    </tr>
</c:forEach>
</table>
	</div>

	<div id="myModal" class="modal">
	  <span class="close" onclick="closeModal()">&times;</span>
	  <div id="modalContent"></div>
	   <button onclick="closeModal()">확인</button>
	</div>
	</main>
	</div>
	</div>
</body>
</html>