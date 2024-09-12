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

import com.toedter.calendar.JDateChooser;

import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.view.elementiPersonalizzati.CustomFrame;

/*
 * View NoleggioAuto
 */

public class NoleggioAutoUI {

	private CustomFrame frame;
	private JPanel mainPanel;
	private JLabel infoAutoLabel;
	private JLabel specificheLabel;
	private JLabel infoNoleggio;
	private JLabel infoPagamento;
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
	private JLabel dataInizioNoleggioLabel;
	private JLabel dataFineNoleggioLabel;
	private JLabel importoTotaleNoleggioLabel;
	private JLabel numeroCartaLabel;
	private JLabel scadenzaCartaLabel;
	private JLabel CVVLabel;
	private JTextField targaCampo;
	private JTextField  marcaCampo;
	private JTextField  modelloCampo;
	private JTextField  annoProduzioneCampo;
	private JTextField  tipoCambioCampo;
	private JTextField  tipoCarburanteCampo1;
	private JTextField  tipoCarburanteCampo2;
	private JTextField  cilindrataCampo;
	private JTextField  potenzaCampo;
	private JTextField  postiAutoCampo;
	private JTextField  costoNoleggioGiornalieroCampo;
	private JDateChooser dataInizioNoleggio;
	private JDateChooser dataFineNoleggio;
	private JTextField importoTotaleNoleggio;
	private JTextField numeroCarta;
	private JDateChooser scadenzaCarta;
	private JTextField CVV;
	private JButton noleggioButton;
	
