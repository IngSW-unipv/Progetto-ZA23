package it.unipv.sfw.rentacar.model.exception;

/*
 * Eccezione Personalizzata NumeroPatenteInvalidoException
 */

public class NumeroPatenteInvalidoException extends Exception{

	public NumeroPatenteInvalidoException() {
		super("Problema con il numero della patente");
	}
	
	@Override
	public String toString() {
		return getMessage() + " - Numero Patente non valido";
	}
	
}
