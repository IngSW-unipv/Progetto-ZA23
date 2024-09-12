package it.unipv.sfw.rentacar.model.database.dao;

import it.unipv.sfw.rentacar.model.utenti.Cliente;
import it.unipv.sfw.rentacar.model.utenti.Utente;
import it.unipv.sfw.rentacar.model.utenti.documenti.Patente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.database.DatabaseConnection;
import it.unipv.sfw.rentacar.model.exception.CategoriaBPatenteException;
import it.unipv.sfw.rentacar.model.exception.NumeroPatenteInvalidoException;
import it.unipv.sfw.rentacar.model.exception.PatenteScadutaException;
import it.unipv.sfw.rentacar.model.exception.TargaNonValidaException;
import it.unipv.sfw.rentacar.model.exception.UsernameDuplicatoException;
import it.unipv.sfw.rentacar.model.utenti.Amministratore;

/*
 * Classe UtenteDAO
 */

public class UtenteDAO {

	public UtenteDAO() {
		
	}
	
	// Metodo di aggiunta di un cliente al DB
	
	public void aggiungiCliente(Cliente c) throws SQLException {
		String query = "INSERT INTO utente VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, c.getUsername());
			stmt.setString(2, c.getPassword());
			stmt.setString(3, c.getNome());
			stmt.setString(4, c.getCognome());
			stmt.setString(5, "CLIENTE");
			stmt.setString(6, c.getPatente().getNumero());
			stmt.setString(7, c.getPatente().getScadenza().toString());
			stmt.setString(8, c.getPatente().getCategorie()[0]);
			switch (c.getPatente().getCategorie().length) {
				case 2: 
					stmt.setString(9, c.getPatente().getCategorie()[1]);
					stmt.setString(10, null);
					break;
				case 3:
					stmt.setString(9, c.getPatente().getCategorie()[1]);
					stmt.setString(10, c.getPatente().getCategorie()[2]);
					break;
			default:
				stmt.setString(9, null);
				stmt.setString(10, null);
				break;
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode() == 1062) {
				System.err.println("Username gia in uso: " + c.getUsername() + " -> Impossibile aggiungere");
			} else {
				throw e;
            }
		}
	}
	
	// Metodo di aggiunta di un amministratore al DB
	
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
	
	// Metodo cancellazione di un cliente dal DB 
	
	public void cancellaCliente(Cliente c) throws SQLException {
		String query = "DELETE FROM utente WHERE username = ? AND nome = ? AND cognome = ?";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, c.getUsername());
			stmt.setString(2, c.getNome());
			stmt.setString(3, c.getCognome());
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("Errore nella cancellazione di un cliente");
		}
	}
	
	// Metodo aggiornamento della password di un cliente
	
	public void aggiornaPasswordCliente(Utente utente, String nuovaPassword) throws SQLException {
		String query = "UPDATE utente SET password = ? WHERE username = ?";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, nuovaPassword);
			stmt.setString(2, utente.getUsername());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Errore nell'aggiornamento della Password");
		}
		
	}
	
	// Metodo di lettura dei dati dal DB
	
	public ArrayList<Utente> letturaDati() throws TargaNonValidaException, NumeroPatenteInvalidoException, PatenteScadutaException, CategoriaBPatenteException{
		String query, username, password, nome, cognome, numero_patente, cat1, cat2, cat3, scadenza_patente, data_formattata;
		String[] categorie;
		ArrayList<Utente> utenti = new ArrayList<>();
		
		query = "SELECT username, password, nome, cognome, ruolo, numero_patente, scadenza_patente, categoria_patente1, categoria_patente2, categoria_patente3  FROM utente";
		
		try(Connection connection = DatabaseConnection.connessione();
				PreparedStatement stmt = connection.prepareStatement(query)) {
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				username = rs.getString("username");
				password = rs.getString("password");
				nome = rs.getString("nome");
				cognome = rs.getString("cognome");
				if (rs.getString("ruolo").equals("CLIENTE")) {
					numero_patente = rs.getString("numero_patente");
					DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Formato che arriva dal database
				    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato che desideri ottenere
				    scadenza_patente = rs.getString("scadenza_patente");
				    LocalDate scadenzaPatenteLocalDate = LocalDate.parse(scadenza_patente, inputFormatter);
				    data_formattata = scadenzaPatenteLocalDate.format(outputFormatter);
					cat1 = rs.getString("categoria_patente1");
					cat2 = rs.getString("categoria_patente2");
					cat3 = rs.getString("categoria_patente3");
					categorie = new String[3];
					categorie[0] = cat1;
					if (cat2 != null) {
						categorie[1] = cat2;
						if (cat3 != null) {
							categorie[2] = cat3;
						}
					}
					Patente p = new Patente(numero_patente, data_formattata, categorie);
					Cliente c = new Cliente(nome, cognome, username, password, p);
					
					utenti.add(c);
					
				}else {
					Amministratore a = new Amministratore(nome, cognome, username, password);
					utenti.add(a);
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utenti;
	}

	// Metodo di rinnovo della patente
	
	public void rinnovaPatente(Cliente c, LocalDate scadenzaPatente) {
		String query;
		
		query = "UPDATE utente SET scadenza_patente = ? WHERE username = ?";
		
		try (Connection connection = DatabaseConnection.connessione();
				PreparedStatement stmt = connection.prepareStatement(query)){
			
			stmt.setDate(1, java.sql.Date.valueOf(scadenzaPatente));
			stmt.setString(2, c.getUsername());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}	
	
}
