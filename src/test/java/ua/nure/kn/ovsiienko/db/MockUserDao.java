package ua.nure.kn.ovsiienko.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ua.nure.kn.ovsiienko.domain.User;

public class MockUserDao implements DAO<User> {
	private long id = 0;
	private Map users = new HashMap();

	public User create(User user) throws DatabaseException {
		Long currentId = new Long(++id);
		user.setId(currentId);
		users.put(currentId, user);
		return user;
	}

	public void update(User user) throws DatabaseException {
		Long currentId = user.getId();
		users.remove(currentId);
		users.put(currentId, user);
		
	}

	
	public void delete(User user) throws DatabaseException {
		Long currentId = new Long(++id);
		users.remove(currentId);
		
	}

	
	public User find(Long id) throws DatabaseException {
		
		return (User) users.get(id);
	}

	
	public Collection findAll() throws DatabaseException {
		return users.values();
	}

	
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub
		
	}

}
