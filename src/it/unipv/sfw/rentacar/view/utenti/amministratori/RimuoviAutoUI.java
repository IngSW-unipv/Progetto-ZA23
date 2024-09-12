package it.unipv.sfw.rentacar.view.utenti.amministratori;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unipv.sfw.rentacar.view.elementiPersonalizzati.CustomFrame;

/*
 * View RimuoviAuto
 */

public class RimuoviAutoUI {

	private CustomFrame frame;
	private JPanel mainPanel;
	private JLabel titolo;
	private JLabel targaLabel;
	private JTextField targaField;
	private JButton confermaButton;
	private JButton annullaButton;
	
	public RimuoviAutoUI() throws IOException {
		frame = new CustomFrame();
		
		mainPanel = frame.getMainPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		
		titolo = new JLabel("Rimuovi Auto");
		titolo.setFont(new Font("Comic Sans MS", Font.PLAIN, 45));
		titolo.setForeground(Color.BLUE);
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(titolo, gbc);
		
		targaLabel = new JLabel("Targa : ");
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.add(targaLabel, gbc);
		
		targaField = new JTextField();
		targaField.setPreferredSize(new Dimension(120,20));
		gbc.gridx = 1;
		gbc.gridy = 1;
		mainPanel.add(targaField, gbc);
		
		confermaButton = new JButton("Conferma");
		gbc.gridx = 0;
		gbc.gridy = 2;
		mainPanel.add(confermaButton, gbc);
		
		annullaButton = new JButton("Annulla");
		gbc.gridx = 1;
		gbc.gridy = 2;
		mainPanel.add(annullaButton, gbc);
		
		frame.setVisible(true);
	}

	// Getter e Setter
	
	public CustomFrame getFrame() {
		return frame;
	}

	public void setFrame(CustomFrame frame) {
		this.frame = frame;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public JLabel getTitolo() {
		return titolo;
	}

	public void setTitolo(JLabel titolo) {
		this.titolo = titolo;
	}

	public JLabel getTargaLabel() {
		return targaLabel;
	}

	public void setTargaLabel(JLabel targaLabel) {
		this.targaLabel = targaLabel;
	}

	public JTextField getTargaField() {
		return targaField;
	}

	public void setTargaField(JTextField targaField) {
		this.targaField = targaField;
	}

	public JButton getConfermaButton() {
		return confermaButton;
	}

	public void setConfermaButton(JButton confermaButton) {
		this.confermaButton = confermaButton;
	}

	public JButton getAnnullaButton() {
		return annullaButton;
	}

	public void setAnnullaButton(JButton annullaButton) {
		this.annullaButton = annullaButton;
	}
	
}
