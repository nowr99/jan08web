<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1"> <!-- 자동으로 사이즈 조정 -->
<link href="./css/index.css" rel="stylesheet"/>
<link href="./css/menu.css" rel="stylesheet"/>
<script type="text/javascript" src="./js/menu.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script>
<style type="text/css">
.id-alert, .name-alert, .pw-alert{
	background-color: gray;
}
.alert{
	color: red;
}
</style>
<script type="text/javascript">
	
	// 글로벌 변수 (변역변수)
	let idCheckBool = false;
	
	// $(document).ready(function (){}); 얘 말고 축소된 모양으로 해볼게요
	$(function(){ // 제이쿼리 시작문 = 제이쿼리를 시작합니다.
		$('.id-alert, .name-alert, .pw-alert').hide(); // hide()는 일단 숨기기
		
		//$("#id").change(function(){ //input창의 값이 변경될 때 마다 alert띄워줌
		//	alert("아이디 입력창 값이 변경되었습니다.")
		//});
		
		
		// onchange()라는 녀석 써보기 on은 무언가를 감지함
		$('#id').on("change keyup paste", function(){ // 키 입력을 받으면 바로 실행됨
			//alert("아이디 입력창 값이 변경되었습니다.");
			$('.id-alert').show();
			$('.id-alert').html('<p class="alert">당신이 입력한 ID는 ' + $('#id').val() + '</p>')
							// .html : html관련 태그를 사용할 수 있게 함
			if($('#id').val().length > 4){
				idCheck();	
			}
		});
		
	});
	
	function check(){
		// $(선택자).할일();
		let id = $("#id").val();
		//alert(id + " : " + id.length) // isNaN()함수:Not a Number(영문자는 참, 숫자는 거짓 반환)
		if(id.length < 3 || id == ""){
			//alert("아이디는 3글자 이상이어야 합니다. 다시 입력하세요.");
			$('.id-alert').show(); // 위에 쓴 hide가 나타나게 하는 show()
			$("#id").focus(); ///$("#id").focus() : 커서를 "#id" 쪽으로 자동 옮겨줌
			return false;
		} else {
		$('.id-alert').hide(); // hide()써서 조건에 부합하면 alert문을 다시 가리기
		}
		
		if(!idCheckBool){
			alert("ID 검사를 먼저 실행해주세요.")
			return false;
		}
		
		
		let name = $('#name').val();
		if(name.length < 3) {
			//alert("이름은 3글자 이상이어야 합니다. 다시 입력하세요.");
			$('.name-alert').show();
			$('#name').focus();
			return false;
		}
		$('.name-alert').hide();
		
		let pw1 = $('#pw1').val();
		let pw2 = $('#pw2').val();
		if(pw1.length < 8 || pw2.length < 8 || pw1 == "" || pw2 == "") {
			//alert("비밀번호는 8글자 이상이어야 합니다. 다시 입력하세요.");
			$('.pw-alert').show();
			$('#pw1').focus();
			return false;
		}
		$('.pw-alert').hide();
		
		if (pw1 != pw2) {
			alert("암호가 일치하지 않습니다. 다시 입력해주세요.")
			$('#pw2').val(""); // ""을 넣어서 지워지는 거 처럼 보임
			$('#pw2').focus();
			return false;
		}
		$('.pw-alert').hide();
	}
	function idCheck(){
		//alert('id 검사를 눌렀습니다.');
		let id = $('#id').val();
		//const regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"\sㄱ-ㅎㅏ-ㅣ가-힣]/g;//한글+공백
		// alert(regExp.test(id)); // 한글, 공백 포함여부 검사하는 정규식
		// 나중에는 영어 대문자, 소문자, 숫자만 들어오게 해줘야해요
		const regExp = /^[a-z0-9]{5,15}$/;
		if(id.length < 5 || !regExp.test(id)){
			//alert("아이디는 5글자 이상이어야 합니다.")
			$('.id-alert').html('<p class ="alert">아이디는 영문자 5글자 이상이고 특수문자나 공백이 없어야 해요.</p>')
			$('#id').focus();
		} else {
			// AJAX의 등장
			$.ajax({
				url : './idCheck',  // 어디로 보낼지?
				type : 'post',      // 어떤방식으로 보낼지 (IDCheck.java의 post로)
				dataType : 'text',  // 오는 데이터 타입
				data : {id : id}, 	// 진짜 보낼 데이터 {보낼이름 :  id}
				success : function (result){
					// alert("통신에 성공했습니다");
					if(result == 1){
						// alert("아이디가 중복됩니다. 다른 아이디를 입력하세요")
						$('.id-alert').append ("<p class='alert'>아이디가 이미 가입되어 있어.</p>") // append 원래꺼에서 계속 이어붙이기
						idCheckBool = false;
						$('#joinBtn').attr('disabled', 'disabled'); // attr()은 속성. 비활성화 시키기
						$('#id').focus();
					} else {
						//alert("가입할 수 있습니다. 계속하세요")
						$('.id-alert').append ("<p class='alert'>가입할 수 있습니다 계속해.</p>")
						$('.id-alert .alert').css("color", "green");
						idCheckBool = true;
						$('#joinBtn').removeAttr('disabled'); // 비활성화 제거 = 활성화
						// $('#name').focus();
					}
				},
				error : function (request, status, error){ // 접속불가, 문제 발생 시 
					alert("문제가 발생했습니다.");
				}
			});
		}
		return false;
	}
</script>
</head>
<body>
<% //보강수업 24.01.20 jstl
int num = 100;
// 가독성이 떨어져요.. php
%>
<%=num %>






	<div class="container1">
		<header>
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
				
					<div class="join-form">
						<h2>회원가입</h2>
						<div class="mx-3 p-3 rounded" style= "background-color: silver">
							<form action="./join" method="post" onsubmit="return check()">
								<div class="input-group mb-2">
									<label class="input-group-text col-6">아이디&ensp;<input type="text" id="id" name="id" class="form-control" placeholder="아이디를 입력하세요."></label>
									<button class="btn btn-success input-group-text" onclick="return idCheck()">ID검사</button>
								</div>
									<div class="input-group mb-2 id-alert">
										<p class="alert">올바른 아이디를 3글자 이상 입력하세요</p>
									</div>
								<div class="input-group mb-2">
									<label class="input-group-text col-6">이&ensp;&ensp;름&ensp;<input type="text" id="name" name="name" class="form-control" placeholder="이름을 입력하세요."></label>
								</div>
									<div class="input-group mb-2 name-alert">
										<p class="alert">올바른 이름을 3글자 이상 입력하세요</p>
									</div>
								<div class="input-group mb-2">
									<label class="input-group-text col-5">암&ensp;&ensp;호&ensp;<input type="password" id="pw1" name="pw1" class="form-control" placeholder="암호를 입력하세요."></label>
									<label class="input-group-text col-5">재입력&ensp;<input type="password" id="pw2" name="pw2" class="form-control" placeholder="한번 더 입력하세요."></label>
								</div>
									<div class="input-group mb-2 pw-alert">
										<p class="alert">올바른 암호를 8글자 이상 입력하세요</p>
									</div>
								<div class="input-group mb-2">
									<button type="reset" class="btn btn-danger col-5">초기화</button>
									<button id="joinBtn" type="submit" disabled="disabled" class="btn btn-primary col-5">가입하기</button>
								</div>
							</form>					
						</div>
					</div>
				</article>
			</div>
		</div>
		<footer>
		<c:import url="footer.jsp"/>
		</footer>
	</div>
</body>
</html>