package ua.nure.kn.ovsiienko.db;

import ua.nure.kn.ovsiienko.domain.User;
import junit.framework.Assert;
import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetUserDao() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        Assert.assertNotNull(daoFactory);
        DAO<User> userDao;
        try {
            userDao = daoFactory.getUserDao();
            Assert.assertNotNull(userDao);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }

}
