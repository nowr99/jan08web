package com.poseidon.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.CommentDAO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

// 24-01-22
@WebServlet("/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Comment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(); // mid 불러오기 위해 session 연결
		
		if(request.getParameter("commentcontent") != null && request.getParameter("bno") != null && session.getAttribute("mid") != null ) {
			// 오는 값 받기
			String commentcontent = request.getParameter("commentcontent"); // 댓글내용
			
			// 24.01.23 오는 값에서 특수기호 <,>를 변경하기 및 줄바꿈처리하기
			commentcontent = Util.removeTag(commentcontent); // util에 removeTag 만듬
			// 엔터(줄바꿈)처리 /r /n /nr Util로 ㄱㄱ
			commentcontent = Util.addBR(commentcontent);
			
			String bno = request.getParameter("bno"); // 글번호
			// System.out.println(commentcontent + " : " + bno);
			
			// 저장해주세요
			CommentDTO dto = new CommentDTO();
			dto.setComment(commentcontent);
			dto.setBoard_no(Util.str2Int(bno)); // 유틸 만들어놓은거로 String -> int 변환
			dto.setMid((String)session.getAttribute("mid"));
			dto.setIp(Util.getIP(request));
			
			CommentDAO dao = new CommentDAO();
			dao.commentWrite(dto);
			
			// 이동해주세요.
			response.sendRedirect("./detail?no="+bno);
		} else {
			response.sendRedirect("./login");
		}
	}

}
