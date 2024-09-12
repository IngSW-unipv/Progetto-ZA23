package it.unipv.sfw.rentacar.view.accesso;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import it.unipv.sfw.rentacar.view.elementiPersonalizzati.CustomFrame;

/*
 * View Registation
 */

public class RegistrationUI {
	
	private CustomFrame frame;
	private JLabel registrationLabel;
	private JLabel datiLabel;
	private JLabel nomeLabel;
	private JLabel cognomeLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel datiPatenteLabel;
	private JLabel numeroPatenteLabel;
	private JLabel scadenzaLabel;
	private JLabel categorieLabel;
	private JTextField nome;
	private JTextField cognome;
	private JTextField username;
	private JPasswordField password;
	private JTextField numeroPatente;
	private JDateChooser scadenzaPatente;
	private JCheckBox categoriePatenteA;
	private JCheckBox categoriePatenteB;
	private JCheckBox categoriePatenteC;
	private JButton registrazioneButton;

	public RegistrationUI() throws IOException {
		frame = new CustomFrame();
		
		frame.getMainPanel().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 2, 5, 5);
		
		registrationLabel = new JLabel("Registrazione");
		registrationLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		frame.getMainPanel().add(registrationLabel, gbc);
		
		datiLabel = new JLabel("Dati Anagrafici");
		datiLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		gbc.gridx = 0;
		gbc.gridy = 1;
		frame.getMainPanel().add(datiLabel, gbc);
		
		nomeLabel = new JLabel("Nome : ");
		nomeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		frame.getMainPanel().add(nomeLabel, gbc);
		
		nome = new JTextField(10);
		gbc.gridx = 1;
		gbc.gridy = 2;
		frame.getMainPanel().add(nome, gbc);

		cognomeLabel = new JLabel("Cognome : ");
		cognomeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		gbc.gridx = 0;
		gbc.gridy = 3;
		frame.getMainPanel().add(cognomeLabel, gbc);
		
		cognome = new JTextField(10);
		gbc.gridx = 1;
		gbc.gridy = 3;
		frame.getMainPanel().add(cognome, gbc);
		
		usernameLabel = new JLabel("Username : ");
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		gbc.gridx = 0;
		gbc.gridy = 4;
		frame.getMainPanel().add(usernameLabel, gbc);
		
		username = new JTextField(10);
		gbc.gridx = 1;
		gbc.gridy = 4;
		frame.getMainPanel().add(username, gbc);
		
		passwordLabel = new JLabel("Password : ");
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		gbc.gridx = 0;
		gbc.gridy = 5;
		frame.getMainPanel().add(passwordLabel, gbc);
		
		password = new JPasswordField(10);
		gbc.gridx = 1;
		gbc.gridy = 5;
		frame.getMainPanel().add(password, gbc);
		
		datiPatenteLabel = new JLabel("Dati Patente");
		datiPatenteLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		frame.getMainPanel().add(datiPatenteLabel, gbc);
		
		numeroPatenteLabel = new JLabel("Numero Patente : ");
		numeroPatenteLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 7;
		frame.getMainPanel().add(numeroPatenteLabel, gbc);
		
		numeroPatente = new JTextField(10);
		gbc.gridx = 1;
		gbc.gridy = 7;
		frame.getMainPanel().add(numeroPatente, gbc);

		scadenzaLabel = new JLabel("Scadenza Patente : ");
		scadenzaLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		gbc.gridx = 0;
		gbc.gridy = 8;
		frame.getMainPanel().add(scadenzaLabel, gbc);
		
		scadenzaPatente = new JDateChooser();
		scadenzaPatente.setDateFormatString("dd/MM/yyyy");
		scadenzaPatente.setPreferredSize(new Dimension(115, 20));
		JTextField dataField = (JTextField) scadenzaPatente.getDateEditor().getUiComponent();
		dataField.setEditable(false);
		gbc.gridx = 1;
		gbc.gridy = 8;
		frame.getMainPanel().add(scadenzaPatente, gbc);
		
		categorieLabel = new JLabel("Categorie Patente : ");
		categorieLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 9;
		frame.getMainPanel().add(categorieLabel, gbc);
		
