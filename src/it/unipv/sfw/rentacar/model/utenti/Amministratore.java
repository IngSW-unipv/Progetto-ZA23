package it.unipv.sfw.rentacar.model.utenti;

/*
 * Classe Amministratore
 */

import java.sql.SQLException;

import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.database.dao.AutoDAO;
import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.model.veicolo.noleggio.Noleggio;

public class Amministratore extends Utente{

	public Amministratore(String nome, String cognome, String username, String password) {
		super(nome, cognome, username, password);
	}
	
	// Metodo aggiunta auto
	
	public void aggiungiAuto(AgenziaNoleggioAuto agenzia, Auto a) throws SQLException {
		agenzia.getElencoAuto().add(a);
		AutoDAO dao = new AutoDAO();
		dao.aggiungiAuto(a);
	}
	
	// Metodo rimozione auto
	
	public void rimuoviAuto(AgenziaNoleggioAuto agenzia, Auto a) {
		agenzia.getElencoAuto().remove(a);
		AutoDAO dao = new AutoDAO();
		dao.eliminaAuto(a);
	}
	
	// Metodo Aggiornamento Stato Auto
	
	public void aggiornaStatoAuto(Auto a) {
		if (a.getStatoNoleggio().equals(Noleggio.DISPONIBILE)) 
			a.setStatoNoleggio(Noleggio.NOLEGGIATA);
		else
			a.setStatoNoleggio(Noleggio.DISPONIBILE);
	}
	
	@Override
	public String ruolo() {
		return "Amministratore";
	}
	
	@Override
	public String toString() {
		return "Amministratore [nome=" + getNome() + ", cognome=" + getCognome() + ", Username="
				+ getUsername() + ", password=" + getPassword() + "]";
	}
	
}
