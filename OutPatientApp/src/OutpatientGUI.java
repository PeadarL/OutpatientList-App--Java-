import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class OutpatientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstNameTF;
	private JTextField lastNameTF;
	private JTextField ageTF;
	private JTextField phoneTF;
	private JTextField priorityTF;
	private JTable table;
	private JTextArea summaryArea;


	private SelectionSort sort;
	
	DefaultTableModel model;
	private DoubleLinkedList list;
	private DataBase sqlite;
	private TextFileSystem text;

	public OutpatientGUI(DoubleLinkedList list, DataBase sqlite, TextFileSystem text) 
	{
		this.list = list;
		this.sqlite=sqlite;
		this.text = text;
		createView();
	}


	public void createView()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblOutpatientEr = new JLabel("OutPatient ER");
		lblOutpatientEr.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutpatientEr.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblOutpatientEr.setBounds(162, -5, 102, 59);
		contentPane.add(lblOutpatientEr);
		
		JLabel lblSummary = new JLabel("Summary");
		lblSummary.setBounds(183, 98, 70, 14);
		contentPane.add(lblSummary);
		
		summaryArea = new JTextArea();
		summaryArea.setBounds(259, 99, 153, 72);
		summaryArea.setLineWrap(true);
		contentPane.add(summaryArea);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 68, 67, 14);
		contentPane.add(lblFirstName);
		
		firstNameTF = new JTextField();
		firstNameTF.setBounds(87, 65, 86, 20);
		contentPane.add(firstNameTF);
		firstNameTF.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 98, 67, 14);
		contentPane.add(lblLastName);
		
		lastNameTF = new JTextField();
		lastNameTF.setBounds(87, 95, 86, 20);
		contentPane.add(lastNameTF);
		lastNameTF.setColumns(10);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(10, 126, 46, 14);
		contentPane.add(lblAge);
		
		ageTF = new JTextField();
		ageTF.setBounds(87, 123, 86, 20);
		contentPane.add(ageTF);
		ageTF.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(10, 154, 46, 14);
		contentPane.add(lblPhone);
		
		phoneTF = new JTextField();
		phoneTF.setBounds(87, 151, 86, 20);
		contentPane.add(phoneTF);
		phoneTF.setColumns(10);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setBounds(183, 68, 46, 14);
		contentPane.add(lblPriority);
		
		priorityTF = new JTextField();
		priorityTF.setBounds(249, 65, 86, 20);
		contentPane.add(priorityTF);
		priorityTF.setColumns(10);
		

		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(validatePatient())
				{
				Patient temp = new Patient(firstNameTF.getText(), lastNameTF.getText(), Integer.parseInt(ageTF.getText()),
						phoneTF.getText(),Integer.parseInt(priorityTF.getText()), summaryArea.getText());
				sqlite.insertIntoDatabase(temp);
				list.addFront(temp);
				loadDataToTable();
				clearTextFields();
				}
			}
		});
		btnSave.setBounds(326, 185, 86, 23);
		contentPane.add(btnSave);	
		
		JButton btnDeleteSelected = new JButton("Remove Selected");
		btnDeleteSelected.addActionListener(new ActionListener()
		{
			  @Override
			    public void actionPerformed(ActionEvent arg0) 
			  {
				  int selected = table.convertRowIndexToModel(table.getSelectedRow());
			        if (selected != -1) 
			        {
			            String firstname = (String) table.getValueAt(selected, 0);
			            String lastname = (String) table.getValueAt(selected, 1);
			            int age = (int) table.getValueAt(selected, 2);
			            String mobile= (String) table.getValueAt(selected, 3);
			            int priority = (int) table.getValueAt(selected, 4);
			            String summary= summaryArea.getText();
			            Patient temp = new Patient(firstname,lastname,age,mobile,priority,summary);
			            if(summary.equals(""))
			            {
			            	summaryArea.setText("Please Fill In Patient Treatment Summary!");
			            }
			            else
			            {
			            	text.writeData(temp);
				            model.removeRow(selected);
				            System.out.println(firstname);
							sqlite.removeFromDatabase(temp); 	
							clearTextFields();
			            }
				     }
			    }		  
		});
		btnDeleteSelected.setBounds(282, 268, 142, 23);
		contentPane.add(btnDeleteSelected);
		

		
		JButton btnSelectedSummary = new JButton("Get Selected Summary");
		btnSelectedSummary.addActionListener(new ActionListener()
		{
			  @Override
			    public void actionPerformed(ActionEvent arg0) {
				  int selected = table.convertRowIndexToModel(table.getSelectedRow());
			        if (selected != -1) {
			            String firstname = (String) table.getValueAt(selected, 0);
			            String lastname = (String) table.getValueAt(selected, 1);
						Patient temp = new Patient(firstname,lastname,0,"",0,"");
						clearTextFields();
						String summary =sqlite.getPatientSummary(temp);
						summaryArea.setText(firstname + " "+ lastname + "-"+ summary);
			        }
			    }
		});
		btnSelectedSummary.setBounds(10, 268, 200, 23);
		contentPane.add(btnSelectedSummary);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 302, 414, 118);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"First Name", "Second Name", "Age", "Phone", "Priority"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		

		
		model = (DefaultTableModel) table.getModel();
		loadDataToTable();
	}
	
	
	public void loadDataToTable()
	{
		sort = new SelectionSort();
		sort.selectionSortPriority(list);
		Node head=list.getHead();
		model.setRowCount(0);
		for(int i=0; i  < list.getSize(); i++)
		{
		Patient data = (Patient) head.getData();
		model.addRow(new Object[]{data.getFirstname(),data.getLastname(), data.getAge(),
				data.mobile,data.getPriority()});
		head = head.getNext();
		}
	}

	private boolean validatePatient() {
		if(firstNameTF.getText().equals(""))
		{
			firstNameTF.setText("Empty Field!");
			return false;
		}
		if(lastNameTF.getText().equals(""))
		{
			lastNameTF.setText("Empty Field!");
			return false;
		}
		if(ageTF.getText().equals(""))
		{
			ageTF.setText("Empty Field!");
			return false;
		}
		if(phoneTF.getText().equals(""))
		{
			phoneTF.setText("Empty Field!");
			return false;
		}
		if(priorityTF.getText().equals(""))
		{
			priorityTF.setText("Empty Field!");
			return false;
		}
		if(summaryArea.getText().equals(""))
		{
			summaryArea.setText("Empty Field!");
			return false;
		}
		else
		{
		return true;
		}
	}
	private void clearTextFields()
	{
		firstNameTF.setText("");
		lastNameTF.setText("");
		ageTF.setText("");
		phoneTF.setText("");
		priorityTF.setText("");	
		summaryArea.setText("");
	}
}

