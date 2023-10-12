<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 리뷰 목록</title>
<link href="css/admin_kkg.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="aQnA_style.css">
  <script>
        // 전체 데이터 개수
        var totalData = ${ListSize};

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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<style>
    body {
        background-color: white;
        padding: 20px;
    }

    h1 {
        color: indigo;
        margin-bottom: 30px;
    }

    th {
        background-color: indigo;
        color: white;
        padding: 10px;
        text-align: center;
    }

    td {
        padding: 10px;
        text-align: center;
    }

    a {
        color: indigo;
        text-decoration: none;
    }

    .table-striped tbody tr:nth-of-type(odd) {
        background-color: #f8f8ff; /* 연보라색과 어울리는 밝은 연보라색 */
    }

    .table-striped tbody tr:hover {
        background-color: #f1f1f1; /* 연보라색과 어울리는 더 밝은 연보라색 */
    }
</style>

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

    <c:if test="${ftype eq 1}">
        <h1>리뷰 게시판</h1>
    </c:if>

    <c:if test="${ftype eq 2}">
        <h1>QnA 게시판</h1>
    </c:if>

    <c:if test="${ftype eq 1}">
    	<form action="AdminNoticeWrite.do">
    		<input type="hidden" name="ftype" value="${ftype}">
        	<input type="submit" value="공지사항 적기">
        </form>
        <form action="pjh_WriteForum.jsp">
            <input type="submit" value="리뷰쓰기">
        </form>
    </c:if>
    <c:if test="${ftype eq 2}">
        <form action="AdminNoticeWrite.do">
        	<input type="hidden" name="ftype" value="${ftype}">
        	<input type="submit" value="공지사항 적기">
        </form>
    </c:if>

    <div style="text-align: right;">
        <form action="Aforumsearch.do" method="post">
            <input type="hidden" name="ftype" value="${ftype}">
            <input type="text" name="content" placeholder="원하시는 상품을 검색하세요!">
            <input type="submit" value="검색">
        </form>
    </div>

    <table class="table table-striped table-bordered">
        <thead class="thead-light">
            <tr>
                <th style="width: 100px; background-color: lavender; color: purple;">
                    <c:if test="${ftype eq 1}">Review No.</c:if>
                    <c:if test="${ftype eq 2}">QnA No.</c:if>
                </th>
                <th style="width: 200px;background-color: lavender;color: purple;">작성자</th>
                <th style="width: 200px;background-color: lavender;color: purple;">제품</th>
                <th style="background-color: lavender;color: purple;">제목</th>
                <th style="width: 300px; background-color: lavender;color: purple;">작성일</th>
            </tr>
        </thead>
        <tbody id="tableBody">
        	<c:forEach items="${noticelist}" var="noticedto" varStatus="status">
        	<tr>
        			<td>${noticedto.nid}</td>       
                    <td>관리자</td>
                    <td></td>
                    <td><a href="ANoticeView.do?nid=${noticedto.nid}&ftype=${ftype}">${noticedto.ntitle}</a></td>     
                    <td>${noticedto.ninsertdate}
                    <form action ="AdeleteNotice.do?nid=${noticedto.nid}&ftype=${ftype}" method="post">
                    <input type="submit" value="삭제">
                    </form></td>
        	</tr>
        	</c:forEach>
            <c:forEach items="${RList}" var="dto" varStatus="status">
            <tr class="data-row hidden-row" id="dataRow${status.index}">
                <td>${dto.fid}</td>
                <td>${dto.cname}</td>
                <td>${dto.pname}</td>
                <td><a href="AForumView.do?fid=${dto.fid}&ftype=${ftype}">${dto.ftitle}</a></td>
                <td>${dto.finsertdate}
                 <form action ="deleteRevieworQnA.do?fid=${dto.fid}&ftype=${ftype}" method="post">
                 <input type="submit" value="삭제">
                 </form></td>
            	</tr>
        </c:forEach>
        </tbody>
    </table>
	<div class="pagination-wrapper clearfix">
        <ul class="pagination float--right" id="pages">
        </ul>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </div>
    </main>
    </div>
    </div>
</body>
</html>