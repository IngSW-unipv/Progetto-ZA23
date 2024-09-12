package it.unipv.sfw.rentacar.controller.utenti.amministratori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import it.unipv.sfw.rentacar.controller.managerview.ViewManager;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.utenti.Amministratore;
import it.unipv.sfw.rentacar.model.utenti.log.SessioneLogin;
import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.model.veicolo.noleggio.Noleggio;
import it.unipv.sfw.rentacar.view.utenti.amministratori.RimuoviAutoUI;

/*
 * Controller RimuoviAutoUI
 */

public class RimuoviAutoController {

	private AgenziaNoleggioAuto model;
	private RimuoviAutoUI view;
	
	public RimuoviAutoController(AgenziaNoleggioAuto model, RimuoviAutoUI view) {
		this.model = model;
		this.view = view;
		initController();
	}

	private void initController() {
		
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
		
		
		
		view.getFrame().getSideBarPanel().getAggiungiAutoButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ViewManager manager = new ViewManager();
					manager.passaAdAggiungiAutoUI(model);
					view.getFrame().dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		view.getFrame().getSideBarPanel().getRimuoviAutoButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ViewManager manager = new ViewManager();
					manager.passaARimuoviAutoUI(model);
					view.getFrame().dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		view.getConfermaButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String targa;
				Amministratore a;
				
				targa = view.getTargaField().getText();
				
				for (int i = 0; i < model.getElencoAuto().size(); i++) {
					Auto auto = model.getElencoAuto().get(i);
					if (auto.getTarga().equals(targa) && auto.getStatoNoleggio().equals(Noleggio.DISPONIBILE)) {
						a = (Amministratore)SessioneLogin.getInstance().getUtenteLoggato();
						a.rimuoviAuto(model, auto);
						JOptionPane.showMessageDialog(view.getFrame(),
								"Auto rimossa con successo!",
								"Rimozione avvenuta",
								JOptionPane.INFORMATION_MESSAGE);
						try {
							ViewManager manager = new ViewManager();
							manager.passaAllaHomePageUI(model);
							view.getFrame().dispose();
							return;
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				
				JOptionPane.showMessageDialog(view.getFrame(), 
                        "Targa inserita non valida, non esistente oppure Auto noleggiata", 
                        "Errore", 
                        JOptionPane.ERROR_MESSAGE);
				return;
			}
		});
		
		view.getAnnullaButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(view.getFrame(), 
						"Sei sicuro di voler annullare l'operazione ?", 
						"Annullamento Cambio Password", 
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(view.getFrame(), 
							"Rimozione Auto Annullata", 
							"Annullamento", 
							JOptionPane.ERROR_MESSAGE);
					ViewManager manager = new ViewManager();
					try {
						manager.passaAllaHomePageUI(model);
					} catch (IOException  e1) {
						e1.getMessage();
						return;
					}
					view.getFrame().dispose();
					
				}
			}
		});
		
	}
	
}
