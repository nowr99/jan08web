<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>디테일디테일</title>

<link href="./css/index.css" rel="stylesheet">
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/detail.css" rel="stylesheet">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript" src="./js/menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type= "text/javascript">
	$(document).ready(function(){
		
		// 24.01.24 댓글 수정하기
		$(".commentEdit").click(function(){
			if(confirm("정말 수정할꺼니?")){ 
				// 필요한 cno 값 잡기 / 수정한 내용 + 로그인 ==== 서블릿에서 정리
				let cno = $(this).parent().children(".cno").val(); // cno 잡기
				let comment = $(this).parents(".chead").next();/*next()는 html 태그를 잡음 Object object*/; // 변경해야됨 나중에
				// alert(cno + " : " + comment.html()); html태그도 포함해서 출력하기
				
				$(this).prev().hide();
				$(this).hide();
				comment.css('height', '110px');
				comment.css('padding-top', '10px');
				comment.css('backgroundColor', '#c1c1c1');
				let commentChange = comment.html().replaceAll("<br>", "\r\n");
				
				// 옛날방식
				let recommentBox='<div class="recommentBox">';
				recommentBox += '<textarea class="commentcontent" name="comment">' + commentChange + '</textarea>';
				recommentBox += '<input type="hidden" name="cno" value="'+cno+'">';
				recommentBox += '<button class="comment-btn">댓글수정</button>';
				recommentBox += '</div>';
				
				comment.html(recommentBox);
				
			
			}
		});
		
		//댓글수정 .comment-btn버튼 눌렀을 때 .cno값, .commentcontent값 가져오는 명령 만들기
		// 2024.01.25
		$(document).on('click', ".comment-btn", function(){
			
			let cno = $(this).prev().val();
			let recomment = $('.commentcontent').val();
			let comment = $(this).parents(".ccomment"); // 댓글 위치
			//alert(cno + " : " + recomment);
			$.ajax({
				url : './recomment',
				type : 'post',
				dataType : 'text',
				data : {'cno' : cno, 'comment' : recomment},
				success:function(result){
					//alert('통신 성공 : ' + result);
					if (result == 1){
						// 수정 된 데이터를 화면에 보여지게 해요
						$(this).parent(".recommentBox").remove();
						comment.css('backgroundColor', '#ffffff');
						comment.css('min-heigt', '100px');
						comment.css('height', 'auto');  
						recomment = recomment.replace(/(?:\r\n|\r|\n)/g, '<br>');
						comment.html(recomment);
						$(".commentDelete").show();
						$(".commentEdit").show();
					} else {
						// 실패시 화면을 재로드.
						alert("수정을 실패했습니다.")
						location.href='./detail?page=${param.page}&no=${param.no}';
						// location.href='./detail?page=${param.page}&no=${detail.no}'; 얘도 됨
					}
				},
				error:function(error){
					alert('문제발생문제발생 : ' + error);
				}
			}); // end ajax
			
		});
		
		
		
		// 24.01.24 댓글 삭제버튼을 눌렀습니다. id 가 아닌 class로 지정 (id로 하면 댓글 한개 지우면 멈춰버림 class는 여러개 가능) 
		$(".commentDelete").click(function(){
			//alert("삭제버튼을 눌렀습니다.");
			// 부모(parent) 객체 찾아가기(delete 바로 위 클래스인 cname) = this 나 (.commentDelete)
			//$(this).parent(".cname").css('color', 'green'); // 내 부모중 cname의 css변경
			//$(this).parent(".cname").text("변경가능"); // 삭제버튼 누르면 이름이 변경가능으로 바뀜
			//let text = $(this).parent(".cname").text(); // val()? text() html()
			
			// 부모 요소 아래 자식 요소 찾기 children()
			//let cno = $(this).parent(".cname").children(".cno").val();
			//let cno = $(this).parent().children(".cno").val();
			// 형제요소 찾기 .siblings() .prev() 바로이전 .next() 바로 다음
			//let cno = $(this).siblings(".cno").val(); // 내 형제요소에 .cno 이름을 찾아줘
			
			if(confirm("진짜 삭제할꺼니?")) { // 24.01.24 진짜 삭제할꺼냐고 한번 물어보기
				
			let cno = $(this).prev().val(); // cno 잡기
			
			// 24.01.24 ajax (각 항목 순서 상관X)
			let point = $(this).closest(".comment");
			// $(this).closest(".comment").hide();
			$.ajax({ 
				url : './commentDel',		// 주소
				type : 'post',				// get or post
				dataType: 'text',			// 수신타입 json
				data: {no : cno},			// 보낼 값
				success:function(result){   // 서버에서 온 값을 날려줌 0 또는 1 
					if(result == 1) {
						//정상 삭제됨 : this의 부모(.comment)를 찾아서 remove 하기
						point.remove(); // 1이었던 cdel을 0으로 바꿔줌
					} else {
						alert("삭제할 수 없습니다. 관리자에 문의바람")
					}
				},
				error:function(request, status, error){ // 통신오류
					alert("문제가 발생했슴다");
				}
			}); // end ajax
		
			}
		});
			
		
		// 댓글쓰기 버튼 누르면 댓글창 나오게 하기 24.01.24
		$(".comment-write").hide(); // ready바로 아래 두는걸 추천
		$(".xi-comment-o").click(function(){
			$(".xi-comment-o").hide();
			//$(".comment-write").show();
			$(".comment-write").slideToggle('slow');
		});
		
		// alert("준ㅂㅣ완료");
		$("#comment-btn").click(function(){
			let content = $("#commentcontent").val();
			let bno = ${detail.no };// 여기는 글번호
			// alert("content : " + content + " no : " + no);
			// 가상 form 만들기 = 동적생성 (어려워요)
			// 전송 --> content가 5글자 이상인 경우 실행하게 만들겠습니다.
			if(content.length < 5){
				 alert("5글자 이상 쓰세요.");
				$("#commentcontent").focus();
				// return false; 멈추기 위해 false를 적어야 하지만 어차피 멈추므로 안써도 무방
			} else {
				let form = $('<form></form>');
				form.attr('name', 'form'); /* attr은 속성지정  */
				form.attr('method', 'post');
				form.attr('action', './comment');
				
				form.append($('<input/>', {type: 'hidden', name: 'commentcontent', value: content})); // json형태 
				form.append($('<input/>', {type: 'hidden', name: 'bno', value: bno}));
				
				form.appendTo("body");
				form.submit();

				
				// 어려운 방식
				/* let form = document.createElement('form');
				form.name='form';
				form.method='post';
				form.action='./comment';
				
				// 붙이기
				let text = document.createElement('input');
				text.setAttribute("type", "hidden");
				text.setAttribute("name", "commentcontent");
				text.setAttribute("value", content); // $("#commentcontent").val() 써도 됨
				
				// 붙이기
				let no = document.createElement('input');
				no.setAttribute("type", "hidden");
				no.setAttribute("name", "bno");
				no.setAttribute("value", ${detail.no }); //bno로 써도 됨
				
				// form에 붙이기
				form.appendChild(text);
				form.appendChild(no);
				
				// 전송하기
				document.body.appendChild(form);
				form.submit(); */
			}
		}); // 댓글쓰기 동적생성 끝
		
		//댓글쓰기 창에 쓸 수 있는 글자를 표시해주고 넘어가면 입력불가로 바꾸기
		$("#commentcontent").keyup(function(){
			let text = $(this).val();
			if(text.length > 200) {
				alert("200자 넘으면 안되ㅇ.")
				$(this).val(text.substr(0,200));
			}
			$("#comment-btn").text("글쓰기 " + text.length + "/200");
		});
	});

