package com.more.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.more.conUtil.ConnectionUtil;
import com.more.model.User;

public class Register {

	private static Connection con = ConnectionUtil.getDbCon();

	public static boolean isValidRegister(String email) {

		System.out.println("Registration verifaction for:-- " + " Email: " + email);
		boolean flag = false;

		try {

			PreparedStatement ps = con.prepareStatement("select * from user where email=?");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			flag = rs.next();
			if (flag) {
				System.out.println(email + " Already exixts");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(email + " UserId is not in database");
		return true;
	}

	public static boolean register(User user) {

		boolean flag = false;
		String name = user.getName();
		String email = user.getEmail();
		String password = user.getPassword();
		String role = user.getRole();

		try {

			PreparedStatement ps = con.prepareStatement("insert into user values(?, ?, ?, ?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, role);
			int rs = ps.executeUpdate();

			if (rs == 1) {
				System.out.println(email + " Successfully Regestered");

				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(email + " Already exixts");
		return false;
	}

}
