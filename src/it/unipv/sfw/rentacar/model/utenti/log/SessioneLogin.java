package it.unipv.sfw.rentacar.model.utenti.log;

/*
 * Classe SessioneLogin
 */

import it.unipv.sfw.rentacar.model.utenti.Utente;

public class SessioneLogin {


	private static SessioneLogin instance; // Istanza Singleton 
	private Utente utente; // Utente loggato
	private boolean statoLogin; // stato Login
	
	private SessioneLogin() {
		this.statoLogin = false;
	}
	
	public static synchronized SessioneLogin getInstance() {
		if (instance == null) {
			instance = new SessioneLogin();
		}
		return instance;
	}
	
	// Metodi
	
    public void setNuovoUtente(Utente utente) {
        this.utente = utente;
        this.statoLogin = true;
    }

    public void login() {
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
