package it.unipv.sfw.rentacar.view.accesso;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import it.unipv.sfw.rentacar.view.elementiPersonalizzati.CustomFrame;

public class LoginUI {
	
	private JLabel loginLabel;
	private JLabel nomeLabel;
	private JLabel cognomeLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField nome;
	private JTextField cognome;
	private JTextField username;
	private JPasswordField password;

	public LoginUI() throws IOException {
		CustomFrame frame = new CustomFrame();
		
		frame.getMainPanel().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		
		loginLabel = new JLabel("Login");
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		frame.getMainPanel().add(loginLabel, gbc);
		
		nomeLabel = new JLabel("Nome : ");
		nomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		frame.getMainPanel().add(nomeLabel, gbc);
		
		JTextField nome = new JTextField(10);
		gbc.gridx = 1;
		gbc.gridy = 1;
		frame.getMainPanel().add(nome, gbc);

		cognomeLabel = new JLabel("Cognome : ");
		cognomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc.gridx = 0;
		gbc.gridy = 2;
		frame.getMainPanel().add(cognomeLabel, gbc);
		
		cognome = new JTextField(10);
		gbc.gridx = 1;
		gbc.gridy = 2;
		frame.getMainPanel().add(cognome, gbc);
		
		usernameLabel = new JLabel("Username : ");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc.gridx = 0;
		gbc.gridy = 3;
		frame.getMainPanel().add(usernameLabel, gbc);
		
		username = new JTextField(10);
		gbc.gridx = 1;
		gbc.gridy = 3;
		frame.getMainPanel().add(username, gbc);
		
		passwordLabel = new JLabel("Password : ");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc.gridx = 0;
		gbc.gridy = 4;
		frame.getMainPanel().add(passwordLabel, gbc);
		
		password = new JPasswordField(10);
		gbc.gridx = 1;
		gbc.gridy = 4;
		frame.getMainPanel().add(password, gbc);
		
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) throws IOException {
		LoginUI login = new LoginUI();
		
	}
}
