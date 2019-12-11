package ua.nure.kn.ovsiienko.db;

import com.mockobjects.dynamic.Mock;

import ua.nure.kn.ovsiienko.domain.User;

public class MockDaoFactory extends DaoFactory {
	
	private Mock mockUserDao;
	
	public MockDaoFactory(){
		mockUserDao = new Mock(DAO.class);
	}
	
	public Mock getMockUserDao(){
		return mockUserDao;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DAO<User> getUserDao() {
		return (DAO<User>) mockUserDao.proxy();
	}

}
