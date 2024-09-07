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

public class UtenteDAO {

	public UtenteDAO() {
		
	}
	
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
			stmt.setString(3, c.getCognome());
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("Errore nella cancellazione di un cliente");
		}
	}
	
	public void aggiornaPasswordCliente(Cliente c, String nuovaPassword) throws SQLException {
		String query = "UPDATE utente SET password = ? WHERE username = ?";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, nuovaPassword);
			stmt.setString(2, c.getUsername());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Errore nell'aggiornamento della Password");
		}
		
	}
	
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

	
	public static void main(String[] args) throws NumeroPatenteInvalidoException, PatenteScadutaException, CategoriaBPatenteException, SQLException, UsernameDuplicatoException, TargaNonValidaException {
		UtenteDAO dao = new UtenteDAO();
		
		AgenziaNoleggioAuto agenzia = AgenziaNoleggioAuto.getInstance("Rent-a-Car", "Via G. Mazzini, 17");
		
		agenzia.setElencoUtenti(dao.letturaDati()); 
		agenzia.stampaUtenti();
		/*
		String[] categorie1 = {"B"}
        Patente patente1 = new Patente("AB123456CC", "18/05/2025", categorie1);
        Cliente cliente1 = new Cliente("Mario", "Rossi", "mrossi", "password123", patente1);
        
        String[] categorie2 = {"B", "C"};
        Patente patente2 = new Patente("CD789012EF", "22/11/2026", categorie2);
        Cliente cliente2 = new Cliente("Luigi", "Verdi", "lverdi", "password456", patente2);
        
        String[] categorie3 = {"B", "C", "D"};
		Patente patente3 = new Patente("AB123456CC", "18/05/2025", categorie3);
		Cliente cliente3 = new Cliente("Roberto", "Pitorac" , "Pito", "password789", patente3);
		
        String[] categorie4 = {"B", "C", "D"};
		Patente patente4 = new Patente("AB123456CC", "18/05/2025", categorie4);
		Cliente cliente4 = new Cliente("Roberto", "Pitorac" , "Pitorac", "password789", patente4);
		
		Amministratore amm = new Amministratore("Roberto", "Pitorac" , "PitoSan", "Pitorac01");
		AgenziaNoleggioAuto agenzia = AgenziaNoleggioAuto.getInstance("Rent-a-Car", "Via G. Mazzini, 17");
		agenzia.aggiungiUtente(amm);
		agenzia.aggiungiUtente(cliente1);
		agenzia.aggiungiUtente(cliente2);
		agenzia.aggiungiUtente(cliente3);
		agenzia.aggiungiUtente(cliente4);
		dao.aggiungiAmministratore(amm);
        dao.aggiungiCliente(cliente1);
        dao.aggiungiCliente(cliente2);
        dao.aggiungiCliente(cliente3);	
        dao.aggiungiCliente(cliente4);	
        
        agenzia.stampaUtenti();
        
        cliente1.setPassword("pass45");
        dao.aggiornaPasswordCliente(cliente1, "pass45");
        agenzia.stampaUtenti();
        System.out.println("-----");
        //agenzia.eliminaUtente(cliente1);
        //dao.cancellaCliente(cliente1);
        System.out.println("-----");
        agenzia.stampaUtenti();
        System.out.println("-----");
        agenzia.eliminaUtente(cliente2);
        dao.cancellaCliente(cliente2);
        agenzia.stampaUtenti();
        
        System.out.println("Funziona");
        */
	}
	
}
