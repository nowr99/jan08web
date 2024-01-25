package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.CoffeeDAO;
import com.poseidon.dto.CoffeeDTO;

@WebServlet("/coffee")
public class Coffee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Coffee() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher rd = request.getRequestDispatcher("coffee.jsp");
	System.out.println("커피서블릿통과");
	rd.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글처리
		String name = request.getParameter("name");
		
		int result = 0;
		
		CoffeeDTO dto = new CoffeeDTO();
		dto.setName(name);
		
		CoffeeDAO dao = new CoffeeDAO();
		result = dao.update(dto);
		
		System.out.println(name + "님 저장되었습니다.");
		response.sendRedirect("./order");
	}
	
}	
