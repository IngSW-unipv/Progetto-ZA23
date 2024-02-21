package it.unipv.sfw.rentacar.model.exception;

public class TargaNonValidaException extends Exception{

	public TargaNonValidaException() {
		super("Errore Targa");
	}
	
	@Override
	public String toString() {
		return getMessage() + " - Targa non valida";
	}
	
}
