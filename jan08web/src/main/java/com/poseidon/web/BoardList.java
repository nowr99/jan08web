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

import com.poseidon.dao.BoardDAO;
import com.poseidon.dao.LogDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;

@WebServlet("/board")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// 페이징 파라미터(웹에서 주소창) 잡기
		int page = 1;
		if(request.getParameter("page") != null && request.getParameter("page")!= "") {
			page = Util.str2Int2(request.getParameter("page"));
		}
		
		//24.01.23 log
		Map<String, Object> log = new HashMap<String, Object>();
		log.put("ip", Util.getIP(request));
		log.put("url", "./board");
		log.put("data", "page="+page );
		LogDAO logDAO = new LogDAO();
		logDAO.write(log);
		
		
		
		// DAO랑 연결
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list = dao.boardList(page);
		int totalCount = dao.totalCount();
		
		System.out.println("서블릿 통과");
		
		request.setAttribute("list", list); // set 값 저장
		request.setAttribute("totalCount", totalCount); 
		request.setAttribute("page", page);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("board.jsp");
		// dispatcher은 url은 고정, 화면만 이동
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
