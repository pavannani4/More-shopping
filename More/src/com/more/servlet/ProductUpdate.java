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

@WebServlet("/Proup")
public class ProductUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher disparcher = null;
		/*out.println("In get method of product update");*/
		System.out.println("In Admin Products Display");
		out.println("<h1>Product List</h1>");

		List<Product> list = ProductDAO.getProducts();

		out.print("<table border='1' width='100%'");
		out.print(
				"<tr><th>ProductId</th><th>ProductName</th><th>ProductDesc</th><th>Price</th><th>Edit</th><th>Delete</th></tr>");
		for (Product pro : list) {
			out.print("<tr><td>" + pro.getId() + "</td><td>" + pro.getName() + "</td><td>" + pro.getDescreption()
					+ "</td><td>" + pro.getPrice() + "</td><td><a href='ProEdit?id=" + pro.getId()
					+ "'>edit</a></td><td><a href='ProDelete?id=" + pro.getId() + "'>delete</a></td></tr>");
		}
		out.print("</table>");
		out.println("<a href=\"adminlogin.html\"><button type=\"button\" class=\"btn btn-primary active\">Back to AdminPage</button></a>");

		out.close();

		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher disparcher = null;
		/*out.println("In get method of product update");*/
		System.out.println("In Admin Products Display");
		out.println("<h1>Product List</h1>");

		List<Product> list = ProductDAO.getProducts();

		out.print("<table border='1' width='100%'");
		out.print(
				"<tr><th>ProductId</th><th>ProductName</th><th>ProductDesc</th><th>Price</th><th>Edit</th><th>Delete</th></tr>");
		for (Product pro : list) {
			out.print("<tr><td>" + pro.getId() + "</td><td>" + pro.getName() + "</td><td>" + pro.getDescreption()
					+ "</td><td>" + pro.getPrice() + "</td><td><a href='ProEdit?id=" + pro.getId()
					+ "'>edit</a></td><td><a href='ProDelete?id=" + pro.getId() + "'>delete</a></td></tr>");
		}
		out.print("</table>");

		out.close();

	}
}
