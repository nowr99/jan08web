package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class LogDAO extends AbstractDAO {

	// AbstractDAO로 옮겼습니다 (String형태 write)

	public void write(Map<String, Object> log) {
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO iplog (iip, iurl, idata) VALUES (?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, (String)log.get("ip"));
			pstmt.setString(2, (String)log.get("url"));
			pstmt.setString(3, (String)log.get("data"));
			pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
	}
}


