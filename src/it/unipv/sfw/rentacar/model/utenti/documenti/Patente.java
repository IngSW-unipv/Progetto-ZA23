package it.unipv.sfw.rentacar.model.utenti.documenti;

import java.util.Arrays;
import java.util.Date;

public class Patente {
	
	private String numero;
	private Date scadenza;
	private String[] categorie;
	
	public Patente(String numero, Date scadenza, String[] categorie) {
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

	@Override
	public String toString() {
		return "Patente [numero=" + numero + ", scadenza=" + scadenza + ", categorie=" + Arrays.toString(categorie)
				+ "]";
	}

}
