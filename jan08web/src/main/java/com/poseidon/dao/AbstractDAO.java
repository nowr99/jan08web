package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poseidon.db.DBConnection;

// 상속할 부모 DAO = DBConn, close
public class AbstractDAO {
	
	// DBConn
	DBConnection db = DBConnection.getInstance();
	
	// 24.01.23 LogDAO에서 가져왔습니다
	public void logWrite(String ip, String url, String data) {
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO iplog (iip, iurl, idata) VALUES (?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ip);
			pstmt.setString(2, url);
			pstmt.setString(3, data);
			pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
	}
	
	
	
	
	// close 메소드 생성 (protected 지워야 접근 가능 = default)
	 void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if (rs != null) { // rs가 not null 이면? = rs에 객체가 있으면?
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	} if (pstmt != null) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} if (con != null) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
}
