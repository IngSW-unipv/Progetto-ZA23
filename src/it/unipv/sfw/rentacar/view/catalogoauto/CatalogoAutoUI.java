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

/*
 * View CatalogoAuto
 */

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

	// Getter e Setter
	
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
