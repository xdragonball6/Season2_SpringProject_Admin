<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }


      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
    </style>

<!--e94ea7cf7a4161d305da7590513621dc  -->

<link href="login.css" rel="stylesheet">
</head>





<body>
<!-- 카카오 스크립트 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
	Kakao.init('e94ea7cf7a4161d305da7590513621dc'); // 발급받은 키 중 javascript키를 사용해준다.
	console.log(Kakao.isInitialized()); // sdk초기화여부판단

	// 카카오로그인
	function kakaoLogin() {
		Kakao.Auth.login({
			success: function(response) {
				Kakao.API.request({
					url: '/v2/user/me',
					success: function(response) {
						var nickname = response.properties.nickname; // 닉네임 가져오기
						var kakaoAccount = response.kakao_account;
						var email = kakaoAccount.email; // 카카오 계정 이메일 가져오기
						var gender = kakaoAccount.gender; // 성별 가져오기
						var birthday = kakaoAccount.birthday; // 생일 가져오기

						// 가져온 정보 활용
						console.log("닉네임: " + nickname);
						console.log("카카오 계정 이메일: " + email);
						console.log("성별: " + gender);
						console.log("생일: " + birthday);

						// 이메일을 서버로 전송하여 데이터베이스에 있는지 확인
						
						axios.post('kalogin.kao', null, {
								params: {
									cid: email,
									cname: nickname,
									cgender: gender,
									cbirthday: birthday
								}
							})
							.then(response => {
								// 로그인 성공 시 처리
								console.log(response.data);
								if(response.data == "join"){
									alert("등록되어 있는 회원정보가 없습니다. 회원가입을 해주세요");
									window.location.href = 'join.jsp?kakao=1'; 
								}else if(response.data == "mdraw"){
									alert("탈퇴한 회원입니다.");
								}else{
									alert("로그인 성공");
									window.location.href = 'home.do'; 
								}
								
							})
							.catch(error => {
								// 로그인 실패 시 처리
								this.errorMessage = error.response.data.message;
								alert("아이디와 비밀번호를 확인해주세요!");
							});
					},
					fail: function(error) {
						console.log(error);
					}
				});
			},
			fail: function(error) {
				console.log(error);
			}
		});
	}
</script>


<div class="container">
	<main class="form-signin w-100 m-auto">
	<div class="container">
		<figure class="text-center">
			<h3 class="text-dark">Little and Precious</h3>
		</figure>
		<hr/>
		</div>
	<div id="app">	
	  <form id="login-form" @submit.prevent="login" method="post">
	    <h6 class="h6 mb-3 fw-normal">*가입하신 이메일 주소로 로그인 해주세요</h6>
	
	    <div class="form-floating">
	      <input type="text" class="form-control" id="floatingInput" placeholder="name@example.com" v-model="username" required>
	      <label for="floatingInput">이메일 (아이디)</label>
	    </div>
	    
	    <div class="form-floating">
	      <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="password" required>
	      <label for="floatingPassword">비밀번호</label>
	    </div>
	
	    <div class="checkbox mb-3">
	      <label>
	        <input type="checkbox" value="remember-me"> Remember me
	      </label>
	    </div>
	    <button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>
	  </form>
<hr/>
	<div class="d-flex justify-content-between">
	  <div>
	 	 <a href="join.do" class="text-dark">회원가입</a>
	  </div>
	  <div>
	  	<a href="findId.do" class="text-dark">아이디</a> / <a href="findPw.do" class="text-dark">비밀번호 찾기</a>
	  </div>
	</div>
<hr/>
	<a href="javascript:void(0)" onclick="kakaoLogin();">
	   <img src="image/so8.png" alt="" class="w-100 btn btn-lg">
	</a>

</div>
</main>



  <script>
    new Vue({
      el: '#app',
      data: {
        username: '',
        password: '',
        errorMessage: ''
      },
      methods: {
    	  login() {
    		  const username = this.username;
    		  const password = this.password;

    		  axios.post('login.go', null, {
    		    params: {
    		      username: username,
    		      password: password
    		    }
    		  })
    		  .then(response => {
    		    // 로그인 성공 시 처리
    		    console.log(response.data);
    		    if(response.data == "mdraw"){
    		    	 alert("탈퇴한 회원입니다.")
    		    }else if(response.data == "admin"){
    		    	alert("관리자 입니다.")
    		    	window.location.href = 'adminHome.do';  
    		    }else{
	    		    alert("로그인 성공")
	    		    window.location.href = 'home.do';    		    	
    		    }
    		 
    	         
    	      
    		  })
    		  .catch(error => {
    		    // 로그인 실패 시 처리
    		    this.errorMessage = error.response.data.message;
    		    alert("아이디와 비밀번호를 확인해주세요!")
    		  });
    		}
        
      }
    });
  </script>



	<%@ include file="bottom.jsp" %>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>