package ua.nure.kn.ovsiienko.db;

import java.io.IOException;
import java.util.Properties;

import ua.nure.kn.ovsiienko.domain.User;

public abstract class DaoFactory {

	protected static final String USER_DAO = "dao.ua.nure.kn.ovsiienko.db.DAO";
	private static final String DAO_FACTORY = "dao.factory";
	protected static Properties properties;
	
	private static DaoFactory instance;
	
	static{
		properties = new Properties();
		try {
			properties.load(DaoFactory.class.getClassLoader()
					.getResourceAsStream("setting.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);}
	}
	
	public static synchronized DaoFactory getInstance(){
		if (instance == null){
			try{
				Class factoryClass = Class.forName(properties
						.getProperty(DAO_FACTORY));
				instance = (DaoFactory) factoryClass.newInstance();
			} catch(Exception e){
				throw new RuntimeException(e);
			}
		}
		return instance;
	}
	
	protected DaoFactory(){
		}
	
	
	protected ConnectionFactory getConnectionFactory(){
		return new ConnectionFactoryImpl(properties);
	}
	
	public abstract DAO<User> getUserDao();
	
	public static void init(Properties prop){
		properties = prop;
		instance = null;
	}
}
