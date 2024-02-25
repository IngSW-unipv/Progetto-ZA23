package it.unipv.sfw.rentacar.view.catalogoauto;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import it.unipv.sfw.rentacar.model.agenzia.AgenziaNoleggioAuto;
import it.unipv.sfw.rentacar.model.exception.CategoriaBPatenteException;
import it.unipv.sfw.rentacar.model.exception.NumeroPatenteInvalidoException;
import it.unipv.sfw.rentacar.model.exception.PatenteScadutaException;
import it.unipv.sfw.rentacar.model.exception.TargaNonValidaException;
import it.unipv.sfw.rentacar.model.utenti.Amministratore;
import it.unipv.sfw.rentacar.model.utenti.Cliente;
import it.unipv.sfw.rentacar.model.utenti.Utente;
import it.unipv.sfw.rentacar.model.utenti.documenti.Patente;
import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.Cambio;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.CaratteristicheTecniche;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.Carburante;
import it.unipv.sfw.rentacar.model.veicolo.noleggio.Noleggio;
import it.unipv.sfw.rentacar.view.elementiPersonalizzati.CustomFrame;

public class CatalogoAutoUI {

	public CatalogoAutoUI(AgenziaNoleggioAuto agenzia) throws IOException, UnsupportedLookAndFeelException {
		CustomFrame frame = new CustomFrame();
		
		frame.getMainPanel().setLayout(new GridLayout(0, 3, 10, 10));
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		
		for (Auto a : agenzia.getElencoAuto()) {
			if (a.getStatoNoleggio().equals(Noleggio.DISPONIBILE)) {
				JPanel cardAuto = creaCardAuto(a);
				cardAuto.setBackground(Color.WHITE);
				frame.getMainPanel().add(cardAuto);
			}
		}
		
		frame.getMainPanel().setVisible(true);
		frame.setVisible(true);
	}
	
	private JPanel creaCardAuto(Auto a) throws UnsupportedLookAndFeelException {
		
		JPanel auto = new JPanel();
		auto.setLayout(new BoxLayout(auto, BoxLayout.Y_AXIS));
		
		JLabel marcaLabel = new JLabel("Marca: " + a.getMarca());
        JLabel modelloLabel = new JLabel("Modello: " + a.getModello());
        JLabel costoNoleggioLabel = new JLabel("Costo Noleggio Giornaliero: " + a.getCostoNoleggioGiornaliero() + " €");
		JButton noleggiaButton = new JButton("Noleggia");
        
        auto.add(marcaLabel);
        auto.add(modelloLabel);
        auto.add(costoNoleggioLabel);
        auto.add(noleggiaButton);
		return auto;
	}

	public static void main(String[] args) throws IOException, NumeroPatenteInvalidoException, PatenteScadutaException, CategoriaBPatenteException, TargaNonValidaException, UnsupportedLookAndFeelException {
		
		String[] cat = {"B"};
		Patente p = new Patente("AB123456CC", "18/05/2025", cat);
		
		Utente cl = new Cliente("Roberto", "Pitorac" , "Pito", "Pitorac01", p);
		Amministratore amm = new Amministratore("Roberto", "Pitorac" , "Pito", "Pitorac01");
		AgenziaNoleggioAuto agenzia = new AgenziaNoleggioAuto("Rent-a-Car", "Via Mazzini, 17");
		agenzia.aggiungiUtente(cl);
		Carburante[] carburante = {Carburante.BENZINA};
		CaratteristicheTecniche ct1 = new CaratteristicheTecniche(2008, Cambio.AUTOMATICO, carburante, 5, 150, 200);
		Auto a1 = new Auto("AB456CD", "Fiat", "Panda", ct1, 5);
		Auto a2 = new Auto("AB789CD", "Ford", "Fiesta", ct1, 10);
		Auto a3 = new Auto("AB000CD", "Volkswagen", "Golf", ct1, 7);
		Auto a4 = new Auto("AB159CD", "Fiat", "Panda", ct1, 6.5);
		amm.aggiungiAuto(agenzia, a1);
		amm.aggiungiAuto(agenzia, a2);
		amm.aggiungiAuto(agenzia, a3);
		amm.aggiungiAuto(agenzia, a4);
		CatalogoAutoUI catalogo = new CatalogoAutoUI(agenzia);
	}
	
}
