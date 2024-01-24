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

@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Update() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// 세션잡기
		HttpSession session = request.getSession();
		if(session.getAttribute("mid") != null) {
			// 세션이 있을 때 = 정상작업
			// no 잡기
			int no = Util.str2Int(request.getParameter("no")); // Util 만든거 활용
			// DAO에 질의하기
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = dao.detail(no);
			System.out.println(dto.getMid());
			
			// 들어오는 값 비교
			//System.out.println(dto.getMid().equals(session.getAttribute("mid")));
			//System.out.println(session.getAttribute("mid").equals(dto.getMid()));
			//System.out.println(((String)session.getAttribute("mid")).equals(dto.getMid()));
			//System.out.println(session.getAttribute("mid") == dto.getMid());
		
				// 되긴하는데 cast걸어서 맞춰주는게 좋음
			if (session.getAttribute("mid").equals(dto.getMid())) {
				request.setAttribute("update", dto);
				RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
				rd.forward(request, response);
			} else { 
				response.sendRedirect("error.jsp");
			}
		} else {
			// 없으면 로그인으로 보내기
			response.sendRedirect("./login?login=nologin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		if (request.getParameter("title") != null 
				&& request.getParameter("content") != null
				&& Util.intCheck(request.getParameter("no")) 
				&& session.getAttribute("mid") != null) {
			// 진짜 수정
			BoardDTO dto = new BoardDTO();
			dto.setTitle(request.getParameter("title"));
			dto.setContent(request.getParameter("content"));
			dto.setNo(Util.str2Int(request.getParameter("no")));
			dto.setMid((String)session.getAttribute("mid"));
			
			BoardDAO dao = new BoardDAO();
			int result = dao.update(dto);
			//System.out.println("수정 결과 : " + result);
					
			response.sendRedirect("./detail?no=" + request.getParameter("no"));
		} else {
			// error
			response.sendRedirect("./error.jsp");
		}
		
		// 얜 이제 실행 안됨 잘 써지나 검사한거
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String no = request.getParameter("no");
	}

}
