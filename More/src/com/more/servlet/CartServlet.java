package com.more.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.more.dao.CartDAO;
import com.more.model.Cart;

@WebServlet("/AddToCart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher rd = null;
		HttpSession session = req.getSession(false);
		String uid = (String) session.getAttribute("email");
		String role = (String) session.getAttribute("role");
		String id = req.getParameter("id");
		System.out.println("In Add to Cart, received product: " + id);
		System.out.println("Session email: " + uid + " role: " + role);

		Cart c = new Cart();

		c.setProductId(id);
		c.setUserId(uid);

		// c.setQuantity(quantity);

		if (CartDAO.addCart(id, uid)) {
			System.out.println("Successfully updated in cart table");
		} else {
			System.out.println("Add to cart failed");
		} 

		rd = req.getRequestDispatcher("OpenUser");

		rd.forward(req, resp);

	}
}
