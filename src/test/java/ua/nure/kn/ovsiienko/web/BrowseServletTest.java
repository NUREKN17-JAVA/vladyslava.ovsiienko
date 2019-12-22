package ua.nure.kn.ovsiienko.web;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ua.nure.kn.ovsiienko.web.BrowseServlet;
import ua.nure.kn.ovsiienko.domain.User;

public class BrowseServletTest extends MockServletTestCase {

	public void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
	}
	
	public void testBrowse() {
		User user = new User(new Long(1000),"John","Doe",new Date());
		List<User> list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("findAll", list);
		doGet();
		@SuppressWarnings("unchecked")
		Collection<User> collection = (Collection<User>)getWebMockObjectFactory().getMockSession().getAttribute("users");
		assertNotNull("Could not find list of users in session",collection);
		assertSame(list,collection);

	}
	
	public void testEdit(){
		User user = new User(new Long(1000),"John","Doe",new Date());
		getMockUserDao().expectAndReturn("find", new Long(1000),user);
		addRequestParameter("editButton","Edit");
		addRequestParameter("id","1000");
		doPost();
		User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
		assertNotNull("Could not find user in session,sorry",user);
		assertSame(user,userInSession);
	}
}

