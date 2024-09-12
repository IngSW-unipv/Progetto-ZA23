package it.unipv.sfw.rentacar.controller.managerview;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

import it.unipv.sfw.rentacar.controller.accesso.RegistrationController;
import it.unipv.sfw.rentacar.controller.accesso.LoginController;
import it.unipv.sfw.rentacar.controller.catalogoauto.CatalogoAutoController;
import it.unipv.sfw.rentacar.controller.catalogoauto.NoleggioAutoController;
import it.unipv.sfw.rentacar.controller.homepage.HomepageController;
import it.unipv.sfw.rentacar.controller.utenti.amministratori.AggiungiAutoController;
import it.unipv.sfw.rentacar.controller.utenti.amministratori.RimuoviAutoController;
import it.unipv.sfw.rentacar.controller.utenti.clienti.AreaPersonaleController;
import it.unipv.sfw.rentacar.controller.utenti.clienti.CambioPasswordController;
import it.unipv.sfw.rentacar.controller.utenti.clienti.RinnovoPatenteController;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.view.accesso.LoginUI;
import it.unipv.sfw.rentacar.view.accesso.RegistrationUI;
import it.unipv.sfw.rentacar.view.catalogoauto.CatalogoAutoUI;
import it.unipv.sfw.rentacar.view.catalogoauto.NoleggioAutoUI;
import it.unipv.sfw.rentacar.view.homepage.HomepageUI;
import it.unipv.sfw.rentacar.view.utenti.amministratori.AggiungiAutoUI;
import it.unipv.sfw.rentacar.view.utenti.amministratori.RimuoviAutoUI;
import it.unipv.sfw.rentacar.view.utenti.clienti.AreaPersonaleUI;
import it.unipv.sfw.rentacar.view.utenti.clienti.CambioPasswordUI;
import it.unipv.sfw.rentacar.view.utenti.clienti.RinnovoPatenteUI;

public class ViewManager {
	
	public ViewManager() {
		super();
	}
	
	public void passaAllaHomePageUI(AgenziaNoleggioAuto agenzia) throws IOException {
		new HomepageController(agenzia, new HomepageUI());
	}
	
	public void passaAllaLoginUI(AgenziaNoleggioAuto agenzia) throws IOException {
		new LoginController(agenzia, new LoginUI());
	}

	public void passaARegistrationUI(AgenziaNoleggioAuto agenzia) throws IOException {
		new RegistrationController(agenzia, new RegistrationUI());
	}
	
	public void passaAlCatalogoAutoUI(AgenziaNoleggioAuto agenzia) throws IOException, UnsupportedLookAndFeelException {
		new CatalogoAutoController(agenzia, new CatalogoAutoUI());
	}
	
	public void passaAllAreaPersonaleUI(AgenziaNoleggioAuto agenzia) throws IOException, UnsupportedLookAndFeelException {
		new AreaPersonaleController(agenzia, new AreaPersonaleUI());
	}
	
	public void passaAlCambioPasswordUI(AgenziaNoleggioAuto agenzia) throws IOException {
		new CambioPasswordController(agenzia, new CambioPasswordUI());
	}
	
	public void passaAlRinnovoPatenteUI(AgenziaNoleggioAuto agenzia) throws IOException {
		new RinnovoPatenteController(agenzia, new RinnovoPatenteUI());
	}
	
	public void passaAdAggiungiAutoUI(AgenziaNoleggioAuto agenzia) throws IOException {
		new AggiungiAutoController(agenzia, new AggiungiAutoUI());
	}
	
	public void passaARimuoviAutoUI(AgenziaNoleggioAuto agenzia) throws IOException {
		new RimuoviAutoController(agenzia, new RimuoviAutoUI());
	}
	
	public void passaANoleggioAutoUI(AgenziaNoleggioAuto agenzia, Auto a) throws IOException {
		new NoleggioAutoController(agenzia, new NoleggioAutoUI(), a);
	}
}
