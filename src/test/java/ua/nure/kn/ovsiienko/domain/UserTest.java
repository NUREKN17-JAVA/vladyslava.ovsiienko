package ua.nure.kn.ovsiienko.domain;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class UserTest extends TestCase {
	
	private static final String LAST_NAME = "Doe";
	private static final String FIRST_NAME = "John";
	private static final int YEAR_OF_BIRTH = 2000;
	private static final int CURRENT_YEAR = 2019;
	private static final int MONTH_OF_BIRTH= Calendar.MARCH;
	private static final int DAY_OF_BIRTH = 4;
	
	/** Test Case №1 birthday will be next month **/
	private static final int DAY_OF_BIRTH1 = 3;
	private static final int MONTH_OF_BIRTH1 = Calendar.NOVEMBER;
	private static final int ETALONE_AGE1 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
	
	/** Test Case №2 birthday is today **/
	private static final int DAY_OF_BIRTH2 = 8;
	private static final int MONTH_OF_BIRTH2 = Calendar.OCTOBER;
	private static final int ETALONE_AGE2 = CURRENT_YEAR - YEAR_OF_BIRTH ;
	
	/** Test Case №3 birthday was last month **/
	private static final int DAY_OF_BIRTH3 = 8;
	private static final int MONTH_OF_BIRTH3 = Calendar.SEPTEMBER;
	private static final int ETALONE_AGE3 = CURRENT_YEAR - YEAR_OF_BIRTH;
	
	/** Test Case №4 birthday is the last day of year **/
	private static final int DAY_OF_BIRTH4 = 31;
	private static final int MONTH_OF_BIRTH4 = Calendar.DECEMBER;
	private static final int ETALONE_AGE4 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
	
	/** Test Case №5 birthday is the first day of year **/
	private static final int DAY_OF_BIRTH5 = 1;
	private static final int MONTH_OF_BIRTH5 = Calendar.JANUARY;
	private static final int ETALONE_AGE5 = CURRENT_YEAR - YEAR_OF_BIRTH;
	
	/** Test Case №6 birthday is the same day but next month **/
	private static final int DAY_OF_BIRTH6 = 8;
	private static final int MONTH_OF_BIRTH6 = Calendar.NOVEMBER;
	private static final int ETALONE_AGE6 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
	
	/** Test Case №7 birthday is the same day but last month **/
	private static final int DAY_OF_BIRTH7 = 8;
	private static final int MONTH_OF_BIRTH7 = Calendar.SEPTEMBER;
	private static final int ETALONE_AGE7 = CURRENT_YEAR - YEAR_OF_BIRTH;
	
	/** Test Case №8 birthday was yesterday **/
	private static final int DAY_OF_BIRTH8 = 7;
	private static final int MONTH_OF_BIRTH8 = Calendar.OCTOBER;
	private static final int ETALONE_AGE8 = CURRENT_YEAR - YEAR_OF_BIRTH ;
	
	/** Test Case №9 birthday is tomorrow **/
	private static final int DAY_OF_BIRTH9 = 9;
	private static final int MONTH_OF_BIRTH9 = Calendar.OCTOBER;
	private static final int ETALONE_AGE9 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
	
	private User user;
	private Date dateOfBirth;
	
	/**Test Case №1 **/
	public void testGetAge1(){
		Calendar calendar =Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH1,DAY_OF_BIRTH1);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE1,user.getAge());
	}

	/**Test Case №2**/
	public void testGetAge2(){
		Calendar calendar =Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH2,DAY_OF_BIRTH2);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE2,user.getAge());
	}
	
	/**Test Case №3**/
	public void testGetAge3(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH3,DAY_OF_BIRTH3);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE3,user.getAge());
	}
	
	/**Test Case №4**/
	public void testGetAge4(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH4,DAY_OF_BIRTH4);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE4,user.getAge());
	}
	
	/**Test Case №5**/
	public void testGetAge5(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH5,DAY_OF_BIRTH5);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE5,user.getAge());
	}
	
	/**Test Case №6**/
	public void testGetAge6(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH6,DAY_OF_BIRTH6);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE6,user.getAge());
	}
	
	/**Test Case №7**/
	public void testGetAge7(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH7,DAY_OF_BIRTH7);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE7,user.getAge());
	}
	
	/**Test Case №8**/
	public void testGetAge8(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH8,DAY_OF_BIRTH8);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE8,user.getAge());
	}
	
	/**Test Case №9**/
	public void testGetAge9(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH9,DAY_OF_BIRTH9);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE9,user.getAge());
	}
	/** Test Case №10 Full name has both attributes **/
	public void testGetFullName1(){
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		assertEquals("Doe, John",user.getFullName());
	}
	
	/** Test Case №11 Full name doesn't have firstName **/
	public void testGetFullName2(){
		user.setLastName(LAST_NAME);
		assertEquals(LAST_NAME,user.getFullName());
	}
	
	/** Test Case №12 Full name doesn't have lastName **/
	public void testGetFullName3(){
		user.setFirstName(FIRST_NAME);
		assertEquals(FIRST_NAME,user.getFullName());
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		dateOfBirth = calendar.getTime();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
