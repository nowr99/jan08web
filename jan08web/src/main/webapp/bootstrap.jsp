<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>New Age - Start Bootstrap Theme</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Google fonts-->
        <link rel="preconnect" href="https://fonts.gstatic.com" />
        <link href="https://fonts.googleapis.com/css2?family=Newsreader:ital,wght@0,600;1,600&amp;display=swap" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css2?family=Mulish:ital,wght@0,300;0,500;0,600;0,700;1,300;1,500;1,600;1,700&amp;display=swap" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,400;1,400&amp;display=swap" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light fixed-top shadow-sm" id="mainNav">
            <div class="container px-5">
                <a class="navbar-brand fw-bold" href="#page-top">웹사이트</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="bi-list"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto me-4 my-3 my-lg-0">
                        <li class="nav-item"><a class="nav-link me-lg-3" href="./board">보드</a></li>
                        <li class="nav-item"><a class="nav-link me-lg-3" href="./qna">문의게시판</a></li>
                        <li class="nav-item"><a class="nav-link me-lg-3" href="./notice">공지사항</a></li>
                        <li class="nav-item"><a class="nav-link me-lg-3" href="./login">로그인</a></li>
                    </ul>
                </div>
            </div>
        </nav>
       
        <section>
        
   			<div class="my-5 border">
   			</div>
            <div class="mx-5 p-5 border">
            <table class="table table-dark table-hover">
            	<thead>
            		<tr class="row"> <!-- 그리드 시스템(자동으로 나눠주나 12분할 할 수 있음) -->
            			<th class="col-1">번호</th>
            			<th class="col-6">제목</th>
            			<th class="col-2">글쓴이</th>
            			<th class="col-1">날짜</th>
            			<th class="col-2">조회수</th>
            		</tr>
            	</thead>
            	<tbody>
            		<c:forEach items="${list }" var="row">
            		<tr class="row">
            			<td class="col-1">${row.no }</td>
            			<td class="col-6">${row.title }</td>
            			<td class="col-2">${row.write }</td>
            			<td class="col-1">${row.date }</td>
            			<td class="col-2">${row.count }</td>
            		</tr>
            		</c:forEach>
            	</tbody>
            </table>
            </div>
            <button type="button" class="mx-5 btn btn-outline-primary spinner-border">글쓰기</button>
            
            <div class="container bg-info text-white">구역입니다.
            <div class="m-5 mb-0 bg-primary">구역2입니다.</div>
            <div class="mx-2 my-4 bg-secondary">구역3입니다.</div>
            <div class="my-2 bg-warning w-25">구역4입니다.</div>
            <div class="my-2 bg-warning w-50">구역5입니다.</div>
            <div class="my-2 bg-warning w-75">구역6입니다.</div>
            </div>
            
        </section>
        <!-- Footer-->
        <footer class="bg-black text-center py-5">
            <div class="container px-5">
                <div class="text-white-50 small">
                    <div class="mb-2">&copy; poseidon 2024. All Rights Reserved.</div>
                    <a href="#!">Privacy</a>
                    <span class="mx-1">&middot;</span>
                    <a href="#!">Terms</a>
                    <span class="mx-1">&middot;</span>
                    <a href="#!">FAQ</a>
                </div>
            </div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
