package it.unipv.sfw.rentacar.view.homepage;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import it.unipv.sfw.rentacar.controller.homepage.HomepageController;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.database.dao.AutoDAO;
import it.unipv.sfw.rentacar.model.database.dao.UtenteDAO;
import it.unipv.sfw.rentacar.model.exception.CategoriaBPatenteException;
import it.unipv.sfw.rentacar.model.exception.NumeroPatenteInvalidoException;
import it.unipv.sfw.rentacar.model.exception.PatenteScadutaException;
import it.unipv.sfw.rentacar.model.exception.TargaNonValidaException;
import it.unipv.sfw.rentacar.view.elementiPersonalizzati.CustomFrame;

/*
 * View Homepage
 */

public class HomepageUI {

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
	private JButton buttonNoleggio;
	
	public HomepageUI() throws IOException {
		frame = new CustomFrame();
		
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
		gbc.insets = new Insets(40, 0, 0, 0);
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
		buttonNoleggio = new JButton();
		buttonNoleggio.add(labelButtonNoleggio);
        gbc.gridx = 1;
		gbc.gridy = 5;
		frame.getMainPanel().add(buttonNoleggio, gbc);
		
		frame.add(frame.getMainPanel(), BorderLayout.CENTER);
		frame.getMainPanel().setVisible(true);
		frame.setVisible(true);

	}

	// Getter e Setter
	
	public CustomFrame getFrame() {
		return frame;
	}

	public void setFrame(CustomFrame frame) {
		this.frame = frame;
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}

	public ImageIcon getImgAuto1() {
		return imgAuto1;
	}

	public void setImgAuto1(ImageIcon imgAuto1) {
		this.imgAuto1 = imgAuto1;
	}

	public ImageIcon getImgAuto2() {
		return imgAuto2;
	}

	public void setImgAuto2(ImageIcon imgAuto2) {
		this.imgAuto2 = imgAuto2;
	}

	public ImageIcon getArrow() {
		return arrow;
	}

	public void setArrow(ImageIcon arrow) {
		this.arrow = arrow;
	}

	public JLabel getTitle() {
		return title;
	}

	public void setTitle(JLabel title) {
		this.title = title;
	}

	public JLabel getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(JLabel subTitle) {
		this.subTitle = subTitle;
	}

	public JLabel getDescription() {
		return description;
	}

	public void setDescription(JLabel description) {
		this.description = description;
	}

	public JLabel getImageLabel() {
		return imageLabel;
	}

	public void setImageLabel(JLabel imageLabel) {
		this.imageLabel = imageLabel;
	}

	public JLabel getLabelAuto1() {
		return labelAuto1;
	}

	public void setLabelAuto1(JLabel labelAuto1) {
		this.labelAuto1 = labelAuto1;
	}

	public JLabel getImgAuto1Descr() {
		return imgAuto1Descr;
	}

	public void setImgAuto1Descr(JLabel imgAuto1Descr) {
		this.imgAuto1Descr = imgAuto1Descr;
	}

	public JLabel getLabelAuto2() {
		return labelAuto2;
	}

	public void setLabelAuto2(JLabel labelAuto2) {
		this.labelAuto2 = labelAuto2;
	}

	public JLabel getImgAuto2Descr() {
		return imgAuto2Descr;
	}

	public void setImgAuto2Descr(JLabel imgAuto2Descr) {
		this.imgAuto2Descr = imgAuto2Descr;
	}

	public JLabel getArrowLabel() {
		return arrowLabel;
	}

	public void setArrowLabel(JLabel arrowLabel) {
		this.arrowLabel = arrowLabel;
	}
	
	public JButton getButtonNoleggio() {
		return buttonNoleggio;
	}

	public void setButtonNoleggio(JButton buttonNoleggio) {
		this.buttonNoleggio = buttonNoleggio;
	}
	
}
