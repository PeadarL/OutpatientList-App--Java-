import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class TextFileSystem
{
	File file = new File("TextFileDataBase.csv");

	public void create() 
	{
			if(!file.exists()) // if doesn't exist, create a new file.
			{
				try 
				{
					file.createNewFile();
					PrintWriter pw = new PrintWriter(file);
					pw.append("Firstname ,Lastname, Age, PhoneNumber, Summary, Date \n" );
					System.out.println("Created Text File!");
					pw.close();

				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("File Already Exists.");
			}

	}
	public void writeData(Patient data) // Dependency inversion
	{
		try 
		{
			PrintWriter pw = new PrintWriter(new FileWriter(file, true));
			pw.append(data.getTextDetails());
			System.out.println("Printed to Text File!");
			pw.close(); // close file at end of loop
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}