package it.unipv.sfw.rentacar.model.utenti;

/*
 * Classe Utente
 */

public abstract class Utente {

	private String nome; // Nome dell'utente
	private String cognome; // Cognome dell'utente
	private String username; // Username dell'utente
	private String password; // Password dell'utente
	
	public Utente(String nome, String cognome, String username, String password) {
		
		controlli(nome, cognome, username, password);
		
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
	}
	
	// Metodo di controllo degli attributi
	
	private void controlli(String nome, String cognome, String username, String password) {
		if (nome.equals(null)) {
			throw new NullPointerException("Nome non può essere null");
		}
		
		if (cognome.equals(null)) {
			throw new NullPointerException("Cognome non può essere null");
		}
		
		if (username.equals(null)) {
			throw new NullPointerException("Username non può essere null");
		}
		
		if (password.equals(null)) {
			throw new NullPointerException("Password non può essere null");
		}
		
		if (nome.length() <= 0) {
			throw new IllegalAccessError("Campo Nome vuoto");
		}
		
		if (cognome.length() <= 0) {
			throw new IllegalAccessError("Campo Cognome vuoto");
		}
		
		if (username.length() <= 0) {
			throw new IllegalAccessError("Campo Username vuoto");
		}
		
		if (password.length() <= 0) {
			throw new IllegalAccessError("Campo Password vuoto");
		}
	}

	// Getter e Setter
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", password=" + password
				+ "]";
	}
	
	public abstract String ruolo();
	
}
