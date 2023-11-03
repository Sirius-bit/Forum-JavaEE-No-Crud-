package it.corso.service;

import java.util.List;
import it.corso.model.Post;
import jakarta.servlet.http.HttpSession;

public interface PostService {
	
	String URL = "jdbc:mysql://localhost/forum";
	String USERNAME = "root";
	String PASSWORD = null;
	
	
	List<Post> getPosts();
	Post getPostById(String id,HttpSession session);
	String createPost(HttpSession session,String... dati);
}
