package it.unipv.sfw.rentacar.model.contratti;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import it.unipv.sfw.rentacar.model.contratti.pagamenti.CartaDiCredito;
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
	
	public ContrattoNoleggio(Cliente cliente, Auto auto, String inizioNoleggio, String fineNoleggio, double importo, CartaDiCredito pagamento) {
		this.idContratto = creazioneIDContratto();
		this.cliente = cliente;
		this.auto = auto;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.inizioNoleggio = LocalDate.parse(inizioNoleggio, formatter);
		this.fineNoleggio = LocalDate.parse(fineNoleggio, formatter);
		this.importo = calcolaNoleggio();
		this.pagamento = pagamento;
		
		incrementaID();
	}
	
	public static int getID() {
		return ID;
	}

	public static void setID(int iD) {
		ID = iD;
	}

	public String getIdContratto() {
		return idContratto;
	}

	public void setIdContratto(String idContratto) {
		this.idContratto = idContratto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public LocalDate getInizioNoleggio() {
		return inizioNoleggio;
	}

	public void setInizioNoleggio(LocalDate inizioNoleggio) {
		this.inizioNoleggio = inizioNoleggio;
	}

	public LocalDate getFineNoleggio() {
		return fineNoleggio;
	}

	public void setFineNoleggio(LocalDate fineNoleggio) {
		this.fineNoleggio = fineNoleggio;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
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
	
	public double calcolaNoleggio() {
		return conteggioGiorniNoleggio() * this.auto.getCostoNoleggioGiornaliero();
	}
	
}
