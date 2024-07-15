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
	private JLabel autoLabel;
	private JLabel areaPersonaleLabel;
	private JButton homeButton;
	private JButton noleggioButton;
	private JButton autoButton;
	private JButton areaPersonaleButton;
	
	public SideBarPanel() {
		super();
		
		setBackground(new Color(173, 233, 255));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Border customBorder = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK);
		
		homeLabel = new JLabel("Home");
		homeButton = new JButton();
		homeButton.add(homeLabel);
		setButtonDimension(homeButton);
        
		noleggioLabel = new JLabel("Catalogo");
        noleggioButton = new JButton();
        noleggioButton.add(noleggioLabel);
        setButtonDimension(noleggioButton);
        
        autoLabel = new JLabel("Auto");
        autoButton = new JButton();
        autoButton.add(autoLabel);
        setButtonDimension(autoButton);
        
        areaPersonaleLabel = new JLabel("Area Personale");
        areaPersonaleButton = new JButton();
        areaPersonaleButton.add(areaPersonaleLabel);
        setButtonDimension(areaPersonaleButton);
        
        setBorder(customBorder);
        add(homeButton);
        add(noleggioButton);
        add(autoButton);
        add(areaPersonaleButton);
        
        areaPersonaleButton.setVisible(false);
        autoButton.setVisible(false);
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
	
    public JLabel getAutoLabel() {
		return autoLabel;
	}

	public void setAutoLabel(JLabel autoLabel) {
		this.autoLabel = autoLabel;
	}

	public JButton getAutoButton() {
		return autoButton;
	}

	public void setAutoButton(JButton autoButton) {
		this.autoButton = autoButton;
	}

	public JLabel getAreaPersonaleLabel() {
		return areaPersonaleLabel;
	}

	public void setAreaPersonaleLabel(JLabel areaPersonaleLabel) {
		this.areaPersonaleLabel = areaPersonaleLabel;
	}

	public JButton getAreaPersonaleButton() {
		return areaPersonaleButton;
	}

	public void setAreaPersonaleButton(JButton areaPersonaleButton) {
		this.areaPersonaleButton = areaPersonaleButton;
	}

	private void setButtonDimension(JButton button) {
    	button.setMaximumSize(new Dimension(150, 35));
    }
	
}
