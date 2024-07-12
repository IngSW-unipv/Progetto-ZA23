package it.unipv.sfw.rentacar.model.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import it.unipv.sfw.rentacar.model.database.DatabaseConnection;
import it.unipv.sfw.rentacar.model.veicolo.Auto;

public class AutoDAO {

	public AutoDAO() {
		
	}
	
	public void creaAuto(Auto a) throws SQLException {
		String query = "INSERT INTO auto VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.connessione();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, a.getTarga());
	            stmt.setString(2, a.getMarca());
	            stmt.setString(3, a.getModello());
	            stmt.setDouble(4, a.getCostoNoleggioGiornaliero());
	            stmt.setString(5, a.getStatoNoleggio().name());
	            stmt.setInt(6, a.getCaratteristicheTecniche().getAnnoProduzione());
	            stmt.setString(7, a.getCaratteristicheTecniche().getTipoCambio().name());
	            stmt.setString(8, a.getCaratteristicheTecniche().getTipoCarburante()[0].name());
	            /*if (a.getCaratteristicheTecniche().getTipoCarburante().length > 1) {
	            	stmt.setString(10, a.getCaratteristicheTecniche().getTipoCarburante()[1].name());	
				}*/
	            stmt.setString(9, a.getCaratteristicheTecniche().getTipoCarburante()[1].name());
	            stmt.setInt(10, a.getCaratteristicheTecniche().getPostiAuto());
	            stmt.setInt(11, a.getCaratteristicheTecniche().getCilindrata());
	            stmt.setInt(12, a.getCaratteristicheTecniche().getPotenza());
	            stmt.executeUpdate();
	        }
	}
}
