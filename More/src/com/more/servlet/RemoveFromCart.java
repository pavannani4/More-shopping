package com.more.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.more.dao.CartDAO;
import com.more.model.Product;

@WebServlet("/RemProCar")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In Cart Remove Servlet");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rs = null;

		Product product = new Product();
		String pid = request.getParameter("id");
		System.out.println("==========In Remove method " + pid);
		HttpSession session = request.getSession(false);

		String uid = (String) session.getAttribute("email");
		String role = (String) session.getAttribute("role");
		System.out.println("Session role: " + role);

		if (CartDAO.RemoveFromCart(uid, pid)) {
			// out.println("")
			System.out.println("Succesfully Removed from cart: " + pid);
		} else {
			System.out.println("Failed to Remove from Cart");
		}

		rs = request.getRequestDispatcher("Cart");
		rs.include(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In RemoveCart Post method:");
		HttpSession session = req.getSession(false);
		String uid = (String) session.getAttribute("email");
		RequestDispatcher rs = null;
		PrintWriter out=resp.getWriter();
		
		if(CartDAO.proceedToCheckout(uid)) {
			rs = req.getRequestDispatcher("OpenUser");
			rs.include(req, resp);
			out.println("Checkout completed---> Thank you Shopping");
		}
		else {
			rs = req.getRequestDispatcher("OpenUser");
			rs.include(req, resp);
			out.println("No Products in cart--> Please add products");
		}
	}

}
