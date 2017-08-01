package com.more.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.more.conUtil.ConnectionUtil;

public class Validate {
	public static boolean checkUser(String email, String pass) {

		System.out.println("User verifaction for:-- " + " Email: " + email + " Password: " + pass);
		boolean flag = false;

		Connection con = ConnectionUtil.getDbCon();
		
		try {

			PreparedStatement ps = con.prepareStatement("select * from user where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			flag = rs.next();
			if (flag) {
				System.out.println(email + " welcome");
			} else {
				System.out.println(email + " User is not in database");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static String checkRole(String email, String pass) {

		System.out.println("Role verification for-- " + " Email: " + email + " Password: " + pass);
		String role = null;
		Connection con = ConnectionUtil.getDbCon();
		try {
			PreparedStatement ps = con.prepareStatement("select * from user where email=?");
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				role = rs.getString("role");
				System.out.println("role validate for: "+email+"   role: "+role);
				return role;

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}
}
