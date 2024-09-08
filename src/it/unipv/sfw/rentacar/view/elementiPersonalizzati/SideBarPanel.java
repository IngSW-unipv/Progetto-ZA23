package it.unipv.sfw.rentacar.view.elementiPersonalizzati;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import it.unipv.sfw.rentacar.model.utenti.log.SessioneLogin;

public class SideBarPanel extends JPanel{

	private JLabel homeLabel;
	private JLabel noleggioLabel;
	private JLabel areaPersonaleLabel;
	private JLabel aggiungiAutoLabel;
	private JLabel rimuoviAutoLabel;
	private JButton homeButton;
	private JButton noleggioButton;
	private JButton areaPersonaleButton;
	private JButton aggiungiAutoButton;
	private JButton rimuoviAutoButton;
	
	public SideBarPanel() {
		super();
		
		setBackground(new Color(173, 233, 255));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Border customBorder = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK);
		
		homeLabel = new JLabel("Home");
		homeButton = new JButton();
		homeButton.add(homeLabel);
		setButtonDimension(homeButton);
        
		noleggioLabel = new JLabel("Catalogo");
        noleggioButton = new JButton();
        noleggioButton.add(noleggioLabel);
        setButtonDimension(noleggioButton);
        
        aggiungiAutoLabel = new JLabel("Aggiungi Auto");
        aggiungiAutoButton = new JButton();
        aggiungiAutoButton.add(aggiungiAutoLabel);
        setButtonDimension(aggiungiAutoButton);
        
        rimuoviAutoLabel = new JLabel("Rimuovi Auto");
        rimuoviAutoButton = new JButton();
        rimuoviAutoButton.add(rimuoviAutoLabel );
        setButtonDimension(rimuoviAutoButton);
        
        areaPersonaleLabel = new JLabel("Area Personale");
        areaPersonaleButton = new JButton();
        areaPersonaleButton.add(areaPersonaleLabel);
        setButtonDimension(areaPersonaleButton);
        
        setBorder(customBorder);
        add(homeButton);
        add(noleggioButton);
        add(aggiungiAutoButton);
        add(rimuoviAutoButton);
        add(areaPersonaleButton);
        
        areaPersonaleButton.setVisible(false);
        aggiungiAutoButton.setVisible(false);
        rimuoviAutoButton.setVisible(false);
        
        if (SessioneLogin.getInstance().isLoggedIn()) {
			this.modalitàUtente();
		}else
			this.modalitàLogout();
        
	}

	public JLabel getNoleggioLabel() {
		return noleggioLabel;
	}

	public void setNoleggioLabel(JLabel noleggioLabel) {
		this.noleggioLabel = noleggioLabel;
	}

	public JButton getNoleggioButton() {
		return noleggioButton;
	}

	public void setNoleggioButton(JButton noleggioButton) {
		this.noleggioButton = noleggioButton;
	}

	public JLabel getHomeLabel() {
		return homeLabel;
	}

	public void setHomeLabel(JLabel homeLabel) {
		this.homeLabel = homeLabel;
	}

	public JButton getHomeButton() {
		return homeButton;
	}

	public void setHomeButton(JButton homeButton) {
		this.homeButton = homeButton;
	}

	public JLabel getAggiungiAutoLabel() {
		return aggiungiAutoLabel;
	}

	public void setAggiungiAutoLabel(JLabel aggiungiAutoLabel) {
		this.aggiungiAutoLabel = aggiungiAutoLabel;
	}

	public JButton getAggiungiAutoButton() {
		return aggiungiAutoButton;
	}

	public void setAggiungiAutoButton(JButton aggiungiAutoButton) {
		this.aggiungiAutoButton = aggiungiAutoButton;
	}

	public JLabel getAreaPersonaleLabel() {
		return areaPersonaleLabel;
	}

	public void setAreaPersonaleLabel(JLabel areaPersonaleLabel) {
		this.areaPersonaleLabel = areaPersonaleLabel;
	}

	public JButton getAreaPersonaleButton() {
		return areaPersonaleButton;
	}

	public void setAreaPersonaleButton(JButton areaPersonaleButton) {
		this.areaPersonaleButton = areaPersonaleButton;
	}
	
	public JLabel getRimuoviAutoLabel() {
		return rimuoviAutoLabel;
	}

	public void setRimuoviAutoLabel(JLabel rimuoviAutoLabel) {
		this.rimuoviAutoLabel = rimuoviAutoLabel;
	}

	public JButton getRimuoviAutoButton() {
		return rimuoviAutoButton;
	}

	public void setRimuoviAutoButton(JButton rimuoviAutoButton) {
		this.rimuoviAutoButton = rimuoviAutoButton;
	}

	private void setButtonDimension(JButton button) {
    	button.setMaximumSize(new Dimension(150, 35));
    }
	
	public void modalitàUtente() {
		if (SessioneLogin.getInstance().getUtenteLoggato().ruolo().equals("Cliente")) {
			this.getAreaPersonaleButton().setVisible(true);
		}else {
			this.getAggiungiAutoButton().setVisible(true);
			this.getRimuoviAutoButton().setVisible(true);
		}
	}
	
	public void modalitàLogout() {
		this.getAreaPersonaleButton().setVisible(false);
	}

}
