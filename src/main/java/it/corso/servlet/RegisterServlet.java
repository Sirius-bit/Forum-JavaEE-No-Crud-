package it.corso.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import it.corso.service.UsersService;
import it.corso.service.UsersServiceImpl;

@WebServlet("/")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    UsersService userFunc = new UsersServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext()
		.getRequestDispatcher("/WEB-INF/view/register.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String nome_inserito = request.getParameter("nome");
			String pass_inserita = request.getParameter("pass");
			userFunc.registerUser(nome_inserito,pass_inserita);
			response.sendRedirect("login");
	}
}
