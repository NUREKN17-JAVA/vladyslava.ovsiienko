package ua.nure.kn.ovsiienko.web;

import java.text.DateFormat;
import java.util.Date;

import ua.nure.kn.ovsiienko.domain.User;

public class AddServletTest extends MockServletTestCase {

	public void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
	}
	public void testAdd(){
		Date date = new Date();
		User newUser = new User("Bon","Jovi",date);
		User user = new User(new Long(1000),"Bon","Jovi",date);
		getMockUserDao().expectAndReturn("create",newUser,user);
		
		addRequestParameter("firstName","Bon");
		addRequestParameter("lastName","Jovi");
		addRequestParameter("date",DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton","Ok");
		doPost();
	}

	public void testAddEmptyFirstName(){
		Date date = new Date();
		addRequestParameter("lastName","Jovi");
		addRequestParameter("date",DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope",errorMessage);
	}
	public void testAddEmptyLastName(){
		Date date = new Date();
		addRequestParameter("firstName","Bon");
		addRequestParameter("date",DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope",errorMessage);
	}
	public void testAddEmptyDateOfBirth(){
		Date date = new Date();
		addRequestParameter("firstName","Bon");
		addRequestParameter("lastName","Jovi");
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope",errorMessage);
	}
	public void testAddEmptyDateIncorrect(){
		Date date = new Date();
		addRequestParameter("firstName","Bon");
		addRequestParameter("lastName","Jovi");
		addRequestParameter("date","oops=)");
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope",errorMessage);
	}
}
