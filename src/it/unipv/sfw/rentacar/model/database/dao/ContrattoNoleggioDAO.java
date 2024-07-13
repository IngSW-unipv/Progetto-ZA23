package it.unipv.sfw.rentacar.model.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import it.unipv.sfw.rentacar.model.contratti.ContrattoNoleggio;
import it.unipv.sfw.rentacar.model.database.DatabaseConnection;

public class ContrattoNoleggioDAO {

	public ContrattoNoleggioDAO() {
		
	}
	
	public void aggiungiContratto(ContrattoNoleggio cn) throws SQLException {
		String stato_noleggio = "ATTIVO";
		String query = "INSERT INTO contratto_noleggio VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
	
	public void aggiornaStatoNoleggio() throws SQLException {
		String stato_noleggio = "TERMINATO";
		String query = "SELECT id_contratto, data_fine , stato_noleggio FROM contratto_noleggio WHERE data_fine < ?";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
			
		}
	}
	
	public void aggiornaStatoContratti() {
        String querySelect = "SELECT id_contratto, data_fine FROM contratto_noleggio WHERE stato_contratto != 'TERMINATO'";
        String queryUpdate = "UPDATE contratto_noleggio SET stato_noleggio = 'TERMINATO' WHERE id = ?";

        try (Connection connection = DatabaseConnection.connessione();
             PreparedStatement stmtSelect = connection.prepareStatement(querySelect);
             PreparedStatement stmtUpdate = connection.prepareStatement(queryUpdate)) {

            ResultSet rs = stmtSelect.executeQuery();
            LocalDate oggi = LocalDate.now();

            while (rs.next()) {
                int idContratto = rs.getInt("id_contratto");
                Date dataFine = rs.getDate("data_fine");
                
                if (dataFine != null) {
                    LocalDate dataFineLocal = dataFine.toLocalDate();
                    if (dataFineLocal.isBefore(oggi)) {
                        stmtUpdate.setInt(1, idContratto);
                        stmtUpdate.executeUpdate();
                    }
				}
            }

        } catch (SQLException e) {
            System.err.println("Errore nell'aggiornamento dello stato dei contratti: " + e.getMessage());
        }
    }

	
}
