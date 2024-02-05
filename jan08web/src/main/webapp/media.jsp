<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<!-- 뷰포트메타태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>미디어쿼리</title>
<style>
/* 모든기기공통 CSS */
/*  body{ background-color: orange; } */

/* desktop 규격 */
@media screen and (min-width: 1024px) {
	body {
		background-color: yellow;
	}
}

/* tablet 규격 */
@media screen and (max-width: 1023px) {
	body {
		background-color: orange;
	}
}

/* mobile 규격 */
@media screen and (max-width: 540px) {
	body {
		background-color: red;
	}
}
</style>
<script type="text/javascript">
//console.log(window.innerWidth); /* F12눌러서 Console log 창에 너비 표시*/
//console.log(window.innerHeight); /* F12눌러서 Console log 창에 높이 표시*/

window.onresize = function(event){ // onresize : size가 변경되면 함수를 실행시킬꺼야
	document.getElementById("size").textContent = window.innerWidth + " x "+window.innerHeight;
}
</script>
</head>
<body>
	<h1 id="size">너비 : </h1>
</body>
</html>