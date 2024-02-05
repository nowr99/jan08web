package com.poseidon.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.CommentDAO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

@WebServlet("/commentDel")
public class CommentDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentDel() {
        super();
    }

    // 댓글 삭제하기 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("mid") != null && request.getParameter("no") != null 
				&& Util.intCheck(request.getParameter("cno"))){
		
		// System.out.println(request.getParameter("no"));
		// System.out.println(request.getParameter("cno"));
			
			CommentDTO dto = new CommentDTO();
			dto.setMid((String)session.getAttribute("mid"));
			dto.setCno(Util.str2Int(request.getParameter("cno")));
			dto.setBoard_no(Util.str2Int(request.getParameter("no")));
		
			CommentDAO dao = new CommentDAO();
			int result = dao.commentDelete(dto);
			if(result == 1) {
				response.sendRedirect("./detail?no="+request.getParameter("no"));
				System.out.println("2");
			} else {
				response.sendRedirect("./error.jsp");
			}	
	} else {
			response.sendRedirect("./error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 24.01.24 detail.jsp ajax에서 보내기
		
		//doGet꺼 가져왔어요
		int result = 0; // dataScope 때문에
		HttpSession session = request.getSession();
		if(session.getAttribute("mid") != null && Util.intCheck(request.getParameter("no"))){
		
			CommentDTO dto = new CommentDTO();
			dto.setMid((String)session.getAttribute("mid"));
			dto.setCno(Util.str2Int(request.getParameter("no")));
		
			CommentDAO dao = new CommentDAO();
			result = dao.commentDelete(dto);
			
		
		PrintWriter pw = response.getWriter();
		pw.print(result);
		System.out.println("1");
		}

	}
}
