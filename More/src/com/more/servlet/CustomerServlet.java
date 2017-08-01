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

import com.more.dao.ProductDAO;
import com.more.model.Product;


@WebServlet("/OpenUser")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = null;
		/*out.println("In get method of product update");*/
		System.out.println("In Customer afterlogin page");
		out.println("<h1>Product List</h1>");

		dispatcher=request.getRequestDispatcher("customerlogin.html");
		dispatcher.include(request, response);
		List<Product> list = ProductDAO.getProducts();

		out.print("<table border='1' width='80%' align='center'>");
		out.print("<tr><th>ProductName</th><th>ProductDesc</th><th>Price</th><th>Add to Cart</th></tr>");
		for (Product pro : list) {
			out.print("<tr><td>" + pro.getName() + "</td><td>" + pro.getDescreption()
					+ "</td><td>" + pro.getPrice() + "</td><td><a href='AddToCart?id=" + pro.getId()
					+ "'>AddToCart</a></td></tr>");
		}
		out.print("</table>");

		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = null;
		/*out.println("In get method of product update");*/
		System.out.println("In Customer afterlogin page");
		out.println("<h1>Product List</h1>");

		dispatcher=request.getRequestDispatcher("customerlogin.html");
		dispatcher.include(request, response);
		List<Product> list = ProductDAO.getProducts();

		out.print("<table border='1' width='80%' align='center'>");
		out.print("<tr><th>ProductName</th><th>ProductDesc</th><th>Price</th><th>Add to Cart</th></tr>");
		for (Product pro : list) {
			out.print("<tr><td>" + pro.getName() + "</td><td>" + pro.getDescreption()
					+ "</td><td>" + pro.getPrice() + "</td><td><a href='AddToCart?id=" + pro.getId()
					+ "'>AddToCart</a></td></tr>");
		}
		out.print("</table>");

		out.close();
		
	}

}
