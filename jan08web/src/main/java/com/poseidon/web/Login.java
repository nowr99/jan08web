package com.poseidon.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.LogDAO;
import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;
import com.poseidon.util.Util;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(); // 세션검사
		
		// 로그인 했는지 안했는지 검사하기
		String url = null;
		if(session.getAttribute("mname") != null) {
			url = "already.jsp";
		} else {
			url = "login.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		request.setCharacterEncoding("UTF-8"); // 한글처리
		
		
		if (request.getParameter("id") != null && request.getParameter("pw") != null) {
			MemberDTO dto = new MemberDTO(); //가져다 쓸려고 DTO객체 생성
			dto.setMid(request.getParameter("id"));
			dto.setMpw(request.getParameter("pw"));
			
			MemberDAO dao = new MemberDAO();
			dto = dao.login(dto);
			
			// 24.01.23 ip도 저장하기
			Map<String, Object> log = new HashMap<String, Object>();
			log.put("ip", Util.getIP(request));
			log.put("url", "./login");
			log.put("data", "id : " + dto.getMid() + ", pw : " + dto.getMpw() + " 결과 :  " + dto.getCount());
			
			LogDAO logDAO = new LogDAO();
			logDAO.write(log);
			
			
			if(dto.getCount() == 1) {
				System.out.println("로그인 되었습니다.");
				
				// 세션만들기 (겁나어려움)
				HttpSession session = request.getSession();
				// dto에서 Mname을 꺼내와서 세션값으로 저장
				session.setAttribute("mname", dto.getMname());
				// dto에서 Mid을 꺼내와서 세션값으로 저장
				session.setAttribute("mid", dto.getMid()); 
				
				// 페이지 이동 = board
				response.sendRedirect("./board");
			} else {
				//System.out.println("아이디나 비밀번호가 틀렸습니다.");
				// 페이지 이동 = login?error=4567
				response.sendRedirect("./login?error=4567");
			}
			
		} else {
			
		}
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println("id : " + id + "   pw : " + pw);
	}

}
