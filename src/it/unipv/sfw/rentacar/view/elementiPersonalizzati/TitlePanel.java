package it.unipv.sfw.rentacar.view.elementiPersonalizzati;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unipv.sfw.rentacar.model.veicolo.Auto;

public class TitlePanel extends JPanel{

	private ImageIcon imageIcon;
	private JLabel imageLabel;
	private JLabel titleLabel;
	private JLabel loginLabel;
	private JButton loginButton;
	private JButton registerButton;
	private JButton logoutButton;
	
	public TitlePanel() throws IOException {
		super();
		
		setLayout(new BorderLayout());
		setBackground(new Color(173, 233, 255));
		
        imageIcon = new ImageIcon("src\\it\\unipv\\sfw\\rentacar\\resources\\logo_trasparente2.png");
        imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.WEST);
        
        titleLabel = new JLabel("Rent-a-Car");
        titleLabel.setFont(new Font("Brush Script MT", Font.BOLD, 50));
        titleLabel.setForeground(new Color(130, 50, 150));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(titleLabel, BorderLayout.CENTER);
        
        JPanel access = new JPanel();
        access.setLayout(new BoxLayout(access, BoxLayout.X_AXIS));
        access.setBackground(new Color(173, 233, 255));
        
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(120, 30));
        
        registerButton = new JButton("Registrazione");
        registerButton.setPreferredSize(new Dimension(120, 30));
        
        loginLabel = new JLabel("Benvenuto");
        loginLabel.setPreferredSize(new Dimension(120, 30));
        
        logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(120, 30));
        
        access.add(loginLabel);
        access.add(loginButton);
        access.add(registerButton);
        access.add(logoutButton);
        add(access, BorderLayout.EAST);
        
        loginLabel.setVisible(false);
        logoutButton.setVisible(false);
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}

	public JLabel getImageLabel() {
		return imageLabel;
	}

	public void setImageLabel(JLabel imageLabel) {
		this.imageLabel = imageLabel;
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public void setRegisterButton(JButton registerButton) {
		this.registerButton = registerButton;
	}

	public JLabel getLoginLabel() {
		return loginLabel;
	}

	public void setLoginLabel(JLabel loginLabel) {
		this.loginLabel = loginLabel;
	}

	public JButton getLogoutButton() {
		return logoutButton;
	}

	public void setLogoutButton(JButton logoutButton) {
		this.logoutButton = logoutButton;
	}
}
