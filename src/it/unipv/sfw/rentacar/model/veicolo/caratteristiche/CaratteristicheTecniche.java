package it.unipv.sfw.rentacar.model.veicolo.caratteristiche;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;

public class CaratteristicheTecniche {

	private int annoProduzione;
	private Cambio tipoCambio;
	private Carburante[] tipoCarburante;
	private int postiAuto;
	private int cilindrata;
	private int potenza;
	
	public CaratteristicheTecniche(int annoProduzione, Cambio tipoCambio, Carburante[] tipoCarburante, int postiAuto,
			int cilindrata, int potenza) {
		
		if (annoProduzione > LocalDate.now().getYear()) {
			throw new IllegalArgumentException("L'anno di produzione non può essere maggiore dell'anno corrente");
		}
		
		if (tipoCambio == null || tipoCarburante == null) {
			throw new NullPointerException("La tipologia di cambio e/o la tipologia di carburante non può essere null");
		}

		if (postiAuto <= 0) {
			throw new IllegalArgumentException("I posti auto non possono essere negativi o pari a 0");
		}
		
		if (cilindrata <= 0) {
			throw new IllegalArgumentException("La cilindrata non può essere negativa o pari a 0");
		}
		
		if (potenza <= 0) {
			throw new IllegalArgumentException("La potenza non può essere negativa o pari a 0");
		}
		
		this.annoProduzione = annoProduzione;
		this.tipoCambio = tipoCambio;
		this.tipoCarburante = tipoCarburante;
		this.postiAuto = postiAuto;
		this.cilindrata = cilindrata;
		this.potenza = potenza;
	}

	public int getAnnoProduzione() {
		return annoProduzione;
	}

	public void setAnnoProduzione(int annoProduzione) {
		this.annoProduzione = annoProduzione;
	}

	public Cambio getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(Cambio tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public Carburante[] getTipoCarburante() {
		return tipoCarburante;
	}
	
	public void setTipoCarburante(Carburante[] tipoCarburante) {
		this.tipoCarburante = tipoCarburante;
	}

	public int getPostiAuto() {
		return postiAuto;
	}

	public void setPostiAuto(int postiAuto) {
		this.postiAuto = postiAuto;
	}

	public int getCilindrata() {
		return cilindrata;
	}

	public void setCilindrata(int cilindrata) {
		this.cilindrata = cilindrata;
	}

	public int getPotenza() {
		return potenza;
	}

	public void setPotenza(int potenza) {
		this.potenza = potenza;
	}

	@Override
	public String toString() {
		return "CaratteristicheTecniche [annoProduzione=" + annoProduzione + ", tipoCambio=" + tipoCambio
				+ ", tipoCarburante=" + Arrays.toString(tipoCarburante) + ", postiAuto=" + postiAuto + ", cilindrata="
				+ cilindrata + ", potenza=" + potenza + "]";
	}
	
	
	
}
