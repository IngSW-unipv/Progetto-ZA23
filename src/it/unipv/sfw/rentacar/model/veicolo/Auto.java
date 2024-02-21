package it.unipv.sfw.rentacar.model.veicolo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unipv.sfw.rentacar.model.exception.TargaNonValidaException;
import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.CaratteristicheTecniche;
import it.unipv.sfw.rentacar.model.veicolo.noleggio.INoleggiabile;
import it.unipv.sfw.rentacar.model.veicolo.noleggio.Noleggio;

public class Auto implements INoleggiabile{

	private String targa;
	private String marca;
	private String modello;
	private CaratteristicheTecniche caratteristicheTecniche;
	private double costoNoleggioGiornaliero;
	private Noleggio statoNoleggio;
	
	public Auto(String targa, String marca, String modello, CaratteristicheTecniche caratteristicheTecniche, double costoNoleggioGiornaliero) throws TargaNonValidaException {
		
		if (marca.equals(null)) {
			throw new NullPointerException("Marca  non può essere null");
		}
		
		if (modello.equals(null)) {
			throw new NullPointerException("Modello non può essere null");
		}
		
		if (!verificaTarga(targa)) {
			throw new TargaNonValidaException();
		}
		
		if (marca.length() <= 0) {
			throw new IllegalArgumentException("Il campo marca non é valido");
		}
		
		if (modello.length() <= 0) {
			throw new IllegalArgumentException("Il campo modello non é valido");
		}
		
		if (caratteristicheTecniche.equals(null)) {
			throw new NullPointerException("CaratteristicheTecniche non può essere null");
		}
		
		if (costoNoleggioGiornaliero <= 0) {
			throw new IllegalArgumentException("Il campo costoNoleggioGiornaliero non può essere nullo o negativo");
		}
		
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.caratteristicheTecniche = caratteristicheTecniche;
		this.costoNoleggioGiornaliero = costoNoleggioGiornaliero;
		this.statoNoleggio = Noleggio.DISPONIBILE;
	}
	
	public String getTarga() {
		return targa;
	}
	
	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public CaratteristicheTecniche getCaratteristicheTecniche() {
		return caratteristicheTecniche;
	}

	public void setCaratteristicheTecniche(CaratteristicheTecniche caratteristicheTecniche) {
		this.caratteristicheTecniche = caratteristicheTecniche;
	}
	
	public double getCostoNoleggioGiornaliero() {
		return costoNoleggioGiornaliero;
	}
	
	public void setCostoNoleggioGiornaliero(double nuovoCostoNoleggioGiornaliero) {
		this.costoNoleggioGiornaliero = nuovoCostoNoleggioGiornaliero;
	}
	
	public Noleggio getStatoNoleggio() {
		return statoNoleggio;
	}
	
	public void setStatoNoleggio(Noleggio statoNoleggio) {
		this.statoNoleggio = statoNoleggio;
	}
	
	public void cambioStatoNoleggio() {
		if (statoNoleggio.equals(Noleggio.DISPONIBILE)) {
			this.statoNoleggio = Noleggio.NOLEGGIATA;
		}else
			this.statoNoleggio = Noleggio.DISPONIBILE;
	}
	
	public boolean verificaTarga(String targa) {
		String formatoTarga = "[A-HJ-NP-TV-Z]{2}[0-9]{3}[A-HJ-NP-TV-Z]{2}[0-9]{2}";
		Pattern pattern = Pattern.compile(formatoTarga);
		Matcher matcher = pattern.matcher(targa);
		
		return matcher.matches();
	}
	
	@Override
	public boolean noleggiabile() {
		return (statoNoleggio.equals(Noleggio.DISPONIBILE));
	}

	@Override
	public String toString() {
		return "Auto [targa=" + targa + ", marca=" + marca + ", modello=" + modello + ", caratteristicheTecniche="
				+ caratteristicheTecniche + ", statoNoleggio=" + statoNoleggio + "]";
	}
	
}
