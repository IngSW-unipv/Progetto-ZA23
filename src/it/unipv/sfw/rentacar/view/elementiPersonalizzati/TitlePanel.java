package it.unipv.sfw.rentacar.view.elementiPersonalizzati;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel{

	private ImageIcon imageIcon;
	private JLabel imageLabel;
	private JLabel titleLabel;
	
	public TitlePanel() throws IOException {
		
		setLayout(new BorderLayout());
		setBackground(Color.CYAN);
		
        imageIcon = new ImageIcon("src\\it\\unipv\\sfw\\rentacar\\resources\\logo_trasparente2.png");
        imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.WEST);
        
        titleLabel = new JLabel("Rent-a-Car");
        titleLabel.setFont(new Font("Brush Script MT", Font.BOLD, 50));
        titleLabel.setForeground(Color.BLUE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(titleLabel, BorderLayout.CENTER);
        
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
	
	
}
