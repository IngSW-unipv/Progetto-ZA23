package it.unipv.sfw.rentacar.view.utenti.clienti;

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
 *  View CambioPassword
 */

public class CambioPasswordUI {

	private CustomFrame frame;
	private JPanel mainPanel;
	private JLabel cambioPassword;
	private JLabel vecchiaPassLabel;
	private JLabel nuovaPassLabel;
	private JTextField vecchiaPassField;
	private JTextField nuovaPassField;
	private JButton confermaPass;
	private JButton annullaPass;
	
	public CambioPasswordUI() throws IOException {
		frame = new CustomFrame();
		
		mainPanel = frame.getMainPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 2, 5, 5);
		
		cambioPassword = new JLabel("Cambio Password");
		cambioPassword.setFont(new Font("Comic Sans MS", Font.PLAIN, 45));
		cambioPassword.setForeground(Color.BLUE);
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(cambioPassword, gbc);
		
		vecchiaPassLabel = new JLabel("Vecchia Password : ");
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.add(vecchiaPassLabel, gbc);
		
		vecchiaPassField = new JTextField();
		vecchiaPassField.setPreferredSize(new Dimension(150,20));
		gbc.gridx = 1;
		gbc.gridy = 1;
		mainPanel.add(vecchiaPassField, gbc);
		
		nuovaPassLabel = new JLabel("Nuova Password : ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		mainPanel.add(nuovaPassLabel, gbc);
		
		nuovaPassField = new JTextField();
		nuovaPassField.setPreferredSize(new Dimension(150,20));
		gbc.gridx = 1;
		gbc.gridy = 2;
		mainPanel.add(nuovaPassField, gbc);
		
		confermaPass = new JButton("Conferma");
		gbc.gridx = 0;
		gbc.gridy = 3;
		mainPanel.add(confermaPass, gbc);
		
		annullaPass = new JButton("Annulla");
		gbc.gridx = 1;
		gbc.gridy = 3;
		mainPanel.add(annullaPass, gbc);
		
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

	public JLabel getCambioPassword() {
		return cambioPassword;
	}

	public void setCambioPassword(JLabel cambioPassword) {
		this.cambioPassword = cambioPassword;
	}

	public JLabel getVecchiaPassLabel() {
		return vecchiaPassLabel;
	}

	public void setVecchiaPassLabel(JLabel vecchiaPassLabel) {
		this.vecchiaPassLabel = vecchiaPassLabel;
	}

	public JLabel getNuovaPassLabel() {
		return nuovaPassLabel;
	}

	public void setNuovaPassLabel(JLabel nuovaPassLabel) {
		this.nuovaPassLabel = nuovaPassLabel;
	}

	public JTextField getVecchiaPassField() {
		return vecchiaPassField;
	}

	public void setVecchiaPassField(JTextField vecchiaPassField) {
		this.vecchiaPassField = vecchiaPassField;
	}

	public JTextField getNuovaPassField() {
		return nuovaPassField;
	}

	public void setNuovaPassField(JTextField nuovaPassField) {
		this.nuovaPassField = nuovaPassField;
	}

	public JButton getConfermaPass() {
		return confermaPass;
	}

	public void setConfermaPass(JButton confermaPass) {
		this.confermaPass = confermaPass;
	}

	public JButton getAnnullaPass() {
		return annullaPass;
	}

	public void setAnnullaPass(JButton annullaPass) {
		this.annullaPass = annullaPass;
	}
	
}
