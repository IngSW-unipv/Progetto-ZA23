package it.unipv.sfw.rentacar.model.utenti;

import it.unipv.sfw.rentacar.model.utenti.documenti.Patente;

public class Cliente extends Utente {

	public Patente patente;
	
	public Cliente(String nome, String cognome, String username, String password, Patente patente) {
		super(nome, cognome, username, password);
		this.patente = patente;
	}

	public Patente getPatente() {
		return patente;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + getNome() + ", cognome=" + getCognome()
				+ ", username=" + getUsername() + ", password()=" + getPassword() + "patente=" + patente + " ]";
	}
	
	

}
