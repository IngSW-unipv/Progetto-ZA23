package it.unipv.sfw.rentacar.model.exception;

public class PatenteScadutaException extends Exception{

	public PatenteScadutaException() {
		super("Errore scadenza patente");
	}
	
	@Override
	public String toString() {
		return getMessage() + " - Patente Scaduta";
	}
	
}
