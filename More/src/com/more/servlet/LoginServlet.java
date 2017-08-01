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

import com.more.dao.Validate;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rs=null;

		String email = request.getParameter("email");
		String pass = request.getParameter("password");

		System.out.println("In LoginServlet: Email: "+email);
		
		if (Validate.checkUser(email, pass)) {
			
			HttpSession session=request.getSession();
			session.setAttribute("email",email);
			System.out.println("User Exists");
			
			if(Validate.checkRole(email, pass).equals("admin")) {
				//out.println(email+" Welcome");
				System.out.println("Admin has logged in");
				session.setAttribute("role","admin");
				rs = request.getRequestDispatcher("adminlogin.html");
				rs.include(request, response);
				out.println("Welcome to Admin Page: "+ email);
			} 
			
			else {
				out.println(email+" Welcome");
				session.setAttribute("role","user");
				System.out.println("Customer has logged in");
				rs = request.getRequestDispatcher("OpenUser");
				rs.include(request, response);
			}
			
		} 
		
		else {
			out.println("Username or Password incorrect");
			rs = request.getRequestDispatcher("login.html");
			rs.include(request, response);
		}

	}

}
