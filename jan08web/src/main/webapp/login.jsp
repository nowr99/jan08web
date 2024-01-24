<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginloginloginloginloginloginlogin</title>

<link href="./css/index.css" rel="stylesheet">
<link href="./css/menu.css" rel="stylesheet">
<script type="text/javascript" src="./js/menu.js"></script>
<style type="text/css">
.login{
	width: 300px;
	margin: 0 auto;
	text-align: center;
	min-height: 300px;
	background-color: silver;
	margin-bottom: 10px;
	box-sizing: border-box;
	padding-top: 10px;
}
.login input {
	width: 200px;
	height: 30px;
	text-align: center;
	color: green;
	margin: 5px;
	box-sizing: border-box;
	
}
.login button{
	width: 30%;
	height: 30px;
	color: green;
	font-size: large;
}

</style>
<script type="text/javascript">
	function err(){
		let errBox = document.querySelector("#errorMSG");
		errBox.innerHTML = "<marquee>올바른 ID와 PW를 입력하세료.</marquee>";
		errBox.style.color = 'red';
		errBox.style.fontSize = "10pt";
	}
</script>
</head>
<body>
	<div class="container">
		<header>
			<!-- 시멘틱태그 -->
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<h1 style="text-align: center;">로그인</h1>
					<c:if test="${param.error ne null }">
						<script type="text/javascript">
							alert("올바른 ID와 PW를 입력하세요.");
						</script>
					</c:if>
					
					<div class="login">
						<form action="./login" method="post">
						<img alt="x" src="./img/abc.jpg" style="width: 280px"><br>
							 <input type="text" name="id" placeholder="ID"> <br> <!-- 자바로 잡을때는 name -->
							 <input type="password" name="pw" placeholder="PW"> <br>
							<button type="reset">지우기</button>
							<button type="submit">로그인</button>
							<div id="errorMSG"></div>
						</form>
						<a href="./join">회원가입</a>
					
					</div>
				</article>
			</div>
		</div>

		
	<footer> 
	<c:import url="footer.jsp"/>
	</footer>
	</div>
	<c:if test="${param.error ne null }">
		<script type="text/javascript">
		err();
		</script>
	</c:if>
</body>
</html>