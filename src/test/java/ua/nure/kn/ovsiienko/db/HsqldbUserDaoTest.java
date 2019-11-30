package ua.nure.kn.ovsiienko.db;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import ua.nure.kn.ovsiienko.domain.User;
import ua.nure.kn.ovsiienko.db.HsqldbUserDao;

public class HsqldbUserDaoTest extends DatabaseTestCase {//DatabaseTestCase
	
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	private static final int DAY_OF_BIRTH = 1;
	private static final int MONTH = 1;
	private static final int YEAR = 2010;
	
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:hsqldb:file:db/usermanagment";
    private static final String DRIVER = "org.hsqldb.jdbcDriver";
    
    private User user;

    private static final String UPDATED_FIRST_NAME = "Vlada";
	private static final String FIRST_NAME = "Sasha";
    private static final String LAST_NAME = "Kasha";
 
    private static final Long ID = 4L;

	public void testCreate() throws DatabaseException{
		try {
		User user = new User();
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR,MONTH,DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		
		assertNull(user.getId());
		
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
	
    public void testFind() throws DatabaseException {
    	try{
        assertNotNull(user.getId());
        User ethalon = dao.create(user);
        User finded = dao.find(ethalon.getId());
        assertNotNull(finded);
        assertEquals(ethalon.getId(), finded.getId());
        assertEquals(ethalon.getFirstName(), finded.getFirstName());
        assertEquals(ethalon.getLastName(), finded.getLastName());
        }catch(DatabaseException e){
			e.printStackTrace();
			fail(e.toString());
        }
    	

    }
    
    public void testUpdate() throws DatabaseException {
        User test = user;
        dao.create(user);
        test.setFirstName(UPDATED_FIRST_NAME);
        dao.update(test);
        User updated = dao.find(test.getId());
        assertNotNull(updated);
        assertEquals(test.getFirstName(), updated.getFirstName());
    }

  

    public void testDelete() throws DatabaseException {
        User deleted = dao.create(user);
        dao.delete(deleted);
        assertNull(dao.find(deleted.getId()));
    }
	@Override
	protected void setUp() throws Exception{
		super.setUp();

        user = new User();
        user.setId(ID);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setDateOfBirth(new Date());

        dao = new HsqldbUserDao(connectionFactory);
	}
	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl(USER,PASSWORD,URL,DRIVER);
		return new DatabaseConnection(connectionFactory.createConnection());
	}
	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}
