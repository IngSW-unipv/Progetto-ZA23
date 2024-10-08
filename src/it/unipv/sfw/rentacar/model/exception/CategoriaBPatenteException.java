package it.unipv.sfw.rentacar.model.exception;

/*
 * Eccezione Personalizzata CategoriaBPatenteException
 */

public class CategoriaBPatenteException extends Exception{

	public CategoriaBPatenteException() {
		super("Problema Categoria Patente");
	}
	
	@Override
	public String toString() {
		return getMessage() + " - Categoria B non presente";
	}
	
}
