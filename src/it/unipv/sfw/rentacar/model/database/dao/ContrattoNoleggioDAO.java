package it.unipv.sfw.rentacar.model.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

	/*
	public static void main(String[] args) throws NumeroPatenteInvalidoException, PatenteScadutaException, CategoriaBPatenteException, TargaNonValidaException, CartaDiCreditoScadutaException, SQLException {
		ContrattoNoleggioDAO dao = new ContrattoNoleggioDAO();
		
		String[] categorie1 = {"B"};
        Patente patente1 = new Patente("AB123456CC", "18/05/2025", categorie1);
        Cliente cliente1 = new Cliente("Mario", "Rossi", "mrossi", "password123", patente1);
        
        String[] categorie2 = {"B", "C"};
        Patente patente2 = new Patente("CD789012EF", "22/11/2026", categorie2);
        Cliente cliente2 = new Cliente("Luigi", "Verdi", "lverdi", "password456", patente2);
        
        String[] categorie3 = {"B", "C", "D"};
        Patente patente3 = new Patente("CD789012GH", "22/11/2026", categorie3);
        Cliente cliente3 = new Cliente("Luigi", "Verdi", "lverdi", "password456", patente3);
		
		Carburante[] carburante = {Carburante.BENZINA, null};
		CaratteristicheTecniche ct1 = new CaratteristicheTecniche(2008, Cambio.AUTOMATICO, carburante, 5, 150, 200);
		Auto a1 = new Auto("AB456CD", "Fiat", "Panda", ct1, 5);
		Auto a2 = new Auto("CD456AS", "Ford", "Fiesta", ct1, 7.5);
		Auto a3 = new Auto("FG456AS", "Audi", "Q7", ct1, 10);
		
		String dataInizio1, dataFine1, dataInizio2, dataFine2, dataInizio3 , dataFine3;
		CartaDiCredito cdc1 = new CartaDiCredito("Abe", "Pagamento Noleggio", "1111222233334444", "20/04/2027", 123);
		CartaDiCredito cdc2 = new CartaDiCredito("Pepe", "Pagamento Noleggio", "0000222233334444", "28/04/2028", 456);
		
		dataInizio1 = "14/07/2024";
		dataFine1 = "07/12/2024";
		
		dataInizio2 = "16/07/2024";
		dataFine2 = "25/12/2024";
		
		dataInizio3 = "25/08/2024";
		dataFine3 = "14/07/2024";
		
		ContrattoNoleggio cn1 = new ContrattoNoleggio(cliente1, a1, dataInizio1, dataFine1, cdc1);
		ContrattoNoleggio cn2 = new ContrattoNoleggio(cliente2, a2, dataInizio2, dataFine2, cdc2);
		ContrattoNoleggio cn3 = new ContrattoNoleggio(cliente3, a3, dataInizio3, dataFine3, cdc1);
		AgenziaNoleggioAuto agenzia = new AgenziaNoleggioAuto("Rent-a-Car", "Via G. Mazzini, 17");
		
		agenzia.aggiungiContratto(cn1);
		agenzia.aggiungiContratto(cn2);
		agenzia.aggiungiContratto(cn3);
		dao.aggiungiContratto(cn1);
		dao.aggiungiContratto(cn2);
		dao.aggiungiContratto(cn3);
        
        System.out.println("Funziona");
	}
	*/
}
