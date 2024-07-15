package it.unipv.sfw.rentacar.model.utenti.log;

import it.unipv.sfw.rentacar.model.utenti.Utente;

public class SessioneLogin {

	private Utente utente;
	private static SessioneLogin instance;
	private boolean statoLogin;
	
	public SessioneLogin() {
		this.statoLogin = false;
	}
	
	public static synchronized SessioneLogin getInstance() {
		if (instance == null) {
			instance = new SessioneLogin();
		}
		return instance;
	}
    public void setNuovoUtente(Utente utente) {
        this.utente = utente;
        this.statoLogin = true;
    }

    public void logout() {
        this.utente = null;
        this.statoLogin = false;
    }

    public boolean isLoggedIn() {
        return statoLogin;
    }

    public Utente getUtenteLoggato() {
        return utente;
    }
}
