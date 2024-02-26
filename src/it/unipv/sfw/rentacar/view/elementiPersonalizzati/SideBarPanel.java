package it.unipv.sfw.rentacar.view.elementiPersonalizzati;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class SideBarPanel extends JPanel{

	private JLabel homeLabel;
	private JLabel noleggioLabel;
	private JButton homeButton;
	private JButton noleggioButton;
	
	
	public SideBarPanel() {
		super();
		
		setBackground(new Color(173, 233, 255));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Border customBorder = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK);
		
		homeLabel = new JLabel("Home");
		homeButton = new JButton();
		homeButton.add(homeLabel);
		setLabelDimension(homeButton);
        noleggioLabel = new JLabel("Noleggio");
        noleggioButton = new JButton();
        noleggioButton.add(noleggioLabel);
        setLabelDimension(noleggioButton);
        
        
        setBorder(customBorder);
        add(homeButton);
        add(noleggioButton);
        
	}

	public JLabel getNoleggioLabel() {
		return noleggioLabel;
	}

	public void setNoleggioLabel(JLabel noleggioLabel) {
		this.noleggioLabel = noleggioLabel;
	}

	public JButton getNoleggioButton() {
		return noleggioButton;
	}

	public void setNoleggioButton(JButton noleggioButton) {
		this.noleggioButton = noleggioButton;
	}

	public JLabel getHomeLabel() {
		return homeLabel;
	}

	public void setHomeLabel(JLabel homeLabel) {
		this.homeLabel = homeLabel;
	}

	public JButton getHomeButton() {
		return homeButton;
	}

	public void setHomeButton(JButton homeButton) {
		this.homeButton = homeButton;
	}
	
    private void setLabelDimension(JButton button) {
    	button.setMaximumSize(new Dimension(120, 30));
        button.setMinimumSize(new Dimension(120, 30));
    }
	
}
