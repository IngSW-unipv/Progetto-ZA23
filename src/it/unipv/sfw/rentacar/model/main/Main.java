package it.unipv.sfw.rentacar.model.main;

import java.io.IOException;
import it.unipv.sfw.rentacar.controller.homepage.HomepageController;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.database.dao.AutoDAO;
import it.unipv.sfw.rentacar.model.database.dao.UtenteDAO;
import it.unipv.sfw.rentacar.model.exception.CategoriaBPatenteException;
import it.unipv.sfw.rentacar.model.exception.NumeroPatenteInvalidoException;
import it.unipv.sfw.rentacar.model.exception.PatenteScadutaException;
import it.unipv.sfw.rentacar.model.exception.TargaNonValidaException;
import it.unipv.sfw.rentacar.view.homepage.HomepageUI;

public class Main {

	public static void main(String[] args) throws IOException, TargaNonValidaException, NumeroPatenteInvalidoException, PatenteScadutaException, CategoriaBPatenteException {
		HomepageUI home = new HomepageUI();
		AgenziaNoleggioAuto agenzia = AgenziaNoleggioAuto.getInstance("Rent-a-Car", "Via Roma, 71, Milano");
		UtenteDAO daoUtenti = new UtenteDAO();
		AutoDAO daoAuto = new AutoDAO();
		agenzia.setElencoUtenti(daoUtenti.letturaDati());
		agenzia.setElencoAuto(daoAuto.letturaDati());
		agenzia.stampaAuto();
		agenzia.stampaUtenti();
		HomepageController contr = new HomepageController(agenzia, home);
	}
	
}
