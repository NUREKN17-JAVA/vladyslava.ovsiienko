package ua.nure.kn.ovsiienko.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImpl implements ConnectionFactory {
	
	private String driver;
	private String url;
	private String user;
	private String password;
	
	ConnectionFactoryImpl() {}
	
    public Connection createConnection() throws DatabaseException{
        String driver= "org.hsqldb.jdbcDriver";
        String url= "jdbc:hsqldb:file:db/usermanagement";
        String user= "sa";
        String password= "";
        
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



   
    
    public ConnectionFactoryImpl(String driver, String url, String user, String password){
    	this.driver = driver;
    	this.url = url;
    	this.user = user;
    	this.password = password;
    }





	public ConnectionFactoryImpl(Properties properties) {
		user = properties.getProperty("connection.user");
		password = properties.getProperty("connection.password");
		url = properties.getProperty("connection.url");
		driver = properties.getProperty("connection.driver");
	}





	

        
        

}
