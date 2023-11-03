package it.corso.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.corso.model.Post;
import it.corso.model.Utente;
import jakarta.servlet.http.HttpSession;

public  class PostServiceImpl implements PostService {
	
	private Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			return connection;
		} catch (Exception e) {
			return null;
		}
	}
	

	@Override
	public String createPost(HttpSession session,String ...dati) {
		Connection conn = getConnection();
		if(conn == null)
			return "Impossibile connettersi al database";
		String sql = "INSERT INTO post (titolo_post,descrizione_post,utente_id) VALUES (?,?,?)";
		try(Connection connection = conn; PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.setString(1, dati[0]);
			preparedStatement.setString(2, dati[1]);
			preparedStatement.setString(3,dati[2]);
			preparedStatement.executeUpdate();
			
			return "Post pubblicato correttamente";
			
		} catch (SQLException e) {
			return "Registrazione non riuscita";
		}
	}

	@Override
	public List<Post> getPosts() {
		Connection conn = getConnection();
		if(conn == null)
			return null;
		String sql = "SELECT post.id, post.titolo_post, post.descrizione_post, utente.id, utente.nome FROM post JOIN utente ON post.utente_id = utente.id";
		try(Connection connection = conn;
			Statement Statement = connection.createStatement();
			ResultSet resultSet = Statement.executeQuery(sql)) {
			
			List<Post> posts = new ArrayList<>();
			while(resultSet.next()) {
			    Post post = new Post();
			    post.setId(resultSet.getInt("id"));
			    post.setTitolo_post(resultSet.getString("titolo_post"));
			    post.setDescrizione(resultSet.getString("descrizione_post"));

			    Utente utente = new Utente();
			    utente.setId(resultSet.getInt("id"));
			    utente.setUtente(resultSet.getString("nome"));
			    post.setUtente(utente);

			    posts.add(post);
			}
			return posts;
		} catch (SQLException e) {
			return null;
		}
		
	}

	@Override
	public Post getPostById(String id,HttpSession session) {
		Connection conn = getConnection();
		if(conn == null)
			return null;
		String sql = "SELECT * FROM post WHERE id=?";
		try(Connection connection = conn; PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			

	        if (resultSet.next()) {
	            Post post = new Post();
	            post.setId(resultSet.getInt("id"));
	            post.setTitolo_post(resultSet.getString("titolo_post"));
	            post.setDescrizione(resultSet.getString("descrizione_post"));
	            post.setId(resultSet.getInt("utente_id"));

	            Utente utente = getUtenteById(post.getId(), connection);
	            post.setUtente(utente);

	            session.setAttribute("post", post);
	            return post;
	        }
	        resultSet.close();
	        return null;
	    } catch (SQLException e) {
	        return null;
	    }
	}

	private Utente getUtenteById(int userId, Connection connection) throws SQLException {
	    String sql = "SELECT * FROM utente WHERE id = ?";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, userId);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            Utente utente = new Utente();
	            utente.setId(resultSet.getInt("id"));
	            utente.setUtente(resultSet.getString("nome"));

	            return utente;
	        }
	        return null;
	    }
	}
}
