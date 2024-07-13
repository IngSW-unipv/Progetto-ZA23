package it.unipv.sfw.rentacar.model.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.unipv.sfw.rentacar.model.contratti.ContrattoNoleggio;
import it.unipv.sfw.rentacar.model.database.DatabaseConnection;

public class ContrattoNoleggioDAO {

	public ContrattoNoleggioDAO() {
		
	}
	
	public void aggiungiContratto(ContrattoNoleggio cn) throws SQLException {
		String stato_noleggio = "ATTIVO";
		String query = "INSERT INTO contratto_noleggio VALUES (?, ?, ?, ?, ?, ?, ?, ?,9)";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, cn.getIdContratto());
			stmt.setString(2, cn.getCliente().getNome());
			stmt.setString(3, cn.getCliente().getCognome());
			stmt.setString(4, cn.getCliente().getPatente().getNumero());
			stmt.setString(5, cn.getAuto().getTarga());
			stmt.setString(6, cn.getInizioNoleggio().toString());
			stmt.setString(7, cn.getFineNoleggio().toString());
			stmt.setDouble(8, cn.getImporto());
			stmt.setString(9, stato_noleggio);
			stmt.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				System.err.println("Errore id contratto");
			}else {
				
				throw e;
			}
		}
			
	}
	
}
