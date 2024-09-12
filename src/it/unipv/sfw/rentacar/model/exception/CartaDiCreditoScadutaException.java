package it.unipv.sfw.rentacar.model.exception;

/*
 * Eccezione Personalizzata CartaDiCreditoScadutaException
 */

public class CartaDiCreditoScadutaException extends Exception{

	public CartaDiCreditoScadutaException() {
		super("Errore scadenza carta di credito");
	}

	@Override
	public String toString() {
		return getMessage() + " - Carta di Credito Scaduta";
	}
	
}
