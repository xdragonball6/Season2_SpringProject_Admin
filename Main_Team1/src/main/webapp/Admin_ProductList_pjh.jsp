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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="css/admin_kkg.css" rel="stylesheet">
    <link href="css/modifymodal.css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
    <title>상품 리스트</title>
    
    <script>
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

            // 선택된 상품들의 값을 'pid'라는 이름으로 서버로 전송
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'deleteProduct.do';

            for (var i = 0; i < selectedItems.length; i++) {
                var input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'pid';
                input.value = selectedItems[i];
                form.appendChild(input);
            }

            document.body.appendChild(form);
            form.submit();

            return false; // 폼 제출 중지
        }

        var product = {}; // 전역 범위에 product 객체 정의

        function openEditModal(pid, pprice, pstock, cname, pname, pcontent, pfilename) {
            product = {
                pid: pid,
                pprice: pprice,
                pstock: pstock,
                cname: cname,
                pname: pname,
                pcontent: pcontent,
                pfilename: pfilename
            };

            populateEditModal(product);

            var modal = document.getElementById('myModal');
            modal.style.display = 'block';
        }

        function populateEditModal(product) {
            document.getElementById('editPid').value = product.pid;
            document.getElementById('editPidDisplay').innerText = product.pid;
            document.getElementById('editPprice').value = product.pprice;
            document.getElementById('editPstock').value = product.pstock;
            document.getElementById('editCname').innerText = product.cname;
            document.getElementById('editPname').value = product.pname;
            document.getElementById('editPcontent').value = product.pcontent;

            var imageContainer = document.getElementById('editImageContainer');
            imageContainer.innerHTML = ""; // 이미지 컨테이너 초기화

            if (product.pfilename) {
                var image = document.createElement('img');
                image.src = product.pfilename;
                image.alt = "Product Image";
                image.width = 100;
                imageContainer.appendChild(image);
            }
        }

        function saveChanges() {
            var form = document.getElementById('editForm');
            var fileInput = document.getElementById('pfilename');
            var fileInput1 = document.getElementById('pcontentfilename1');
            var fileInput2 = document.getElementById('pcontentfilename2');

            // 파일이 선택되었는지 확인
            if (fileInput.files.length > 0 || fileInput1.files.length > 0 || fileInput2.files.length > 0) {
                // FormData 객체 생성
                var formData = new FormData(form);

                // 선택된 파일들을 FormData에 추가
                if (fileInput.files.length > 0) {
                    var file = fileInput.files[0];
                    var newFileName = generateNewFileName(product.pid, file.name);
                    formData.append('pfilename', file, newFileName);
                }
                if (fileInput1.files.length > 0) {
                    var file1 = fileInput1.files[0];
                    var newFileName1 = generateNewFileName(product.pid, file1.name);
                    formData.append('pcontentfilename1', file1, newFileName1);
                }
                if (fileInput2.files.length > 0) {
                    var file2 = fileInput2.files[0];
                    var newFileName2 = generateNewFileName(product.pid, file2.name);
                    formData.append('pcontentfilename2', file2, newFileName2);
                }

                // AJAX를 사용하여 파일 업로드 및 폼 데이터 전송
                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'editProduct.do', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        // 업로드 및 저장 완료 후 처리할 로직 작성
                        closeModal(); // 업로드 및 저장 완료 후 모달 닫기
                    }
                };
                xhr.send(formData);
            } else {
                // 파일이 선택되지 않은 경우 기존 폼 데이터만 전송
                closeModal(); // 모달 닫기
            }
        }

        function generateNewFileName(pid, originalFileName) {
            var timestamp = new Date().getTime();
            var extension = originalFileName.split('.').pop();
            var newFileName = pid + '_' + timestamp + '.' + extension;
            return newFileName;
        }

        function closeModal() {
            var modal = document.getElementById('myModal');
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

        <h3>상품 리스트</h3>
        <form action="productQuery.do" method="post">
            <select name="list" class="form-select">
                <option value="pname" selected="selected">상품명</option>
                <option value="pcategory">카테고리</option>
            </select>
            <input type="text" name="query">
            <input type="submit" name="action" value="검색">
        </form>
        <form action="APinsert.do" method="post" enctype="multipart/form-data">
            <input type="submit" name="action" value="상품 추가하기">
        </form>
        <form>
		    <input type="checkbox" id="selectAllCheckbox" onchange="selectAll()"> 전체 선택
		    <input type="button" value="삭제" onclick="deleteSelectedItems()">
		</form>
            <table border=1>
                <tr>
                    <th>상품선택</th>
                    <th>사진</th>
                    <th>카테고리</th>
                    <th>제품명</th>
                    <th>가격</th>
                </tr>
                <c:forEach items="${list}" var="dto">
                    <tr>
                        <td><input type="checkbox" name="selectedItems" value="${dto.pid}"></td>
                        <td><img src="${dto.pfilename}" alt="Product Image" width="100" /></td>
                        <td>${dto.c_name }</td>
                        <td>${dto.pname }</td>
                        <td>${dto.pprice }</td>
                        <td>
                            <button onclick="openEditModal('${dto.pid}', '${dto.pprice}', '${dto.pstock}','${dto.c_name}', '${dto.pname}', '${dto.pcontent}')">편집하기</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        <br />
        <hr>
        <br />
    </div>
    <div id="myModal" class="modal">
    <div id="editImageContainer"></div>
        <div class="modal-content">
            <form id="editForm" enctype="multipart/form-data"	>
                <table border="1">
                    <tr>
                        <td>상품번호</td>
                        <td>
                            <input type="hidden" id="editPid" name="pid">
                            <span id="editPidDisplay"></span>
                        </td>
                        <td>상품가격</td>
                        <td><input type="text" id="editPprice" name="pprice"></td>
                    </tr>
                    <tr>
                        <td>종류</td>
                        <td>
                            <span id="editCname"></span>
                        </td>
                        <td>모델명</td>
                        <td><input type="text" id="editPname" name="pname"></td>
                    </tr>
                    <tr>
                        <td>재고</td>
                        <td><input type="text" id="editPstock" name="pstock"></td>
                        <td>이미지 변경</td>
                        <td><input type="file" id="pfilename" name="pfilename"></td>
                    </tr>
                </table>
                <p>상세 설명</p>
                <p><textarea id="editPcontent" id="editPcontent" name="pcontent"></textarea></p>
                <p><input type="file" id="pcontentfilename1" name="pcontentfilename1"></p>
                <p><input type="file" id="pcontentfilename2" name="pcontentfilename2"></p>
                <input type="button" name="action" value="저장" onclick="saveChanges()">
            </form>
            <span class="close" onclick="closeModal()">&times;</span>
        </div>
    </div>
    </main>
    </div>
    </div>
</body>
</html>