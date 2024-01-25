package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.poseidon.dto.CommentDTO;

public class CommentDAO extends AbstractDAO {
	
	
	public int commentWrite(CommentDTO dto) { // 댓글쓰기
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "INSERT INTO comment (ccomment, board_no, mno, cip) VALUES (?, ?, (SELECT mno FROM member WHERE mid=?), ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getComment());
			pstmt.setInt(2, dto.getBoard_no());
			pstmt.setString(3, dto.getMid());
			pstmt.setString(4, dto.getIp());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return result;
	}

	public int commentDelete(CommentDTO dto) { // 댓글 삭제하기 (1에서 0으로 변경하여 옮기기)
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE comment SET cdel='0' WHERE cno=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getCno());
			pstmt.setString(2, dto.getMid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		return result;
	}
	
	// 24.01.25 댓글 수정하기
	public int commentUpdate(CommentDTO dto) {
		System.out.println(dto.getCno());
		System.out.println(dto.getMid());
		System.out.println(dto.getComment());
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE comment SET ccomment=? WHERE cno=? AND mno=(SELECT mno FROM member WHERE mid=?)"; 
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getComment());
			pstmt.setInt(2, dto.getCno());
			pstmt.setString(3, dto.getMid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		return result;
	}
}
