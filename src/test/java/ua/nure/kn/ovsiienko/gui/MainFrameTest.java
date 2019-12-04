package ua.nure.kn.ovsiienko.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mockobjects.dynamic.Mock;

import ua.nure.kn.ovsiienko.db.DaoFactory;
import ua.nure.kn.ovsiienko.db.DaoFactoryImpl;
import ua.nure.kn.ovsiienko.db.MockDaoFactory;
import ua.nure.kn.ovsiienko.db.MockUserDao;
import ua.nure.kn.ovsiienko.domain.User;
import ua.nure.kn.ovsiienko.util.Messages;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.JTableMouseEventData;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

public class MainFrameTest extends JFCTestCase {
	
	private static final int ID_TEST = 1003;
	private static final String LAST_NAME_TEST = "Bush";
	private static final String FIRST_NAME_TEST = "George";
	private ArrayList<User> users;
	private MainFrame mainFrame;
	private Mock mockUserDao;
	private Date now = new Date();
	
	protected void setUp() throws Exception {
		super.setUp();
		try{
		Properties properties = new Properties();
			
		properties.setProperty("dao.factory",MockDaoFactory.class.getName());
		DaoFactory.getInstance().init(properties);

		mockUserDao = ((MockDaoFactory)DaoFactory.getInstance()).getMockUserDao();	
		
		
		User expectedUser = new User(new Long(ID_TEST), FIRST_NAME_TEST, LAST_NAME_TEST, now);
        users = new ArrayList<User>();
        users.add(expectedUser);
        
        mockUserDao.expectAndReturn("findAll",users);
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
		}catch(Exception e){
			e.printStackTrace();
		}
		mainFrame.setVisible(true);
	}

	protected void tearDown() throws Exception {
		try {
					mockUserDao.verify();
		mainFrame.setVisible(false);
		getHelper().cleanUp(this);
		super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Component find(Class<?> componentClass,String name){
		NamedComponentFinder finder;
		finder = new NamedComponentFinder(componentClass, name);
		finder.setWait(0);
		Component component = finder.find(mainFrame,0);
		assertNotNull("Could not find component '" + name + "'",component);
		return component;
	}
	
	public void testBrowseControls(){
		find(JPanel.class,"browsePanel");
		JTable table = (JTable) find(JTable.class,"userTable");
		assertEquals(3,table.getColumnCount());
		assertEquals(Messages.getString("UserTableModel.id"),table.getColumnName(0));
		assertEquals(Messages.getString("UserTableModel.first_name"),table.getColumnName(1));
		assertEquals(Messages.getString("UserTableModel.last_name"),table.getColumnName(2));
		find(JButton.class,"addButton");
		find(JButton.class,"editButton");
		find(JButton.class,"deleteButton");
		find(JButton.class,"detailsButton");
	}
	public void testAddUser(){
        try {
            String firstName = FIRST_NAME_TEST;
            String lastName = LAST_NAME_TEST;

            User user = new User(FIRST_NAME_TEST, LAST_NAME_TEST, now);

            User expectedUser = new User(new Long(1003), FIRST_NAME_TEST, LAST_NAME_TEST, now);
            mockUserDao.expectAndReturn("create", user, expectedUser);
            System.out.println("1a" + expectedUser + user);

            
            ArrayList<User> users = new ArrayList<User>(this.users);
            users.add(expectedUser);
            mockUserDao.expectAndReturn("findAll", users);
            
            JTable table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());

            JButton addButton = (JButton) find(JButton.class, "addButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

            find(JPanel.class, "addPanel");

            fillField(firstName, lastName, now);

            JButton okButton = (JButton) find(JButton.class, "okButton");
            getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

            find(JPanel.class, "browsePanel");
            table = (JTable) find(JTable.class, "userTable");
            assertEquals(2, table.getRowCount());
        } catch (Exception e) {
            fail(e.toString());
        }
		
	}
	public void testEditPanel(){
		Date now = new Date();
		try { 
			
        find(JPanel.class, "browsePanel");

        User expectedUser = new User(new Long(1003), FIRST_NAME_TEST, LAST_NAME_TEST, now);
        mockUserDao.expect("update", expectedUser);
        List<User> users = new ArrayList<User>(this.users);
        users.add(expectedUser);

        mockUserDao.expectAndReturn("findAll", users);

        JTable table = (JTable) find(JTable.class, "userTable");
        assertEquals(1, table.getRowCount());
        JButton editButton = (JButton) find(JButton.class, "editButton");
        getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
        getHelper().enterClickAndLeave(new MouseEventData(this, editButton));
        
        find(JPanel.class, "editPanel");

        JTextField firstNameField = (JTextField) find(JTextField.class,
                "firstNameField");
        JTextField lastNameField = (JTextField) find(JTextField.class,
                "lastNameField");
        
        getHelper().sendString(
                new StringEventData(this, firstNameField, "1"));
        getHelper().sendString(
                new StringEventData(this, lastNameField, "1"));

        JButton okButton = (JButton) find(JButton.class, "okButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
        

        find(JPanel.class, "browsePanel");
        table = (JTable) find(JTable.class, "userTable");
        assertEquals(2, table.getRowCount());
    	} catch (Exception e) {
        fail(e.toString());
    }
	}
	
    private void fillField(String firstName, String lastName, Date now) {
        JTextField firstNameField = (JTextField) find(JTextField.class,
                "firstNameField");
        JTextField lastNameField = (JTextField) find(JTextField.class,
                "lastNameField");
        JTextField dateOfBirthField = (JTextField) find(JTextField.class,
                "dateOfBirthField");

        getHelper().sendString(
                new StringEventData(this, firstNameField, firstName));
        getHelper().sendString(
                new StringEventData(this, lastNameField, lastName));
        DateFormat formatter = DateFormat.getDateInstance();
        String date = formatter.format(now);
        getHelper().sendString(
                new StringEventData(this, dateOfBirthField, date));
    }
	
    
}
