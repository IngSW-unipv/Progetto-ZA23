package it.unipv.sfw.rentacar.controller.utenti.clienti;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import it.unipv.sfw.rentacar.controller.managerview.ViewManager;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.database.dao.UtenteDAO;
import it.unipv.sfw.rentacar.model.utenti.Utente;
import it.unipv.sfw.rentacar.model.utenti.log.SessioneLogin;
import it.unipv.sfw.rentacar.view.utenti.clienti.CambioPasswordUI;

/*
 * Controller CambioPasswordUI
 */

public class CambioPasswordController {

	private AgenziaNoleggioAuto model;
	private CambioPasswordUI view;
	
	public CambioPasswordController(AgenziaNoleggioAuto model, CambioPasswordUI view) {
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
		
		view.getConfermaPass().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String vecchiaPass, nuovaPass;
				
				vecchiaPass = view.getVecchiaPassField().getText();
				nuovaPass = view.getNuovaPassField().getText();
				
				if (vecchiaPass.isEmpty() || nuovaPass.isEmpty()) {
					JOptionPane.showMessageDialog(view.getFrame(),
							"I campi non possono essere vuoti",
							"Errore",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (vecchiaPass.equals(SessioneLogin.getInstance().getUtenteLoggato().getPassword()) && !vecchiaPass.equals(nuovaPass)) {
					UtenteDAO dao = new UtenteDAO();
					try {
						for (Iterator<Utente> iterator = model.getElencoUtenti().iterator(); iterator.hasNext();) {
							Utente u = iterator.next();
							if (u.getUsername().equals(SessioneLogin.getInstance().getUtenteLoggato().getUsername())) {
								dao.aggiornaPasswordCliente(SessioneLogin.getInstance().getUtenteLoggato(), nuovaPass);
								u.setPassword(nuovaPass);
								SessioneLogin.getInstance().getUtenteLoggato().setPassword(nuovaPass);
								JOptionPane.showMessageDialog(view.getFrame(),
										"Cambio password avvenuto con successo!",
										"Cambio password avvenuto",
										JOptionPane.INFORMATION_MESSAGE);
								ViewManager manager = new ViewManager();
								manager.passaAllAreaPersonaleUI(model);
								view.getFrame().dispose();
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
				}else {
					if (vecchiaPass.equals(nuovaPass)) {
						JOptionPane.showMessageDialog(view.getFrame(),
								"La nuova password é uguale a quella vecchia",
								"Errore",
								JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(view.getFrame(),
								"La password vecchia non é corretta",
								"Errore",
								JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		view.getAnnullaPass().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(view.getFrame(), 
						"Sei sicuro di voler annullare l'operazione ?", 
						"Annullamento Cambio Password", 
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(view.getFrame(), "Cambio Password Annullato", "Annullamento", JOptionPane.ERROR_MESSAGE);
					ViewManager manager = new ViewManager();
					try {
						manager.passaAllAreaPersonaleUI(model);
					} catch (IOException | UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
					view.getFrame().dispose();
					
				}
			}
		});

		
	}
	
}
