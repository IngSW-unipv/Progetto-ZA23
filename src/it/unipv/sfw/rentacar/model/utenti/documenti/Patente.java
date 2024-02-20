package it.unipv.sfw.rentacar.model.utenti.documenti;

import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unipv.sfw.rentacar.model.exception.NumeroPatenteInvalidoException;

public class Patente {
	
	private String numero;
	private Date scadenza;
	private String[] categorie;
	
	public Patente(String numero, Date scadenza, String[] categorie) throws NumeroPatenteInvalidoException {
		
		if (!verificaNumeroPatente(numero)) {
			throw new NumeroPatenteInvalidoException();
		}
		
		this.numero = numero;
		this.scadenza = scadenza;
		this.categorie = categorie;
	}

	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	public Date getScadenza() {
		return scadenza;
	}
	
	public void rinnova(Date rinnovo) {
		this.scadenza = rinnovo;
	}
	
	public String[] getCategorie() {
		return categorie;
	}
	
	public boolean verificaNumeroPatente(String numeroPatente) {
		
		String formatoPatente = "^[A-Za-z]{2}\\d{6}+[A-Za-z0-9]{2}$";
		
		Pattern pattern = Pattern.compile(formatoPatente);
		Matcher matcher = pattern.matcher(numeroPatente);
		
		return matcher.matches();
	}

	@Override
	public String toString() {
		return "Patente [numero=" + numero + ", scadenza=" + scadenza + ", categorie=" + Arrays.toString(categorie)
				+ "]";
	}
	
}
