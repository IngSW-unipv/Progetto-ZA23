package it.unipv.sfw.rentacar.view.homepage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.unipv.sfw.rentacar.view.elementiPersonalizzati.CustomFrame;

public class Homepage {

	private CustomFrame frame;
	private ImageIcon imageIcon;
	private ImageIcon imgAuto1;
	private ImageIcon imgAuto2;
	private ImageIcon arrow;
	private JLabel title;
	private JLabel subTitle;
	private JLabel description;
	private JLabel imageLabel;
	private JLabel labelAuto1;
	private JLabel imgAuto1Descr;
	private JLabel labelAuto2;
	private JLabel imgAuto2Descr;
	private JLabel arrowLabel;
	
	public Homepage() throws IOException {
		frame = new CustomFrame();
		
		frame.getMainPanel().setBackground(new Color(168, 255, 184));
		GridBagConstraints gbc = new GridBagConstraints();
		frame.getMainPanel().setLayout(new GridBagLayout());
		
		title = new JLabel("Benvenuto da noi");
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 45));
		gbc.gridx = 0;
		gbc.gridy = 0;
		frame.getMainPanel().add(title, gbc);
		
        imageIcon = new ImageIcon("src\\it\\unipv\\sfw\\rentacar\\resources\\rent_a_car.png");
        imageLabel = new JLabel(imageIcon);
        gbc.gridx = 1;
		gbc.gridy = 0;
		frame.getMainPanel().add(imageLabel, gbc);
		
		subTitle = new JLabel("Qui potrai noleggiare l'auto che più soddisfa le tue esigenze");
		Font subTitleFont = new Font("Lucida Calligraphy", Font.BOLD, 30);
		subTitle.setFont(subTitleFont);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(50, 0, 0, 0);
		frame.getMainPanel().add(subTitle, gbc);
		
		description = new JLabel("Il nostro catalogo contiene auto di ogni genere");
		Font descriptionFont = new Font("Lucida Calligraphy", Font.BOLD, 20);
		description.setFont(descriptionFont);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		frame.getMainPanel().add(description, gbc);
		
        imgAuto1 = new ImageIcon("src\\it\\unipv\\sfw\\rentacar\\resources\\auto1.png");
        labelAuto1 = new JLabel(imgAuto1);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 0, 15, 0);
		frame.getMainPanel().add(labelAuto1, gbc);
		
        imgAuto1Descr = new JLabel("Da auto d'epoca");
        imgAuto1Descr.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
        gbc.gridx = 0;
		gbc.gridy = 4;
		frame.getMainPanel().add(imgAuto1Descr, gbc);
		
        arrow = new ImageIcon("src\\it\\unipv\\sfw\\rentacar\\resources\\freccia.png");
        arrowLabel = new JLabel(arrow);
        gbc.gridx = 1;
		gbc.gridy = 3;
		frame.getMainPanel().add(arrowLabel, gbc);
		
        imgAuto2 = new ImageIcon("src\\it\\unipv\\sfw\\rentacar\\resources\\auto2.png");
        labelAuto2 = new JLabel(imgAuto2);
        gbc.gridx = 2;
		gbc.gridy = 3;
		frame.getMainPanel().add(labelAuto2, gbc);
		
        imgAuto2Descr = new JLabel("Ad auto sportive");
        imgAuto2Descr.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
        gbc.gridx = 2;
		gbc.gridy = 4;
		frame.getMainPanel().add(imgAuto2Descr, gbc);
		
		JLabel labelButtonNoleggio = new JLabel("Inizia Noleggio");
		labelButtonNoleggio.setFont(new Font("Arial", Font.BOLD, 20));
		JButton buttonNoleggio = new JButton();
		buttonNoleggio.add(labelButtonNoleggio);
        gbc.gridx = 1;
		gbc.gridy = 5;
		frame.getMainPanel().add(buttonNoleggio, gbc);
		
		frame.add(frame.getMainPanel(), BorderLayout.CENTER);
		frame.getMainPanel().setVisible(true);
		frame.setVisible(true);

	}
	
	public static void main(String[] args) throws IOException {
		Homepage home = new Homepage();
	}
}
