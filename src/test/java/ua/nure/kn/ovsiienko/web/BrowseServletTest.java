package ua.nure.kn.ovsiienko.web;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ua.nure.kn.ovsiienko.web.BrowseServlet;
import ua.nure.kn.ovsiienko.domain.User;

public class BrowseServletTest extends MockServletTestCase {

	private static final String DELETE_BUTTON = "Delete";
	private static final String EDIT_BUTTON = "Edit";
	private static final String ID_TEST = "1000";
	private static final String LAST_NAME_TEST = "Jovi";
	private static final String FIRST_NAME_TEST = "Bon";
	public void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
	}
	
	public void testBrowse() {
		User user = new User(new Long(1000),FIRST_NAME_TEST,LAST_NAME_TEST,new Date());
		List<User> list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("findAll", list);
		doGet();
		@SuppressWarnings("unchecked")
		Collection<User> collection = (Collection<User>)getWebMockObjectFactory().getMockSession().getAttribute("users");
		assertNotNull("Could not find list of users in session",collection);
		assertSame(list,collection);

	}
	
	public void testEdit(){
		User user = new User(new Long(1000),FIRST_NAME_TEST,LAST_NAME_TEST,new Date());
		getMockUserDao().expectAndReturn("find", new Long(1000),user);
		addRequestParameter("editButton",EDIT_BUTTON);
		addRequestParameter("id",ID_TEST);
		doPost();
		User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
		assertNotNull("Could not find user in session,sorry",user);
		assertSame(user,userInSession);
	}
    public void testDelete() {
        User user = new User(new Long(1000),FIRST_NAME_TEST,LAST_NAME_TEST, new Date());
        getMockUserDao().expectAndReturn("find", new Long(1000), user);
        getMockUserDao().expect("delete", user);
        addRequestParameter("deleteButton", DELETE_BUTTON);
        addRequestParameter("id", ID_TEST);
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("message"));
    }
    

}

