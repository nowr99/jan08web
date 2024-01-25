<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커피커피커피</title>
<link href="./css/index.css" rel="stylesheet">
<link href="./css/menu.css" rel="stylesheet">
<script type="text/javascript" src="./js/menu.js"></script>


</head>
<body>
	<div class="container">
		<header>
			<!-- 시멘틱태그 -->
			<%@ include file="menu.jsp"%>
		</header>
		
		<div class="main">
			<div class="mainStyle">
				<form action="./coffee" method="post">
					<article style="text-align: center;">
					<h1> Coffee</h1>
					<input type="text" id="name" name="name" style="font-size: x-large;" placeholder="이름을 입력하세요."><br><br>
					<button type="submit" style="font-size: xx-large;"> 다음으로 </button> 
	
				</article>
				</form>
			</div>
		</div>

	
	<footer> 
	<c:import url="footer.jsp"/>
	</footer>
	</div>
</body>
</html>