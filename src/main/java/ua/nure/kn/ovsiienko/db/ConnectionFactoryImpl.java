package ua.nure.kn.ovsiienko.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {
	
	private String driver;
	private String url;
	private String user;
	private String password;
	
    public Connection createConnection() throws DatabaseException{
        //method reflecsia
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



   
    
    public ConnectionFactoryImpl(String driver2, String url2, String user2, String password2){
    	this.driver = driver;
    	this.url = url;
    	this.user = user;
    	this.password = password;
    }





	

        
        

}
