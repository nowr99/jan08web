<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기는 글쓰기</title>

<link href="./css/index.css" rel="stylesheet">
<link href="./css/menu.css" rel="stylesheet">
<script type="text/javascript" src="./js/menu.js"></script>

<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<style type="text/css">
#title{ /* id css 주기 */
	width: 100%;
	height: 30px;
	margin-bottom: 10px;
	
}
</style>
</head>
<body>
	<div class="container1">
		<header>
			<!-- 시멘틱태그 -->
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<h1>글을 써보세요</h1>
					<div class="writeFORM">
						<form action="./write" method ="post"> <!-- post방법 -->
							<input type="text" id="title" name="title">
							<textarea id="summernote" name="content"></textarea>
							<button type="submit">저장하기</button>
						</form> <!-- id는 html, 스크립트 등에서 name은 자바가 잡을 때 -->
					
					</div>
					
				</article>
			</div>
		</div>
	<footer> 
	<c:import url="footer.jsp"/>
	</footer>
	</div>
	<script>
	$(document).ready(function() {
		  $('#summernote').summernote({
			  height: 500
		  });
		});
	</script>
</body>
</html>