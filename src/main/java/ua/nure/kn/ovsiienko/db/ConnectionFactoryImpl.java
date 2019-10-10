package ua.nure.kn.ovsiienko.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {

	@Override
	public Connection getConnection() throws DatabaseException {
        String driver = "ord.hsqldb.jdbcDriver";
        String url = "jdbc:hsqldb:file:db/usermanagement";
        String user = "sa";
        String password = "";
        //method reflecsia
        try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DatabaseException(e);
		}
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

}
