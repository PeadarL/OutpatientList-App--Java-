import java.util.ArrayList;
// Tight Coupling to Database? Put query string here and return objects?
public class Data {
	ArrayList<Patient> patients = new ArrayList<Patient>();
	DataBase sqlite;
	
	public Data(DataBase sqlite)
	{
		this.sqlite=sqlite;
	}
	
	public ArrayList<Patient> getPatients()
	{
		
		
		return patients;
	}
	
	public ArrayList<Patient> getTestPatients()
	{
		Patient patient1 = new Patient("Hans", "Johanson", 18, "087777777", 1,"");
		patients.add(patient1);
		Patient patient2 = new Patient("Josef", "Langgreiffer", 24, "08123456", 6,"");
		patients.add(patient2);
		Patient patient3 = new Patient("Patte", "McCul", 88, "0872121212", 10,"");
		patients.add(patient3);
		Patient patient4 = new Patient("Schmidt", "Mundatmer", 44, "0823233434", 4,"");
		patients.add(patient4);
		Patient patient5 = new Patient("Bertholdt", "Handtaucher", 26, "08654321", 2,"");
		patients.add(patient5);
		return patients;
	}
}
