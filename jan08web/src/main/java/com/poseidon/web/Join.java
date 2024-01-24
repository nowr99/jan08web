package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;

@WebServlet("/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Join() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("join.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글처리
		int result = 0;
		//값 잡기 id, name, pw1
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw1 = request.getParameter("pw1");
		
		//db에 보내주기
		
		MemberDTO dto = new MemberDTO();
		dto.setMid(id);
		dto.setMname(name);
		dto.setMpw(pw1);
		
		MemberDAO dao = new MemberDAO();
		result = dao.join(dto);
		
		//정상적으로 데이터입력을 했다면 로그인 페이지 ㄱ
		if (result == 1) {
			System.out.println("성공적으로 만들어졌습니다.");
			response.sendRedirect("./login");
		} else {
			//비정상이면 에러로
			System.out.println("에러에요");
			response.sendRedirect("./error");
		}
	}
}
