package it.unipv.sfw.rentacar.view.utenti.amministratori;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.Cambio;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.Carburante;
import it.unipv.sfw.rentacar.view.elementiPersonalizzati.CustomFrame;

public class AggiungiAutoUI {

	private CustomFrame frame;
	private JPanel mainPanel;
	private JLabel titolo;
	private JLabel infoAuto;
	private JLabel caratteristicheAuto;
	private JLabel targaLabel;
	private JLabel marcaLabel;
	private JLabel modelloLabel;
	private JLabel costoGiornalieroLabel;
	private JLabel annoProduzioneLabel;
	private JLabel tipoCambioLabel;
	private JLabel carburanteLabel;
	private JLabel postiAutoLabel;
	private JLabel cilindrataLabel;
	private JLabel potenzaLabel;
	private JTextField targaField;
	private JTextField marcaField;
	private JTextField modelloField;
	private JSpinner costoGiornalieroField;
	private JSpinner annoProduzioneField;
	private JComboBox<Cambio>  tipoCambioField;
	private JCheckBox benzinaCheckBox;
	private JCheckBox gasolioCheckBox;
	private JCheckBox elettricaCheckBox;
	private JCheckBox gplCheckBox;
	private JComboBox<Integer> postiAutoField;
	private JSpinner cilindrataField;
	private JSpinner potenzaField;
	private JButton aggiungiAutoButton;
	
