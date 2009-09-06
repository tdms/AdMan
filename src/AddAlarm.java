
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.*;
import java.awt.event.*;
import java.util.*;

public class AddAlarm extends JFrame{
	
	private FlowLayout layout1;
	private GridLayout layout2;
	
	private JLabel labelEmpty;
	private JLabel labelAdvisorName;
	private JTextArea textAreaAdvisorName;
	private JLabel labelAdvisorEmail;
	private JTextArea textAreaAdvisorEmail;
	private JButton buttonAdd, buttonSelectAlarm;
	
	private JLabel labelSelectAlarm;
	
	private AddButtonHandler addHandler;
	private BrowseButtonHandler browseHandler;
	private JFileChooser fileChooser;
	private URL alarmToneURL;
	
	private String advisorName[], advisorEmail[];
	private Scanner input;
	private Formatter output;
	private File file;
	
	public AddAlarm()
	{
		super("Add new alarm");
		
		layout1=new FlowLayout();
		layout1.setAlignment(FlowLayout.LEFT);
		setLayout(layout1);
		
		
		layout2=new GridLayout(3,3,2,2);
		//setLayout(layout2);
		
		labelEmpty=new JLabel("                                                                                                        ");
		add(labelEmpty);
		
		layout1.setAlignment(FlowLayout.CENTER);
		setLayout(layout1);
		
		labelAdvisorName=new JLabel("                  Advisor's Name:");
		add(labelAdvisorName);
		
		textAreaAdvisorName=new JTextArea(1, 20);
		add(textAreaAdvisorName);
		
		labelAdvisorEmail=new JLabel("Advisor's E-mail address: ");
		add(labelAdvisorEmail);
		
		textAreaAdvisorEmail=new JTextArea(1, 20);
		add(textAreaAdvisorEmail);
		
		labelSelectAlarm=new JLabel("Select Alarm Tone: ");
		add(labelSelectAlarm);
		
		buttonSelectAlarm=new JButton("Browse");
		add(buttonSelectAlarm);
		browseHandler=new BrowseButtonHandler();
		buttonSelectAlarm.addActionListener(browseHandler);
		
		add(labelEmpty);
		buttonAdd=new JButton("Add Alarm");
		add(buttonAdd);
		addHandler=new AddButtonHandler();
		buttonAdd.addActionListener(addHandler);
		
	}
	
	private class BrowseButtonHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent event)
		{
			int status;
			System.out.println("Browse Button");
			fileChooser=new JFileChooser();
			status=fileChooser.showOpenDialog(null);
			
			if(status==JFileChooser.APPROVE_OPTION)
			{
				alarmToneURL=null;
				try{
					alarmToneURL=fileChooser.getSelectedFile().toURL();
					System.out.println(alarmToneURL);
				}	
				catch(Exception e)
				{
			
				}
			}
		}
	}
	
	private class AddButtonHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent event)
		{
			int index=0, i;
			System.out.println("Add Button");
			file=new File("info.txt");
			
			try{
				
				input=new Scanner(file);
				
				advisorName=new String[100];
				advisorEmail=new String[100];
				
				while(input.hasNext())
				{
					System.out.println("add");
					advisorName[index]=input.next();
					System.out.println(advisorName[index]);
					
					advisorEmail[index]=input.next();
					System.out.println("email "+advisorEmail[index]);
									
					index++;
					System.out.println("index1: "+index);
				}
				input.close();
			}
			catch(Exception e)
			{
				try{
				file.createNewFile();
				}catch(Exception e1){}
			}
			
			try{
					output=new Formatter("info.txt");
					for(i=0;i<index;i++)
						output.format("%s %s\n",advisorName[i], advisorEmail[i]);
					output.format("%s %s\n",textAreaAdvisorName.getText().trim(), textAreaAdvisorEmail.getText().trim());
			}
			catch(Exception e)
			{
				
			}
			output.close();
		}
	}
}
