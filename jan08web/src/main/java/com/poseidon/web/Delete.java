package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;

@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 글 삭제하기 2024-01-11 + 2024-01-16 로그인 한 사용자 + 내글
		
		// 세션확인 (체크 순서 1번)
		HttpSession session = request.getSession(); 
		//no가 숫자야? (판별하는 util 생성) + 로그인 했나 확인까지 (2번)             
		if(Util.intCheck(request.getParameter("no")) && session.getAttribute("mid") != null) {
			// 숫자면 여기 -> 삭제 진행 -> board 이동
			// 번호잡기 (Util 에서 가져옴)
			int no = Util.str2Int(request.getParameter("no"));
			
			// DAO에게 일 시키기
			BoardDAO dao = new BoardDAO();
			
			// board_no, mid가 같이 있는 클래스는? boardDTO
			BoardDTO dto = new BoardDTO();
			dto.setNo(no);
			dto.setMid((String)session.getAttribute("mid"));
			
			int result = dao.delete(dto);
			
			// 잘 삭제되었는지 값 받기
			System.out.println("삭제됬나요? : " + result);
			if(result == 1) {
				// 값이 1이면 어디로 (삭제됨)
				response.sendRedirect("./board");
			} else {
				// 값이 0이면 어디로 (삭제안됨)
				response.sendRedirect("./error.jsp");
			}
			
			System.out.println("숫자입니다.");
		} else {
			// 아니면 여기 -> 에러표시
			System.out.println("문자입니다.");
			response.sendRedirect("./error.jsp");
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
