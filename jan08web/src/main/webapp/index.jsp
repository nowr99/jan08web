<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인덱스인덱스인덱스인덱스인덱스인덱스인덱스인덱스인덱스인덱스인덱스</title>

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
			<div>
				<article>
					<!-- 그냥 뒤에/ 넣으면 닫힘 -->
					<c:set var="number" value="105" />
					<c:out value="${number }" /><br>
					<br>
					<c:forEach begin="1" end="9" var="row" step="2"> <!-- step은 begin에 2씩더함 -->
						2 x ${row } = ${row * 2 } <br>
					
					</c:forEach>
					
					
					<c:if test="${number eq 105}">
						number는 105 입니다.<br>
						eq 11 == 5 false
						ne 11 != 5 true
						lt 11 〈 5 false
						gt 11 〉 5 true
						le 11〈= 5 false							
						ge 11〉= 5 true
						&&
						||
						empty 	  비어있나?
						not empty 파일에 값이 있나?
					</c:if>
					<hr>
					1~45까지 짝수만 출력하세요 <br>
					<c:forEach begin="1" end="45" var="row">
					<c:if test="${row%2 eq 0}">
						${row } <br>
					</c:if>
					
					</c:forEach>
				</article>
				<article>
					<c:import url="menu.jsp"/>
				<!-- c:redirect url="./board"/  58번 줄 닿자마자 ./board이동 -->
					<br>
					<c:forEach begin="1" end="10" var="row" varStatus="s">
						 ${s.begin } /${s.end }
					
					</c:forEach>
				</article>
				<h1>indexxxxXXxxXXxXXxXxXXXx</h1>
				
				// 서버에서 보내준 map : ${map } <br>
				<c:forEach items="${map }" var="row">
					이름 : ${row.name } <br>
					나이 : ${row.age} <br>
					주소 : ${row.addr } <br>
				</c:forEach>
				
				<c:set value="${map }" var="m"></c:set>
				<c:out value="${m }"></c:out>
				<c:out value="${m[1]['name']}"></c:out>
				
				<c:if test="조건">
				
				</c:if>
				
				if(){
					참일 때 실행
				}
				
				<c:choose> 
					<c:when test=""></c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
				
				스프링 -> 스프링부트... 스프링부트 타임리프 (여긴 jstl 안씀) 전까진 많이 씀
				Controller --- servlet
				Service ------ 서비스 로직
				Model -------- DAO DTO
				XML ---- 확장 html
			</div>
		</div>

		<a href="./board">board</a>
	</div>
	<footer> 
		<c:import url="footer.jsp"/>
	</footer>
</body>
</html>