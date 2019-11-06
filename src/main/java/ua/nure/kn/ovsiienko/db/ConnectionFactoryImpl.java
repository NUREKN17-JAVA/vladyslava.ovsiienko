package ua.nure.kn.ovsiienko.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {
	
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



   
    
    public ConnectionFactoryImpl(){
    	//this.driver = driver;
    	//this.url = url;
    	//this.user = user;
    	//this.password = password;
    }





	

        
        

}
