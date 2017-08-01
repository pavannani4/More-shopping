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

import com.more.dao.ProductDAO;
import com.more.model.Product;

@WebServlet("/Product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rs = null;
		Product prop = new Product();

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		double price = Double.parseDouble(request.getParameter("price"));

		System.out.println("In ProductServlet: ProductId: " + id + " name: " + name + " Price: " + price);

		prop.setId(id);
		prop.setName(name);
		prop.setDescreption(desc);
		prop.setPrice(price);

		HttpSession session = request.getSession(false);
		String role = (String) session.getAttribute("role");
		System.out.println("Session role: " + role);

		if (role.equals("admin")) {
			if (ProductDAO.validate(id)) {
				if (ProductDAO.addProduct(prop)) {
					System.out.println("Product added Successfully");
					rs = request.getRequestDispatcher("adminlogin.html");
					out.println("Product added");
					rs.include(request, response);
 
				}
			} else {
				rs = request.getRequestDispatcher("adminlogin.html");
				out.println("product already exist");
				rs.include(request, response);
			}
		} else {
			out.println("Please login as admin");
			rs = request.getRequestDispatcher("logout");
			rs.include(request, response);
			
		}
	}
}
