package it.corso.service;

import it.corso.model.Utente;
import jakarta.servlet.http.HttpSession;

public interface UsersService {
	
	String URL = "jdbc:mysql://localhost/forum";
	String USERNAME = "root";
	String PASSWORD = null;
	
	String registerUser(String... dati);
	Utente loginUser(HttpSession session, String... dati);

}
