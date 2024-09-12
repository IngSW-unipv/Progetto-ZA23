package it.unipv.sfw.rentacar.controller.catalogoauto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import it.unipv.sfw.rentacar.controller.managerview.ViewManager;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.contratti.ContrattoNoleggio;
import it.unipv.sfw.rentacar.model.contratti.pagamenti.CartaDiCredito;
import it.unipv.sfw.rentacar.model.database.dao.AutoDAO;
import it.unipv.sfw.rentacar.model.exception.CartaDiCreditoScadutaException;
import it.unipv.sfw.rentacar.model.utenti.Cliente;
import it.unipv.sfw.rentacar.model.utenti.log.SessioneLogin;
import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.model.veicolo.noleggio.Noleggio;
import it.unipv.sfw.rentacar.view.catalogoauto.NoleggioAutoUI;

public class NoleggioAutoController {

	private AgenziaNoleggioAuto model;
	private NoleggioAutoUI view;
	
	
	public NoleggioAutoController(AgenziaNoleggioAuto model, NoleggioAutoUI view, Auto a) {
		this.model = model;
		this.view = view;
		initController(a);
	}

	private void initController(Auto a) {
		
		impostaCampi(a);
		
		view.getDataInizioNoleggio().getDateEditor().addPropertyChangeListener(e -> aggiornaImporto(a));
	    view.getDataFineNoleggio().getDateEditor().addPropertyChangeListener(e -> aggiornaImporto(a));
		
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

		
		view.getNoleggioButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String numeroCarta, stringCVV;
				java.util.Date dataInizio, dataFine, dataScadenzaCarta;
				LocalDate dataLocalInizio, dataLocalFine, dataLocalScadenza;
				
				dataInizio =  (java.util.Date)view.getDataInizioNoleggio().getDate();
		        if (dataInizio != null) {
		            Instant instant = dataInizio.toInstant();
		            dataLocalInizio = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		        }else
		        	dataLocalInizio = null;
		        
		        dataFine =  (java.util.Date)view.getDataFineNoleggio().getDate();
		        if (dataFine != null) {
		            Instant instant = dataFine.toInstant();
		            dataLocalFine = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		        }else
		        	dataLocalFine = null;
		        
		        numeroCarta = view.getNumeroCarta().getText();
		        dataScadenzaCarta =  (java.util.Date)view.getScadenzaCarta().getDate();
		        if (dataScadenzaCarta != null) {
		            Instant instant = dataScadenzaCarta.toInstant();
		            dataLocalScadenza = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		        }else
		        	dataLocalScadenza = null;
		        stringCVV = view.getCVV().getText();
				
		        if (!numeroCarta.isEmpty() &&  dataInizio != null && dataLocalInizio != null
		        		&& dataFine != null && dataLocalFine != null && dataScadenzaCarta != null
		        		&& dataLocalScadenza != null && stringCVV.length() == 3) {
		        	
		        	if (dataLocalInizio.isBefore(LocalDate.now()) || dataLocalInizio.isAfter(dataLocalFine)) {
						JOptionPane.showMessageDialog(view.getFrame(), 
		                        "Date noleggio selezionate non valide", 
		                        "Errore Periodo Noleggio", 
		                        JOptionPane.ERROR_MESSAGE);
						return;
					}

		        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        	String dataInizioNoleggio = dataLocalInizio.format(formatter);
		        	String dataFineNoleggio = dataLocalFine.format(formatter);
		        	String dataScadenza = dataLocalScadenza.format(formatter);
		        	int cvv = Integer.parseInt(view.getCVV().getText());
		        	
		        	try {
			        	Cliente c = (Cliente)SessioneLogin.getInstance().getUtenteLoggato();
			        	
			        	if (c.getPatente().getScadenza().isBefore(LocalDate.now())) {
							JOptionPane.showMessageDialog(view.getFrame(), 
			                        "La tua patente é scaduta, devi rinnovarla !", 
			                        "Errore Noleggio", 
			                        JOptionPane.ERROR_MESSAGE);
							return;
						}
			        	
			        	for (ContrattoNoleggio contratto : model.getContratti()) {
							if (c.getPatente().getNumero().equals(contratto.getCliente().getPatente().getNumero()) 
									&& contratto.getFineNoleggio().isAfter(LocalDate.now())) {
								JOptionPane.showMessageDialog(view.getFrame(), 
				                        "Hai un noleggio ancora in corso ! Impossibile Proseguire", 
				                        "Errore Noleggio", 
				                        JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
			        	
						CartaDiCredito carta = new CartaDiCredito(numeroCarta, dataScadenza, cvv);
						ContrattoNoleggio cn = new ContrattoNoleggio(c, a, dataInizioNoleggio, dataFineNoleggio, carta);
						model.aggiungiContratto(cn);
						
						AutoDAO autoDAO = new AutoDAO();
						autoDAO.aggiornaStatoNoleggio(a);
						
						model.stampaAuto();
						JOptionPane.showMessageDialog(view.getFrame(), 
		                        "Contratto aggiunto con successo", 
		                        "Errore", 
		                        JOptionPane.INFORMATION_MESSAGE);
			        	ViewManager manager = new ViewManager();
			        	manager.passaAlCatalogoAutoUI(model);
						view.getFrame().dispose();
					} catch (CartaDiCreditoScadutaException e1) {
						JOptionPane.showMessageDialog(view.getFrame(), 
		                        "Carta di Credito Scaduta", 
		                        "Errore Carta di Credito", 
		                        JOptionPane.ERROR_MESSAGE);
						e1.getMessage();
						return;
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(view.getFrame(), 
		                        "Errore Database", 
		                        "Errore", 
		                        JOptionPane.ERROR_MESSAGE);
						e2.getMessage();
						return;
					} catch (IllegalArgumentException e3) {
						JOptionPane.showMessageDialog(view.getFrame(), 
		                        "Errore Cliente", 
		                        "Errore", 
		                        JOptionPane.ERROR_MESSAGE);
						e3.getMessage();
						return;
					} catch (DateTimeException e4) {
						JOptionPane.showMessageDialog(view.getFrame(), 
		                        "Errore Date Noleggio", 
		                        "Errore", 
		                        JOptionPane.ERROR_MESSAGE);
						e4.getMessage();
						return;
					} catch (IOException e5) {
						e5.getMessage();
						return;
					} catch (UnsupportedLookAndFeelException e6) {
						e6.getMessage();
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
		});
		
		
	}

	private void aggiornaImporto(Auto a) {
	    java.util.Date dataInizio = (java.util.Date) view.getDataInizioNoleggio().getDate();
	    java.util.Date dataFine = (java.util.Date) view.getDataFineNoleggio().getDate();
	    
	    if (dataInizio != null && dataFine != null) {
	        LocalDate dataLocalInizio = dataInizio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalDate dataLocalFine = dataFine.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        
	        if (!dataLocalInizio.isAfter(dataLocalFine)) {
	        	
	            long giorniNoleggio = java.time.temporal.ChronoUnit.DAYS.between(dataLocalInizio, dataLocalFine) + 1;
	            
	            double costoGiornaliero = a.getCostoNoleggioGiornaliero();
	            double importoTotale = giorniNoleggio * costoGiornaliero;
	            
	            view.getImportoTotaleNoleggio().setText(String.format("%.2f", importoTotale));
	        } else {
	            view.getImportoTotaleNoleggio().setText("");
	        }
	    } else {
	        view.getImportoTotaleNoleggio().setText("");
	    }
	}


	private void impostaCampi(Auto a) {
		
		view.getTargaCampo().setText(a.getTarga());
		view.getMarcaCampo().setText(a.getMarca());
		view.getModelloCampo().setText(a.getModello());
		view.getCostoNoleggioGiornalieroCampo().setText(String.valueOf(a.getCostoNoleggioGiornaliero()));
		view.getAnnoProduzioneCampo().setText(String.valueOf(a.getCaratteristicheTecniche().getAnnoProduzione()));
		view.getTipoCambioCampo().setText(String.valueOf(a.getCaratteristicheTecniche().getTipoCambio()));
		view.getTipoCarburanteCampo1().setText(String.valueOf(a.getCaratteristicheTecniche().getTipoCarburante()[0]));
		if (a.getCaratteristicheTecniche().getTipoCarburante()[1] != null) {
			view.getTipoCarburanteCampo2().setText(String.valueOf(a.getCaratteristicheTecniche().getTipoCarburante()[1]));	
		}
		view.getPostiAutoCampo().setText(String.valueOf(a.getCaratteristicheTecniche().getPostiAuto()));
		view.getCilindrataCampo().setText(String.valueOf(a.getCaratteristicheTecniche().getCilindrata()));
		view.getPotenzaCampo().setText(String.valueOf(a.getCaratteristicheTecniche().getPotenza()));
		
	}
	
	
	
}
