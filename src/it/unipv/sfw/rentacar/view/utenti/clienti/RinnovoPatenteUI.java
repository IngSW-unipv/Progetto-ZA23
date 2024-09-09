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

import com.toedter.calendar.JDateChooser;

import it.unipv.sfw.rentacar.view.elementiPersonalizzati.CustomFrame;

public class RinnovoPatenteUI {

	private CustomFrame frame;
	private JPanel mainPanel;
	private JLabel rinnovoPatenteLabel;
	private JLabel scadenzaLabel;
	private JDateChooser nuovaScadenza;
	private JButton confermaButton;
	private JButton annullaButton;
	
	public RinnovoPatenteUI() throws IOException {
		
		frame = new CustomFrame();
		
		mainPanel = frame.getMainPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 2, 5, 5);

		rinnovoPatenteLabel = new JLabel("Rinnovo Patente");
		rinnovoPatenteLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		rinnovoPatenteLabel.setForeground(Color.BLUE);
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(rinnovoPatenteLabel, gbc);
		
		scadenzaLabel = new JLabel("Nuova Scadenza Patente");
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.add(scadenzaLabel, gbc);
		
		nuovaScadenza = new JDateChooser();
		nuovaScadenza.setDateFormatString("dd/MM/yyyy");
		nuovaScadenza.setPreferredSize(new Dimension(115, 20));
		JTextField dataField = (JTextField) nuovaScadenza.getDateEditor().getUiComponent();
		dataField.setEditable(false);

		gbc.gridx = 1;
		gbc.gridy = 1;
		mainPanel.add(nuovaScadenza, gbc);
		
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

	public JLabel getRinnovoPatenteLabel() {
		return rinnovoPatenteLabel;
	}

	public void setRinnovoPatenteLabel(JLabel rinnovoPatenteLabel) {
		this.rinnovoPatenteLabel = rinnovoPatenteLabel;
	}

	public JLabel getScadenzaLabel() {
		return scadenzaLabel;
	}

	public void setScadenzaLabel(JLabel scadenzaLabel) {
		this.scadenzaLabel = scadenzaLabel;
	}

	public JDateChooser getNuovaScadenza() {
		return nuovaScadenza;
	}

	public void setNuovaScadenza(JDateChooser nuovaScadenza) {
		this.nuovaScadenza = nuovaScadenza;
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
