package it.unipv.sfw.rentacar.view.catalogoauto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unipv.sfw.rentacar.view.elementiPersonalizzati.CustomFrame;

public class NoleggioAutoUI {

	private CustomFrame frame;
	private JLabel infoAutoLabel;
	private JLabel specificheLabel;
	private JLabel targaLabel;
	private JLabel marcaLabel;
	private JLabel modelloLabel;
	private JLabel annoProduzioneLabel;
	private JLabel tipoCambioLabel;
	private JLabel tipoCarburanteLabel;
	private JLabel cilindrataLabel;
	private JLabel potenzaLabel;
	private JLabel postiAutoLabel;
	private JLabel costoNoleggioGiornalieroLabel;
	private JTextField targaCampo;
	private JTextField  marcaCampo;
	private JTextField  modelloCampo;
	private JTextField  annoProduzioneCampo;
	private JTextField  tipoCambioCampo;
	private JTextField  tipoCarburanteCampo;
	private JTextField  cilindrataCampo;
	private JTextField  potenzaCampo;
	private JTextField  postiAutoCampo;
	private JTextField  costoNoleggioGiornalieroCampo;
	private JButton noleggioButton;
	
	public NoleggioAutoUI() throws IOException {
		frame = new CustomFrame();
		
		JPanel mainPanel = frame.getMainPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        infoAutoLabel = new JLabel("Informazioni Auto");
        infoAutoLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        infoAutoLabel.setForeground(Color.blue);
        mainPanel.add(infoAutoLabel, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        specificheLabel = new JLabel("Specifiche Auto");
        specificheLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        specificheLabel.setForeground(Color.blue);
        mainPanel.add(specificheLabel, gbc);
        

        targaLabel = new JLabel("Targa:");
        setFontLabel(targaLabel);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(targaLabel, gbc);

        
        gbc.gridx = 1;
        gbc.gridy = 1;
        targaCampo = new JTextField();
        setTextAreaDimension(targaCampo);
        mainPanel.add(targaCampo, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 2;
        marcaLabel = new JLabel("Marca:");
        setFontLabel(marcaLabel);
        mainPanel.add(marcaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        marcaCampo = new JTextField();
        setTextAreaDimension(marcaCampo);
        mainPanel.add(marcaCampo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        modelloLabel = new JLabel("Modello:");
        setFontLabel(modelloLabel);
        mainPanel.add(modelloLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        modelloCampo = new JTextField();
        setTextAreaDimension(modelloCampo);
        mainPanel.add(modelloCampo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        costoNoleggioGiornalieroLabel = new JLabel("Costo Noleggio Giornaliero:");
        setFontLabel(costoNoleggioGiornalieroLabel);
        mainPanel.add(costoNoleggioGiornalieroLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        costoNoleggioGiornalieroCampo= new JTextField();
        setTextAreaDimension(costoNoleggioGiornalieroCampo);
        mainPanel.add(costoNoleggioGiornalieroCampo, gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 1;
        annoProduzioneLabel = new JLabel("Anno di produzione:");
        setFontLabel(annoProduzioneLabel);
        mainPanel.add(annoProduzioneLabel, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 1;
        annoProduzioneCampo= new JTextField();
        setTextAreaDimension(annoProduzioneCampo);
        mainPanel.add(annoProduzioneCampo, gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 2;
        tipoCambioLabel = new JLabel("Tipo di cambio:");
        setFontLabel(tipoCambioLabel);
        mainPanel.add(tipoCambioLabel, gbc);

        gbc.gridx = 5;
        gbc.gridy = 2; 
        tipoCambioCampo = new JTextField();
        setTextAreaDimension(tipoCambioCampo);
        mainPanel.add(tipoCambioCampo, gbc);

        gbc.gridx = 4;
        gbc.gridy = 3;
        tipoCarburanteLabel = new JLabel("Tipo di carburante:");
        setFontLabel(tipoCarburanteLabel);
        mainPanel.add(tipoCarburanteLabel, gbc);

        gbc.gridx = 5;
        gbc.gridy = 3;
        tipoCarburanteCampo = new JTextField();
        setTextAreaDimension(tipoCarburanteCampo);
        mainPanel.add(tipoCarburanteCampo, gbc);

        gbc.gridx = 4;
        gbc.gridy = 4;
        postiAutoLabel = new JLabel("Posti auto:");
        setFontLabel(postiAutoLabel);
        mainPanel.add(postiAutoLabel, gbc);

        gbc.gridx = 5;
        gbc.gridy = 4;
        postiAutoCampo = new JTextField();
        setTextAreaDimension(postiAutoCampo);
        mainPanel.add(postiAutoCampo, gbc);

        gbc.gridx = 4;
        gbc.gridy = 5;
        cilindrataLabel = new JLabel("Cilindrata:");
        setFontLabel(cilindrataLabel);
        mainPanel.add(cilindrataLabel, gbc);

        gbc.gridx = 5;
        gbc.gridy = 5;
        cilindrataCampo = new JTextField();
        setTextAreaDimension(cilindrataCampo);
        mainPanel.add(cilindrataCampo, gbc);

        gbc.gridx = 4;
        gbc.gridy = 6;
        potenzaLabel = new JLabel("Potenza:");
        setFontLabel(potenzaLabel);
        mainPanel.add(potenzaLabel, gbc);

        gbc.gridx = 5;
        gbc.gridy = 6;
        potenzaCampo = new JTextField();
        setTextAreaDimension(potenzaCampo);
        mainPanel.add(potenzaCampo, gbc);
        
        noleggioButton = new JButton("Avvia Noleggio Auto");
        gbc.gridwidth = 2;
        gbc.gridx = 2;
        gbc.gridy = 7;
        mainPanel.add(noleggioButton, gbc);
        
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException {
		NoleggioAutoUI noleggio = new NoleggioAutoUI();
	}
	
	private void setTextAreaDimension(JTextField campo) {
		campo.setPreferredSize(new Dimension(100, 20));
    }
	
	private void setFontLabel(JLabel label) {
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
    }
	
}
