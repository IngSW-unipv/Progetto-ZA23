package it.unipv.sfw.rentacar.controller.accesso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import it.unipv.sfw.rentacar.controller.homepage.HomepageController;
import it.unipv.sfw.rentacar.controller.managerview.ViewManager;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.utenti.Utente;
import it.unipv.sfw.rentacar.model.utenti.log.SessioneLogin;
import it.unipv.sfw.rentacar.view.accesso.LoginUI;
import it.unipv.sfw.rentacar.view.homepage.HomepageUI;

/*
 * Controller LoginUI
 */

public class LoginController {

	private AgenziaNoleggioAuto model;
	private LoginUI view;
	
	public LoginController(AgenziaNoleggioAuto model, LoginUI view) {
		this.model = model;
		this.view = view;
		initController();
	}

	private void initController() {

		view.getFrame().getSideBarPanel().getHomeButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new HomepageController(model, new HomepageUI());
					view.getFrame().dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		view.getFrame().getSideBarPanel().getNoleggioButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ViewManager manager = new ViewManager();
					manager.passaAlCatalogoAutoUI(model);
					view.getFrame().dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		view.getFrame().getTitlePanel().getLoginButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ViewManager manager = new ViewManager();
					manager.passaAllaLoginUI(model);
					view.getFrame().dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		view.getFrame().getTitlePanel().getRegisterButton().addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ViewManager manager = new ViewManager();
					manager.passaARegistrationUI(model);
					view.getFrame().dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		view.getLoginButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username, password;
				char[] pass;
				
				username = view.getUsername().getText();
				pass = view.getPassword().getPassword();
				password = new String(pass);				
				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(view.getFrame(), "Username o password non possono essere vuoti", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				model.cercaUtenteRegistrato(username, password);
						
				if (SessioneLogin.getInstance().isLoggedIn()) {
					JOptionPane.showMessageDialog(view.getFrame(),
							"Login avvenuto con successo!",
							"Successo",
							JOptionPane.INFORMATION_MESSAGE);
					try {
						ViewManager manager = new ViewManager();
						manager.passaAllaHomePageUI(model);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					view.getFrame().dispose();
				}else {
					JOptionPane.showMessageDialog(view.getFrame(), "Username o password errati", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}
		});
	}
}
