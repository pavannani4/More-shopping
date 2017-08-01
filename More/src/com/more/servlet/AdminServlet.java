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

@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("In AdminServlet");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rs=null;
		String clickPro = null;
		String clickEmp = null;
		HttpSession session = request.getSession(false);
		String role = (String) session.getAttribute("role");
		System.out.println("Session role: " + role);

		clickPro = request.getParameter("product");
		clickEmp = request.getParameter("employee");

		if (clickPro != null) {
			System.out.println(clickPro);
			rs = request.getRequestDispatcher("adproducts.html");
			rs.include(request, response);
		}
		if (clickEmp != null) {
			System.out.println(clickEmp);
			rs = request.getRequestDispatcher("ademployee.html");
			rs.include(request, response);
		}
	}

}
