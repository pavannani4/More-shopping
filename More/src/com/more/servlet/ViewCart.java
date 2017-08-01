package com.more.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.more.dao.CartDAO;
import com.more.model.CartProduct;

@WebServlet("/Cart")
public class ViewCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String uid = (String) session.getAttribute("email");
		String role = (String) session.getAttribute("role");
		System.out.println("====================================================");
		System.out.println("View cart Session email: " + uid + " role: " + role);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = null;
		/* out.println("In get method of product update"); */
		System.out.println("In Customer afterlogin page");
		out.println("<h1>Product List</h1>");

		dispatcher = request.getRequestDispatcher("customerlogin.html");
		dispatcher.include(request, response);
		List<CartProduct> list = CartDAO.getAllCartProducts(uid);

		out.print("<form action='RemProCar' method='post'><table border='1' width='80%' align='center'>");
		out.print("<tr><th align='center'>ProductName</th><th align='center'>ProductPrice</th><th align='center'>Quantity</th><th align='center'>Remove<th align='center'>Total Price</th></tr>");
		for (CartProduct pro : list) {
			out.print("<tr><td align='center'>" + pro.getName() + "</td><td align='center'>" + pro.getPrice() + "</td><td align='center'>" + pro.getQuantity()
					+ "</td><td align='center'><a href='RemProCar?id="+pro.getProductId()+"'>Remove</a></td><td align='center'>" + pro.getTotalPrice() + "</td></tr>");
		}
		out.print("<tr><td colspan='4' align='center'>Total Price</td><td align='center'>" + CartDAO.total(uid) + "</td></tr>");
		out.print(
				"<tr><td colspan='4' align='center'>Total Price + GST</td><td align='center'>" + CartDAO.gstTotal(uid) + "</td></tr>");
		out.print("</table>");
		
		out.println("<input type='submit' value=submit></form>");

		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
