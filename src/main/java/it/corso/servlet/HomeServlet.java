package it.corso.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import it.corso.model.Commento;
import it.corso.model.Post;
import it.corso.model.Utente;
import it.corso.service.CommentService;
import it.corso.service.CommentServiceImpl;
import it.corso.service.PostService;
import it.corso.service.PostServiceImpl;

// localhost:8080/forum/home
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PostService postFunc = new PostServiceImpl();
	Post post = new Post();
	CommentService commFunc = new CommentServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    
	    Utente utente = (Utente) session.getAttribute("utente");
	    
	    if (utente != null) {
	        session.setAttribute("utente", utente);
	    }
	    
	    recuperaPostCommenti(session);
	    
	    request.getServletContext()
	        .getRequestDispatcher("/WEB-INF/view/home.jsp")
	        .forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.getAttribute("utente");
		doGet(request, response);
	}
	
	
	@SuppressWarnings("unchecked")
	private void recuperaPostCommenti(HttpSession session) {
	    if (session.getAttribute("ListaPost") == null) {
	        List<Post> posts = postFunc.getPosts();
	        for (Post post : posts) {
	            List<Commento> commenti = commFunc.getCommentiByPost(post.getId());
	            post.setCommenti(commenti);
	        }
	        session.setAttribute("ListaPost", posts);
	    } else {
	        List<Post> posts = (List<Post>) session.getAttribute("ListaPost");
	        for (Post post : posts) {
	            List<Commento> commenti = commFunc.getCommentiByPost(post.getId());
	            post.setCommenti(commenti);
	        }
	    }
	}
}
