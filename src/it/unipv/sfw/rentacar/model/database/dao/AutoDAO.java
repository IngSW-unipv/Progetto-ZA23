package it.unipv.sfw.rentacar.model.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.database.DatabaseConnection;
import it.unipv.sfw.rentacar.model.exception.CategoriaBPatenteException;
import it.unipv.sfw.rentacar.model.exception.NumeroPatenteInvalidoException;
import it.unipv.sfw.rentacar.model.exception.PatenteScadutaException;
import it.unipv.sfw.rentacar.model.exception.TargaNonValidaException;
import it.unipv.sfw.rentacar.model.utenti.Amministratore;
import it.unipv.sfw.rentacar.model.utenti.Cliente;
import it.unipv.sfw.rentacar.model.utenti.Utente;
import it.unipv.sfw.rentacar.model.utenti.documenti.Patente;
import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.Cambio;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.CaratteristicheTecniche;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.Carburante;
import it.unipv.sfw.rentacar.model.veicolo.noleggio.Noleggio;

public class AutoDAO {

	public AutoDAO() {
		
	}
	
	public void creaAuto(Auto a) throws SQLException {
		String query = "INSERT INTO auto VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, a.getTarga());
			stmt.setString(2, a.getMarca());
	        stmt.setString(3, a.getModello());
	        stmt.setDouble(4, a.getCostoNoleggioGiornaliero());
	        stmt.setString(5, a.getStatoNoleggio().name());
	        stmt.setInt(6, a.getCaratteristicheTecniche().getAnnoProduzione());
	        stmt.setString(7, a.getCaratteristicheTecniche().getTipoCambio().name());
	        stmt.setString(8, a.getCaratteristicheTecniche().getTipoCarburante()[0].name());
	        if (a.getCaratteristicheTecniche().getTipoCarburante().length > 1 && a.getCaratteristicheTecniche().getTipoCarburante()[1] != null) {
	        	stmt.setString(9, a.getCaratteristicheTecniche().getTipoCarburante()[1].name());	
			}
	        stmt.setString(9, null);
	        stmt.setInt(10, a.getCaratteristicheTecniche().getPostiAuto());
	        stmt.setInt(11, a.getCaratteristicheTecniche().getCilindrata());
	        stmt.setInt(12, a.getCaratteristicheTecniche().getPotenza());
	        stmt.executeUpdate();
	    } catch (SQLException e) {
			if(e.getErrorCode() == 1062) {
				System.out.println("Targa duplicata: " + a.getTarga() + " -> Impossibile aggiungere");
			} else {
				throw e;
            }
		}
		
	}
	
	public void eliminaAuto(String targa) {
		String query = "DELETE FROM auto WHERE targa = ?";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, targa);
		} catch (SQLException e) {
			System.err.println("Errore nell'eliminazione dell'auto targata " + targa);
		}
	}
	
	public void aggiornaStatoNoleggio(Auto a) {
		String query = "UPDATE auto SET stato_noleggio = ? WHERE targa = ?; ";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
			if (a.getStatoNoleggio().equals(Noleggio.NOLEGGIATA)) {
				stmt.setString(1, Noleggio.DISPONIBILE.name());
			}else 
				stmt.setString(1, Noleggio.NOLEGGIATA.name());
			stmt.setString(2, a.getTarga());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Errore fase di update");
		}
	}
	
	public static void main(String[] args) throws NumeroPatenteInvalidoException, PatenteScadutaException, CategoriaBPatenteException, TargaNonValidaException, SQLException {
		String[] cat = {"B"};
		Patente p = new Patente("AB123456CC", "18/05/2025", cat);
		
		Utente cl = new Cliente("Roberto", "Pitorac" , "Pito", "Pitorac01", p);
		Amministratore amm = new Amministratore("Roberto", "Pitorac" , "Pito", "Pitorac01");
		AgenziaNoleggioAuto agenzia = new AgenziaNoleggioAuto("Rent-a-Car", "Via Mazzini, 17");
		agenzia.aggiungiUtente(cl);
		Carburante[] carburante = {Carburante.BENZINA, null};
		CaratteristicheTecniche ct1 = new CaratteristicheTecniche(2008, Cambio.AUTOMATICO, carburante, 5, 150, 200);
		Auto a1 = new Auto("AB456CD", "Fiat", "Panda", ct1, 5);
		Auto a2 = new Auto("CD456AS", "Ford", "Fiesta", ct1, 7.5);
		Auto a3 = new Auto("CD456AS", "Ford", "Fiesta", ct1, 7.5);
		AutoDAO dao = new AutoDAO();
		dao.creaAuto(a1);
		dao.creaAuto(a2);
		dao.creaAuto(a3);
		amm.aggiungiAuto(agenzia, a1);
		amm.aggiungiAuto(agenzia, a2);
		amm.aggiungiAuto(agenzia, a3);
		System.out.println("Siamo qua");
	}
	
}
