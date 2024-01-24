package com.poseidon.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;

@WebServlet("/myInfo")
public class MyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyInfo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("mid") != null) {
			// mid를 데이터베이스에 질의
			MemberDTO dto = new MemberDTO();
			dto.setMid((String) session.getAttribute("mid"));

			MemberDAO dao = new MemberDAO();

			// DTO에 담아서
			dto = dao.myInfo(dto);

			// myInfo.jsp에 찍어주도록 하기
			request.setAttribute("myInfo", dto); // myInfo를 dto로 보내기
			
			// 24.01.23 myInfo 정보 찍어주기
			List<Map<String, Object>> readData = dao.readData(dto); // myinfo.jsp넘어가서 ${readData} 사용
			request.setAttribute("readData", readData);
			
			RequestDispatcher rd = request.getRequestDispatcher("myInfo.jsp");
			rd.forward(request, response);

		} else {
			response.sendRedirect("./login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); // 한글처리
		// 회원정보 수정하기
		HttpSession session = request.getSession();
		
		if(session.getAttribute("mid") != null) {
			MemberDTO dto = new MemberDTO();
			dto.setMpw(request.getParameter("newPW"));
			dto.setMid((String)session.getAttribute("mid"));
			
			System.out.println("바뀐 번호는 : " + request.getParameter("newPW"));
			MemberDAO dao = new MemberDAO();
			int result = dao.updateMpw(dto);
			System.out.println("수정 결과 + " + result);
			response.sendRedirect("./myInfo");
		} else {
			response.sendRedirect("./error");
		}
		
	}

}
