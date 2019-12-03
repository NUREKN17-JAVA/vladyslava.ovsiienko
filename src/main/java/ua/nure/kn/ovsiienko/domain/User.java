package ua.nure.kn.ovsiienko.domain;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8639206768998662271L;

	private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private Date dateOfBirth;

	public User(String firstName, String lastName, Date date) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = date;
	}

	public User(Long id, String FirstName, String LastName, Date date) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = date;
	}

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateofBirth) {
		this.dateOfBirth = dateofBirth;
	}
	public String getFullName() {
		if (getLastName()== "" || getLastName()== " " || getLastName()== null){
			return getFirstName();
		} else if(getFirstName()== "" || getFirstName()== " " || getFirstName()== null){
			return getLastName();
		} else {
		return getLastName() + ", " + getFirstName(); 
		}
	}
/** return age in years for people who are older than 1 year**/
	public long getAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int currentYear = calendar.get(Calendar.YEAR);
		int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
		int currentMonth = calendar.get(Calendar.MONTH);
		calendar.setTime(getDateOfBirth());
		int birthYear = calendar.get(Calendar.YEAR);
		int birthDay = calendar.get(Calendar.DAY_OF_MONTH);
		int birthMonth = calendar.get(Calendar.MONTH);
		int age = currentYear - birthYear;
		if ((currentMonth == birthMonth && currentDay < birthDay) || currentMonth <  birthMonth )
			age --;
		return age ;
	}
    
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if(this == obj){
			return true;
		}
		if(this.getId() == null && ((User)obj).getId()==null){
			return true;
		}
		return this.getId().equals(((User)obj).getId());
	}
	
	
	public int hashCode() {
		if(this.getId() == null){
			return 0;
			}
		return this.getId().hashCode();
	}
    
}
