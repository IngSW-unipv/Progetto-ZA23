package it.unipv.sfw.rentacar.view.elementiPersonalizzati;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class CustomFrame extends JFrame{

	private File iconFile;
	private TitlePanel titlePanel;
	private SideBarPanel sideBarPanel;
	private JPanel mainPanel;
	
	public static void main(String[] args) throws IOException {
		CustomFrame f = new CustomFrame();
	}
	
	public CustomFrame() throws IOException {
		
        setTitle("Rent-a-Car");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

		try {
			iconFile = new File("src\\it\\unipv\\sfw\\rentacar\\resources\\icona.png");
			setIconImage(ImageIO.read(iconFile));
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (UnsupportedLookAndFeelException  e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
        
        titlePanel = new TitlePanel();
        add(titlePanel, BorderLayout.NORTH);
        
        sideBarPanel = new SideBarPanel();
        add(sideBarPanel, BorderLayout.WEST);
        
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(168, 255, 184));
        add(mainPanel, BorderLayout.CENTER);
                
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

	public File getIconFile() {
		return iconFile;
	}

	public void setIconFile(File iconFile) {
		this.iconFile = iconFile;
	}

	public TitlePanel getTitlePanel() {
		return titlePanel;
	}

	public void setTitlePanel(TitlePanel titlePanel) {
		this.titlePanel = titlePanel;
	}

	public SideBarPanel getSideBarPanel() {
		return sideBarPanel;
	}

	public void setSideBarPanel(SideBarPanel sideBarPanel) {
		this.sideBarPanel = sideBarPanel;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

}
