
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }

    th {
        text-align: center;
        padding: 10px;
    }

    th:first-child {
        text-align: left;
        width: 50%;
    }

    td {
        text-align: center;
        padding: 10px;
    }

    td:first-child {
        text-align: left;
    }
	.subscript {
    	vertical-align: super;
    	font-size: smaller;
	}
  .center-align {
    display: flex;
    justify-content: center;
    align-items: center;
  }
</style>
    <title>리뷰 상세 보기</title>
    <script type="text/javascript">
        function confirmDelete(cid, form) {
            var userCid = "${cid}"; // 로그인 사용자의 f_cid 값
            var commentCid = form.elements["f_cid"].value;

            if (userCid !== commentCid) {
                // 삭제 권한이 없는 경우 경고 메시지 출력
                alert("댓글 삭제 권한이 없습니다.");
                return false;
            }

            // 댓글 삭제 확인 메시지 표시
            var confirmed = confirm("정말로 댓글을 삭제하시겠습니까?");
            if (confirmed) {
                // 사용자가 확인한 경우에만 댓글 삭제 로직 수행
                // ...

                // 삭제 성공 메시지 출력
                alert("댓글이 성공적으로 삭제되었습니다.");

                // 삭제된 댓글의 삭제와 댓글 버튼을 숨김

                return true;
            } else {
                return false;
            }
        }
    </script>
</head>
<body>
    <table>
        <tr>
            <td style="width: 100px;"><strong>제목:</strong></td>
            <td>${forumView.ftitle}</td>
        </tr>
        <tr>
            <td><strong>작성자:</strong></td>
            <td>${forumView.f_cid}</td>
        </tr>
        <tr>
            <td><strong>상품:</strong></td>
            <td>${forumView.cname}</td>
        </tr>
        <tr>
            <td colspan="2"><strong>내용:</strong></td>
        </tr>
        <tr>
            <td colspan="2">${forumView.fcontent}</td>
        </tr>
    </table>
    <br/>
    <h3>댓글 목록</h3>
    <table class="table table-striped table-bordered">
        <thead class="thead-light">
        <tr>
            <th style="width: 100px;">작성자</th>
            <th>제목</th>
            <th>작성일</th>
            <th>댓글</th>
            <th>삭제</th>
        </tr>
        </thead>
        <tbody id="tableBody">
        <c:if test="${empty Clist}">
    		<tr>
        		<td colspan="5">아직 등록된 댓글이 없습니다.</td>
   	 		</tr>
		</c:if>
            <c:forEach items="${Clist}" var="cdto" varStatus="status">
                <tr class="data-row hidden-row" id="dataRow${status.index}">
                    <td>${cdto.cname}</td>
                    <td class="text-left">
				    <c:choose>
				        <c:when test="${cdto.fstep eq 0}">
				            ${cdto.ftitle}
				        </c:when>
				        <c:otherwise>
				            <span style="font-weight: bold; margin-left: ${cdto.fstep * 30}px">ㄴ${cdto.ftitle}</span>
				        </c:otherwise>
				    </c:choose>
					</td>

                    <td>${cdto.finsertdate}</td>
                    <td>
                        <c:if test="${cdto.fdeletedate eq null}">
                            <form action="BigCommentWrite.do" method="post">
                                <input type="text" name="ftitle" placeholder="댓글을 입력하세요.">
                                <input type="hidden" name="page" value="${forumView.fid}">
                                <input type="hidden" name="f_cid" value="${cid}">
                                <input type="hidden" name="fid" value="${cdto.fid}">
                                <input type="hidden" name="f_pid" value="${cdto.f_pid}">
                                <input type="hidden" name="fref" value="${cdto.fref}">
                                <input type="hidden" name="fstep" value="${cdto.fstep}">
                                <input type="hidden" name="fsteporder" value="${cdto.fsteporder}">
                                <input type="hidden" name="freforder" value="${cdto.freforder}">
                                <input type="hidden" name="fmotherid" value="${cdto.fmotherid}">
                                <input type="hidden" name="fanswernum" value="${cdto.fanswernum}">
                                <input type="submit" id="replyButton_${cdto.f_cid}" value="입력">
                            </form>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${cdto.fdeletedate eq null}">
                            <form action="commentdelete.do" method="post" onsubmit="return confirmDelete('${cdto.f_cid}', this)">
                                <input type="hidden" name="page" value="${forumView.fid}">
                                <input type="hidden" name="f_cid" value="${cdto.f_cid}">
                                <input type="hidden" name="fid" value="${cdto.fid}">
                                <input type="submit" id="deleteButton_${cdto.f_cid}" value="삭제">
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	<br/><br/>
    <div class="center-align">
    <form action="commentwrite.do" method="post">
        <input type="text" name="ftitle" style="width: 300px;" placeholder="댓글을 입력하세요.">
        <input type="hidden" name="fsteporder" value="${forumView.fsteporder}">
        <input type="hidden" name="fid" value="${forumView.fid}">
        <input type="hidden" name="f_cid" value="${cid}">
        <input type="hidden" name="f_pid" value="${forumView.f_pid}">
        <input type="submit" value="입력">
    </form>
</div>
<div class="pagination-wrapper clearfix" style="text-align: center;">
        <ul class="pagination float--right" id="pages">
        </ul>
    </div>
    <%-- <form action="Kms_WriteReply.jsp" method="post">
        <input type="hidden" name="fid" value="${forumView.fid}">
        <input type="hidden" name="fref" value="${forumView.fref}">
        <input type="hidden" name="freforder" value="${forumView.freforder}">
        <input type="hidden" name="fstep" value="${forumView.fstep}">
        <input type="hidden" name="fmotherid" value="${forumView.fmotherid}">
        <input type="hidden" name="fanswernum" value="${forumView.fanswernum}">
        <input type="submit" value="답글 달기">
    </form> --%>
    <%@ include file="bottom.jsp" %>
</body>
</html>
