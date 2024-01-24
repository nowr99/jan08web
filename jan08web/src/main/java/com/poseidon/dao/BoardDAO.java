package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poseidon.db.DBConnection;
import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

public class BoardDAO extends AbstractDAO {

	public List<BoardDTO> boardList(int page) { // 게시판의 첫 화면
		List<BoardDTO> list = null; // null 처리하는 이유? 확실하게 만들어놓고 하기위해
		// DB정보
		DBConnection db = DBConnection.getInstance();
		// conn 객체
		Connection con = null;
		// pstmt
		PreparedStatement pstmt = null;
		// rs
		ResultSet rs = null;
		// sql
		String sql = "SELECT * FROM boardview LIMIT ?, 10";

		con = db.getConnection();

		try {
			pstmt = con.prepareStatement(sql);
			// pstmt.setInt(1, page * 10); 그냥 page * 10 하면 10개씩 나오긴 하지만 10번째부터 나옴
			pstmt.setInt(1, (page-1) * 10); // page에 -1을 하여 0부터 시작되게 만듬
			rs = pstmt.executeQuery(); // rs에 오는 결과를 받음
			list = new ArrayList<BoardDTO>(); // 위에서 null 처리했기 때문에 만들어야함

			while (rs.next()) {
				BoardDTO e = new BoardDTO();
				e.setNo(rs.getInt("board_no"));
				e.setTitle(rs.getString("board_title")); // 순서를 모를 땐 column명 쓰기!
				e.setWrite(rs.getString("board_write"));
				e.setDate(rs.getString("board_date"));
				e.setCount(rs.getInt("board_count")); // 번호 or column명으로 통일하는게 좋음
				e.setComment(rs.getInt("comment")); // comment 갯수 보기위해 추가 (24.01.19)
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con); // 하나씩 닫지말고 한번에 닫는 close 메소드 만들기 -> AbstractDAO에 만들어줬음
		}
		return list;
	}

	
	// 게시판 내용물 확인하기
	public BoardDTO detail(int no) { 
		BoardDTO dto = new BoardDTO();
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT b.board_no, b.board_title, b.board_content, m.mname as board_write, m.mid, "
	            + "b.board_date, b.board_ip, (SELECT COUNT(*) FROM visitcount WHERE board_no=b.board_no) AS board_count "
	            + "FROM board b JOIN member m ON b.mno=m.mno "
	            + "WHERE b.board_no=?";
		//countUp(no); // 게시물 들어갈때 마다 조회수 늘어나게 -> 지금은 안씀
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 하나밖에 안나와서 while 대신 if 써도 됨
				dto.setNo(rs.getInt("board_no"));
				dto.setTitle(rs.getString("board_title"));
				dto.setContent(rs.getString("board_content"));
				dto.setWrite(rs.getString("board_write"));
				dto.setDate(rs.getString("board_date"));
				dto.setIp(rs.getString("board_ip"));
				dto.setCount(rs.getInt("board_count"));
				dto.setMid(rs.getString("mid"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	// 글 작성하기 (24.01.23 ip정보 저장도 추가)
	public int write(BoardDTO dto) {
		int result = 0; // 글을 작성하다가 문제가 있으면 0으로 리턴
		
		// DB에 접속해서 저장해야되니까
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board (board_title, board_content, mno, board_ip) "
				+ "VALUES(?,?,(SELECT mno FROM member WHERE mid=?), ?)";
										// mid값을 구해서 mno로 넣는 서브쿼리
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getMid()); // 01.15 "이대역"에서 수정완료
			pstmt.setString(4, dto.getIp());
			result = pstmt.executeUpdate(); // 영향받은 행을 result에 저장
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return result;
	}

	// 게시글 삭제기능이었으나 UPDATE로 1을 0으로 바꿔줌 -> 따로 보관하고 보이지않게 하기
	public int delete(BoardDTO dto) {
		int result = 0;
		// con
		Connection con = DBConnection.getInstance().getConnection();
		// pstmt
		PreparedStatement pstmt = null;
		// sql (board_no와 mno가 일치해야 삭제가능하게 만드는 쿼리. url로 접속해도 안지워짐)
		//String sql = "DELETE FROM board WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)"; 업데이트로 바꿀꺼야
		String sql = "UPDATE board SET board_del='0' WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			pstmt.setString(2, dto.getMid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		return result;
	}

	public int update(BoardDTO dto) {
		int result = 0;
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_title=?, board_content=? "
				+ "WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNo());
			pstmt.setString(4, dto.getMid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close (null, pstmt, con);
		}
		
		return result;
	}


	public int totalCount() {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM boardview"; //1인 애들만 모였으므로 board대신 
													   // boardview로 수정 24.01.19
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1); // column 값이 하나라서
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return result;
	}
	
		public void countUp(int no, String mid) { // 조회수 업데이트
			Connection con = db.getConnection();
			PreparedStatement pstmt = null; 
			ResultSet rs =null;	// 해당 mid가 ?번째 글을 읽은횟수를 0이나 1로 출력하게하기
			String sql = "SELECT count(*) FROM visitcount WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)"; 
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.setString(2, mid);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					int result = rs.getInt(1);
					if(result == 0) { // 본적이 없으므로 0이면 realCountUp실행
						realCountUp(no, mid); 
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(null, pstmt, con);
			}
		}


		private void realCountUp(int no, String mid) { // 진짜 조회수 올리기
			Connection con = db.getConnection();
			PreparedStatement pstmt = null;
			String sql = "INSERT INTO visitcount(board_no, mno) VALUES(?, (SELECT mno FROM member WHERE mid=?))";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.setString(2, mid);
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(null, pstmt, con);
			}
		}


		public List<CommentDTO> commentList(int no) { // 댓글 불러오기 List
			List<CommentDTO> list = new ArrayList<CommentDTO>();
			Connection con = db.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM commentview WHERE board_no=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					CommentDTO dto = new CommentDTO();
					dto.setCno(rs.getInt("cno"));
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setComment(rs.getString("ccomment"));
					dto.setCdate(rs.getString("cdate"));
					dto.setMno(rs.getInt("mno")); // 뷰 만들어서 mname, mid 가져오기
					dto.setMid(rs.getString("mid"));
					dto.setMname(rs.getString("mname"));
					dto.setClike(rs.getInt("clike"));
					dto.setIp(Util.ipMasking(rs.getString("cip")));
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs, pstmt, con);
			}
			return list;
		}
}