</script>

<style type="text/css">
.detailDIV{
	width: 900px;
	height: auto;
	background-color: silver;
	padding: 10px;
	box-sizing: border-box;
}
.detailTITLE{
	width: 100%;
	height: 50px;
	font-size: large;
	border-bottom: 5px solid black;
	line-height: 50px;
}
.detailWRITECOUNT{
	width: 100%;
	height: 50px;
}
.detailWRITE, .detailCOUNT{
	width: 50%;
	height: 50px;
	line-height: 50px;
}
.detailWRITE{
	float: left;
}
.detailWRITE img{
	/* WRITE의 img를 따로 글자와 정렬 */
	vertical-align: text-bottom;
}
.detailWRITE img:hover{
	background-color: aqua;
}
.detailCOUNT {

	float: right;
}
.detailCONTENT{
	width: calc(100% - 5px);
	min-height: 300px;
	height: auto;
	margin: 3px;
	border: 5px solid black;
	box-sizing: border-box;
}

</style>
</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<div class="detailDIV">
						<div class="detailTITLE">
							${detail.title }
						</div>
						<div class="detailWRITECOUNT">
							<div class="detailWRITE">
							${detail.write }
							
							<c:if test="${sessionScope.mname ne null && detail.mid eq sessionScope.mid }">
								<img alt="update" src="./img/update.png" onclick="update()">
								<img alt="delete" src="./img/delete1.png" onclick="del()">
							</c:if>
							
							</div>
							<div class="detailCOUNT">${detail.ip} / ${detail.count }</div>
						</div>
						<div class="detailCONTENT">
							${detail.content }
						</div>
					</div>
					
					
					<c:if test="${sessionScope.mid ne null }">
					<button class="xi-comment-o">댓글쓰기</button>
					<!-- 댓글쓰는 창 만들기. 댓글내용, 누가, 어느 2024.01.22 -->
					<div class="comment-write">
						<div class="comment-form">
							<textarea id="commentcontent" name="commentcontent"></textarea>
							<button id="comment-btn">댓글쓰기</button>
						</div>
					</div>
					</c:if>
					
					<!-- 댓글 출력창-->
					<div class="comments">
						<c:forEach items="${commentList }" var="co"> 
							<div class="comment">
								<div class="chead">
									<div class="cname">${co.mname } 님 
									<c:if test="${sessionScope.mname ne null && co.mid eq sessionScope.mid }">
										<input type="hidden" class="cno" value="${co.cno }">
										<img alt="delete" src="./img/commentdel.png" class="commentDelete">
										<img alt="update" src="./img/commentupdate.png" class="commentEdit">
									</c:if>
										<div class="cdate">${co.ip} /${co.cdate }</div>
									</div>
								</div>
								<div class="ccomment">${co.comment }</div>
							</div>
						</c:forEach>
					</div>
					<button onclick="url('./board?page=${param.page}')"> 게시판으로 돌아가기</button>
				</article>
				<article>
				푸터에 숨은 녀석 보이기
				</article>

			</div>
		</div>

	</div>
	<script type="text/javascript">
		function del(){
			//alert("진짜로 삭제합니다?");
			var ch = confirm("진짜 삭제?"); /* 예=true 아니오=false 처리 */
			if(ch){
				location.href="./delete?no=${detail.no }";
			}
		}
		
		function update() {
			if(confirm("진짜 수정?")){
				location.href="./update?no=${detail.no}";
			}
		}
	// 24.01.24 다른삭제 기능 만들려고 주석처리	function commentDel(cno){if(confirm("댓글 삭제?")){location.href='./commentDel?no=${detail.no}&cno='+cno;}}
		
	</script>      
	<footer> 
	<c:import url="footer.jsp"/>
	</footer>
</body>
</html>