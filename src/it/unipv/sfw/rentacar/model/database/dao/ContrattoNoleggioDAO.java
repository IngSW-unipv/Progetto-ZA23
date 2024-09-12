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
			aggiornaStatoContratti();
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
	
	public static void main(String[] args) throws NumeroPatenteInvalidoException, PatenteScadutaException, CategoriaBPatenteException, TargaNonValidaException, CartaDiCreditoScadutaException, SQLException {
		ContrattoNoleggioDAO dao = new ContrattoNoleggioDAO();
		
		AgenziaNoleggioAuto agenzia = AgenziaNoleggioAuto.getInstance("Rent-a-Car", "Via G. Mazzini, 17");
		
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
		CartaDiCredito cdc1 = new CartaDiCredito("1111222233334444", "20/04/2027", 123);
		CartaDiCredito cdc2 = new CartaDiCredito("0000222233334444", "28/04/2028", 456);
		
		dataInizio1 = "06/09/2024";
		dataFine1 = "07/09/2024";
		
		dataInizio2 = "16/09/2024";
		dataFine2 = "25/12/2024";
		
		dataInizio3 = "25/09/2024";
		dataFine3 = "14/10/2024";
		
		ContrattoNoleggio cn1 = new ContrattoNoleggio(cliente1, a1, dataInizio1, dataFine1, cdc1);
		agenzia.aggiungiContratto(cn1);
		dao.aggiungiContratto(cn1);
		ContrattoNoleggio cn2 = new ContrattoNoleggio(cliente2, a2, dataInizio2, dataFine2, cdc2);
		agenzia.aggiungiContratto(cn2);
		dao.aggiungiContratto(cn2);
		ContrattoNoleggio cn3 = new ContrattoNoleggio(cliente3, a3, dataInizio3, dataFine3, cdc1);
		agenzia.aggiungiContratto(cn3);
		dao.aggiungiContratto(cn3);
		
		agenzia.stampaContratti();
        
        System.out.println("Funziona");
        
		
        String[] categorie4 = {"B", "C", "D"};
        Patente patente4 = new Patente("GH789012GH", "22/11/2026", categorie4);
        Cliente cliente4 = new Cliente("Luigi", "Verdi", "lverdi", "password456", patente4);
        
        Carburante[] carburante4 = {Carburante.BENZINA, null};
		CaratteristicheTecniche ct = new CaratteristicheTecniche(2008, Cambio.AUTOMATICO, carburante4, 5, 150, 200);
		Auto a4 = new Auto("FG456AS", "Audi", "Q7", ct, 10);
		
		String dataInizio4, dataFine4;
		CartaDiCredito cdc4 = new CartaDiCredito("1111222233334444", "20/04/2027", 123);
		
		dataInizio4 = "05/10/2024";
		dataFine4 = "06/12/2024";
		
		ContrattoNoleggio cn = new ContrattoNoleggio(cliente4, a4, dataInizio4, dataFine4, cdc4);
		
		agenzia.aggiungiContratto(cn);
		agenzia.stampaContratti();
		dao.aggiungiContratto(cn);

	}
	
}
