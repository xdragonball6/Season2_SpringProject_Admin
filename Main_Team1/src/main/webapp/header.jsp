<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Little</title>
	<style>

	  	html,
		body {
		  height: 100%;
		}
		
		body {
		  display: flex;
		  padding-top: 40px;
		  padding-bottom: 40px;
		}
		.header {
		  position: fixed;
		  top: 0;
		  left: 0;
		  width: 100%;
		}


	</style>



</head>
<body>
<div class="container">

 <div class="row">
 	<div class="col-3">
		<ul class="nav">
		  <li class="nav-item">
		    <a class="nav-link active" href="home.do">
		      <!-- <img src="image/so7.png" alt="" width="80" height="40"> -->
		      <h3 class="text-dark">Little and Precious</h3>
		    </a>
		  </li>
		 </ul>
	 </div>
	 <div class="col-6">
		<ul class="nav justify-content-center">
			  <li class="nav-item">
			    <a class="nav-link active" aria-current="page" href="home.do"><p class="text-dark">HOME</p></a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" href="CategoryView.do?num=0"><p class="text-dark">LAMP</p></a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" href="CategoryView.do?num=1"><p class="text-dark">MINIATURE</p></a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" href="CategoryView.do?num=2"><p class="text-dark">CHAIR</p></a>
			  </li>
			  <li class="nav-item">
			 	 <c:set var="ftype" value="${param.ftype}" />
			    <a class="nav-link" href="writelist.do?ftype=2"><p class="text-dark">Q&A</p></a>
			 <!-- <li class="nav-item">
			  <a class="nav-link" href="notice.do?"><p class="text-dark">공지사항</p></a>
			  </li> -->
		
		</ul>
		
	</div>
	<div class="col-3">
		<ul class="nav justify-content-end">
			<c:if test="${cid != null }">
				<li class="nav-item">
				   	<a class="nav-link" href="mypageview.do"><p class="text-dark" >${name }님</p></a>
				<li class="nav-item">
				   	 <a class="nav-link" href="#" onclick="logoutAndKakaoLogout();"><p class="text-dark">로그아웃</p></a>
			    </li>
			</c:if>
			<c:if test="${cid == null }">
				<li class="nav-item">
				   	 <a class="nav-link" href="login.jsp"><p class="text-dark">로그인</p></a>
			    </li>
			</c:if>
			<li class="nav-item">	<!-- 마이 페이지 이동 -->
			    <a class="nav-link" href="mypageview.do" onclick="return userCheck()">
			   	 <img src="image/so04.png" alt="" width="30" height="24">
			    </a>
		    </li>
			<li class="nav-item">	<!-- 장바구니 이동 -->
			    <a class="nav-link" href="cart.do" onclick="return userCheck()">
			   	 <img src="image/so9.png" alt="" width="30" height="24">
			    </a>
		    </li>
		</ul>
	</div>
</div>

<hr/>

<script type="text/javascript">

function userCheck() {
	if(${cid == null }){
	    alert("로그인을 해주세요.")
	    return false
	}else{
		return true
	}
	
}



</script>


<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
  Kakao.init('e94ea7cf7a4161d305da7590513621dc'); // 발급받은 키 중 javascript키를 사용해준다.
  console.log(Kakao.isInitialized()); // sdk초기화여부판단
  
  function logoutAndKakaoLogout() {
    // 카카오 로그아웃 처리
    Kakao.Auth.logout(function() {
      // 로그아웃이 성공한 후에 서버의 로그아웃 처리를 진행
      window.location.href = 'logout.do';
      alert("로그아웃되었습니다. 편안한 하루 되세요!")
    });
  }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>