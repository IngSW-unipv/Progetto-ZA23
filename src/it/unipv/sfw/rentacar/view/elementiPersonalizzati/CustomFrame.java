package it.unipv.sfw.rentacar.view.elementiPersonalizzati;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomFrame extends JFrame{

	public CustomFrame() throws IOException {
		
        setTitle("Rent-a-Car");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		File iconFile = new File("src\\it\\unipv\\sfw\\rentacar\\resources\\icona.png");
		this.setIconImage(ImageIO.read(iconFile));
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Dimension dimTitolo = new Dimension(screenSize.width, 100);
        titlePanel.setPreferredSize(dimTitolo);
        ImageIcon logoAgenzia = new ImageIcon("src\\it\\unipv\\sfw\\rentacar\\resources\\logo_trasparente.png");
        JLabel logoLabel = new JLabel(logoAgenzia);
        logoLabel.setPreferredSize(new Dimension(100, 100));
        titlePanel.add(logoLabel);
        titlePanel.setBackground(Color.LIGHT_GRAY);

        JLabel titleLabel = new JLabel("Rent-a-Car");
        titleLabel.setFont(new Font("Brush Script MT", Font.BOLD, 50));
        titleLabel.setForeground(Color.BLUE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
        
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        JLabel noleggioLabel = new JLabel("Noleggio");
        noleggioLabel.setForeground(Color.WHITE);
        sidebarPanel.add(noleggioLabel);
        sidebarPanel.setBackground(Color.GRAY);
        Dimension sidebarSize = new Dimension(100, screenSize.height);
        sidebarPanel.setPreferredSize(sidebarSize);
        add(sidebarPanel, BorderLayout.WEST);
        

        
        setVisible(true);
    }

	public static void main(String[] args) throws IOException {
		CustomFrame f = new CustomFrame();
	}
}
