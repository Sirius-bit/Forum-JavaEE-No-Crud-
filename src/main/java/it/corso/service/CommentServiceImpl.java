package it.corso.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.corso.model.Commento;
import it.corso.model.Post;
import it.corso.model.Utente;

public class CommentServiceImpl implements CommentService {
	
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
	public Commento createComment(Commento commento) {
		Connection conn = getConnection();
		if(conn == null)
			return null;
		String sql = "INSERT INTO commenti (testo,post_id,utente_id) VALUES (?,?,?)";
		try(Connection connection = conn; PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.setString(1, commento.getTesto());
			preparedStatement.setInt(2, commento.getPost().getId());
			preparedStatement.setInt(3, commento.getUtente().getId());
			preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			return null;
		}
		return null;
	}

	@Override
	public List<Commento> getComments() {
		Connection conn = getConnection();
		if(conn == null)
			return null;
		String sql = "SELECT * FROM commenti";
		try(Connection connection = conn;
				Statement Statement = connection.createStatement();
				ResultSet resultSet = Statement.executeQuery(sql)) {

			List<Commento> commenti = new ArrayList<>();
			while(resultSet.next()) {
				Commento commento = new Commento();
				commento.setId(resultSet.getInt("id"));
				commento.setTesto(resultSet.getString("testo"));
			}
			return commenti;
		}
		catch (SQLException e) {
			return null;
		}
	}

	@Override
	public void createCommento(String commento, int post_id, int utente_id) {
		Connection conn  = getConnection();
		if(conn == null)
			System.out.println("Impossibile connettersi al database");
		String sql = "INSERT INTO commenti (testo, post_id, utente_id) VALUES (?,?,?)";
		try(Connection connection = conn; PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.setString(1, commento);
			preparedStatement.setInt(2, post_id);
			preparedStatement.setInt(3,utente_id);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


	@Override
	public List<Commento> getCommentiByPost(int postId) {
	    List<Commento> commenti = new ArrayList<>();
	    String sql = "SELECT * FROM commenti WHERE post_id = ?";
	    
	    try (Connection conn = getConnection();
	         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
	        preparedStatement.setInt(1, postId);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
	            Commento commento = new Commento();
	            commento.setId(resultSet.getInt("id"));
	            commento.setTesto(resultSet.getString("testo"));
	            
	            Post post = new Post();
	            post.setId(resultSet.getInt("id"));
	            commento.setPost(post);
	            
	            Utente utente = new Utente();
	            utente.setId(resultSet.getInt("id"));
	            commento.setUtente(utente);
	            
	            commenti.add(commento);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return commenti;
	}
}
