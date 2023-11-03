package it.corso.service;

import java.util.List;

import it.corso.model.Commento;

public interface CommentService {
	
	String URL = "jdbc:mysql://localhost/forum";
	String USERNAME = "root";
	String PASSWORD = null;

	Commento createComment(Commento commento);
	List<Commento> getComments();
	void createCommento(String commento, int post_id, int utente_id);
	List<Commento> getCommentiByPost(int id);
}
