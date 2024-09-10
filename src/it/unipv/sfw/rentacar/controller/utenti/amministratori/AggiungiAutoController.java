package it.unipv.sfw.rentacar.controller.utenti.amministratori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import it.unipv.sfw.rentacar.controller.managerview.ViewManager;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.database.dao.AutoDAO;
import it.unipv.sfw.rentacar.model.exception.TargaNonValidaException;
import it.unipv.sfw.rentacar.model.utenti.Amministratore;
import it.unipv.sfw.rentacar.model.utenti.log.SessioneLogin;
import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.Cambio;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.CaratteristicheTecniche;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.Carburante;
import it.unipv.sfw.rentacar.view.utenti.amministratori.AggiungiAutoUI;

public class AggiungiAutoController {

	private AgenziaNoleggioAuto model;
	private AggiungiAutoUI view;
	
	public AggiungiAutoController(AgenziaNoleggioAuto model, AggiungiAutoUI view) {
		this.model = model;
		this.view = view;
		initController();
	}

	private void initController() {
		
		view.getAggiungiAutoButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String targa, modello, marca;
				double costoNoleggio;
				int annoProduzione, postiAuto, cilindrata, potenza, count;
				Cambio cambio;
				Carburante[] carburante;
				
				targa = view.getTargaField().getText();
				marca = view.getMarcaField().getText();
				modello = view.getModelloField().getText();
				annoProduzione = (Integer) view.getAnnoProduzioneField().getValue();
				costoNoleggio = (Double) view.getCostoGiornalieroField().getValue();
				cambio = (Cambio)view.getTipoCambioField().getSelectedItem();
				carburante = controlloCarburanti();
				postiAuto = (Integer)view.getPostiAutoField().getSelectedItem();
				cilindrata = (Integer)view.getCilindrataField().getValue();
				potenza = (Integer)view.getPotenzaField().getValue();
				
				if (!targa.isEmpty() && !marca.isEmpty() && !modello.isEmpty() && carburante != null &&
						costoNoleggio != 0 && cilindrata != 0 && potenza != 0) {

					for (Auto a : model.getElencoAuto()) {
						if (a.getTarga().equals(targa)) {
							JOptionPane.showMessageDialog(view.getFrame(), 
			                        "La targa inserita esiste gia", 
			                        "Targa Duplicata", 
			                        JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					
					try {
						CaratteristicheTecniche ct = new CaratteristicheTecniche(annoProduzione, cambio, carburante, postiAuto, cilindrata, potenza);
						Auto a = new Auto(targa, marca, modello, ct, costoNoleggio);
						Amministratore amm = (Amministratore)SessioneLogin.getInstance().getUtenteLoggato();
						amm.aggiungiAuto(model, a);
						JOptionPane.showMessageDialog(view.getFrame(),
								"Auto aggiunta con successo!",
								"Aggiunta avvenuta",
								JOptionPane.INFORMATION_MESSAGE);
						ViewManager manager = new ViewManager();
						manager.passaAllaHomePageUI(model);
						view.getFrame().dispose();
						return;
					} catch (TargaNonValidaException e1) {
						JOptionPane.showMessageDialog(view.getFrame(), 
		                        "Targa Inserita non valida", 
		                        "Targa non Valida", 
		                        JOptionPane.ERROR_MESSAGE);
						e1.getMessage();
						return;
					} catch (IOException e1) {
						e1.printStackTrace();
						return;
					} catch (SQLException e1) {
						e1.printStackTrace();
						return;
					}
					
				} else {
					JOptionPane.showMessageDialog(view.getFrame(), 
	                        "Campi non validi o hai effettuato nessuna / troppe scelte su carburante", 
	                        "Errore", 
	                        JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			private Carburante[] controlloCarburanti() {
				List<Carburante> carburante;
				int count = 0;
				carburante = new ArrayList<>();
				count = 0;
				if (view.getBenzinaCheckBox().isSelected()) {
					carburante.add(Carburante.BENZINA);
					count++;
				}
				
				if (view.getGplCheckBox().isSelected()) {
					carburante.add(Carburante.GPL);
					count++;
				}
				
				if (view.getGasolioCheckBox().isSelected()) {
					carburante.add(Carburante.GASOLIO);
					count++;
				}
				
				if (view.getElettricaCheckBox().isSelected()) {
					carburante.add(Carburante.ELETTRICA);
					count++;
				}
				
				if (count > 2 || count == 0)
					return null;
				
				return carburante.toArray(new Carburante[carburante.size()]);
				
			}

		});
		
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
		
	}
}
