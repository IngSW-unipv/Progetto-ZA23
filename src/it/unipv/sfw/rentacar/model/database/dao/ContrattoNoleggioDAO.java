package it.unipv.sfw.rentacar.model.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.contratti.ContrattoNoleggio;
import it.unipv.sfw.rentacar.model.contratti.pagamenti.CartaDiCredito;
import it.unipv.sfw.rentacar.model.database.DatabaseConnection;
import it.unipv.sfw.rentacar.model.exception.CartaDiCreditoScadutaException;
import it.unipv.sfw.rentacar.model.exception.CategoriaBPatenteException;
import it.unipv.sfw.rentacar.model.exception.NumeroPatenteInvalidoException;
import it.unipv.sfw.rentacar.model.exception.PatenteScadutaException;
import it.unipv.sfw.rentacar.model.exception.TargaNonValidaException;
import it.unipv.sfw.rentacar.model.utenti.Cliente;
import it.unipv.sfw.rentacar.model.utenti.documenti.Patente;
import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.Cambio;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.CaratteristicheTecniche;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.Carburante;

/*
 * Classe ContrattoNoleggioDAO
 */

public class ContrattoNoleggioDAO {

	public ContrattoNoleggioDAO() {
		
	}
	
	// Metodo di aggiunta di un contratto al DV
	
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
			aggiornaStatoContratti();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				System.err.println("Errore id contratto");
			}else {
				throw e;
			}
		}
			
	}
	
	// Metodo di aggiornamento dello stato dei contratti
	
	public void aggiornaStatoContratti() {
        String querySelect = "SELECT id_contratto, data_fine FROM contratto_noleggio WHERE stato_contratto != 'TERMINATO'";
        String queryUpdate = "UPDATE contratto_noleggio SET stato_noleggio = 'TERMINATO' WHERE id = ?";

        try (Connection connection = DatabaseConnection.connessione();
             PreparedStatement stmtSelect = connection.prepareStatement(querySelect);
             PreparedStatement stmtUpdate = connection.prepareStatement(queryUpdate)) {

            ResultSet rs = stmtSelect.executeQuery();
            LocalDate oggi = LocalDate.now();

            while (rs.next()) {
                String idContratto = rs.getString("id_contratto");
                Date dataFine = rs.getDate("data_fine");
                
                if (dataFine != null) {
                    LocalDate dataFineLocal = dataFine.toLocalDate();
                    if (dataFineLocal.isBefore(oggi)) {
                        stmtUpdate.setString(1, idContratto);
                        stmtUpdate.executeUpdate();
                    }
				}
            }

        } catch (SQLException e) {
            System.err.println("Errore nell'aggiornamento dello stato dei contratti: " + e.getMessage());
        }
    }

	
	public int verificaNumeroContratti() {
		int id;
		String query;
		
		query = "SELECT COUNT(*) AS id FROM contratto_noleggio";
		
		try (Connection connection = DatabaseConnection.connessione();
				PreparedStatement stmtSelect = connection.prepareStatement(query)) {
			ResultSet rs = stmtSelect.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
				return id;
			}else
				return id = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id = 0;
	}	
}
