package it.corso.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import it.corso.model.Utente;
import it.corso.service.UsersService;
import it.corso.service.UsersServiceImpl;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	UsersService userFunc = new UsersServiceImpl();
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nome_inserito = request.getParameter("nome");
        String pass_inserita = request.getParameter("pass");
        Utente utente = userFunc.loginUser(session, nome_inserito, pass_inserita);
        
        if (utente != null) {
            createCookie(nome_inserito, response);
            session.setAttribute("utente", utente);
            response.sendRedirect("home");
        } 
        else 
            response.sendRedirect("login");
        
    }
	
	private void createCookie(String nome, HttpServletResponse response) {
		Cookie cookie = new Cookie("log", nome);
		cookie.setMaxAge(3600);
		cookie.setPath("/forum");
		response.addCookie(cookie);
	}
}
