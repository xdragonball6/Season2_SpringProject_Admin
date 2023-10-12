<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항</title>
    <link href="css/admin_kkg.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="aQnA_style.css">
    <script>
        // 전체 데이터 개수
        var totalData = ${listSize};

        // 데이터를 테이블에 렌더링하는 함수
        function renderData(data) {
            var tableBody = $("#tableBody");
            tableBody.empty();
            tableBody.append(data);
        }

        // 특정 페이지 번호에 해당하는 데이터를 가져오는 함수
        function GetTarget(pageNumber) {
            var dataPerPage = 10;
            var startIndex = (pageNumber - 1) * dataPerPage;
            var endIndex = startIndex + dataPerPage;

            var rows = $("#tableBody tr.data-row");
            rows.addClass("hidden-row"); // 모든 행 숨기기
            rows.slice(startIndex, endIndex).removeClass("hidden-row"); // 현재 페이지에 해당하는 행 보이기

            paging(totalData, pageNumber);
        }

        // 페이지네이션 링크 생성 함수
        function paging(totalData, currentPage) {
            var dataPerPage = 10;
            var pageCount = 10;

            var totalPage = Math.ceil(totalData / dataPerPage);
            var pageGroup = Math.ceil(currentPage / pageCount);

            var last = pageGroup * pageCount;
            var first = last - (pageCount - 1);
            var next = last + 1;
            var prev = first - 1;

            var pages = $("#pages");
            pages.empty();

            // 이전 링크 추가 (이전 페이지가 있는 경우)
            if (first > 10) {
                pages.append("<li class=\"pagination-item\">" +
                    "<a onclick=\"GetTarget(" + prev + ");\" style='margin-left: 2px'>이전</a></li>");
            }

            // 페이지 번호 링크 생성
            for (var i = first; i <= last; i++) {
                if (i > totalPage) {
                    break;
                }
                if (i == currentPage) {
                    pages.append("<li class=\"pagination-item\">" +
                        "<a class=\"active\">" + i + "</a></li>"); // 현재 페이지를 강조 표시
                } else {
                    pages.append("<li class=\"pagination-item\">" +
                        "<a onclick=\"GetTarget(" + i + ");\">" + i + "</a></li>"); // 다른 페이지에 대한 링크 추가
                }
            }

            // 다음 링크 추가 (더 많은 페이지가 있는 경우)
            if (last < totalPage) {
                pages.append("<li class=\"pagination-item\">" +
                    "<a onclick=\"GetTarget(" + next + ");\" style='margin-left: 2px'>다음</a></li>");
            }
        }

        $(document).ready(function() {
            GetTarget(1); // 초기 페이지를 1로 설정
        });
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

    <table>
        <thead>
            <tr class="data-row">
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>등록일</th>
            </tr>
        </thead>
        <tbody id="tableBody">
        	<c:forEach items="${noticelist}" var="noticedto" varStatus="status">
        	<tr>
        			<td>${noticedto.nid}</td>       
                    <td><a href="">${noticedto.ntitle}</a></td>     
                    <td>관리자</td>     
                    <td>${noticedto.ninsertdate}</td>
        	</tr>
        	</c:forEach>
        	
            <c:forEach items="${list}" var="dto" varStatus="status">
                <tr class="data-row hidden-row" id="dataRow${status.index}">
                    <td>${dto.fid}</td>        <!-- 현재 아이템의 'fid' 속성을 출력 -->
                    <td>${dto.ftitle}</td>     <!-- 현재 아이템의 'ftitle' 속성을 출력 -->
                    <td>${dto.f_cid}</td>     <!-- 현재 아이템의 'f_cid' 속성을 출력 -->
                    <td>${dto.finsertdate}</td>   <!-- 현재 아이템의 'finsertdate' 속성을 출력 -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="pagination-wrapper clearfix">
        <ul class="pagination float--right" id="pages">
        </ul>
    </div>
	<form action="noticewrite.do" method="post">
	 <input type="submit" name="action" value="게시물 작성">
	</form>
    </div>
    </main>
    </div>
    </div>
</body>
</html>