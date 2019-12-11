package ua.nure.kn.ovsiienko.web;

import java.util.Properties;

import ua.nure.kn.ovsiienko.db.DaoFactory;
import ua.nure.kn.ovsiienko.db.MockDaoFactory;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

public class MockServletTestCase extends BasicServletTestCaseAdapter {

	private Mock mockUserDao;
	
	public void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;
	}
	public Mock getMockUserDao() {
		return mockUserDao;
	}

	public void tearDown() throws Exception{
		getMockUserDao().verify();
		super.tearDown();
	}
	
	public void setUp() throws Exception{
		super.setUp();
		Properties properties = new Properties();
		properties.setProperty("dao.factory", MockDaoFactory.class.getName());
		DaoFactory.init(properties);
		setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
	}
}
