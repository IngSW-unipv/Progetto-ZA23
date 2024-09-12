package it.unipv.sfw.rentacar.controller.catalogoauto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import it.unipv.sfw.rentacar.controller.managerview.ViewManager;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.utenti.log.SessioneLogin;
import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.model.veicolo.noleggio.Noleggio;
import it.unipv.sfw.rentacar.view.catalogoauto.CatalogoAutoUI;

public class CatalogoAutoController {

	private AgenziaNoleggioAuto model;
	private CatalogoAutoUI view;
	private Map<JButton, Auto> pulsantiAutoMap = new HashMap<>();
	
	
	public CatalogoAutoController(AgenziaNoleggioAuto model, CatalogoAutoUI view) throws UnsupportedLookAndFeelException {
		this.model = model;
		this.view = view;
		initController();
	}


	private void initController() throws UnsupportedLookAndFeelException {
		
		mostraCatalogoAuto();
		
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
	
		view.getFrame().getTitlePanel().getRegisterButton().addActionListener(new ActionListener() {
			
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
		
		view.getCercaButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String marca, modello;
				
				marca = view.getRicercaMarca().getText().toLowerCase();
				modello = view.getRicercaModello().getText().toLowerCase();
				
				if (marca.isEmpty() && modello.isEmpty()) {
					try {
						mostraCatalogoAuto();
						return;
					} catch (UnsupportedLookAndFeelException e1) {
						e1.getMessage();
						return;
					}
				}
				
				
				if (!marca.isEmpty() && modello.isEmpty()) {
					List<Auto> auto = new ArrayList<>();
					auto = model.cercaAutoPerMarca(marca);
					try {
						filtraMarcaAuto(auto, marca);
						return;
					} catch (UnsupportedLookAndFeelException e1) {
						e1.getMessage();
						return;
					}
					
				}
				
				if (!marca.isEmpty() && !modello.isEmpty()) {
					List<Auto> auto = new ArrayList<>();
					auto = model.cercaAutoPerMarcaEPerModello(marca, modello);
					try {
						filtraMarcaModelloAuto(auto, marca, modello);
						return;
					} catch (UnsupportedLookAndFeelException e1) {
						e1.getMessage();
						return;
					}
				}
				
				if (marca.isEmpty() && !modello.isEmpty()) {
					JOptionPane.showMessageDialog(view.getFrame(),
							"Inserisci una marca!",
							"Errore Marca",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				return;
					
			}
			
			private void filtraMarcaAuto(List<Auto> auto, String marca) throws UnsupportedLookAndFeelException {
				pulisciCatalogo();
				for (Auto a : auto) {
					if (a.getStatoNoleggio().equals(Noleggio.DISPONIBILE) && a.getMarca().toLowerCase().contains(marca)) {
						JPanel cardAuto = creaCardAuto(a);
						view.getCatalogo().add(cardAuto);
					}
				}
				aggiornaCatalogo();
			}

			private void filtraMarcaModelloAuto(List<Auto> auto, String marca, String modello) throws UnsupportedLookAndFeelException {
				pulisciCatalogo();
				for (Auto a : auto) {
					if (a.getStatoNoleggio().equals(Noleggio.DISPONIBILE) && a.getMarca().toLowerCase().contains(marca) 
							&& a.getModello().toLowerCase().contains(modello)) {
						JPanel cardAuto = creaCardAuto(a);
						view.getCatalogo().add(cardAuto);
					}
				}
				aggiornaCatalogo();
			}
			
		});
		
	}

	private void mostraCatalogoAuto() throws UnsupportedLookAndFeelException {
		pulisciCatalogo();
		for (Auto a : model.getElencoAuto()) {
			if (a.getStatoNoleggio().equals(Noleggio.DISPONIBILE)) {
				JPanel cardAuto = creaCardAuto(a);
				view.getCatalogo().add(cardAuto);
			}
		}
		aggiornaCatalogo();
	
	}

	public JPanel creaCardAuto(Auto a) throws UnsupportedLookAndFeelException {
		
		JPanel auto = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		auto.setLayout(new GridBagLayout());
		gbc.insets = new Insets(5, 5, 5, 5); 
		
		auto.setPreferredSize(new Dimension(200, 100));
		auto.setBackground(Color.ORANGE);
		
		auto.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		JLabel marcaLabel = new JLabel("Marca: " + a.getMarca());
        JLabel modelloLabel = new JLabel("Modello: " + a.getModello());
        JLabel costoNoleggioLabel = new JLabel("Costo Noleggio Giornaliero: " + a.getCostoNoleggioGiornaliero() + " €");
		JButton noleggiaButton = new JButton("Noleggia");
        
		gbc.gridx = 0;
        gbc.gridy = 0;
        auto.add(marcaLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        auto.add(modelloLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        auto.add(costoNoleggioLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        auto.add(noleggiaButton, gbc);
        
        pulsantiAutoMap.put(noleggiaButton, a);
        
        noleggiaButton.addActionListener(new ActionListener() {
            
        	@Override
            public void actionPerformed(ActionEvent e) {
                
        		if (controlloTipoUtenteLoggato()) {
        			Auto autoSelezionata = pulsantiAutoMap.get(noleggiaButton);
                    try {
    					ViewManager manager = new ViewManager();
    					manager.passaANoleggioAutoUI(model, autoSelezionata);
    					view.getFrame().dispose();
    				} catch (IOException e1) {
    					e1.printStackTrace();
    				}
				}
        		return;
            }
        });
        
		return auto;
	}
	
	private boolean controlloTipoUtenteLoggato() {
		
		if (SessioneLogin.getInstance().getUtenteLoggato() == null) {
			JOptionPane.showMessageDialog(view.getFrame(),
					"Devi effettuare il login per noleggiare un'auto!",
					"Errore Noleggio",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (SessioneLogin.getInstance().getUtenteLoggato().ruolo().equals("Amministratore")) {
			JOptionPane.showMessageDialog(view.getFrame(),
					"Gli amministratori non possono effettuare il noleggio!",
					"Errore Noleggio",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	private void aggiornaCatalogo() {
	    view.getCatalogo().revalidate();
	    view.getCatalogo().repaint();
	}
	
	
	private void pulisciCatalogo() {
		view.getCatalogo().removeAll();
		pulsantiAutoMap.clear();
	}
	
}
