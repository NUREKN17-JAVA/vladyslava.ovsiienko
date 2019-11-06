package ua.nure.kn.ovsiienko.db;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Collection;

import junit.framework.TestCase;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import ua.nure.kn.ovsiienko.domain.User;
import ua.nure.kn.ovsiienko.db.HsqldbUserDao;

public class HsqldbUserDaoTest extends DatabaseTestCase {//DatabaseTestCase
	
    private static final String LAST_NAME = "Gates";
	private static final String FIRST_NAME = "Bill";
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	private static final int DAY_OF_BIRTH = 1;
	private static final int MONTH = 1;
	private static final int YEAR = 2010;

	public void testCreate() throws DatabaseException{
		try {
		User user = new User();
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR,MONTH,DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		
		assertNull(user.getId());//проверить что создало id usera
		
		User userToCheck = dao.create(user);
		assertNotNull(userToCheck);
		assertNotNull(userToCheck.getId());
		
		assertEquals(FIRST_NAME,userToCheck.getFirstName());
		assertEquals(LAST_NAME,userToCheck.getLastName());
		assertEquals(calendar.getTime(),userToCheck.getDateOfBirth());
		} catch (DatabaseException e ){
			e.printStackTrace();
			fail(e.toString());
		}

	}
	
	public void testFindAll() throws DatabaseException{
		try{
		Collection<User> items = dao.findAll();
		assertNotNull("Collection is null",items);
		assertEquals("Collection size.",2, items.size());
		} catch (DatabaseException e){
			e.printStackTrace();
			fail(e.toString());
		}
		
	}
	@Override
	protected void setUp() throws Exception{
		super.setUp();

		dao = new HsqldbUserDao(connectionFactory);//empty in ()
	}
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl();
		return new DatabaseConnection(connectionFactory.createConnection());
	}
	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}
