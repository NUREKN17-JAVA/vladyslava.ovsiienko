package ua.nure.kn.ovsiienko.db;

import java.util.Calendar;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;

import ua.nure.kn.ovsiienko.domain.User;
import ua.nure.kn.ovsiienko.db.HsqldbUserDao;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	
    private static final String LAST_NAME = "Gates";
	private static final String FIRST_NAME = "Bill";
	private HsqldbUserDao dao;
	private static final int DAY_OF_BIRTH = 1;
	private static final int MONTH = 1;
	private static final int YEAR = 2010;

	public void testCreate() throws DatabaseException{
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
	
	}
	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
