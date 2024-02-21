package it.unipv.sfw.rentacar.model.contratti.pagamenti;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unipv.sfw.rentacar.model.exception.CartaDiCreditoScadutaException;

public class CartaDiCredito extends Pagamento{

	private String numero;
	private LocalDate scadenza;
	private int cvv;
	
	public CartaDiCredito(String titolare, String causale, String numero, String scadenza, int cvv) throws CartaDiCreditoScadutaException {
		super(titolare, causale);
		
		if (!controlloNumeroCarta(numero)) {
			throw new IllegalArgumentException("Numero Carta di Credito non valido");
		}
		
		if (!verificaScadenza(scadenza)) {
			throw new CartaDiCreditoScadutaException();
		}
		
		if (Integer.toString(cvv).length() != 3) {
			throw new IllegalArgumentException("Cvv errato o non corretto");
		}
		
		this.numero = numero;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.scadenza = LocalDate.parse(scadenza, formatter);
		this.cvv = cvv;
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

	public void setScadenza(LocalDate scadenza) {
		this.scadenza = scadenza;
	}

	public int getCVV() {
		return cvv;
	}

	public void setCVV(int cvv) {
		this.cvv = cvv;
	}

	private boolean controlloNumeroCarta(String numero) {
		String formatoCartaDiCredito = "^(\\d{4}-?){3}\\d{4}$";
		Pattern pattern = Pattern.compile(formatoCartaDiCredito);
		Matcher matcher = pattern.matcher(numero);
		
		return matcher.matches();
	}

	public boolean verificaScadenza(String scadenza) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataScadenza = LocalDate.parse(scadenza, formatter);
		LocalDate dataCorrente = LocalDate.now();
		return dataScadenza.isAfter(dataCorrente);
	}

	@Override
	public void effettuaPagamento() {
		System.out.println("Verifica dei dati immessi");
		System.out.println("...");
		System.out.println("...");
		System.out.println("Pagamento effettuato con successo");
	}

	@Override
	public String toString() {
		return "CartaDiCredito [titolare= "+ super.getTitolare() + ", causale= "+ super.getCausale()+ 
				", data Esecuzione= "+ super.dataEsecuzioneFormattata()+ ", numero=" + numero + ", scadenza=" + scadenza + ", cvv=" + cvv + "]";
	}
	
}
