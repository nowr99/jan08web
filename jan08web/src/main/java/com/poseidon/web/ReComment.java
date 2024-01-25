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

@WebServlet("/recomment")
public class ReComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReComment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	// 24.01.25 댓글 수정하기 서블릿
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("cno"));
		System.out.println(request.getParameter("comment"));
		
		HttpSession session = request.getSession();
	      int result = 0;
	      if (session.getAttribute("mid") != null 
	    		  && Util.intCheck2(request.getParameter("cno"))
	    		  && request.getParameter("comment") != null) {

	         CommentDTO dto = new CommentDTO();
	         dto.setMid((String) session.getAttribute("mid"));
	         dto.setCno(Util.str2Int2(request.getParameter("cno")));
	         dto.setComment(Util.addBR(request.getParameter("comment")));

	         CommentDAO dao = new CommentDAO();
	         result = dao.commentUpdate(dto);
	      }

	      PrintWriter pw = response.getWriter();
	      pw.print(result);
	
	}

}