		categoriePatenteA = new JCheckBox("A");
		categoriePatenteB = new JCheckBox("B");
		categoriePatenteC = new JCheckBox("C");
		gbc.gridx = 1;
		gbc.gridy = 9;
		frame.getMainPanel().add(categoriePatenteA, gbc);
		gbc.gridx = 1;
		gbc.gridy = 10;
		frame.getMainPanel().add(categoriePatenteB, gbc);
		gbc.gridx = 1;
		gbc.gridy = 11;
		frame.getMainPanel().add(categoriePatenteC, gbc);
		
		registrazioneButton = new JButton("Registrati");
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 12;
		frame.getMainPanel().add(registrazioneButton, gbc);
		
		frame.setVisible(true);
		
	}

	// Getter e Setter
	
	public CustomFrame getFrame() {
		return frame;
	}

	public void setFrame(CustomFrame frame) {
		this.frame = frame;
	}

	public JLabel getRegistrationLabel() {
		return registrationLabel;
	}

	public void setRegistrationLabel(JLabel registrationLabel) {
		this.registrationLabel = registrationLabel;
	}

	public JLabel getDatiLabel() {
		return datiLabel;
	}

	public void setDatiLabel(JLabel datiLabel) {
		this.datiLabel = datiLabel;
	}

	public JLabel getNomeLabel() {
		return nomeLabel;
	}

	public void setNomeLabel(JLabel nomeLabel) {
		this.nomeLabel = nomeLabel;
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

	public JLabel getPasswordLabel() {
		return passwordLabel;
	}

	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
	}

	public JLabel getDatiPatenteLabel() {
		return datiPatenteLabel;
	}

	public void setDatiPatenteLabel(JLabel datiPatenteLabel) {
		this.datiPatenteLabel = datiPatenteLabel;
	}

	public JLabel getNumeroPatenteLabel() {
		return numeroPatenteLabel;
	}

	public void setNumeroPatenteLabel(JLabel numeroPatenteLabel) {
		this.numeroPatenteLabel = numeroPatenteLabel;
	}

	public JLabel getScadenzaLabel() {
		return scadenzaLabel;
	}

	public void setScadenzaLabel(JLabel scadenzaLabel) {
		this.scadenzaLabel = scadenzaLabel;
	}

	public JLabel getCategorieLabel() {
		return categorieLabel;
	}

	public void setCategorieLabel(JLabel categorieLabel) {
		this.categorieLabel = categorieLabel;
	}

	public JTextField getNome() {
		return nome;
	}

	public void setNome(JTextField nome) {
		this.nome = nome;
	}

	public JTextField getCognome() {
		return cognome;
	}

	public void setCognome(JTextField cognome) {
		this.cognome = cognome;
	}

	public JTextField getUsername() {
		return username;
	}

	public void setUsername(JTextField username) {
		this.username = username;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public JTextField getNumeroPatente() {
		return numeroPatente;
	}

	public void setNumeroPatente(JTextField numeroPatente) {
		this.numeroPatente = numeroPatente;
	}

	public JDateChooser getScadenzaPatente() {
		return scadenzaPatente;
	}

	public void setScadenzaPatente(JDateChooser scadenzaPatente) {
		this.scadenzaPatente = scadenzaPatente;
	}

	public JCheckBox getCategoriePatenteA() {
		return categoriePatenteA;
	}

	public void setCategoriePatenteA(JCheckBox categoriePatenteA) {
		this.categoriePatenteA = categoriePatenteA;
	}

	public JCheckBox getCategoriePatenteB() {
		return categoriePatenteB;
	}

	public void setCategoriePatenteB(JCheckBox categoriePatenteB) {
		this.categoriePatenteB = categoriePatenteB;
	}

	public JCheckBox getCategoriePatenteC() {
		return categoriePatenteC;
	}

	public void setCategoriePatenteC(JCheckBox categoriePatenteC) {
		this.categoriePatenteC = categoriePatenteC;
	}

	public JButton getRegistrazioneButton() {
		return registrazioneButton;
	}

	public void setRegistrazioneButton(JButton registrazioneButton) {
		this.registrazioneButton = registrazioneButton;
	}
}
