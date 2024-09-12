package it.unipv.sfw.rentacar.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Classe DatabaseConnection
 */

public class DatabaseConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/rent_car"; // URL di connessione
	private static final String USER= "root"; // Username di accesso al DB
	private static final String PASSWORD = "ProgettoPO"; // Password di accesso al DB
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Driver per stabilire la connessione
    
    
    // Metodo di connessione al DB
    
    public static Connection connessione() throws SQLException {
    	try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC non trovato", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    
    public static void chiusura(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static boolean connessioneAperta(DatabaseConnection conn) {
    	if (conn == null) {
			return false;
		}else
			return true;
    }
    
    public static void main(String[] args) throws SQLException {
       	DatabaseConnection.connessione();
	}
}
