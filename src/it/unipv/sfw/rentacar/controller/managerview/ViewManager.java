package it.unipv.sfw.rentacar.controller.managerview;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

import it.unipv.sfw.rentacar.controller.accesso.RegistrationController;
import it.unipv.sfw.rentacar.controller.accesso.LoginController;
import it.unipv.sfw.rentacar.controller.catalogoauto.CatalogoAutoController;
import it.unipv.sfw.rentacar.controller.homepage.HomepageController;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.view.accesso.LoginUI;
import it.unipv.sfw.rentacar.view.accesso.RegistrationUI;
import it.unipv.sfw.rentacar.view.catalogoauto.CatalogoAutoUI;
import it.unipv.sfw.rentacar.view.homepage.HomepageUI;

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
}
