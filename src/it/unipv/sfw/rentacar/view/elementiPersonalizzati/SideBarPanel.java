package it.unipv.sfw.rentacar.view.elementiPersonalizzati;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SideBarPanel extends JPanel{

	private JLabel noleggioLabel;
	private JButton noleggioButton;
	
	public SideBarPanel() {
		super();
		
		setBackground(Color.CYAN);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        noleggioLabel = new JLabel("Noleggio");
        noleggioButton = new JButton();
        noleggioButton.add(noleggioLabel);
        add(noleggioButton);
        
	}

	
}
