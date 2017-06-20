import java.awt.EventQueue;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args)
	{	
		DataBase sqlite = new DataBase();
		TextFileSystem text = new TextFileSystem();
		sqlite.createDatabase();
		text.create();
		ArrayList<Patient> patients = sqlite.getStoredPatients();//returns an ArrayList of patient objects from the databases table
		
		DoubleLinkedList list = new DoubleLinkedList();
		SelectionSort sort = new SelectionSort();
		
		for(Patient patient : patients)
		{
			list.addFront(patient);
		}
		sort.selectionSortPriority(list);
		OutpatientGUI frame = new OutpatientGUI(list,sqlite,text);		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}