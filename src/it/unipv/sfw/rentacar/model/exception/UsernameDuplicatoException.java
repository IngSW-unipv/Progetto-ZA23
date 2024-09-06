package it.unipv.sfw.rentacar.model.exception;

public class UsernameDuplicatoException extends Exception{

	public UsernameDuplicatoException() {
		super("Errore scadenza carta di credito");
	}

	@Override
	public String toString() {
		return getMessage() + " - Username gia in uso";
	}
	
}
