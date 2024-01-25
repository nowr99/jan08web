package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.poseidon.dto.CoffeeDTO;

public class CoffeeDAO extends AbstractDAO {

	public int update(CoffeeDTO dto) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO coffee (name) VALUES (?)";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return result;
	}

}
