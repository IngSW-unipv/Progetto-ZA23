package it.unipv.sfw.rentacar.controller.utenti.clienti;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import it.unipv.sfw.rentacar.controller.managerview.ViewManager;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.database.dao.UtenteDAO;
import it.unipv.sfw.rentacar.model.utenti.Cliente;
import it.unipv.sfw.rentacar.model.utenti.Utente;
import it.unipv.sfw.rentacar.model.utenti.log.SessioneLogin;
import it.unipv.sfw.rentacar.view.utenti.clienti.RinnovoPatenteUI;

public class RinnovoPatenteController {
	
	private AgenziaNoleggioAuto model;
	private RinnovoPatenteUI view;
	
	public RinnovoPatenteController(AgenziaNoleggioAuto model, RinnovoPatenteUI view) {
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

		view.getConfermaButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//String rinnovoPatente;
				java.util.Date data;
				LocalDate dataLocal;
				data =  (java.util.Date)view.getNuovaScadenza().getDate();
				//SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                //rinnovoPatente = dateFormat.format(data);
				Instant instant = data.toInstant();
                dataLocal = instant.atZone(ZoneId.systemDefault()).toLocalDate();
	            		
	            if (dataLocal != null && dataLocal.isAfter(LocalDate.now())) {  
	                Cliente c = (Cliente) SessioneLogin.getInstance().getUtenteLoggato();
	                try {
						UtenteDAO dao = new UtenteDAO();
		                dao.rinnovaPatente(c, dataLocal);
		                model.setElencoUtenti(dao.letturaDati());
						c.getPatente().setScadenza(dataLocal);
						SessioneLogin.getInstance().setNuovoUtente(c);
						JOptionPane.showMessageDialog(view.getFrame(),
								"Rinnovo Patente avvenuto con successo!",
								"Rinnovo Patente avvenuto",
								JOptionPane.INFORMATION_MESSAGE);
						ViewManager manager = new ViewManager();
						manager.passaAllAreaPersonaleUI(model);
						view.getFrame().dispose();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	            }else {
	            	JOptionPane.showMessageDialog(view.getFrame(),
							"La data inserita non é valida",
							"Errore Data Inserita",
							JOptionPane.ERROR_MESSAGE);				}
			}
		});
		
		view.getAnnullaButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(view.getFrame(), 
						"Sei sicuro di voler annullare l'operazione ?", 
						"Annullamento Rinnovo Patente", 
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(view.getFrame(), "Rinnovo Patente Annullato", "Annullamento", JOptionPane.ERROR_MESSAGE);
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
