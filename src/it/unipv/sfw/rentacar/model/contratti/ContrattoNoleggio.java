package it.unipv.sfw.rentacar.model.contratti;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import it.unipv.sfw.rentacar.model.contratti.pagamenti.CartaDiCredito;
import it.unipv.sfw.rentacar.model.database.dao.ContrattoNoleggioDAO;
import it.unipv.sfw.rentacar.model.utenti.Cliente;
import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.model.veicolo.noleggio.Noleggio;

/*
 * Classe ContrattoNoleggio
 */

public class ContrattoNoleggio {

	private static int ID; // ID univoco per i contratti
	private String idContratto; // Stringa univoca che identifica un contratto di noleggio
	private Cliente cliente; // Cliente che stipula il contratto
	private Auto auto; // Auto noleggiata
	private LocalDate inizioNoleggio; // Data inizio noleggio
	private LocalDate fineNoleggio; // Data fine noleggio
	private double importo; // Costo totale noleggio
	private CartaDiCredito pagamento; // Carta di credito 
	
	public ContrattoNoleggio(Cliente cliente, Auto auto, String inizioNoleggio, String fineNoleggio, CartaDiCredito pagamento) {
		
	    if (cliente == null || auto == null || pagamento == null) {
	        throw new IllegalArgumentException("Parametri non possono essere nulli");
	    }

	    if (auto.getStatoNoleggio() != Noleggio.DISPONIBILE) {
	        throw new IllegalStateException("L'auto non è disponibile per il noleggio.");
	    }
		
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
		//incrementaID();
	}
	
	// Getter e Setter
	
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

	public CartaDiCredito getPagamento() {
		return pagamento;
	}

	public void setPagamento(CartaDiCredito pagamento) {
		this.pagamento = pagamento;
	}

	public void incrementaID() {
		ID++;
	}
	
	public String creazioneIDContratto() {
		int id;
		ContrattoNoleggioDAO dao = new ContrattoNoleggioDAO();
		id = dao.verificaNumeroContratti();
		return String.format("Rent-a-Car#%05d", id);
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
		
		if ((dataInizio.isAfter(LocalDate.now()) || dataInizio.equals(LocalDate.now())) 
				&& dataInizio.isBefore(dataFine)) {
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
