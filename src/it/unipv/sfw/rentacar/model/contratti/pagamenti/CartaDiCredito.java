package it.unipv.sfw.rentacar.model.contratti.pagamenti;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import it.unipv.sfw.rentacar.model.exception.CartaDiCreditoScadutaException;

public class CartaDiCredito extends Pagamento{

	private String numero;
	private LocalDate scadenza;
	private int cvv;
	
	public CartaDiCredito(String titolare, String causale, String numero, String scadenza, int cvv) throws CartaDiCreditoScadutaException {
		super(titolare, causale);
		
		
		
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

	@Override
	public void effettuaPagamento() {
		
	}

	public boolean verificaScadenza(String scadenza) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataScadenza = LocalDate.parse(scadenza, formatter);
		LocalDate dataCorrente = LocalDate.now();
		return dataScadenza.isAfter(dataCorrente);
	}

	public static void main(String[] args) throws CartaDiCreditoScadutaException {
		
		Pagamento p = new CartaDiCredito("Roby", "Pito", "234", "12/03/2024", 241);
		System.out.println("fatto");
	}
	
}
