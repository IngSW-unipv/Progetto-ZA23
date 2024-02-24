package it.unipv.sfw.rentacar.view.elementiPersonalizzati;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class SideBarPanel extends JPanel{

	private JLabel noleggioLabel;
	private JButton noleggioButton;
	
	public SideBarPanel() {
		super();
		
		setBackground(new Color(173, 233, 255));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        noleggioLabel = new JLabel("Noleggio");
        noleggioButton = new JButton();
        noleggioButton.add(noleggioLabel);
        Border customBorder = BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK);
        setBorder(customBorder);
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
	
}
