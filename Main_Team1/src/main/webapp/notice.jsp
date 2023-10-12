<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<style>
* {
    margin: 0;
    padding: 0;
}

html {
    font-size: 10px;
}

ul, li {
    list-style: none;
}

a {
    text-decoration: none;
    color: inherit;
}

.board_wrap {
    width: 1000px;
    margin: 100px auto;
}

.board_title {
    margin-bottom: 30px;
}

.board_title strong {
    font-size: 3rem;
}

.board_title p {
    margin-top: 5px;
    font-size: 1.4rem;
}

.bt_wrap {
    margin-top: 30px;
    text-align: center;
    font-size: 0;
}

.bt_wrap a {
    display: inline-block;
    min-width: 80px;
    margin-left: 10px;
    padding: 10px;
    border: 1px solid #000;
    border-radius: 2px;
    font-size: 1.4rem;
}

.bt_wrap a:first-child {
    margin-left: 0;
}

.bt_wrap a.on {
    background: #000;
    color: #fff;
}

.board_list {
    width: 100%;
    border-top: 2px solid #000;
}

.board_list > div {
    border-bottom: 1px solid #ddd;
    font-size: 0;
}

.board_list > div.top {
    border-bottom: 1px solid #000;
}

.board_list > div:last-child {
    border-bottom: 1px solid #000;
}

.board_list > div > div {
    display: inline-block;
    padding: 15px 0;
    text-align: center;
    font-size: 1.4rem;
}

.board_list > div.top > div {
    font-weight: 600;
}

.board_list .num {
    width: 10%;
}

.board_list .title {
    width: 70%;
    text-align: left;
}

.board_list .top .title {
    text-align: center;
}

.board_list .writer {
    width: 10%;
}

.board_list .date {
    width: 10%;
}

.board_list .count {
    width: 10%;
}

.board_page {
    margin-top: 30px;
    text-align: center;
    font-size: 0;
}

.board_page a {
    display: inline-block;
    width: 32px;
    height: 32px;
    box-sizing: border-box;
    vertical-align: middle;
    border: 1px solid #ddd;
    border-left: 0;
    line-height: 100%;
}

.board_page a.bt {
    padding-top: 10px;
    font-size: 1.2rem;
    letter-spacing: -1px;
}

.board_page a.num {
    padding-top: 9px;
    font-size: 1.4rem;
}

.board_page a.num.on {
    border-color: #000;
    background: #000;
    color: #fff;
}

.board_page a:first-child {
    border-left: 1px solid #ddd;
}

.board_view {
    width: 100%;
    border-top: 2px solid #000;
}

.board_view .title {
    padding: 20px 15px;
    border-bottom: 1px dashed #ddd;
    font-size: 2rem;
}

.board_view .info {
 min-height: 20px;
    padding: 15px;
    border-bottom: 1px solid #999;
    border-bottom: 1px solid #ddd;
    font-size: 0;
}

.board_view .info dl {
    position: relative;
    display: inline-block;
    padding: 0 20px;
}

.board_view .info dl:first-child {
    padding-left: 0;
}

.board_view .info dl::before {
    content: "";
    position: absolute;
    top: 1px;
    left: 0;
    display: block;
    width: 1px;
    height: 13px;
    background: #ddd;
}

.board_view .info dl:first-child::before {
    display: none;
}

.board_view .info dl dt,
.board_view .info dl dd {
    display: inline-block;
    font-size: 1.4rem;
}

.board_view .info dl dt {

}

.board_view .info dl dd {
    margin-left: 10px;
    color: #777;
}

.board_view .cont {
    padding: 15px;
    border-bottom: 1px solid #000;
    line-height: 160%;
    font-size: 1.4rem;
}

.board_write {
    border-top: 2px solid #000;
}

.board_write .title,
.board_write .info {
    padding: 15px;
}

.board_write .info {
    border-top: 1px dashed #ddd;
    border-bottom: 1px solid #000;
    font-size: 0;
}

.board_write .title dl {
    font-size: 0;
}

.board_write .info dl {
    display: inline-block;
    width: 50%;
    vertical-align: middle;
}

.board_write .title dt,
.board_write .title dd,
.board_write .info dt,
.board_write .info dd {
    display: inline-block;
    vertical-align: middle;
    font-size: 1.4rem;
}

.board_write .title dt,
.board_write .info dt {
    width: 100px;
}

.board_write .title dd {
    width: calc(100% - 100px);
}

.board_write .title input[type="text"],
.board_write .info input[type="text"],
.board_write .info input[type="password"] {
    padding: 10px;
    box-sizing: border-box;
}

