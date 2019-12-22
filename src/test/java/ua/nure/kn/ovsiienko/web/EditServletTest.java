package ua.nure.kn.ovsiienko.web;

import java.text.DateFormat;
import java.util.Date;

import ua.nure.kn.ovsiienko.domain.User;

public class EditServletTest extends MockServletTestCase {

	private static final String WRONG_DATE_TEST = "oops=)";
	private static final String ID_TEST = "1000";
	private static final String OK_BUTTON = "Ok";
	private static final String LAST_NAME_TEST = "Jovi";
	private static final String FIRST_NAME_TEST = "Bon";
	public void setUp() throws Exception {
		super.setUp();
		createServlet(EditServlet.class);
	}
public void testEdit(){
	Date date = new Date();
	User user = new User(new Long(1000),FIRST_NAME_TEST,LAST_NAME_TEST,date);
	getMockUserDao().expect("update",user);
	
	addRequestParameter("id",ID_TEST);
	addRequestParameter("firstName",FIRST_NAME_TEST);
	addRequestParameter("lastName",LAST_NAME_TEST);
	addRequestParameter("date",DateFormat.getDateInstance().format(date));
	addRequestParameter("okButton",OK_BUTTON);
	doPost();
}

public void testEditEmptyFirstName(){
	Date date = new Date();
	addRequestParameter("id",ID_TEST);
	addRequestParameter("lastName",LAST_NAME_TEST);
	addRequestParameter("date",DateFormat.getDateInstance().format(date));
	addRequestParameter("okButton",OK_BUTTON);
	doPost();
	String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
	assertNotNull("Could not find error message in session scope",errorMessage);
}
public void testEditEmptyLastName(){
	Date date = new Date();
	addRequestParameter("id",ID_TEST);
	addRequestParameter("firstName",FIRST_NAME_TEST);
	addRequestParameter("date",DateFormat.getDateInstance().format(date));
	addRequestParameter("okButton",OK_BUTTON);
	doPost();
	String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
	assertNotNull("Could not find error message in session scope",errorMessage);
}
public void testEditEmptyDateOfBirth(){
	Date date = new Date();
	addRequestParameter("id",ID_TEST);
	addRequestParameter("firstName",FIRST_NAME_TEST);
	addRequestParameter("lastName",LAST_NAME_TEST);
	addRequestParameter("okButton",OK_BUTTON);
	doPost();
	String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
	assertNotNull("Could not find error message in session scope",errorMessage);
}
public void testEditEmptyDateIncorrect(){
	Date date = new Date();
	addRequestParameter("id",ID_TEST);
	addRequestParameter("firstName",FIRST_NAME_TEST);
	addRequestParameter("lastName",LAST_NAME_TEST);
	addRequestParameter("date",WRONG_DATE_TEST);
	addRequestParameter("okButton",OK_BUTTON);
	doPost();
	String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
	assertNotNull("Could not find error message in session scope",errorMessage);
}
}
