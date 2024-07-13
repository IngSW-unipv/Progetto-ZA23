package it.unipv.sfw.rentacar.model.utenti;

import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.model.veicolo.noleggio.Noleggio;

public class Amministratore extends Utente{

	public Amministratore(String nome, String cognome, String username, String password) {
		super(nome, cognome, username, password);
	}
	
	public void aggiungiAuto(AgenziaNoleggioAuto agenzia, Auto a) {
		agenzia.getElencoAuto().add(a);
	}
	
	public void rimuoviAuto(AgenziaNoleggioAuto agenzia, Auto a) {
		agenzia.getElencoAuto().remove(a);
	}
	
	public void aggiornaStatoAuto(AgenziaNoleggioAuto agenzia, Auto a) {
		if (a.getStatoNoleggio().equals(Noleggio.DISPONIBILE)) 
			a.setStatoNoleggio(Noleggio.NOLEGGIATA);
		else
			a.setStatoNoleggio(Noleggio.DISPONIBILE);
	}
	
	@Override
	public String toString() {
		return "Amministratore [nome=" + getNome() + ", cognome=" + getCognome() + ", Username="
				+ getUsername() + ", password()=" + getPassword() + "]";
	}
	
}
