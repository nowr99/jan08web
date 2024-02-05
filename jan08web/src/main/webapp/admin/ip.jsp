<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지 - IP관리</title>
<link href="../css/admin.css?ver=0.14" rel="stylesheet"/>
<link href="../css/member.css" rel="stylesheet"/>
<link href="../css/adminBoard.css?ver=0.00002" rel="stylesheet"/>
<script type="text/javascript" src="../js/menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['IP', 'Manymany'],
          <c:forEach items="${list2 }" var="row">
          ['${row.iip}', ${row.count}],
         </c:forEach>
        ]);

        var options = {
          title: '차트만들기 24.01.31'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
</script>
</head>
<body>
	<div class="wrap">
		<!-- menu -->
		<%@ include file="menu.jsp" %>
		<div class="main">
			<article>
				<h2>IP관리</h2>
				
				
				<div>
				<h2>IP리스트</h2>
				<table>
				<thead>
					<tr>
						<th>IP</th>
					</tr>
				</thead>
					<tbody>
						<c:forEach items="${list1 }" var="row">
							<tr class="row">
								<td class="d1">${row.iip }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<div>
				<h2>가장 많이 접속한 IP</h2>
				<table>
				<thead>
					<tr>
						<th>IP</th>
						<th>방문횟수</th>
					</tr>
				</thead>
					<tbody>
						<c:forEach items="${list2 }" var="row">
							<tr class="row">
								<td class="d1">${row.iip }</td>
								<td class="d1">${row.count }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<!-- 그래프 그리기 -->
				<div id="piechart" style="width: 900px; height: 500px;"></div>
				
				<div class="nav-lists">
					<ul class="nav-lists-group">
						<li class="nav-lists-item" onclick="url('./ip?del=1')"><i class="xi-close-circle-o"></i> 보임</li>					
						<li class="nav-lists-item" onclick="url('./ip?del=0')"><i class="xi-close-circle-o"></i> 숨김</li>					
					</ul>
					<div class="search">
						<input type="text" id="search">
						<button id="searchBtn">검색</button>
					</div>
					<button onclick="location.href='./ip'">초기화</button>
				</div>
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>IP</th>
							<th>날짜</th>
							<th>URL</th>
							<th>기타</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="row">
							<tr class="row">
								<td class="d1">${row.ino }</td>
								<td class="d1"><a href="./ip?ip=${row.iip }">${row.iip }</a></td>
								<td class="d2">${row.idate }</td>
								<td class="d1">${row.iurl }</td>
								<td class="title">${row.idata }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</article>
		</div>
	</div>
</body>
</html>