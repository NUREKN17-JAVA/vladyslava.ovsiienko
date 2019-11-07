package ua.nure.kn.ovsiienko.db;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {

	private final Properties properties;
	
	public DaoFactory(){
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	
	}
	
	private ConnectionFactory getConnectionFactory(){
		String user = properties.getProperty("connection.user");
		String password = properties.getProperty("connection.password");
		String url = properties.getProperty("connection.url");
		String driver = properties.getProperty("connection.driver");
		return new ConnectionFactoryImpl(driver,url,user,password);
	}
	
	public DAO getUserDao(){
		DAO result = null;
		try {
			Class clazz = Class.forName(properties.getProperty("dao.ua.nure.kn.ovsiienko.db.DAO"));
			DAO userDao = (DAO) clazz.newInstance();
			userDao.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
