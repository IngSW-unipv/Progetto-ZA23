package it.unipv.sfw.rentacar.model.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

/*
 * Classe AutoDAO
 */

public class AutoDAO {

	public AutoDAO() {
		
	}
	
	// Metodo di aggiunta di un'auto al DB
	
	public void aggiungiAuto(Auto a) throws SQLException {
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
			}else
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
	
	// Metodo di rimozione di un'auto dal DB
	
	public void eliminaAuto(Auto a) {
		String query = "DELETE FROM auto WHERE targa = ?";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, a.getTarga());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Errore nell'eliminazione dell'auto targata " + a.getTarga());
		}
	}
	
	public void aggiornaStatoNoleggio(Auto a) {
		
		String query = "UPDATE auto SET stato_noleggio = ? WHERE targa = ?; ";
		System.out.println("Stato noleggio attuale : " + a.getStatoNoleggio().toString());
		
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
				
			stmt.setString(1, a.getStatoNoleggio().name());
			stmt.setString(2, a.getTarga());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("Errore fase di update");
		}
	}
	
	// Metodo di Lettura Dati dal DB
	
	public ArrayList<Auto> letturaDati() throws TargaNonValidaException{
		String query;
		String targa, modello, marca, noleggio, tipo, carburante1, carburante2; 
		double costoNoleggioGiornaliero;
		int annoProduzione, postiAuto, cilindrata, potenza;
		Cambio tipoCambio;
		Carburante[] tipoCarburante;
		Noleggio statoNoleggio;
		CaratteristicheTecniche ct;
		
		ArrayList<Auto> auto = new ArrayList<>();
		
		query = "SELECT * FROM auto";
		
		try(Connection connection = DatabaseConnection.connessione();
				PreparedStatement stmt = connection.prepareStatement(query)) {
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				targa = rs.getString("targa");
				marca = rs.getString("marca");
				modello = rs.getString("modello");
				costoNoleggioGiornaliero = rs.getDouble("costo_noleggio");
				noleggio = rs.getString("stato_noleggio");
				statoNoleggio = Noleggio.valueOf(noleggio.toUpperCase());
				annoProduzione = rs.getInt("annoProduzione");
				tipo = rs.getString("tipo_cambio");
				tipoCambio = Cambio.valueOf(tipo.toUpperCase());
				carburante1 = rs.getString("tipo_carburante1");
				carburante2 = rs.getString("tipo_carburante2");
				tipoCarburante = new Carburante[2];
				tipoCarburante[0] = Carburante.valueOf(carburante1.toUpperCase());
				if (carburante2 != null) {
					tipoCarburante[1] = Carburante.valueOf(carburante2.toUpperCase());
				}
				postiAuto = rs.getInt("posti_auto");
				cilindrata = rs.getInt("cilindrata");
				potenza = rs.getInt("potenza");
				
				ct = new CaratteristicheTecniche(annoProduzione, tipoCambio, tipoCarburante, postiAuto, cilindrata, potenza);
				
				Auto a = new Auto(targa, marca, modello, ct, costoNoleggioGiornaliero);
				
				auto.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return auto;
	}
		
}
