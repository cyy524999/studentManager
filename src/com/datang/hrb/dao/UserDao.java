package com.datang.hrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.*;
public class UserDao {
	public String getUsersByUsername(String username) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		String password =null;
		try {
			ps = conn.prepareStatement("select * from user where username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			password=rs.getString(2);
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return password;

	}
}
