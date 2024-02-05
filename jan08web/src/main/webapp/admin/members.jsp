<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지 - 사용자 관리</title>
<link href="../css/admin.css?ver=0.14" rel="stylesheet"/>
<link href="../css/member.css" rel="stylesheet"/>
<script type="text/javascript" src="../js/menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script>
//$(document).ready(function (){
$(function (){
	//alert("!");
	$('select[name=grade]').on("change", function(){
		let val = $(this).val();//변경시킬 등급
		let mno = $(this).closest("tr").children().first().text();
		//두 값을 잡았으면 가상 form에 담아서 전송합니다.
		let form = $('<form></form>');
		form.attr('method', 'post');
		form.attr('action', './members');
		form.append($('<input/>', {type : 'hidden', name : 'mno', value : mno}));
		form.append($('<input/>', {type : 'hidden', name : 'grade', value : val}));
		<c:if test="${param.grade ne null }">
		form.append($('<input/>', {type : 'hidden', name : 'currentgrade', value : ${param.grade}}));
		</c:if>
		form.appendTo('body');
		form.submit();
	});
	
});
</script>
</head>
<body>
	<div class="wrap">
		<!-- menu -->
		<%@ include file="menu.jsp" %>
		<div class="main">
			<article>
				<h2>회원관리</h2>
				<div class="member-lists">
					<ul>
						<li onclick="url('./members?grade=0')"><i class="xi-close-circle-o"></i> 탈퇴</li>
						<li onclick="url('./members?grade=1')"><i class="xi-minus-circle-o"></i> 강퇴</li>
						<li onclick="url('./members?grade=2')"><i class="xi-check-circle-o"></i> 정지</li>
						<li onclick="url('./members?grade=5')"><i class="xi-label-o"></i> 정상</li>
						<li onclick="url('./members?grade=9')"><i class="xi-plus-square-o"></i> 관리자</li>					
					</ul>
				</div>
				
				<table>
					<thead>
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>가입일</th>
						<th>등급</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="row">
						<tr class="row${row.mgrade }">
							<td class="d1">${row.mno }</td>
							<td class="title">${row.mid }</td>
							<td class="d2">${row.mname }</td>
							<td class="d2">${row.mdate }</td>
							<td class="d1">
								<select name="grade">
									<optgroup label="정지">
										<option <c:if test="${row.mgrade eq 0}">selected="selected"</c:if> value="0">0 강퇴</option>
										<option <c:if test="${row.mgrade eq 1}">selected="selected"</c:if> value="1">1 탈퇴</option>
										<option <c:if test="${row.mgrade eq 2}">selected="selected"</c:if> value="2">2 정지</option>
									</optgroup>
									<optgroup label="정상">
										<option <c:if test="${row.mgrade eq 5}">selected="selected"</c:if> value="5">5 평민</option>
									</optgroup>
									<optgroup label="관리자">								
										<option <c:if test="${row.mgrade eq 9}">selected="selected"</c:if> value="9">9 관리자</option>
									</optgroup>
								</select>				
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				페이징 필요
			</article>
		</div>
	</div>
</body>
</html>