package ua.nure.kn.ovsiienko.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

public class MainFrameTest extends JFCTestCase {
	
	private static final String TEST_LAST_NAME = "Doe";
	private static final String TEST_FIRST_NAME = "John";
	private MainFrame mainFrame;
	private Mock mockUserDao;
	protected void setUp() throws Exception {
		super.setUp();
		try{
		Properties properties = new Properties();
			
		properties.setProperty("dao.factory",MockDaoFactory.class.getName());
		DaoFactory.getInstance().init(properties);
		mockUserDao = ((MockDaoFactory)DaoFactory.getInstance()).getMockUserDao();	
		mockUserDao.expectAndReturn("findAll",new ArrayList());
		
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

	private Component find(Class componentClass,String name){
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
		Date now = new Date();
		
		User user = new User(TEST_FIRST_NAME,TEST_LAST_NAME,now);
		
		User expectedUser = new User(new Long(1),TEST_FIRST_NAME,TEST_LAST_NAME,now);
		mockUserDao.expectAndReturn("create",user,expectedUser);
		
		ArrayList users = new ArrayList();
		users.add(expectedUser);
		mockUserDao.expectAndReturn("findAll", users);
		
		
		JTable table = (JTable) find(JTable.class,"userTable");
		assertEquals(0,table.getRowCount());
		
		
		JButton addButton = (JButton) find(JButton.class,"addButton");
		getHelper().enterClickAndLeave(new MouseEventData(this,addButton));
		
		find(JPanel.class,"addPanel");
		
		JTextField firstNameField = (JTextField) find(JTextField.class,"firstNameField");
		JTextField lastNameField = (JTextField) find(JTextField.class,"lastNameField");
		JTextField dateOfBirthField = (JTextField) find(JTextField.class,"dateOfBirthField");
		
		JButton okButton = (JButton)find(JButton.class,"okButton");
		find(JButton.class,"cancelButton");
		
		getHelper().sendString(new StringEventData(this,firstNameField,TEST_FIRST_NAME));
		getHelper().sendString(new StringEventData(this,lastNameField,TEST_LAST_NAME));
		DateFormat formater = DateFormat.getDateInstance();
		
		String date = formater.format(now);
		getHelper().sendString(new StringEventData(this,dateOfBirthField,date));
	
		getHelper().enterClickAndLeave(new MouseEventData(this,okButton));
		
		find(JPanel.class, "browsePanel");
		table = (JTable) find(JTable.class,"userTable");
		
	}
	
}
