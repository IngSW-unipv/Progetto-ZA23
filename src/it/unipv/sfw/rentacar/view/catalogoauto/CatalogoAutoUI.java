package it.unipv.sfw.rentacar.view.catalogoauto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import it.unipv.sfw.rentacar.model.exception.CategoriaBPatenteException;
import it.unipv.sfw.rentacar.model.exception.NumeroPatenteInvalidoException;
import it.unipv.sfw.rentacar.model.exception.PatenteScadutaException;
import it.unipv.sfw.rentacar.model.exception.TargaNonValidaException;
import it.unipv.sfw.rentacar.model.exception.UsernameDuplicatoException;
import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.view.elementiPersonalizzati.CustomFrame;

public class CatalogoAutoUI {

	private CustomFrame frame;
	private JPanel ricercaPanel;
	private JPanel catalogo;
    private JTextField ricercaMarca;
    private JTextField ricercaModello;
    private JLabel marcaLabel;
    private JLabel modelloLabel;
	private JButton cercaButton;
	
	public CatalogoAutoUI() throws IOException, UnsupportedLookAndFeelException {
		
		frame = new CustomFrame();

		frame.getMainPanel().setLayout(new BorderLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
        ricercaPanel = new JPanel();
        ricercaPanel.setBackground(new Color(168, 255, 184));
        ricercaPanel.setLayout(new GridBagLayout());
        ricercaMarca = new JTextField(10);
        JLabel marcaLabel = new JLabel("Marca : ");
        ricercaModello = new JTextField(10);
        JLabel modelloLabel = new JLabel("Modello : ");
        cercaButton = new JButton("Cerca");
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        ricercaPanel.add(marcaLabel);

        gbc.gridx = 1;
        gbc.gridy = 0;
        ricercaPanel.add(marcaLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        ricercaPanel.add(ricercaMarca, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        ricercaPanel.add(modelloLabel, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        ricercaPanel.add(ricercaModello, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        ricercaPanel.add(cercaButton, gbc);
        
        frame.getMainPanel().add(ricercaPanel, BorderLayout.NORTH);
        
        catalogo = new JPanel();
        catalogo.setLayout(new GridLayout(0, 3, 10, 10));
        
        frame.getMainPanel().add(catalogo, BorderLayout.CENTER);
        
		JScrollPane scrollPane = new JScrollPane(frame.getMainPanel());
		frame.add(scrollPane);
		
		frame.getMainPanel().setVisible(true);
		frame.setVisible(true);
	}
	/*
	public JPanel creaCardAuto(Auto a) throws UnsupportedLookAndFeelException {
	
		JPanel auto = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		auto.setLayout(new GridBagLayout());
		gbc.insets = new Insets(5, 5, 5, 5); 
		
		auto.setPreferredSize(new Dimension(200, 100));
		auto.setBackground(Color.ORANGE);
		
		auto.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		marcaLabel = new JLabel("Marca: " + a.getMarca());
        modelloLabel = new JLabel("Modello: " + a.getModello());
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
        
		return auto;
	}
	/*
	public static void main(String[] args) throws IOException, NumeroPatenteInvalidoException, PatenteScadutaException, CategoriaBPatenteException, TargaNonValidaException, UnsupportedLookAndFeelException, UsernameDuplicatoException {
		/*
		String[] cat = {"B"};
		Patente p = new Patente("AB123456CC", "18/05/2025", cat);
		
		Utente cl = new Cliente("Roberto", "Pitorac" , "Pito", "Pitorac01", p);
		Amministratore amm = new Amministratore("Roberto", "Pitorac" , "Pito", "Pitorac01");
		AgenziaNoleggioAuto agenzia = AgenziaNoleggioAuto.getInstance("Rent-a-Car", "Via Mazzini, 17");
		agenzia.aggiungiUtente(cl);
		Carburante[] carburante = {Carburante.BENZINA};
		CaratteristicheTecniche ct1 = new CaratteristicheTecniche(2008, Cambio.AUTOMATICO, carburante, 5, 150, 200);
		Auto a1 = new Auto("AB456CD", "Fiat", "Panda", ct1, 5);
		Auto a2 = new Auto("AB789CD", "Ford", "Fiesta", ct1, 10);
		Auto a3 = new Auto("AB000CD", "Volkswagen", "Golf", ct1, 7);
		Auto a4 = new Auto("AB159CD", "Fiat", "Panda", ct1, 6.5);
		Auto a5 = new Auto("AB456CD", "Fiat", "Panda", ct1, 5);
		Auto a6 = new Auto("AB789CD", "Ford", "Fiesta", ct1, 10);
		Auto a7 = new Auto("AB000CD", "Volkswagen", "Golf", ct1, 7);
		Auto a8 = new Auto("AB159CD", "Fiat", "Panda", ct1, 6.5);
		amm.aggiungiAuto(agenzia, a1);
		amm.aggiungiAuto(agenzia, a2);
		amm.aggiungiAuto(agenzia, a3);
		amm.aggiungiAuto(agenzia, a4);
		amm.aggiungiAuto(agenzia, a5);
		amm.aggiungiAuto(agenzia, a6);
		amm.aggiungiAuto(agenzia, a7);
		amm.aggiungiAuto(agenzia, a8);
		amm.aggiungiAuto(agenzia, a1);
		amm.aggiungiAuto(agenzia, a2);
		amm.aggiungiAuto(agenzia, a3);
		amm.aggiungiAuto(agenzia, a4);
		amm.aggiungiAuto(agenzia, a5);
		amm.aggiungiAuto(agenzia, a6);
		amm.aggiungiAuto(agenzia, a7);
		amm.aggiungiAuto(agenzia, a8);
		*/
		//AgenziaNoleggioAuto agenzia = AgenziaNoleggioAuto.getInstance("Rent a Car","Via roma, mediglia");
		//new CatalogoAutoController(agenzia, new CatalogoAutoUI());
	//}

	public JTextField getRicercaMarca() {
		return ricercaMarca;
	}

	public void setRicercaMarca(JTextField ricercaMarca) {
		this.ricercaMarca = ricercaMarca;
	}

	public JTextField getRicercaModello() {
		return ricercaModello;
	}

	public void setRicercaModello(JTextField ricercaModello) {
		this.ricercaModello = ricercaModello;
	}

	public JLabel getMarcaLabel() {
		return marcaLabel;
	}

	public void setMarcaLabel(JLabel marcaLabel) {
		this.marcaLabel = marcaLabel;
	}

	public JLabel getModelloLabel() {
		return modelloLabel;
	}

	public void setModelloLabel(JLabel modelloLabel) {
		this.modelloLabel = modelloLabel;
	}

	public JButton getCercaButton() {
		return cercaButton;
	}

	public void setCercaButton(JButton cercaButton) {
		this.cercaButton = cercaButton;
	}

	public JPanel getRicercaPanel() {
		return ricercaPanel;
	}

	public void setRicercaPanel(JPanel ricercaPanel) {
		this.ricercaPanel = ricercaPanel;
	}

	public CustomFrame getFrame() {
		return frame;
	}

	public void setFrame(CustomFrame frame) {
		this.frame = frame;
	}

	public JPanel getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(JPanel catalogo) {
		this.catalogo = catalogo;
	}

}
