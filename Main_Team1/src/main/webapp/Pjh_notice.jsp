<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/admin_kkg.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="aQnA_style.css">
<title>공지사항 내용</title>
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
  
  .notice-info {
    margin-bottom: 20px;
    padding: 10px;
    background-color: #fff;
    border-radius: 4px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  
  .notice-info span {
    font-weight: bold;
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
          <h3>${noticeview.ntitle}</h3>
          <div class="notice-info">
            <span>${noticeview.n_aid}</span><br/>
            <span>${noticeview.ninsertdate}</span><br/>
            <span>${noticeview.ncontent}</span><br/>
          </div>
          <div class="btn-group">
            <form action="AnoticeModifyPage.do" method="post">
              <input type="hidden" name="ftype" value="${ftype}">
              <input type="hidden" name="nid" value="${nid}">
              <input type="hidden" name="n_aid" value="${noticeview.n_aid}">
              <input type="hidden" name="ntitle" value="${noticeview.ntitle}">
              <input type="hidden" name="ninsertdate" value="${noticeview.ninsertdate}">
              <input type="hidden" name="ncontent" value="${noticeview.ncontent}">
              <input type="submit" value="수정">
            </form>
            <form action="AdeleteNotice.do?nid=${nid}&ftype=${ftype}" method="post">
              <input type="submit" value="삭제">
            </form>
            <form action="Awritelist.do?ftype=${ftype}" method="post">
              <input type="submit" value="목록">
            </form>
          </div>
        </main>
      </div>
    </div>
  </div>
</body>
</html>