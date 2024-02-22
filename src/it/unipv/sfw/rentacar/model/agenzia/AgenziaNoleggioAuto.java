package it.unipv.sfw.rentacar.model.agenzia;

import java.util.ArrayList;
import java.util.List;

import it.unipv.sfw.rentacar.model.contratti.ContrattoNoleggio;
import it.unipv.sfw.rentacar.model.utenti.Cliente;
import it.unipv.sfw.rentacar.model.utenti.Utente;
import it.unipv.sfw.rentacar.model.veicolo.Auto;

public class AgenziaNoleggioAuto {

	private String nome;
	private String indirizzo;
	private List<Auto> elencoAuto;
	private List<Utente> elencoUtenti;
	private List<ContrattoNoleggio> contratti;
	
	public AgenziaNoleggioAuto(String nome, String indirizzo, List<Auto> elencoAuto, List<Utente> elencoUtenti,
			List<ContrattoNoleggio> contratti) {
		
		controlliGenerali(nome, indirizzo);
		
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.elencoAuto = elencoAuto;
		this.elencoUtenti = elencoUtenti;
		this.contratti = contratti;
	}
	
	public AgenziaNoleggioAuto(String nome, String indirizzo) {
		
		controlliGenerali(nome, indirizzo);
		
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.elencoAuto = new ArrayList<>();
		this.elencoUtenti = new ArrayList<>();
		this.contratti = new ArrayList<>();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public List<Auto> getElencoAuto() {
		return elencoAuto;
	}

	public void setElencoAuto(List<Auto> elencoAuto) {
		this.elencoAuto = elencoAuto;
	}

	public List<Utente> getElencoUtenti() {
		return elencoUtenti;
	}

	public void setElencoUtenti(List<Utente> elencoUtenti) {
		this.elencoUtenti = elencoUtenti;
	}

	public List<ContrattoNoleggio> getContratti() {
		return contratti;
	}

	public void setContratti(List<ContrattoNoleggio> contratti) {
		this.contratti = contratti;
	}
	
	public void stampaAuto() {
		for (Auto a : elencoAuto) {
			System.out.println(a.toString());
		}
	}
	
	public void stampaUtenti() {
		for (Utente u : elencoUtenti) {
			System.out.println(u.toString());
		}
	}
	
	public void aggiungiCliente(Cliente c) {
		this.elencoUtenti.add(c);
	}
	
	public void stampaContratti() {
		for (ContrattoNoleggio c : contratti) {
			System.out.println(c.toString());
		}
	}
	
	public void aggiungiContratto(ContrattoNoleggio c) {
		this.contratti.add(c);
	}
	
	public void controlliGenerali(String nome, String indirizzo) {
		
		if (nome == null || indirizzo == null) {
			throw new NullPointerException("Nome e/o indirizzo non possono essere nulli");
		}
		
		if (nome.length() <= 0 || indirizzo.length() <= 0 ) {
			throw new NullPointerException("Nome e/o indirizzo non possono essere vuoti");
		}
	}
	
	public ArrayList<Auto> cercaAutoPerMarca(String marca){
		ArrayList<Auto> listaRicerca = new ArrayList<>();
		for (Auto auto : elencoAuto) {
			if (auto.getMarca().equals(marca)) {
				listaRicerca.add(auto);
			}
		}
		return listaRicerca;
	}
	
	public ArrayList<Auto> cercaAutoPerMarcaEPerModello(String marca, String modello){
		ArrayList<Auto> listaRicerca = new ArrayList<>();
		for (Auto auto : elencoAuto) {
			if (auto.getMarca().equals(marca) && auto.getModello().equals(modello)) {
				listaRicerca.add(auto);
			}
		}
		return listaRicerca;
	}

	@Override
	public String toString() {
		return "AgenziaNoleggioAuto [nome=" + nome + ", indirizzo=" + indirizzo + ", elencoAuto=" + elencoAuto
				+ ", elencoUtenti=" + elencoUtenti + ", contratti=" + contratti + "]";
	}
	
}
