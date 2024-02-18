package it.unipv.sfw.rentacar.model.veicolo;

import it.unipv.sfw.rentacar.model.veicolo.caratteristiche.CaratteristicheTecniche;

public class Auto {

	private String targa;
	private String marca;
	private String modello;
	private CaratteristicheTecniche caratteristicheTecniche;
	
	public Auto(String targa, String marca, String modello, CaratteristicheTecniche caratteristicheTecniche) {
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.caratteristicheTecniche = caratteristicheTecniche;
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

	@Override
	public String toString() {
		return "Auto [targa=" + targa + ", marca=" + marca + ", modello=" + modello + ", caratteristicheTecniche="
				+ caratteristicheTecniche + "]";
	}

	
}
