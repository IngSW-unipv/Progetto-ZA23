package it.unipv.sfw.rentacar.view.accesso;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import it.unipv.sfw.rentacar.controller.accesso.LoginController;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.view.elementiPersonalizzati.CustomFrame;

public class LoginUI {

	private CustomFrame frame;
	private JLabel loginLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField username;
	private JPasswordField password;
	private JButton loginButton;
	
	public LoginUI() throws IOException {
		frame = new CustomFrame();
		
		frame.getMainPanel().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		
		loginLabel = new JLabel("Login");
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		frame.getMainPanel().add(loginLabel, gbc);
		
		usernameLabel = new JLabel("Username : ");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		frame.getMainPanel().add(usernameLabel, gbc);
		
		username = new JTextField(10);
		gbc.gridx = 1;
		gbc.gridy = 1;
		frame.getMainPanel().add(username, gbc);
		
		passwordLabel = new JLabel("Password : ");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc.gridx = 0;
		gbc.gridy = 2;
		frame.getMainPanel().add(passwordLabel, gbc);
		
		password = new JPasswordField(10);
		gbc.gridx = 1;
		gbc.gridy = 2;
		frame.getMainPanel().add(password, gbc);
		
		loginButton = new JButton("Accedi");
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 3;
		frame.getMainPanel().add(loginButton, gbc);
		
		frame.getMainPanel().setVisible(true);
		frame.setVisible(true);
	}

	public CustomFrame getFrame() {
		return frame;
	}

	public void setFrame(CustomFrame frame) {
		this.frame = frame;
	}

	public JLabel getLoginLabel() {
		return loginLabel;
	}

	public void setLoginLabel(JLabel loginLabel) {
		this.loginLabel = loginLabel;
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

	public JButton getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}
	
	public static void main(String[] args) throws IOException {
		AgenziaNoleggioAuto agenzia = AgenziaNoleggioAuto.getInstance("Rent", "Via Roma, Mediglia");
		new LoginController(agenzia, new LoginUI());
	}
	
}
