package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.LogDAO;
import com.poseidon.util.Util;

@WebServlet("/index") // /index를 치면 -> index.jsp로 이동시켜줌
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Index() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.sendRedirect("index.jsp"); 금지됨
		// 오픈방식. 누구나 다 접속 할 수 있음 ex) 신문기사, 홍보사이트
		// 노출시켜서 보내
		
		//24.01.23 index log추적
		LogDAO log = new LogDAO();
		log.logWrite(Util.getIP(request), "./index", null);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 개인정보들. 나만 봐야할 때 url의 공개 없이 숨겨서 보내줌 ex) 로그인정보, 내가 글 쓰는 상황
		// 헤더에 숨김(꺼내보기는 가능하다)
		// 숨겨서 보내
	}

}
