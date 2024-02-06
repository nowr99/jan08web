<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <!-- core태그 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>S
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판판파라바라판판판판</title>
<link href="./css/index.css" rel="stylesheet">
<link href="./css/menu.css" rel="stylesheet">
<script type="text/javascript" src="./js/menu.js"></script>
<style type="text/css">
table {
	width: 1000px;
	height: 400px;
	border-collapse: collapse;
}
tr:hover{
	background-color: e0e1dd;
}
th{
	background-color: #00d9ff;
	border-bottom: 2px solid black;
}
td{
	border-bottom: 1px solid gray;
	text-align: center;
}
.title{
	text-align: left;
	width: 30px;
}
.w1{
	width: 10%;
}
.w2{
	width: 10%
}
.title a{
	text-decoration: none;
}
.title a:visited{
	color: red;
}
.title a:link{
	color: black;
}
.title a:hover{
	color: white;
	text-decoration: blink;
}
.paging{
	margin: 0 auto;
	width: 500px;
	height: 30px;
	/* background-color: gray; */
	margin-top: 10px;
	line-height: 30px;
	text-align: center;
}
.currentBtn{
	background-color: black;
	color: white;
}
span{
	color: silver;
	font-size: x-small;
	font-weight: bolder;
	width: 10px;
	heigh: 10px;
	border-radius: 15px;
}
</style>
</head>
<body>
	
	<div class="container">
		<header> <!-- 시멘틱태그 -->
			<%@ include file="menu.jsp" %>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<%-- for문 연습하기 <br>
					<c:forEach items="${list }" var="e" varStatus="s">
						${e.no } / ${s.first } / ${s.last } / ${s.index } / ${s.count } / ${s.step }<br>
					
					</c:forEach> --%>
				</article>
			
				<article>  <!-- if else 같은 녀석 -->
						<c:choose> 
						<c:when test="${fn:length(list) gt 0 }">
						<table>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>날짜</th>
							<th>읽음</th>
						</tr>
						<c:forEach items="${list }" var="row" > 
						<tr> 
							<td class="w1">${row.no}</td>
							<td class="title">					      <!--<span>글씨가 작아져요  -->
								<a href="./detail?page=${page }&no=${row.no }">${row.title }<span><c:if test="${row.comment ne 0 }">&ensp;[${row.comment }]</c:if></span></a>
							</td>
							<td class="w2">${row.write }</td>
							<td class="w1">${row.date }</td>
							<td class="w1">${row.count }</td>
						</tr>
						</c:forEach> 
					</table>
					totalCount : ${totalCount } /
					totalPage : <c:set var="totalPage" value="${totalCount / 10}"/>
					<fmt:parseNumber integerOnly="true" value="${totalPage }" var="totalPage"/>
					<c:if test="${totalCount % 10 gt 0 }">
						<c:set var="totalPage" value="${totalPage + 1}"/>
					</c:if>
					<c:out value="${totalPage }"/>
						<c:set var="startPage" value="1"/> 
					<c:if test="${page gt 5 }">
						<c:set var="startPage" value="${page - 5}"/>
					</c:if>
						<c:set var="endPage" value="${startPage + 9 }"/>
					<c:if test="${startPage + 9 gt totalPage }">
						<c:set var="startPage" value="${totalPage - 9 }" />
						<c:set var="endPage" value="${totalPage }"/>
					</c:if>
		<!-- page : ${param.page }  파라미터(주소창)에 있는 page긁어와서 찍어줘 (이렇게도 쓴다 기억하기) -->
					
					<div class="paging">
		<!-- page에 10을 뺐을 때 1보다 작으면 버튼 비활성화되게 조건 걸기 -->
						<button onclick="paging(1)">⏮</button>
						<button <c:if test="${page - 10 lt 1 }">disabled="disabled"</c:if>
						onclick="paging(${page - 10})">⏪</button>
						
						<c:forEach begin="${startPage }" end="${endPage }" var="p">
							<button
							<c:if test="${page eq p }">class="currentBtn"</c:if>
							 onclick="paging(${p })">${p }</button>
						</c:forEach>	
						
		<!-- page에 10을 더했을 때 totalpage를 넘어가면 버튼 비활성화되게 조건 걸기 -->
						<button <c:if test="${page + 10 gt totalPage }">disabled="disabled"</c:if>
						 onclick="paging(${page + 10 })">⏩</button>
						<button onclick="paging(${totalPage })">⏭</button>
											
					</div>
						</c:when>
						<c:otherwise>
							<h1>값이 싹 비었습니다.</h1>
						</c:otherwise>
					</c:choose>
					
					<!-- 로그인 해야 뜨게하기 (null이 아니면) -->
					<c:if test="${sessionScope.mname ne null}">
						<button onclick="url('./write')" class="spinner-border">글쓰기</button>
					</c:if>
		
					
				</article>
				
				<article>
				<%-- 	<fmt:requestEncoding value="UTF-8"/> <!-- 아티클 내 표시되는 언어 설정 -->
					<fmt:setLocale value="ko_kr"/> <!-- 기준이 되는 나라 설정 -->
					<fmt:formatNumber value="3.14" type="number"/>
					<fmt:parseNumber value="3.14" integerOnly="true"/>
					<br>
					<c:set var="nowDate" value="<%=new Date() %>"/>
					${nowDate }
					<br>
					<fmt:formatDate type="time" value="${nowDate }"/><br>
					<fmt:formatDate type="date" value="${nowDate }"/><br>
					<fmt:formatDate type="both" value="${nowDate }"/><br>
					<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${nowDate }"/><br>
					<fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${nowDate }"/><br>
					<fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${nowDate }"/><br>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${nowDate }"/><br> --%>
				
				</article>
				<article>
					<%-- fn이용해서 자료형 데이터 길이 뽑아내기
					${fn:length(list) }<br>
					 --%>
				</article>
			</div>
		</div>
		<footer>
		<c:import url="footer.jsp"/>
		</footer>
	</div>
	<script type="text/javascript">
		function paging(no){
			location.href="./board?page="+no;
		}
	</script>
</body>
</html>