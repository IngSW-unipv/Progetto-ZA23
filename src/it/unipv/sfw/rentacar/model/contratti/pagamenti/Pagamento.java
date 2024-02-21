package it.unipv.sfw.rentacar.model.contratti.pagamenti;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Pagamento {

	private String titolare;
	private String causale;
	private LocalDate dataEsecuzione;
	
	public Pagamento(String titolare, String causale) {
		
		if (titolare.length() <= 0) {
			throw new IllegalArgumentException("Valore Titolare non valido");
		}
		if (causale.length() <= 0) {
			throw new IllegalArgumentException("Valore Causale non valido");
		}
		
		this.titolare = titolare;
		this.causale = causale;
        this.dataEsecuzione = LocalDate.now();
	}

	public String getTitolare() {
		return titolare;
	}

	public void setTitolare(String titolare) {
		this.titolare = titolare;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public LocalDate getDataEsecuzione() {
		return dataEsecuzione;
	}

	public void setDataEsecuzione(LocalDate dataEsecuzione) {
		this.dataEsecuzione = dataEsecuzione;
	}
	
	public String dataEsecuzioneFormattata() {
		return dataEsecuzione.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public abstract void effettuaPagamento();

	@Override
	public String toString() {
		return "Pagamento [titolare=" + titolare + ", causale=" + causale + ", data esecuzione="
				+ dataEsecuzioneFormattata() + "]";
	}
	

}