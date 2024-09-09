package it.unipv.sfw.rentacar.controller.homepage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import it.unipv.sfw.rentacar.controller.managerview.ViewManager;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.utenti.log.SessioneLogin;
import it.unipv.sfw.rentacar.view.homepage.HomepageUI;

public class HomepageController {

	private AgenziaNoleggioAuto model;
	private HomepageUI view;
	
	public HomepageController(AgenziaNoleggioAuto model, HomepageUI view) {
		this.model = model;
		this.view = view;
		initController();
	}

	private void initController() {
		
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
	
		view.getButtonNoleggio().addActionListener(new ActionListener() {
			
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
		
	}
	
	
}
