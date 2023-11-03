package it.corso.model;

import java.util.ArrayList;
import java.util.List;

public class Post {
	
	private int id;
	private String titolo_post,descrizione;
	private Utente utente;
	private List<Commento> commenti =  new ArrayList<>();
	
	public List<Commento> getCommenti() {
		return commenti;
	}
	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo_post() {
		return titolo_post;
	}
	public void setTitolo_post(String titolo_post) {
		this.titolo_post = titolo_post;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
