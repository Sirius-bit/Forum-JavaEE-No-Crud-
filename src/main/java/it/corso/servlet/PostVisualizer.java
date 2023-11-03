package it.corso.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import it.corso.service.CommentService;
import it.corso.service.CommentServiceImpl;
import it.corso.service.PostService;
import it.corso.service.PostServiceImpl;


@WebServlet("/postVisualizer")
public class PostVisualizer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PostService postFunc = new PostServiceImpl();
    CommentService commFunc = new CommentServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String utente_id = request.getParameter("idNome");
        String id = request.getParameter("id");
        postFunc.getPostById(id, session);

        session.setAttribute("post_id", id);
        session.setAttribute("idNome", utente_id);

        request.getServletContext().getRequestDispatcher("/WEB-INF/view/postVisualizer.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String commento = request.getParameter("commento");
        int post_id = Integer.parseInt((String) session.getAttribute("post_id"));
        int utente_id = Integer.parseInt((String) session.getAttribute("idNome"));
        commFunc.createCommento(commento, post_id, utente_id);
        response.sendRedirect("home");
    }
}

