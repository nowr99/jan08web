package com.poseidon.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.AdminDAO;
import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;
import com.poseidon.util.Util;

@WebServlet("/admin/members")
public class Members extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Members() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// System.out.println(request.getParameter("grade"));
		
		AdminDAO dao = new AdminDAO();
		List<MemberDTO> list = null;  
		if(request.getParameter("grade") == null || request.getParameter("grade").equals("")){ // null이 아니면 이거
			list = dao.member();
		} else { // null이면 그냥 접속하게
			list = dao.member(Util.str2Int(request.getParameter("grade")));
		}
		
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/members.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		//System.out.println(request.getParameter("mno")); 넘어오는거 확인함
	//	System.out.println(request.getParameter("grade"));
		
		// db에 변경
		AdminDAO dao = new AdminDAO();
		int result = dao.gradeChange(Util.str2Int(request.getParameter("grade")), Util.str2Int(request.getParameter("mno")));
		System.out.println(result);
		
		// 페이지 이동
		if(request.getParameter("currentgrade") == null || request.getParameter("currentgrade").equals("")) {
			response.sendRedirect("./members");
		} else {
		response.sendRedirect("./members?grade=" + request.getParameter("currentgrade"));
	}
	}
}
