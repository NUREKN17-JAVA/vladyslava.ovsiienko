package ua.nure.kn.ovsiienko.web;

import ua.nure.kn.ovsiienko.domain.User;

public class DetailsServletTest extends MockServletTestCase {

    private static final String CANCEL_BUTTON = "cancel";

	public void setUp() throws Exception {
        super.setUp();
        createServlet(DetailsServlet.class);
    }

    public void testDetails() {
        addRequestParameter("cancelButton", CANCEL_BUTTON);
        User user = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNull("User is in session", user);
    }

}
