package com.maxx;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//jstl -> JSP Standard tag Library 
		
		String name = "Maxx";
		
		List<Student> studs =Arrays.asList(new Student(1,"Maxx"),new Student(2,"Naveen"),new Student(3,"Ram"),new Student(4,"Sham"));
		
		request.setAttribute("students", studs);
		RequestDispatcher rd =request.getRequestDispatcher("display.jsp");
		rd.forward(request, response);
	}
}