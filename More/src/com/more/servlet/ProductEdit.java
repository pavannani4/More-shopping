package com.more.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.more.dao.ProductDAO;
import com.more.model.Product;


@WebServlet("/ProEdit")
public class ProductEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher disparcher = null;
		out.println("In get method of product edit");
		Product product = new Product();
		String id=request.getParameter("id");
		out.println("   Product id:  "+id);
		
		product=ProductDAO.getProductById(id);
		System.out.println("Product to be edited: "+product);
		out.println("<form action='SaveProduct' method='post'>");
		out.println("<input type='hidden' name='id' value='"+ product.getId()+"'/><br>");
		out.println("<input type='text' name='name' value='"+product.getName()+"'/><br>");
		out.println("<input type='text' name='desc' value='"+product.getDescreption()+"'/><br>");
		out.println("<input type='text' name='price' value='"+product.getPrice()+"'/><br>");
		out.println("<input type='submit' value='submit'/><br>");
		out.println("</form>");
		
	}

}
