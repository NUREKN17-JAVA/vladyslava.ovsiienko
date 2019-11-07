package ua.nure.kn.ovsiienko.db;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {

	private static final String USER_DAO = "dao.ua.nure.kn.ovsiienko.db.DAO";
	private final Properties properties;
	
	private final static DaoFactory INSTANCE = new DaoFactory();// ��� 3 �� ������ final
	public static DaoFactory getInstance(){
		return INSTANCE;
	}
	
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
			Class clazz = Class.forName(properties.getProperty(USER_DAO));
			result = (DAO) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
