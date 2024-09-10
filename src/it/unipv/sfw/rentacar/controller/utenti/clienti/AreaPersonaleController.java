package it.unipv.sfw.rentacar.controller.clienti;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import it.unipv.sfw.rentacar.controller.managerview.ViewManager;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.utenti.Cliente;
import it.unipv.sfw.rentacar.model.utenti.log.SessioneLogin;
import it.unipv.sfw.rentacar.view.utenti.clienti.AreaPersonaleUI;

public class AreaPersonaleController {

	private AgenziaNoleggioAuto model;
	private AreaPersonaleUI view;
	
	public AreaPersonaleController(AgenziaNoleggioAuto model, AreaPersonaleUI view) {
		this.model = model;
		this.view = view;
		initController();
	}

	private void initController() {
		
		Cliente c = new Cliente((Cliente)SessioneLogin.getInstance().getUtenteLoggato());
		
		view.getNomeField().setText(SessioneLogin.getInstance().getUtenteLoggato().getNome());
		view.getCognomeField().setText(SessioneLogin.getInstance().getUtenteLoggato().getCognome());
		view.getUsernameField().setText(SessioneLogin.getInstance().getUtenteLoggato().getUsername());
		view.getNumeroPatenteField().setText(c.getPatente().getNumero());
		
		view.getFrame().getTitlePanel().getLogoutButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SessioneLogin sessione = SessioneLogin.getInstance();
				sessione.logout();
				JOptionPane.showMessageDialog(view.getFrame(), "Logout avvenuto con successo!", "Logout", JOptionPane.INFORMATION_MESSAGE);
				try {
					ViewManager manager = new ViewManager();
					manager.passaAllaHomePageUI(model);
					view.getFrame().dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		view.getFrame().getSideBarPanel().getHomeButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				try {
					ViewManager manager = new ViewManager();
					manager.passaAllaHomePageUI(model);
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
				} catch (IOException | UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		view.getFrame().getSideBarPanel().getAreaPersonaleButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ViewManager manager = new ViewManager();
					manager.passaAllAreaPersonaleUI(model);
					view.getFrame().dispose();
				} catch (IOException | UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	
		view.getCambioPasswordButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ViewManager manager = new ViewManager();
					manager.passaAlCambioPasswordUI(model);
					view.getFrame().dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		view.getAggiornaPatenteButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ViewManager manager = new ViewManager();
					manager.passaAlRinnovoPatenteUI(model);
					view.getFrame().dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
			
	}
	
}
