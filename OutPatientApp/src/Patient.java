import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {
	public String firstname;
	public String lastname;
	public int age;
	public String mobile;
	public int priority;
	public String summary;
	public DateFormat dateFormat = new SimpleDateFormat("dd/MM/YY");
	public Date date = new Date();
	

	public Patient(String firstname, String lastname, int age, String mobile, int priority, String summary){
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.mobile = mobile;
		this.priority = priority;
		this.summary = summary;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String getsummary() {
		return summary;
	}

	@Override
	public String toString() {
		return firstname +" "+lastname  +" "+ age  +" "+ mobile
				 +" "+ priority;
	}
	
	
	public String sqlInsert()
	{
		return  
				"VALUES ("+ 				// properties formatted as an SQL statement.
				"'" + this.firstname + "',"+
				"'" + this.lastname + "'," + 
				"'" + this.age + "'," + 
				"'" + this.mobile + "'," + 
				this.priority + ","+
				"'" + this.summary + "'"+
				");" 
				; 

	} 	

	public String sqlRemove()
	{
		return "DELETE FROM	Patients WHERE firstname=" + "'"+this.firstname+"'"+ ";" ;
	}

	public String getTextDetails()
	{
		return  this.firstname + "," + this.lastname + 
				"," + this.age + "," + this.mobile + "," 
				+ this.summary + "," +dateFormat.format(date) +"\n";
	} 
}
