package com.more.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.more.conUtil.ConnectionUtil;
import com.more.model.CartProduct;
import com.more.model.Product;

public class CartDAO {

	private static Connection con = ConnectionUtil.getDbCon();

	public static boolean addCart(String pid, String uid) {

		boolean flag = false;
		System.out.println("In CartDAO with productId: " + pid + " userId: " + uid);
		try {
			PreparedStatement ps1 = con.prepareStatement("select * from cart where productid=? and userid=? ");
			ps1.setString(1, pid);
			ps1.setString(2, uid);

			ResultSet rs = ps1.executeQuery();

			if (rs.next()) {
				PreparedStatement ps = con
						.prepareStatement("update cart set quantity=? where productid=? and userid=?");
				int i = rs.getInt("quantity");
				i++;
				ps.setInt(1, i);
				ps.setString(2, pid);
				ps.setString(3, uid);

				int y = ps.executeUpdate();
				System.out.println("quantity increased" + y);

				flag = true;
			} else {
				PreparedStatement ps = con
						.prepareStatement("insert into cart(productid,userid,quantity) values (?,?,?) ");
				ps.setString(1, pid);
				ps.setString(2, uid);
				ps.setInt(3, 1);
				int i = ps.executeUpdate();
				System.out.println("inserted into cart table" + i);
				flag = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;

	}

	public static List<CartProduct> getAllCartProducts(String uid) {

		System.out.println("in get Cart Products of user: " + uid);
		List<CartProduct> list = new ArrayList<CartProduct>();

		try {
			PreparedStatement ps = con.prepareStatement("select productid, quantity from cart where userid=?");
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();

			/*
			 * while(rs.next()) { System.out.println(rs.getString("productid"));
			 * System.out.println("Quantity: "+rs.getInt("quantity")); }
			 */
			// List proid=new ArrayList();

			while (rs.next()) {
				CartProduct cp = new CartProduct();

				String proid = rs.getString("productid");
				System.out.println("Product id " + proid);
				int i = rs.getInt("quantity");
				String name = null;
				String id = null;
				double price = 0;
				PreparedStatement ps1 = con.prepareStatement("select id, name, price from product where id=?");
				ps1.setString(1, proid);
				ResultSet rs1 = ps1.executeQuery();
				while (rs1.next()) {
					id = rs1.getString("id");
					name = rs1.getString("name");
					price = rs1.getDouble("price");
				}
				cp.setProductId(id);
				cp.setName(name);
				cp.setPrice(price);
				cp.setQuantity(i);
				list.add(cp);
				/*
				 * cp.setName(ProductDAO.getProductName(proid));
				 * cp.setPrice(ProductDAO.getProductPrice(proid)); cp.setQuantity(i);
				 * list.add(cp);
				 */
			}
			for (CartProduct cartProduct : list) {
				System.out.println(cartProduct);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static double total(String uid) {

		double d = 0;
		List<CartProduct> list = getAllCartProducts(uid);

		for (CartProduct pro : list) {
			d = d + pro.getTotalPrice();
		}
		return d;
	}

	public static double gstTotal(String uid) {

		double total = 0;
		double d = total(uid);
		System.out.println("Total cost: " + d);

		if (d < 3000) {
			total = ((15.0 / 100.0) * d) + d;
		} else if (d < 7000) {
			total = ((20.0 / 100.0) * d) + d;
		} else {
			total = ((25.0 / 100.0) * d) + d;
		}
		System.out.println("Total+Gst  " + total);
		return total;
	}

	public static boolean RemoveFromCart(String uid, String pid) {
		boolean flag = false;
		System.out.println("In CartDAO Remove method with productId: " + pid + " userId: " + uid);
		try {
			PreparedStatement ps1 = con.prepareStatement("select * from cart where productid=? and userid=? ");
			ps1.setString(1, pid);
			ps1.setString(2, uid);

			ResultSet rs = ps1.executeQuery();

			if (rs.next()) {

				int i = rs.getInt("quantity");

				if (i > 1) {
					PreparedStatement ps = con
							.prepareStatement("update cart set quantity=? where productid=? and userid=?");
					i = i - 1;
					ps.setInt(1, i);
					ps.setString(2, pid);
					ps.setString(3, uid);
					int y = ps.executeUpdate();
					System.out.println("quantity decreased: " + y);
					flag = true;
				} else {

					PreparedStatement ps = con.prepareStatement("delete from cart where productid=? and userid=?");
					ps.setString(1, pid);
					ps.setString(2, uid);
					int y = ps.executeUpdate();
					System.out.println("decreased product from Cart: " + y);
					flag = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean proceedToCheckout(String uid) {
		boolean flag = false;
		try {
			PreparedStatement ps1 = con.prepareStatement("delete from cart where userid=? ");
			ps1.setString(1, uid);

			int i = ps1.executeUpdate();

			if (i >= 1) {
				System.out.println("Checkout Successful");
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
