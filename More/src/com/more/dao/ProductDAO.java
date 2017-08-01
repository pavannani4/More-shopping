package com.more.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.more.conUtil.ConnectionUtil;
import com.more.model.Product;

public class ProductDAO {

	private static Connection con = ConnectionUtil.getDbCon();

	public static boolean addProduct(Product product) {

		boolean flag = false;
		String id = product.getId();
		String name = product.getName();
		String desc = product.getDescreption();
		double price = product.getPrice();

		try {

			PreparedStatement ps = con.prepareStatement("insert into product values(?, ?, ?, ?)");
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, desc);
			ps.setDouble(4, price);
			int rs = ps.executeUpdate();

			if (rs == 1) {
				System.out.println(id + " Product Successfully added");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(id + " Failed to add Product");
		return false;

	}

	public static boolean editProduct(Product product) {
		boolean flag = false;
		PreparedStatement ps = null;
		try {
			String id = product.getId();
			String name = product.getName();
			String desc = product.getDescreption();
			Double price = product.getPrice();

			ps = con.prepareStatement("update product set name=?, descreption=?, price=? where id=?");

			ps.setString(1, name);
			ps.setString(2, desc);
			ps.setDouble(3, price);
			ps.setString(4, id);

			int i = ps.executeUpdate();

			if (i == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean deleteProduct(String id) {
		try {
			PreparedStatement ps = con.prepareStatement("delete from product where id=?");
			ps.setString(1, id);
			int i = ps.executeUpdate();
			if(i==1) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean validate(String id) {
		boolean flag = false;
		try {

			PreparedStatement ps = con.prepareStatement("select * from product where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			flag = rs.next();
			if (flag) {
				System.out.println(id + " exist");
				return false;
			} else {
				System.out.println(id + " Product is not in database, processed to update");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static List<Product> getProducts() {
		PreparedStatement ps = null;
		List<Product> list = new ArrayList<Product>();

		try {
			ps = con.prepareStatement("select * from product");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setDescreption(rs.getString("descreption"));
				product.setPrice(rs.getDouble("price"));
				System.out.println(product);
				list.add(product);
				// list.add(product);
			}

			System.out.println("In ProductDAO");
			for (Product product2 : list) {
				System.out.println(product2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public static Product getProductById(String id) {

		Product product = new Product();
		try {
			PreparedStatement ps = con.prepareStatement("select * from product where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setDescreption(rs.getString("descreption"));
				product.setPrice(rs.getDouble("price"));
				System.out.println("Get Product By id method: " + product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;
	}

	public static String getProductName(String id) {
		Product product = new Product();
		try {
			PreparedStatement ps = con.prepareStatement("select * from product where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setDescreption(rs.getString("descreption"));
				product.setPrice(rs.getDouble("price"));
				System.out.println("Get Product By id method: " + product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product.getName();
	}
	
	public static double getProductPrice(String id) {
		Product product = new Product();
		try {
			PreparedStatement ps = con.prepareStatement("select * from product where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setDescreption(rs.getString("descreption"));
				product.setPrice(rs.getDouble("price"));
				System.out.println("Get Product By id method: " + product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product.getPrice();
	}

}
