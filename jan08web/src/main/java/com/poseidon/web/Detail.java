package com.poseidon.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dao.LogDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

@WebServlet("/detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Detail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 오븐 no 잡기
		//int no = Integer.parseInt(request.getParameter("no"));
		int no = Util.str2Int(request.getParameter("no"));
		
		//24.01.23 log
		//LogDAO log = new LogDAO();
		
		
		
		
		
		// 데이터베잉스에 질의하기
		BoardDAO dao = new BoardDAO();
		dao.logWrite(Util.getIP(request), "./detail", "no="+no); // 로그쓰는 write
		
		
		
		// 2024.01.19 프레임워크 프로그래밍 해야되는데ㅠㅠ
		// 로그인 한 회원이라면 읽음 수 올리기
		// bno, mno
		HttpSession session = request.getSession(); //외워야함 이거
		if(session.getAttribute("mid") != null) {//로그인 했어? 라고 확인
			dao.countUp(no, (String)session.getAttribute("mid"));
		}
		BoardDTO dto = dao.detail(no);
		
		
		//System.out.println(dto.getTitle());
		//System.out.println(dto.getContent() == null);
		if(no == 0 || dto.getContent() == null) {
			// null 이면 에러로
			response.sendRedirect("./error.jsp");
		} else {
			// 정상출력
			// 내용 가져오기
			request.setAttribute("detail", dto);
			
			// 24.01.22 댓글이 있다면 List 뽑아내기 추가
			List<CommentDTO> commentList = dao.commentList(no); // 보드 번호 들어와야해
			
			if(commentList.size() > 0) { // 코멘트가 있을때만 붙이기
				request.setAttribute("commentList", commentList);
			}
			
			// requestDispatcher 호출하기
			RequestDispatcher rd = request.getRequestDispatcher("./detail.jsp");
			
			rd.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
