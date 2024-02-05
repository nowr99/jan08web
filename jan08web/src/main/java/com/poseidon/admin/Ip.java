package com.poseidon.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.AdminDAO;

@WebServlet("/admin/ip")
public class Ip extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ip() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("ip"));
		
		AdminDAO dao = new AdminDAO();
		//List<Map<String, Object>> list1 = dao.iplist1(); // 중복없이 ip 뽑기
		//List<Map<String, Object>> list2 = dao.iplist2(); // 최다 접속 ip 5개 뽑기
		request.setAttribute("list1", dao.iplist1());
		request.setAttribute("list2", dao.iplist2());
		
		List<Map<String, Object>> list = null; // dto대신 맵이 들어간거
		
		if(request.getParameter("ip") != null && !request.getParameter("ip").equals("")) {
			list = dao.iplist(request.getParameter("ip"));
		} else {
			list = dao.iplist();
		}
		
		request.setAttribute("list", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/ip.jsp"); // 파일 경로
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
