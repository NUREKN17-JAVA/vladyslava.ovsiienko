package ua.nure.kn.ovsiienko.web;

import java.util.Properties;
import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

	private Mock mockUserDao;
	
	public void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;
	}
	public Mock getMockUserDao() {
		return mockUserDao;
	}

	public void tearDown() throws Exception{
		mockUserDao.verify();
		super.tearDown();
	}
	
	public void setUp() throws Exception{
		super.setUp();
		Properties properties = new Properties();
		//дописать
	}
}
