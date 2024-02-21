package it.unipv.sfw.rentacar.model.contratti;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import it.unipv.sfw.rentacar.model.contratti.pagamenti.Pagamento;
import it.unipv.sfw.rentacar.model.utenti.Cliente;
import it.unipv.sfw.rentacar.model.veicolo.Auto;

public class ContrattoNoleggio {

	private static int ID = 0;
	private String idContratto;
	private Cliente cliente;
	private Auto auto;
	private LocalDate inizioNoleggio;
	private LocalDate fineNoleggio;
	private double importo;
	private Pagamento pagamento;
	
	public ContrattoNoleggio(Cliente cliente, Auto auto, String inizioNoleggio, String fineNoleggio, double importo, Pagamento pagamento) {
		this.idContratto = creazioneIDContratto();
		this.cliente = cliente;
		this.auto = auto;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.inizioNoleggio = LocalDate.parse(inizioNoleggio, formatter);
		this.fineNoleggio = LocalDate.parse(fineNoleggio, formatter);
		this.importo = conteggioGiorniNoleggio();
		incrementaID();
	}
	
	public void incrementaID() {
		ID++;
	}
	
	public String creazioneIDContratto() {
		return String.format("Rent-a-Car#%05d", ID);
	}
	
	public int conteggioGiorniNoleggio() {
		return (int)ChronoUnit.DAYS.between(inizioNoleggio, fineNoleggio);
	}
	
}
