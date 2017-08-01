package com.more.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.more.dao.Register;
import com.more.model.User;


@WebServlet("/Register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rs=null;
		User user=new User();
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String role="user";
		
		System.out.println("In RegistrationServlet: Email: "+email+" name: "+name);
		
		user.setName(name);
		user.setEmail(email);
		user.setPassword(pass);
		user.setRole(role);
		
		if(Register.isValidRegister(email)) {
			out.println("Valid user");
			if(Register.register(user)) {
				System.out.println("Registration Successful");
				rs=request.getRequestDispatcher("index.html");
				rs.include(request, response);
				
			}
			
		}
		
		
	}

}
