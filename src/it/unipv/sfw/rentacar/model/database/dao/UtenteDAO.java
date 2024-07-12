package it.unipv.sfw.rentacar.model.database.dao;

import it.unipv.sfw.rentacar.model.utenti.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.unipv.sfw.rentacar.model.database.DatabaseConnection;
import it.unipv.sfw.rentacar.model.utenti.Amministratore;

public class UtenteDAO {

	public UtenteDAO() {
		
	}
	
	public void aggiungiCliente(Cliente c) throws SQLException {
		String query = "INSERT INTO utente VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getCognome());
			stmt.setString(3, c.getUsername());
			stmt.setString(4, c.getPassword());
			stmt.setString(5, "CLIENTE");
			stmt.setString(6, c.getPatente().getNumero());
			stmt.setString(7, c.getPatente().getScadenza().toString());
			stmt.setString(8, c.getPatente().getCategorie()[0]);
			if (c.getPatente().getCategorie()[1] != null )
				stmt.setString(9, c.getPatente().getCategorie()[1]);
			else 
				stmt.setString(9, null);
			if (c.getPatente().getCategorie()[2] != null ) 
				stmt.setString(10, c.getPatente().getCategorie()[2]);
			else
				stmt.setString(10, null);
		} catch (SQLException e) {
			if(e.getErrorCode() == 1062) {
				System.out.println("Username gia in uso: " + c.getUsername() + " -> Impossibile aggiungere");
			} else {
				throw e;
            }
		}
	}
	
	public void aggiungiAmministratore(Amministratore a) throws SQLException {
		String query = "INSERT INTO utente (username, password, nome, cognome, ruolo) VALUES (?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, a.getUsername());
			stmt.setString(2, a.getPassword());
			stmt.setString(3, a.getNome());
			stmt.setString(4, a.getCognome());
			stmt.setString(5, "AMMINISTRATORE");
			stmt.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode() == 1062) {
				System.out.println("Username gia in uso: " + a.getUsername() + " -> Impossibile aggiungere");
			} else {
				throw e;
            }
		}
	}
	
	public void cancellaCliente(Cliente c) throws SQLException {
		String query = "DELETE FROM utente WHERE username = ? AND nome = ? AND cognome = ?";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, c.getUsername());
			stmt.setString(2, c.getNome());
			stmt.setString(2, c.getCognome());
			stmt.executeUpdate();
		}
	}
	/*
	public void aggiornaPassword(Cliente c) throws SQLException {
		String query = "UPDATE utente SET password = ? WHERE username = ?";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, c.());
			stmt.setString(2, c.getNome());
			stmt.setString(2, c.getCognome());
			stmt.executeUpdate();
		}
	}*/

	
}
