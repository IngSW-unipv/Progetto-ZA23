package it.unipv.sfw.rentacar.model.contratti;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import it.unipv.sfw.rentacar.model.contratti.pagamenti.CartaDiCredito;
import it.unipv.sfw.rentacar.model.contratti.pagamenti.Pagamento;
import it.unipv.sfw.rentacar.model.exception.CartaDiCreditoScadutaException;
import it.unipv.sfw.rentacar.model.exception.CategoriaBPatenteException;
import it.unipv.sfw.rentacar.model.exception.NumeroPatenteInvalidoException;
import it.unipv.sfw.rentacar.model.exception.PatenteScadutaException;
import it.unipv.sfw.rentacar.model.exception.TargaNonValidaException;
import it.unipv.sfw.rentacar.model.utenti.Cliente;
import it.unipv.sfw.rentacar.model.utenti.Utente;
import it.unipv.sfw.rentacar.model.utenti.documenti.Patente;
import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.Cambio;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.CaratteristicheTecniche;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.Carburante;
import it.unipv.sfw.rentacar.model.veicolo.noleggio.Noleggio;

public class ContrattoNoleggio {

	private static int ID = 0;
	private String idContratto;
	private Cliente cliente;
	private Auto auto;
	private LocalDate inizioNoleggio;
	private LocalDate fineNoleggio;
	private double importo;
	private Pagamento pagamento;
	
	public ContrattoNoleggio(Cliente cliente, Auto auto, String inizioNoleggio, String fineNoleggio, Pagamento pagamento) {
		
		if (!controlloDateNoleggio(inizioNoleggio, fineNoleggio)) {
			throw new DateTimeException("Date selezionate per il noleggio non valide oppure errate");
		}
		
		this.idContratto = creazioneIDContratto();
		this.cliente = cliente;
		this.auto = auto;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.inizioNoleggio = LocalDate.parse(inizioNoleggio, formatter);
		this.fineNoleggio = LocalDate.parse(fineNoleggio, formatter);
		this.importo = calcolaNoleggio();
		this.pagamento = pagamento;
		this.auto.setStatoNoleggio(Noleggio.NOLEGGIATA);
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

	public Utente getCliente() {
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
	
	public boolean controlloDateNoleggio(String inizio, String fine) {
		
		LocalDate dataInizio, dataFine;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dataInizio = LocalDate.parse(inizio, formatter);
		dataFine = LocalDate.parse(fine, formatter);
		
		if ((dataInizio.isAfter(LocalDate.now()) || dataInizio.equals(LocalDate.now())) && dataInizio.isBefore(dataFine)) {
			return true;
		}else
			return false;
	}
	
	@Override
	public String toString() {
		return "ContrattoNoleggio [idContratto=" + idContratto + ", cliente=" + cliente + ", auto=" + auto
				+ ", inizioNoleggio=" + inizioNoleggio + ", fineNoleggio=" + fineNoleggio + ", importo=" + importo
				+ ", pagamento=" + pagamento + "]";
	}
	
}