.board_write .title input[type="text"] {
    width: 80%;
}

.board_write .cont {
    border-bottom: 1px solid #000;
}

.board_write .cont textarea {
    display: block;
    width: 100%;
    height: 300px;
    padding: 15px;
    box-sizing: border-box;
    border: 0;
    resize: vertical;
}
</style>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>
</head>
<body>
    <div class="board_wrap">
        <div class="board_title">
            <strong>공지사항</strong>
            <p>공지사항을 빠르고 정확하게 안내해드립니다.</p>
        </div>
        <div class="board_list_wrap">
            <div class="board_list">
                <div class="top">
                    <div class="num">번호</div>
                    <div class="title">제목</div>
                    <div class="writer">글쓴이</div>
                    <div class="date">작성일</div>
                </div>
                <div>
                    <c:forEach items="${Notice }" var="dto">
                    <div class="num">${dto.nid}</div>
                    <div class="title"><a href="view.html">${dto.ntitle}</a></div>
                    <div class="writer">${dto.n_aid}</div>
                    <div class="date">${dto.ninsertdate}</div>
                         <td>${dto.ncontent}</td>
            </c:forEach>
                </div>
                
                
                
            </div>
            <div class="board_page">
                <a href="#" class="bt first"><<</a>
                <a href="#" class="bt prev"><</a>
                <a href="#" class="num on">1</a>
                <a href="#" class="num">2</a>
                <a href="#" class="num">3</a>
                <a href="#" class="num">4</a>
                <a href="#" class="num">5</a>
                <a href="#" class="bt next">></a>
                <a href="#" class="bt last">>></a>
            </div>
            <div class="bt_wrap">
                <a href="write.html" class="on">등록</a>
                <!--<a href="#">수정</a>-->
            </div>
        </div>
    </div>
    <div id="noticeList"></div>
<div id="pagination"></div>
    
   <script>
    // 페이지 네비게이션 링크를 클릭했을 때 처리하는 함수
    function navigatePage(event) {
        event.preventDefault();

        // 현재 클릭된 링크 요소와 그 클래스 값을 가져옴
        var clickedLink = event.target;
        var clickedClass = clickedLink.getAttribute("class");

        // 클릭된 링크에 따라 동작 수행
        if (clickedClass.includes("num")) {
            // 선택된 페이지로 이동
            var selectedPage = clickedLink.textContent;
            console.log("Go to page", selectedPage);
            // 여기에서 페이지 이동에 필요한 로직을 추가하세요.

        } else if (clickedClass.includes("first")) {
            // 첫 페이지로 이동
            console.log("Go to first page");
            // 여기에서 첫 페이지로 이동하는 로직을 추가하세요.

        } else if (clickedClass.includes("prev")) {
            // 이전 페이지로 이동
            console.log("Go to previous page");
            // 여기에서 이전 페이지로 이동하는 로직을 추가하세요.

        } else if (clickedClass.includes("next")) {
            // 다음 페이지로 이동
            console.log("Go to next page");
            // 여기에서 다음 페이지로 이동하는 로직을 추가하세요.

        } else if (clickedClass.includes("last")) {
            // 마지막 페이지로 이동
            console.log("Go to last page");
            // 여기에서 마지막 페이지로 이동하는 로직을 추가하세요.
        }
    }

    // 페이지 네비게이션 링크에 클릭 이벤트 리스너 등록
    var pageLinks = document.querySelectorAll(".board_page a");
    pageLinks.forEach(function(link) {
        link.addEventListener("click", navigatePage);
    });
    function fetchNoticeData(page, pageSize) {
        // 서버에서 페이지에 해당하는 공지사항 데이터를 가져오는 AJAX 요청
        // 필요한 경우 페이지 번호와 페이지 크기를 서버에 전달하여 해당 페이지 데이터를 가져올 수 있습니다.
        // 예를 들어, URL에 파라미터로 page와 pageSize를 전달하여 서버에서 필요한 데이터를 가져올 수 있습니다.

        // AJAX 요청 후, 받아온 데이터를 기반으로 아래의 renderNoticeList 함수를 호출하여 공지사항 목록을 화면에 렌더링합니다.
      }

      function renderNoticeList(noticeList) {
        // 공지사항 목록을 받아서 화면에 렌더링하는 로직을 작성합니다.
        // 예를 들어, 받아온 데이터를 반복문을 통해 순회하며 HTML 형식에 맞게 구조화하여 #noticeList 요소에 추가합니다.
      }

      function renderPagination(currentPage, totalPages) {
</script>
</body>
</html> 