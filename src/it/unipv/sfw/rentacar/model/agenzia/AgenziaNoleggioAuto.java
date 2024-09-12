package it.unipv.sfw.rentacar.model.agenzia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unipv.sfw.rentacar.model.contratti.ContrattoNoleggio;
import it.unipv.sfw.rentacar.model.database.dao.ContrattoNoleggioDAO;
import it.unipv.sfw.rentacar.model.database.dao.UtenteDAO;
import it.unipv.sfw.rentacar.model.exception.UsernameDuplicatoException;
import it.unipv.sfw.rentacar.model.utenti.Cliente;
import it.unipv.sfw.rentacar.model.utenti.Utente;
import it.unipv.sfw.rentacar.model.utenti.log.SessioneLogin;
import it.unipv.sfw.rentacar.model.veicolo.Auto;
import it.unipv.sfw.rentacar.model.veicolo.noleggio.Noleggio;

public class AgenziaNoleggioAuto {
	
    // Istanza unica della classe (Singleton)
    private static AgenziaNoleggioAuto instance;
	
	private String nome;
	private String indirizzo;
	private List<Auto> elencoAuto;
	private List<Utente> elencoUtenti;
	private List<ContrattoNoleggio> contratti;
	
    // Costruttore privato
    private AgenziaNoleggioAuto(String nome, String indirizzo, List<Auto> elencoAuto, List<Utente> elencoUtenti,
                                List<ContrattoNoleggio> contratti) {
        
        controlliGenerali(nome, indirizzo);
        this.nome = nome;
        this.indirizzo = indirizzo;
        
        // Inizializza le liste solo se non sono già state passate
        this.elencoAuto = (elencoAuto != null) ? elencoAuto : new ArrayList<>();
        this.elencoUtenti = (elencoUtenti != null) ? elencoUtenti : new ArrayList<>();
        this.contratti = (contratti != null) ? contratti : new ArrayList<>();
    }
	
	private AgenziaNoleggioAuto(String nome, String indirizzo) {
		
		controlliGenerali(nome, indirizzo);
		
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.elencoAuto = new ArrayList<>();
		this.elencoUtenti = new ArrayList<>();
		this.contratti = new ArrayList<>();
	}

    public static synchronized AgenziaNoleggioAuto getInstance(String nome, String indirizzo) {
    	return getInstance(nome, indirizzo, null, null, null);
    }

    public static synchronized AgenziaNoleggioAuto getInstance(String nome, String indirizzo, List<Auto> elencoAuto, List<Utente> elencoUtenti, List<ContrattoNoleggio> contratti) {
        if (instance == null) {
        	instance = new AgenziaNoleggioAuto(nome, indirizzo, elencoAuto, elencoUtenti, contratti);
        }
        return instance;
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
	
	public void aggiungiUtente(Utente c) throws UsernameDuplicatoException, SQLException {
		if (controlloUsernameUtenti(c)) {
			this.elencoUtenti.add(c);
			UtenteDAO dao = new UtenteDAO();
			dao.aggiungiCliente((Cliente) c);
		}
	}
	
	public boolean controlloUsernameUtenti(Utente c) {
		for (Utente u : elencoUtenti) {
			if (c.getUsername().equals(u.getUsername()))
				return false;
		}
		return true;
	}
	
	public void eliminaUtente(Utente c) {
		this.elencoUtenti.remove(c);
	}
	
	public void stampaContratti() {
		for (ContrattoNoleggio c : contratti) {
			System.out.println(c.toString());
		}
	}
	
	public void aggiungiContratto(ContrattoNoleggio c) throws SQLException {
		ContrattoNoleggioDAO dao = new ContrattoNoleggioDAO();
		dao.aggiungiContratto(c);
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
	
	public Auto cercaAutoPerTarga(String targa){
		Auto a = null;
		for (Auto auto : elencoAuto) {
			if (auto.getTarga().equals(targa)) {
				a = auto;
			}
		}
		return a;
	}
	
	public ArrayList<Auto> cercaAutoPerMarca(String marca){
		ArrayList<Auto> listaRicerca = new ArrayList<>();
		for (Auto auto : elencoAuto) {
			if (auto.getMarca().toLowerCase().contains(marca)) {
				listaRicerca.add(auto);
			}
		}
		return listaRicerca;
	}
	
	public ArrayList<Auto> cercaAutoPerMarcaEPerModello(String marca, String modello){
		ArrayList<Auto> listaRicerca = new ArrayList<>();
		for (Auto auto : elencoAuto) {
			if (auto.getStatoNoleggio().equals(Noleggio.DISPONIBILE) &&
					auto.getMarca().toLowerCase().contains(marca) &&
					auto.getModello().toLowerCase().contains(modello)) {
				listaRicerca.add(auto);
			}
		}
		return listaRicerca;
	}
	
	public void cercaUtenteRegistrato(String user, String pass) {
		for (Utente u : this.getElencoUtenti()) {
			if (u.getUsername().equals(user) && u.getPassword().equals(pass)) {
				SessioneLogin sessione = SessioneLogin.getInstance();
				sessione.setNuovoUtente(u);
				sessione.login();
			}
		}
	}

	public void cambiaStatoNoleggio(Auto a) {
		if (a.getStatoNoleggio().equals(Noleggio.DISPONIBILE))
			a.setStatoNoleggio(Noleggio.NOLEGGIATA);
		else
			a.setStatoNoleggio(Noleggio.DISPONIBILE);
	}
	
	@Override
	public String toString() {
		return "AgenziaNoleggioAuto [nome=" + nome + ", indirizzo=" + indirizzo + ", elencoAuto=" + elencoAuto
				+ ", elencoUtenti=" + elencoUtenti + ", contratti=" + contratti + "]";
	}
	
}
