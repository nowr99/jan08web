package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// System.out.println("get으로 들어왔어요");
		// 세션 종료 시켜주기
		/*
		 * 세션 쿠키
		 * 세션 : 서버에 저장(보안 굿)		쿠기 : 클라이언트에 저장(브라우저, 보안 취약)
		 * 		  로그인 정보					   장바구니, 방문내역 등
		 * 		  자바							   스크립트
		 */
		HttpSession session = request.getSession();
		if(session.getAttribute("mname") != null) {
			// session.setMaxInactiveInterval(0) set은 괄호만큼 유효기간 연장
			// System.out.println("세션 유효시간 : " + session.getMaxInactiveInterval());// 기본 30분
			// System.out.println("mname : " + session.getAttribute("mname"));
			 session.removeAttribute("mname"); // mname 중지
		}
		if(session.getAttribute("mid") != null) {
			// System.out.println("mid : " + session.getAttribute("mid"));
			 session.removeAttribute("mid"); // mid 중지
		}
		session.invalidate();
		// invalidate() 는 세션 자체를 무효화 하고 제거
		// removeAttribute()는 현재 세션에서 특정 key-value만 제거
		/* removeAttribute()로 키만 제거를 하면 httpSession 인스턴스가 남아있어서 
		 invalidate()로 제거하는게 좋습니다. */
		
		// 후 login 페이지로 보내기
		// response.sendRedirect("./logout.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("logout.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
		//System.out.println("post으로 들어왔어요");
	}

}