	public AggiungiAutoUI() throws IOException {
		
		JComponent comp;
		SpinnerNumberModel model;
		JSpinner.NumberEditor editor;
		JFormattedTextField formattedText;
		
		frame = new CustomFrame();
		
		mainPanel = frame.getMainPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        
        titolo = new JLabel("Aggiunta Auto");
        titolo.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
        titolo.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        mainPanel.add(titolo, gbc);
        
        infoAuto = new JLabel("Informazioni Auto");
        infoAuto.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        infoAuto.setForeground(Color.MAGENTA);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(infoAuto, gbc);
        
        targaLabel = new JLabel("Targa : ");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(targaLabel, gbc);
        
        targaField = new JTextField();
        setDimensione(targaField);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(targaField, gbc);
        
        marcaLabel = new JLabel("Marca : ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(marcaLabel, gbc);
        
        marcaField = new JTextField();
        setDimensione(marcaField);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(marcaField, gbc);
        
        modelloLabel = new JLabel("Modello : ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(modelloLabel, gbc);
        
        modelloField = new JTextField();
        setDimensione(modelloField);
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(modelloField, gbc);
        
        annoProduzioneLabel = new JLabel("Anno di Produzione : ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(annoProduzioneLabel, gbc);
        
        model = new SpinnerNumberModel(1970, 1970, LocalDate.now().getYear(), 1);
        annoProduzioneField = new JSpinner(model);
        editor = new JSpinner.NumberEditor(annoProduzioneField, "0");
        annoProduzioneField.setEditor(editor);
        annoProduzioneField.setPreferredSize(new Dimension(50, 20));
        comp = annoProduzioneField.getEditor();
        formattedText = ((JSpinner.DefaultEditor) comp).getTextField();
        formattedText.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(annoProduzioneField, gbc);
        
        costoGiornalieroLabel = new JLabel("Costo Giornaliero : ");
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(costoGiornalieroLabel, gbc);

        model = new SpinnerNumberModel(0.0, 0.0, 10000, 0.5);
        costoGiornalieroField = new JSpinner(model);
        editor = new JSpinner.NumberEditor(costoGiornalieroField, "0.00");
        costoGiornalieroField.setEditor(editor);
        costoGiornalieroField.setPreferredSize(new Dimension(50, 20));
        comp = costoGiornalieroField.getEditor();
        formattedText = ((JSpinner.DefaultEditor) comp).getTextField();
        formattedText.setEditable(false);
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        mainPanel.add(costoGiornalieroField, gbc);
        
        caratteristicheAuto = new JLabel("Caratteristiche Auto");
        caratteristicheAuto.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        caratteristicheAuto.setForeground(Color.MAGENTA);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(caratteristicheAuto, gbc);

        tipoCambioLabel = new JLabel("Tipo di Cambio : ");
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 2;
        mainPanel.add(tipoCambioLabel, gbc);

        tipoCambioField = new JComboBox<Cambio>();
        tipoCambioField.addItem(Cambio.MANUALE);
        tipoCambioField.addItem(Cambio.AUTOMATICO);
        gbc.gridx = 3;
        gbc.gridy = 2;
        mainPanel.add(tipoCambioField, gbc);
        
        carburanteLabel = new JLabel("Carburante : ");
        gbc.gridx = 2;
        gbc.gridy = 3;
        mainPanel.add(carburanteLabel, gbc);
        
        benzinaCheckBox = new JCheckBox("BENZINA");
        gbc.gridx = 3;
        gbc.gridy = 3;
        mainPanel.add(benzinaCheckBox, gbc);
        
        gplCheckBox = new JCheckBox("GPL");
        gbc.gridx = 4;
        gbc.gridy = 3;
        mainPanel.add(gplCheckBox, gbc);
        
        gasolioCheckBox = new JCheckBox("GASOLIO");
        gbc.gridx = 5;
        gbc.gridy = 3;
        mainPanel.add(gasolioCheckBox, gbc);
        
        elettricaCheckBox = new JCheckBox("ELETTRICA");
        gbc.gridx = 6;
        gbc.gridy = 3;
        mainPanel.add(elettricaCheckBox, gbc);
        
        postiAutoLabel = new JLabel("Posti auto :");
        gbc.gridx = 2;
        gbc.gridy = 4;
        mainPanel.add(postiAutoLabel, gbc);

        postiAutoField = new JComboBox<Integer>();
        postiAutoField.addItem(1);
        postiAutoField.addItem(2);
        postiAutoField.addItem(3);
        postiAutoField.addItem(5);
        postiAutoField.addItem(7);
        postiAutoField.addItem(9);
        gbc.gridx = 3;
        gbc.gridy = 4;
        mainPanel.add(postiAutoField, gbc);

        cilindrataLabel = new JLabel("Cilindrata : ");
        gbc.gridx = 2;
        gbc.gridy = 5;
        mainPanel.add(cilindrataLabel, gbc);
        
        model = new SpinnerNumberModel(0, 0, 14500, 1);
        cilindrataField = new JSpinner(model);
        editor = new JSpinner.NumberEditor(cilindrataField, "0");
        cilindrataField.setEditor(editor);
        cilindrataField.setPreferredSize(new Dimension(50, 20));
        comp = cilindrataField.getEditor();
        formattedText = ((JSpinner.DefaultEditor) comp).getTextField();
        formattedText.setEditable(false);
        gbc.gridx = 3;
        gbc.gridy = 5;
        mainPanel.add(cilindrataField, gbc);
        
        potenzaLabel = new JLabel("Potenza : ");
        gbc.gridx = 2;
        gbc.gridy = 6;
        mainPanel.add(potenzaLabel, gbc);

        model = new SpinnerNumberModel(0, 0, 2000, 1);
        potenzaField = new JSpinner(model);
        editor = new JSpinner.NumberEditor(potenzaField, "0");
        potenzaField.setEditor(editor);
        potenzaField.setPreferredSize(new Dimension(50, 20));
        comp = potenzaField.getEditor();
        formattedText = ((JSpinner.DefaultEditor) comp).getTextField();
        formattedText.setEditable(false);
        gbc.gridx = 3;
        gbc.gridy = 6;
        mainPanel.add(potenzaField, gbc);
        
        aggiungiAutoButton = new JButton("Aggiungi Auto");
        aggiungiAutoButton.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        mainPanel.add(aggiungiAutoButton, gbc);
        
		frame.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException {
		new AggiungiAutoUI();
	}
	
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

	public JLabel getTitolo() {
		return titolo;
	}

	public void setTitolo(JLabel titolo) {
		this.titolo = titolo;
	}

	public JLabel getInfoAuto() {
		return infoAuto;
	}

	public void setInfoAuto(JLabel infoAuto) {
		this.infoAuto = infoAuto;
	}

	public JLabel getCaratteristicheAuto() {
		return caratteristicheAuto;
	}

	public void setCaratteristicheAuto(JLabel caratteristicheAuto) {
		this.caratteristicheAuto = caratteristicheAuto;
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

	public JLabel getCostoGiornalieroLabel() {
		return costoGiornalieroLabel;
	}

	public void setCostoGiornalieroLabel(JLabel costoGiornalieroLabel) {
		this.costoGiornalieroLabel = costoGiornalieroLabel;
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

	public JLabel getCarburanteLabel() {
		return carburanteLabel;
	}

	public void setCarburanteLabel(JLabel carburanteLabel) {
		this.carburanteLabel = carburanteLabel;
	}

	public JLabel getPostiAutoLabel() {
		return postiAutoLabel;
	}

	public void setPostiAutoLabel(JLabel postiAutoLabel) {
		this.postiAutoLabel = postiAutoLabel;
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

	public JTextField getTargaField() {
		return targaField;
	}

	public void setTargaField(JTextField targaField) {
		this.targaField = targaField;
	}

	public JTextField getMarcaField() {
		return marcaField;
	}

	public void setMarcaField(JTextField marcaField) {
		this.marcaField = marcaField;
	}

	public JTextField getModelloField() {
		return modelloField;
	}

	public void setModelloField(JTextField modelloField) {
		this.modelloField = modelloField;
	}

	public JSpinner getCostoGiornalieroField() {
		return costoGiornalieroField;
	}

	public void setCostoGiornalieroField(JSpinner costoGiornalieroField) {
		this.costoGiornalieroField = costoGiornalieroField;
	}

	public JSpinner getAnnoProduzioneField() {
		return annoProduzioneField;
	}

	public void setAnnoProduzioneField(JSpinner annoProduzioneField) {
		this.annoProduzioneField = annoProduzioneField;
	}

	public JComboBox<Cambio> getTipoCambioField() {
		return tipoCambioField;
	}

	public void setTipoCambioField(JComboBox<Cambio> tipoCambioField) {
		this.tipoCambioField = tipoCambioField;
	}

	public JCheckBox getBenzinaCheckBox() {
		return benzinaCheckBox;
	}

	public void setBenzinaCheckBox(JCheckBox benzinaCheckBox) {
		this.benzinaCheckBox = benzinaCheckBox;
	}

	public JCheckBox getGasolioCheckBox() {
		return gasolioCheckBox;
	}

	public void setGasolioCheckBox(JCheckBox dieselCheckBox) {
		this.gasolioCheckBox = dieselCheckBox;
	}

	public JCheckBox getElettricaCheckBox() {
		return elettricaCheckBox;
	}

	public void setElettricaCheckBox(JCheckBox elettricaCheckBox) {
		this.elettricaCheckBox = elettricaCheckBox;
	}

	public JCheckBox getGplCheckBox() {
		return gplCheckBox;
	}

	public void setGplCheckBox(JCheckBox gplCheckBox) {
		this.gplCheckBox = gplCheckBox;
	}

	public JComboBox<Integer> getPostiAutoField() {
		return postiAutoField;
	}

	public void setPostiAutoField(JComboBox<Integer> postiAutoField) {
		this.postiAutoField = postiAutoField;
	}

	public JSpinner getCilindrataField() {
		return cilindrataField;
	}

	public void setCilindrataField(JSpinner cilindrataField) {
		this.cilindrataField = cilindrataField;
	}

	public JSpinner getPotenzaField() {
		return potenzaField;
	}

	public void setPotenzaField(JSpinner potenzaField) {
		this.potenzaField = potenzaField;
	}

	
	public JButton getAggiungiAutoButton() {
		return aggiungiAutoButton;
	}

	public void setAggiungiAutoButton(JButton aggiungiAutoButton) {
		this.aggiungiAutoButton = aggiungiAutoButton;
	}

	private void setDimensione(JComponent campo) {
		campo.setPreferredSize(new Dimension(150, 20));
	}
	
}
