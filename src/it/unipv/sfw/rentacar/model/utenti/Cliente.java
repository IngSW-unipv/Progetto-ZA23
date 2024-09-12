package it.unipv.sfw.rentacar.model.utenti;

import java.sql.SQLException;

import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.contratti.ContrattoNoleggio;
import it.unipv.sfw.rentacar.model.contratti.pagamenti.CartaDiCredito;
import it.unipv.sfw.rentacar.model.utenti.documenti.Patente;
import it.unipv.sfw.rentacar.model.veicolo.Auto;

public class Cliente extends Utente {

	private Patente patente;
	
	public Cliente(String nome, String cognome, String username, String password, Patente patente) {
		super(nome, cognome, username, password);
		this.patente = patente;
	}
	
	public Cliente(Cliente c) {
		super(c.getNome(), c.getCognome(), c.getUsername(), c.getPassword());
		this.patente = c.getPatente();
	}

	public Patente getPatente() {
		return patente;
	}

	public void setPatente(Patente patente) {
		this.patente = patente;
	}
	
	public void noleggiaAuto(AgenziaNoleggioAuto agenzia, Auto a, String inizioNoleggio, String fineNoleggio, CartaDiCredito carta) throws SQLException {
		carta.effettuaPagamento();
		ContrattoNoleggio contratto = new ContrattoNoleggio(this, a, inizioNoleggio, fineNoleggio, carta);
		agenzia.aggiungiContratto(contratto);
	}

	@Override
	public String ruolo() {
		return "Cliente";
	}
	
	@Override
	public String toString() {
		return "Cliente [nome=" + getNome() + ", cognome=" + getCognome()
				+ ", username=" + getUsername() + ", password=" + getPassword() + ", numero patente=" + patente.getNumero() + "]";
	}

}
