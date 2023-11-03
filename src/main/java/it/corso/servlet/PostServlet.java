package it.corso.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import it.corso.service.PostService;
import it.corso.service.PostServiceImpl;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostService postFunc = new PostServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	    String idUtente = request.getParameter("id");
	    session.setAttribute("idUtente", idUtente);
	    
	    request.getServletContext()
	        .getRequestDispatcher("/WEB-INF/view/post.jsp")
	        .forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    String titolo_inserito = request.getParameter("titolo_post");
	    String descrizione_inserita = request.getParameter("descrizione");
	    String idUtente = (String) session.getAttribute("idUtente");
	    System.out.println(idUtente);
	    postFunc.createPost(session, titolo_inserito, descrizione_inserita, idUtente);
	    session.setAttribute("ListaPost", postFunc.getPosts());
	    response.sendRedirect("home");
	}
}
