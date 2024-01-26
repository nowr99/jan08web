package com.poseidon.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 24.01.26 admin은 admin로 패키지 따로 만들어주겠습니다.
@WebServlet("/admin/index") //url의 경로 = 실제 파일과 다르게 호출 할 url을 지정합니다.
public class AdminIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndex() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/admin/admin.jsp"); // 파일의 경로
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response); post로 들어온걸 doGet으로 다시 던진다는 말
	}

}
