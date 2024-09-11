package it.unipv.sfw.rentacar.controller.accesso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatCodePointException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import it.unipv.sfw.rentacar.controller.managerview.ViewManager;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.exception.CategoriaBPatenteException;
import it.unipv.sfw.rentacar.model.exception.NumeroPatenteInvalidoException;
import it.unipv.sfw.rentacar.model.exception.PatenteScadutaException;
import it.unipv.sfw.rentacar.model.exception.UsernameDuplicatoException;
import it.unipv.sfw.rentacar.model.utenti.Cliente;
import it.unipv.sfw.rentacar.model.utenti.Utente;
import it.unipv.sfw.rentacar.model.utenti.documenti.Patente;
import it.unipv.sfw.rentacar.view.accesso.RegistrationUI;

public class RegistrationController {

	private AgenziaNoleggioAuto model;
	private RegistrationUI view;
	
	public RegistrationController(AgenziaNoleggioAuto model, RegistrationUI view) {
		this.model = model;
		this.view = view;
		initController();
	}

	private void initController() {
		
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
		
		view.getRegistrazioneButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nome, cognome, username, password, numeroPatente;
				String[] categoriePatente;
				char[] pass;
				java.util.Date data;
				LocalDate dataLocal;
				
				nome = view.getNome().getText();
				cognome = view.getCognome().getText();
				username = view.getUsername().getText();
				pass = view.getPassword().getPassword();
				password = new String(pass);
				numeroPatente = view.getNumeroPatente().getText();
				data =  (java.util.Date)view.getScadenzaPatente().getDate();
		        if (data != null) {
		            Instant instant = data.toInstant();
		            dataLocal = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		        }else
		        	dataLocal = null;
				categoriePatente = controlloPatenteB();
				
				if (!nome.isEmpty() && !cognome.isEmpty() && !username.isEmpty() && !password.isEmpty()
						&& !numeroPatente.isEmpty() && data != null && dataLocal != null && categoriePatente != null) {
					
					for (Iterator<Utente> iterator = model.getElencoUtenti().iterator(); iterator.hasNext();) {
						Utente u = (Utente) iterator.next();
						if (u.getUsername().equals(username)) {
							JOptionPane.showMessageDialog(view.getFrame(), 
			                        "Lo username utilizzato é gia in uso", 
			                        "Username gia in uso", 
			                        JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (u.ruolo().equals("Cliente")) {
							Cliente cl = (Cliente) u;
							if (cl.getPatente().getNumero().equals(numeroPatente)) {
								JOptionPane.showMessageDialog(view.getFrame(), 
				                        "Il numero di patente inserito esiste gia", 
				                        "Numero patente duplicato", 
				                        JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						
					}
					
					
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			        String dataFormattata = dataLocal.format(formatter);
					try {
						Patente p = new Patente(numeroPatente, dataFormattata, categoriePatente);
						Cliente c = new Cliente(nome, cognome, username, password, p);
						model.aggiungiUtente(c);
						JOptionPane.showMessageDialog(view.getFrame(),
								"La registrazione ha avuto successo!",
								"Registrazione avvenuta",
								JOptionPane.INFORMATION_MESSAGE);
						ViewManager manager = new ViewManager();
						manager.passaAllaLoginUI(model);
						view.getFrame().dispose();
					} catch (NumeroPatenteInvalidoException e1) {
						JOptionPane.showMessageDialog(view.getFrame(), 
		                        "Numero Patente non valido", 
		                        "Errore Numero Patente", 
		                        JOptionPane.ERROR_MESSAGE);
						e1.getMessage();
						return;
					} catch (PatenteScadutaException e2) {
						JOptionPane.showMessageDialog(view.getFrame(), 
		                        "Patente Scaduta", 
		                        "Errore Scadenza Patente", 
		                        JOptionPane.ERROR_MESSAGE);
						e2.getMessage();
						return;
					} catch (CategoriaBPatenteException e3) {
						JOptionPane.showMessageDialog(view.getFrame(), 
		                        "Patente B non presente", 
		                        "Errore Patente B ", 
		                        JOptionPane.ERROR_MESSAGE);
						e3.getMessage();
						return;
					} catch (UsernameDuplicatoException e4) {
						JOptionPane.showMessageDialog(view.getFrame(), 
		                        "Patente B non presente", 
		                        "Errore Patente B ", 
		                        JOptionPane.ERROR_MESSAGE);
						e4.getMessage();
					} catch (SQLException e5) {
						JOptionPane.showMessageDialog(view.getFrame(), 
		                        "Errore Aggiunta DB", 
		                        "Errore DB", 
		                        JOptionPane.ERROR_MESSAGE);
						e5.getMessage();
						return;
					} catch (IOException e1) {
						e1.getMessage();
						return;
					}
					
				}else {
					JOptionPane.showMessageDialog(view.getFrame(), 
	                        "Non hai inserito tutte le informazioni necessarie, ricontrolla", 
	                        "Errore", 
	                        JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}

			private String[] controlloPatenteB() {
			
				List<String> patenti= new ArrayList<>();
				
				if (view.getCategoriePatenteA().isSelected()) {
					patenti.add(view.getCategoriePatenteA().getText());
				}
				
				if (view.getCategoriePatenteB().isSelected()) {
					patenti.add(view.getCategoriePatenteB().getText());
				}
				
				if (view.getCategoriePatenteC().isSelected()) {
					patenti.add(view.getCategoriePatenteC().getText());
				}
				
				if (patenti.size() == 0) {
					return null;
				}else
					return patenti.toArray(new String[patenti.size()]);
			}
		});
		
	}
	
	
	
}
