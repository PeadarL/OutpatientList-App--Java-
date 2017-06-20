import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBase 
{
	Connection c= null;
	Statement stmt=null;

	public void createDatabase()
	{
		// Connect to Database
				try
				{
					open();					
					stmt = c.createStatement();
					String createTable = "CREATE TABLE if not exists Patients " +  // Whether the table exists or not is caught in the SQL statement
							"( id INTEGER PRIMARY KEY AUTOINCREMENT," + 
							"firstname CHAR(80) DEFAULT NULL," +
							"lastname CHAR(80) DEFAULT NULL," +
							"age INT DEFAULT NULL," +
							"mobile CHAR(80) DEFAULT NULL," +
							"priority INT DEFAULT NULL," +
							"summary TEXT DEFAULT NULL" +
							")";
					stmt.executeUpdate(createTable);
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}	
	}

	public ArrayList<Patient> getStoredPatients()
	{
		String getAll = "SELECT firstname, lastname, age, mobile, priority, summary FROM Patients";
		ArrayList<Patient> patients=new ArrayList<Patient>();
		try(
		ResultSet rs = stmt.executeQuery(getAll)){
			while(rs.next())
			{
				Patient temp;
				System.out.println(
						rs.getString("firstname")+ " "+
						rs.getString("lastname")+ " "+
						rs.getInt("age")+ " "+
						rs.getString("mobile")+ " "+
						rs.getInt("priority"));
				String firstname = rs.getString("firstname");
				String lastname =rs.getString("lastname");
				int age = rs.getInt("age");
				String mobile = rs.getString("mobile");
				int priority = rs.getInt("priority");
				String summary = rs.getString("summary");
				temp = new Patient(firstname, lastname, age, mobile, priority,summary);
				patients.add(temp);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return patients;
	}	
	public String getPatientSummary(Patient data)
	{
		String getSummary = "SELECT firstname, summary FROM Patients";//+ data.getFirstname();// SELECT * FROM table1 WHERE user_id = (SELECT user_id FROM table1 WHERE id = 3)
		String summary="";
		try(
		ResultSet rs = stmt.executeQuery(getSummary)){
			while(rs.next())
			{
				String firstname = rs.getString("firstname");
				String patientName =data.getFirstname();
				if(firstname.equals(patientName))
				{
				summary = rs.getString("summary");	
				System.out.println(summary);
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return summary;
	}
	
	public void insertIntoDatabase(Patient data)// Use Generic?
	{
		String insertPatient= "INSERT INTO Patients (firstname, lastname, age, mobile, priority, summary) "+ data.sqlInsert(); // all to insert patient
		try {
			stmt.executeUpdate(insertPatient);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public void removeFromDatabase(Patient data)// Use Generic?
	{
		String removePatient =  data.sqlRemove(); // all to insert patient
		try {
			stmt.executeUpdate(removePatient);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public void close()
	{
		try {
			c.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public void open()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("JDBC:sqlite:EmergencyPatients.sqlite");
			System.out.println("Connected!");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void dropTable() 
	{
		String dropTable = "DROP TABLE Patients ";
		try 
		{
			stmt.executeUpdate(dropTable);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		createDatabase();
	}
}
