package it.corso.model;

import java.util.ArrayList;
import java.util.List;

public class Utente {

	private int id;
	private String utente,password;
	private List<Post> posts = new ArrayList<>();
	private List<Utente> users = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public List<Utente> getUsers() {
		return users;
	}
	public void setUsers(List<Utente> users) {
		this.users = users;
	}
	
	
	
	
	
}
