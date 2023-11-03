package it.corso.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.corso.model.Utente;
import jakarta.servlet.http.HttpSession;

public class UsersServiceImpl implements UsersService {

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
	public String registerUser(String... dati) {
		Connection conn = getConnection();
		if(conn == null)
			return "Impossibile stabilire la connessione";
		String sql = "INSERT INTO utente (nome,password) VALUES (?,?)";
		
		try(Connection connection = conn; PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.setString(1, dati[0]);
			preparedStatement.setString(2, dati[1]);
			preparedStatement.executeUpdate();
			
			return "Utente registrato correttamente";
			
		} catch (SQLException e) {
			return "Connessione non stabilita";
		}
	}

	@Override
	public Utente loginUser(HttpSession session, String... dati) {
	    Connection conn = getConnection();
	    if (conn == null)
	        return null;
	    
	    String sql = "SELECT id, nome, password FROM utente WHERE nome = ? AND password = ?";

	    try (Connection connection = conn; PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	    	preparedStatement.setString(1, dati[0]); 
	    	preparedStatement.setString(2, dati[1]);

	    	try (ResultSet resultSet = preparedStatement.executeQuery()) {
	    		if (resultSet.next()) {
	    			Utente utente = new Utente();
	    			utente.setId(resultSet.getInt("id"));
	    			utente.setUtente(resultSet.getString("nome"));
	    			utente.setPassword(resultSet.getString("password"));
	    			return utente;
	    		}
	    		resultSet.close();
	    		return null;
	    	} catch (SQLException e) {
	    		System.out.println(e.getMessage());
	    		return null;
	    	}
	    } catch (SQLException e1) {
			System.out.println(e1.getMessage());
			return null;
		}
	}
}

