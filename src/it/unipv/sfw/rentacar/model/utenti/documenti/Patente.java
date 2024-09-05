package it.unipv.sfw.rentacar.model.utenti.documenti;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unipv.sfw.rentacar.model.exception.CategoriaBPatenteException;
import it.unipv.sfw.rentacar.model.exception.NumeroPatenteInvalidoException;
import it.unipv.sfw.rentacar.model.exception.PatenteScadutaException;

public class Patente {
	
	private String numero;
	private LocalDate scadenza;
	private String[] categorie;
	
	public Patente(String numero, String dataScadenza, String[] categorie) throws NumeroPatenteInvalidoException, PatenteScadutaException, CategoriaBPatenteException {
		
		controlli(numero, dataScadenza, categorie);
		
		this.numero = numero;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.scadenza = LocalDate.parse(dataScadenza, formatter);
		this.categorie = categorie;
	}

	private void controlli(String numero, String dataScadenza, String[] categorie) throws NumeroPatenteInvalidoException, PatenteScadutaException, CategoriaBPatenteException {
		if (numero.equals(null) || dataScadenza.equals(null) || categorie.equals(null)) {
			throw new NullPointerException("I valori non possono essere null");
		}
		
		if (!verificaNumeroPatente(numero)) {
			throw new NumeroPatenteInvalidoException();
		}
		
		if (!verificaScadenzaPatente(dataScadenza)) {
			throw new PatenteScadutaException();
		}
		
		if (!controlloPatenteB(categorie)) {
			throw new CategoriaBPatenteException();
		}
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	public LocalDate getScadenza() {
		return scadenza;
	}
	
	public void setScadenza(LocalDate rinnovo) {
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
	
	public boolean verificaScadenzaPatente(String scadenza) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataScadenza = LocalDate.parse(scadenza, formatter);
		LocalDate dataCorrente = LocalDate.now();
		return dataScadenza.isAfter(dataCorrente);
	}
	
	public boolean controlloPatenteB(String[] categorie) {
		
		for (int i = 0; i < categorie.length; i++) {
			if (categorie[i].equals("B")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Patente [numero=" + numero + ", scadenza=" + scadenza + ", categorie=" + Arrays.toString(categorie)
				+ "]";
	}	
	
}
