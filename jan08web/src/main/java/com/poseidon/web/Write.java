package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;

@WebServlet("/write")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Write() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//세션검사
		HttpSession session = request.getSession();
		
		// mname이 null이면 로그인으로가 아님 write로 가
		if(session.getAttribute("mname") == null) {
			response.sendRedirect("./login"); //url까지 변경해서 화면에 보여줌
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("write.jsp"); 
													// url고정, 화면만 변경
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 세션에 들어있는 mid 가져오기 24.01.15
		HttpSession session = request.getSession();
		
		// if문으로 로그인 되어있는 (= 세션이 있는) 사람만 되도록 변경할꺼임
		if(session.getAttribute("mid") == null || session.getAttribute("mname") == null) {
			// 로그인 하지 않았다면 login.jsp로 가게 하기
			response.sendRedirect("./login?login=nologin");
		} else {	
			// 로그인 했다면 아래 로직 실행하기
			String title = request.getParameter("title"); /* summernote post */
			String content = request.getParameter("content");
			
			// 24.01.23 html태그에서 특수기호 <,>를 변경하기 및 줄바꿈처리하기
			title = Util.removeTag(title); // Util에 removeTag 만듬
			
			// 제대로 써지는지 확인
			System.out.println(title);
			System.out.println(content);
			
			// DAO에 write메소드 만들기 (24.01.23 ip저장하는거 추가)
			BoardDTO dto = new BoardDTO();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setMid((String)session.getAttribute("mid")); //session 에서 mid 가져오기
			dto.setIp(Util.getIP(request));
			
			// Object 로 받아오기때문에 cast 필요
			// DAO 일 시키기
			BoardDAO dao = new BoardDAO();
			int result = dao.write(dto); // title과 content가 담긴 객체를 dto로 던짐
			System.out.println("글쓰기 결과는 : " + result);
			
			// 페이지 이동하기 = url값과 화면 모두 이동하기
			if (result == 1) {
				response.sendRedirect("./board");
			} else {
				response.sendRedirect("./error.jsp");
			}
		}
		
		
		
		
	}

}