	public NoleggioAutoUI() throws IOException {
		frame = new CustomFrame();
		
		mainPanel = frame.getMainPanel();
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
        targaCampo.setEditable(false);
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
        marcaCampo.setEditable(false);
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
        modelloCampo.setEditable(false);
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
        costoNoleggioGiornalieroCampo.setEditable(false);
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
        annoProduzioneCampo.setEditable(false);
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
        tipoCambioCampo.setEditable(false);
        mainPanel.add(tipoCambioCampo, gbc);

        gbc.gridx = 4;
        gbc.gridy = 3;
        tipoCarburanteLabel = new JLabel("Tipo di carburante:");
        setFontLabel(tipoCarburanteLabel);
        mainPanel.add(tipoCarburanteLabel, gbc);

        gbc.gridx = 5;
        gbc.gridy = 3;
        tipoCarburanteCampo1 = new JTextField();
        setTextAreaDimension(tipoCarburanteCampo1);
        tipoCarburanteCampo1.setEditable(false);
        mainPanel.add(tipoCarburanteCampo1, gbc);

        gbc.gridx = 6;
        gbc.gridy = 3;
        tipoCarburanteCampo2 = new JTextField();
        setTextAreaDimension(tipoCarburanteCampo2);
        tipoCarburanteCampo2.setEditable(false);
        mainPanel.add(tipoCarburanteCampo2, gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 4;
        postiAutoLabel = new JLabel("Posti auto:");
        setFontLabel(postiAutoLabel);
        mainPanel.add(postiAutoLabel, gbc);

        gbc.gridx = 5;
        gbc.gridy = 4;
        postiAutoCampo = new JTextField();
        setTextAreaDimension(postiAutoCampo);
        postiAutoCampo.setEditable(false);
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
        cilindrataCampo.setEditable(false);
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
        potenzaCampo.setEditable(false);
        mainPanel.add(potenzaCampo, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        infoNoleggio = new JLabel();
        infoNoleggio = new JLabel("Informazioni Noleggio");
        infoNoleggio.setFont(new Font("Arial", Font.PLAIN, 30));
        infoNoleggio.setForeground(Color.blue);
        mainPanel.add(infoNoleggio, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        dataInizioNoleggioLabel = new JLabel("Inizio Noleggio");
        setFontLabel(dataInizioNoleggioLabel);
        mainPanel.add(dataInizioNoleggioLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 8;
        dataInizioNoleggio = new JDateChooser();
        dataInizioNoleggio = new JDateChooser();
        dataInizioNoleggio.setDateFormatString("dd/MM/yyyy");
        dataInizioNoleggio.setPreferredSize(new Dimension(100, 20));
		JTextField dataFieldInizio = (JTextField) dataInizioNoleggio.getDateEditor().getUiComponent();
		dataFieldInizio.setEditable(false);
        mainPanel.add(dataInizioNoleggio, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        dataFineNoleggioLabel = new JLabel("Inizio Noleggio");
        setFontLabel(dataFineNoleggioLabel );
        mainPanel.add(dataFineNoleggioLabel , gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 8;
        dataFineNoleggio = new JDateChooser();
        dataFineNoleggio.setDateFormatString("dd/MM/yyyy");
        dataFineNoleggio.setPreferredSize(new Dimension(100, 20));
		JTextField dataFieldFine = (JTextField) dataFineNoleggio.getDateEditor().getUiComponent();
		dataFieldFine.setEditable(false);
        mainPanel.add(dataFineNoleggio , gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 8;
        importoTotaleNoleggioLabel = new JLabel("Importo Totale");
        setFontLabel(importoTotaleNoleggioLabel);
        mainPanel.add(importoTotaleNoleggioLabel , gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 8;
        importoTotaleNoleggio = new JTextField();
        setTextAreaDimension(importoTotaleNoleggio);
        importoTotaleNoleggio.setEditable(false);
        mainPanel.add(importoTotaleNoleggio , gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        infoPagamento = new JLabel("Informazioni Pagamento");
        infoPagamento.setFont(new Font("Arial", Font.PLAIN, 30));
        infoPagamento.setForeground(Color.blue);
        mainPanel.add(infoPagamento, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        numeroCartaLabel = new JLabel("Numero Carta");
        setFontLabel(numeroCartaLabel);
        mainPanel.add(numeroCartaLabel , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 10;
        numeroCarta = new JTextField();
        setTextAreaDimension(numeroCarta);
        mainPanel.add(numeroCarta, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 10;
        scadenzaCartaLabel = new JLabel("Scadenza Carta");
        setFontLabel(scadenzaCartaLabel);
        mainPanel.add(scadenzaCartaLabel, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 10;
        scadenzaCarta = new JDateChooser();
        scadenzaCarta.setDateFormatString("dd/MM/yyyy");
        scadenzaCarta.setPreferredSize(new Dimension(100, 20));
		JTextField dataFieldScadenza = (JTextField) scadenzaCarta.getDateEditor().getUiComponent();
		dataFieldScadenza.setEditable(false);
        mainPanel.add(scadenzaCarta , gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 10;
        CVVLabel = new JLabel("CVV Carta");
        setFontLabel(CVVLabel);
        mainPanel.add(CVVLabel, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 10;
        CVV = new JTextField();
        setTextAreaDimension(CVV);
        mainPanel.add(CVV, gbc);
        
        gbc.gridwidth = 2;
        gbc.gridx = 2;
        gbc.gridy = 11;
        noleggioButton = new JButton("Avvia Noleggio Auto");
        mainPanel.add(noleggioButton, gbc);
        
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
	}
	
	// Getter e Setter
	
	public CustomFrame getFrame() {
		return frame;
	}

	public void setFrame(CustomFrame frame) {
		this.frame = frame;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public JLabel getInfoAutoLabel() {
		return infoAutoLabel;
	}

	public void setInfoAutoLabel(JLabel infoAutoLabel) {
		this.infoAutoLabel = infoAutoLabel;
	}

	public JLabel getSpecificheLabel() {
		return specificheLabel;
	}

	public void setSpecificheLabel(JLabel specificheLabel) {
		this.specificheLabel = specificheLabel;
	}

	public JLabel getInfoNoleggio() {
		return infoNoleggio;
	}

	public void setInfoNoleggio(JLabel infoNoleggio) {
		this.infoNoleggio = infoNoleggio;
	}

	public JLabel getInfoPagamento() {
		return infoPagamento;
	}

	public void setInfoPagamento(JLabel infoPagamento) {
		this.infoPagamento = infoPagamento;
	}

	public JLabel getTargaLabel() {
		return targaLabel;
	}

	public void setTargaLabel(JLabel targaLabel) {
		this.targaLabel = targaLabel;
	}

	public JLabel getMarcaLabel() {
		return marcaLabel;
	}

	public void setMarcaLabel(JLabel marcaLabel) {
		this.marcaLabel = marcaLabel;
	}

	public JLabel getModelloLabel() {
		return modelloLabel;
	}

	public void setModelloLabel(JLabel modelloLabel) {
		this.modelloLabel = modelloLabel;
	}

	public JLabel getAnnoProduzioneLabel() {
		return annoProduzioneLabel;
	}

	public void setAnnoProduzioneLabel(JLabel annoProduzioneLabel) {
		this.annoProduzioneLabel = annoProduzioneLabel;
	}

	public JLabel getTipoCambioLabel() {
		return tipoCambioLabel;
	}

	public void setTipoCambioLabel(JLabel tipoCambioLabel) {
		this.tipoCambioLabel = tipoCambioLabel;
	}

	public JLabel getTipoCarburanteLabel() {
		return tipoCarburanteLabel;
	}

	public void setTipoCarburanteLabel(JLabel tipoCarburanteLabel) {
		this.tipoCarburanteLabel = tipoCarburanteLabel;
	}

	public JLabel getCilindrataLabel() {
		return cilindrataLabel;
	}

	public void setCilindrataLabel(JLabel cilindrataLabel) {
		this.cilindrataLabel = cilindrataLabel;
	}

	public JLabel getPotenzaLabel() {
		return potenzaLabel;
	}

	public void setPotenzaLabel(JLabel potenzaLabel) {
		this.potenzaLabel = potenzaLabel;
	}

	public JLabel getPostiAutoLabel() {
		return postiAutoLabel;
	}

	public void setPostiAutoLabel(JLabel postiAutoLabel) {
		this.postiAutoLabel = postiAutoLabel;
	}

	public JLabel getCostoNoleggioGiornalieroLabel() {
		return costoNoleggioGiornalieroLabel;
	}

	public void setCostoNoleggioGiornalieroLabel(JLabel costoNoleggioGiornalieroLabel) {
		this.costoNoleggioGiornalieroLabel = costoNoleggioGiornalieroLabel;
	}

	public JLabel getDataInizioNoleggioLabel() {
		return dataInizioNoleggioLabel;
	}

	public void setDataInizioNoleggioLabel(JLabel dataInizioNoleggioLabel) {
		this.dataInizioNoleggioLabel = dataInizioNoleggioLabel;
	}

	public JLabel getDataFineNoleggioLabel() {
		return dataFineNoleggioLabel;
	}

	public void setDataFineNoleggioLabel(JLabel dataFineNoleggioLabel) {
		this.dataFineNoleggioLabel = dataFineNoleggioLabel;
	}

	public JLabel getImportoTotaleNoleggioLabel() {
		return importoTotaleNoleggioLabel;
	}

	public void setImportoTotaleNoleggioLabel(JLabel importoTotaleNoleggioLabel) {
		this.importoTotaleNoleggioLabel = importoTotaleNoleggioLabel;
	}

	public JLabel getNumeroCartaLabel() {
		return numeroCartaLabel;
	}

	public void setNumeroCartaLabel(JLabel numeroCartaLabel) {
		this.numeroCartaLabel = numeroCartaLabel;
	}

	public JLabel getScadenzaCartaLabel() {
		return scadenzaCartaLabel;
	}

	public void setScadenzaCartaLabel(JLabel scadenzaCartaLabel) {
		this.scadenzaCartaLabel = scadenzaCartaLabel;
	}

	public JLabel getCVVLabel() {
		return CVVLabel;
	}

	public void setCVVLabel(JLabel cVVLabel) {
		CVVLabel = cVVLabel;
	}

	public JTextField getTargaCampo() {
		return targaCampo;
	}

	public void setTargaCampo(JTextField targaCampo) {
		this.targaCampo = targaCampo;
	}

	public JTextField getMarcaCampo() {
		return marcaCampo;
	}

	public void setMarcaCampo(JTextField marcaCampo) {
		this.marcaCampo = marcaCampo;
	}

	public JTextField getModelloCampo() {
		return modelloCampo;
	}

	public void setModelloCampo(JTextField modelloCampo) {
		this.modelloCampo = modelloCampo;
	}

	public JTextField getAnnoProduzioneCampo() {
		return annoProduzioneCampo;
	}

	public void setAnnoProduzioneCampo(JTextField annoProduzioneCampo) {
		this.annoProduzioneCampo = annoProduzioneCampo;
	}

	public JTextField getTipoCambioCampo() {
		return tipoCambioCampo;
	}

	public void setTipoCambioCampo(JTextField tipoCambioCampo) {
		this.tipoCambioCampo = tipoCambioCampo;
	}

	public JTextField getTipoCarburanteCampo1() {
		return tipoCarburanteCampo1;
	}

	public void setTipoCarburanteCampo1(JTextField tipoCarburanteCampo1) {
		this.tipoCarburanteCampo1 = tipoCarburanteCampo1;
	}

	public JTextField getTipoCarburanteCampo2() {
		return tipoCarburanteCampo2;
	}

	public void setTipoCarburanteCampo2(JTextField tipoCarburanteCampo2) {
		this.tipoCarburanteCampo2 = tipoCarburanteCampo2;
	}

	public JTextField getCilindrataCampo() {
		return cilindrataCampo;
	}

	public void setCilindrataCampo(JTextField cilindrataCampo) {
		this.cilindrataCampo = cilindrataCampo;
	}

	public JTextField getPotenzaCampo() {
		return potenzaCampo;
	}

	public void setPotenzaCampo(JTextField potenzaCampo) {
		this.potenzaCampo = potenzaCampo;
	}

	public JTextField getPostiAutoCampo() {
		return postiAutoCampo;
	}

	public void setPostiAutoCampo(JTextField postiAutoCampo) {
		this.postiAutoCampo = postiAutoCampo;
	}

	public JTextField getCostoNoleggioGiornalieroCampo() {
		return costoNoleggioGiornalieroCampo;
	}

	public void setCostoNoleggioGiornalieroCampo(JTextField costoNoleggioGiornalieroCampo) {
		this.costoNoleggioGiornalieroCampo = costoNoleggioGiornalieroCampo;
	}

	public JDateChooser getDataInizioNoleggio() {
		return dataInizioNoleggio;
	}

	public void setDataInizioNoleggio(JDateChooser dataInizioNoleggio) {
		this.dataInizioNoleggio = dataInizioNoleggio;
	}

	public JDateChooser getDataFineNoleggio() {
		return dataFineNoleggio;
	}

	public void setDataFineNoleggio(JDateChooser dataFineNoleggio) {
		this.dataFineNoleggio = dataFineNoleggio;
	}

	public JTextField getImportoTotaleNoleggio() {
		return importoTotaleNoleggio;
	}

	public void setImportoTotaleNoleggio(JTextField importoTotaleNoleggio) {
		this.importoTotaleNoleggio = importoTotaleNoleggio;
	}

	public JTextField getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(JTextField numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public JDateChooser getScadenzaCarta() {
		return scadenzaCarta;
	}

	public void setScadenzaCarta(JDateChooser scadenzaCarta) {
		this.scadenzaCarta = scadenzaCarta;
	}

	public JTextField getCVV() {
		return CVV;
	}

	public void setCVV(JTextField cVV) {
		CVV = cVV;
	}

	public JButton getNoleggioButton() {
		return noleggioButton;
	}

	public void setNoleggioButton(JButton noleggioButton) {
		this.noleggioButton = noleggioButton;
	}

	private void setTextAreaDimension(JTextField campo) {
		campo.setPreferredSize(new Dimension(120, 20));
    }
	
	private void setFontLabel(JLabel label) {
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
    }
	
}
