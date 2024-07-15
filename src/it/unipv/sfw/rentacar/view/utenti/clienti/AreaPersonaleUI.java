package it.unipv.sfw.rentacar.view.utenti.clienti;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unipv.sfw.rentacar.view.elementiPersonalizzati.CustomFrame;

public class AreaPersonaleUI {

	private CustomFrame frame;
	private JPanel mainPanel;
	private JLabel informazioniLabel;
	private JLabel contrattiNoleggioLabel;
	private JLabel nomeLabel;
	private JLabel cognomeLabel;
	private JLabel usernameLabel;
	private JLabel cambioPasswordLabel;
	private JLabel idContrattoLabel;
	private JLabel autoLabel;
	private JLabel aggiornaPatenteLabel;
	private JLabel numeroPatenteLabel;
	private JTextField nomeField;
	private JTextField cognomeField;
	private JTextField usernameField;
	private JTextField numeroPatenteField;
	private JButton cambioPasswordButton;
	private JButton aggiornaPatenteButton;
	
	public static void main(String[] args) throws IOException {
		AreaPersonaleUI area = new AreaPersonaleUI();
	}
	
	public AreaPersonaleUI() throws IOException {
		frame = new CustomFrame();
		
		mainPanel = frame.getMainPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
		
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
		informazioniLabel = new JLabel("Informazioni Personali");
		setFontTitoli(informazioniLabel);
		mainPanel.add(informazioniLabel, gbc);
		
		gbc.gridwidth = 1;
        gbc.gridy = 1;
		nomeLabel  = new JLabel("Nome :");
		setFontLabel(nomeLabel);
		mainPanel.add(nomeLabel, gbc);
		
		gbc.gridx = 1;
		nomeField = new JTextField();
		setDimensioneField(nomeField);
		nomeField.setEditable(false);
		mainPanel.add(nomeField, gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 2;
		cognomeLabel  = new JLabel("Cognome :");
		setFontLabel(cognomeLabel);
		mainPanel.add(cognomeLabel, gbc);
		
		gbc.gridx = 1;
		cognomeField = new JTextField();
		setDimensioneField(cognomeField);
		cognomeField.setEditable(false);
		mainPanel.add(cognomeField , gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 3;
        usernameLabel  = new JLabel("Username :");
        setFontLabel(usernameLabel);
		mainPanel.add(usernameLabel, gbc);
		
		gbc.gridx = 1;
		usernameField  = new JTextField();
		setDimensioneField(usernameField);
		usernameField.setEditable(false);
		mainPanel.add(usernameField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		numeroPatenteLabel = new JLabel("Numero Patente :");
		setFontLabel(numeroPatenteLabel);
		mainPanel.add(numeroPatenteLabel, gbc);
		
		gbc.gridx = 1;
		numeroPatenteField = new JTextField();
		setDimensioneField(numeroPatenteField);
		numeroPatenteField.setEditable(false);
		mainPanel.add(numeroPatenteField, gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 5;
		cambioPasswordButton  = new JButton();
		cambioPasswordLabel = new JLabel("Cambia Password");
		cambioPasswordButton.add(cambioPasswordLabel);
		mainPanel.add(cambioPasswordButton, gbc);
		
		gbc.gridx = 1;
		aggiornaPatenteButton = new JButton();
		aggiornaPatenteLabel = new JLabel("Aggiorna Patente");
		aggiornaPatenteButton.add(aggiornaPatenteLabel);
		mainPanel.add(aggiornaPatenteButton, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
        gbc.gridy = 6;
		contrattiNoleggioLabel = new JLabel("Contratti Noleggio");
		setFontTitoli(contrattiNoleggioLabel);
		mainPanel.add(contrattiNoleggioLabel, gbc);
		
		gbc.gridwidth = 1;
        gbc.gridy = 7;
        idContrattoLabel = new JLabel("ID Contratto");
        setFontLabel(idContrattoLabel);
        mainPanel.add(idContrattoLabel, gbc);
        
		gbc.gridx = 1;
        autoLabel = new JLabel("Auto");
        setFontLabel(autoLabel);
        mainPanel.add(autoLabel, gbc);
		
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public JLabel getNomeLabel() {
		return nomeLabel;
	}

	public void setNomeLabel(JLabel nomeLabel) {
		this.nomeLabel = nomeLabel;
	}

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

	public JLabel getInformazioniLabel() {
		return informazioniLabel;
	}

	public void setInformazioniLabel(JLabel informazioniLabel) {
		this.informazioniLabel = informazioniLabel;
	}

	public JLabel getContrattiNoleggioLabel() {
		return contrattiNoleggioLabel;
	}

	public void setContrattiNoleggioLabel(JLabel contrattiNoleggioLabel) {
		this.contrattiNoleggioLabel = contrattiNoleggioLabel;
	}

	public JLabel getCognomeLabel() {
		return cognomeLabel;
	}

	public void setCognomeLabel(JLabel cognomeLabel) {
		this.cognomeLabel = cognomeLabel;
	}

	public JLabel getUsernameLabel() {
		return usernameLabel;
	}

	public void setUsernameLabel(JLabel usernameLabel) {
		this.usernameLabel = usernameLabel;
	}

	public JButton getCambioPasswordButton() {
		return cambioPasswordButton;
	}

	public void setCambioPasswordButton(JButton cambioPasswordButton) {
		this.cambioPasswordButton = cambioPasswordButton;
	}

	private void setFontTitoli(JLabel label) {
		label.setFont(new Font("Arial", Font.PLAIN, 30));
		label.setForeground(Color.BLUE);
	}
	
	private void setFontLabel(JLabel label) {
		label.setFont(new Font("Palatino Linotype", Font.PLAIN, 20));
	}
	
	private void setDimensioneField(JTextField field) {
		field.setPreferredSize(new Dimension(150, 25));	
	}
	
}
