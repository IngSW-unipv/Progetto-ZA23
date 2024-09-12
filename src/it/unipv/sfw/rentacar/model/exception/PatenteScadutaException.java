package it.unipv.sfw.rentacar.model.exception;

/*
 * Eccezione Personalizzata PatenteScadutaException
 */

public class PatenteScadutaException extends Exception{

	public PatenteScadutaException() {
		super("Errore scadenza patente");
	}
	
	@Override
	public String toString() {
		return getMessage() + " - Patente Scaduta";
	}
	
}
