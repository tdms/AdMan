
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
	private JTextField textFieldAdvisorName;
	private JLabel labelAdvisorEmail;
	private JTextField textFieldAdvisorEmail;
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
		
		textFieldAdvisorName=new JTextField(20);
		add(textFieldAdvisorName);
		
		labelAdvisorEmail=new JLabel("Advisor's E-mail address: ");
		add(labelAdvisorEmail);
		
		textFieldAdvisorEmail=new JTextField(20);
		add(textFieldAdvisorEmail);
		
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
	
	public boolean verifyEmailAddress(String emailAddress)
	{
		
		
		int len = emailAddress.length();
		int i,j;
		int dotIndex, dotCount=0;
		int atIndex, atCount = 0;
		
		for(i = 0; i < len; i++)
		{
			if(emailAddress.charAt(i) == '.')
			{
				dotIndex = i;
				dotCount++;
			}
			
			if(emailAddress.charAt(i) == '@')
			{
				atIndex = i;
				atCount++;				
			}
		}
		
		if( dotCount >= 1  && atCount == 1)
			return true;
		else
			return false;
			
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
					String newAdvisorName = new String(textFieldAdvisorName.getText().trim());
					String newEmailAddress = new String(textFieldAdvisorEmail.getText().trim());
					
					boolean status = verifyEmailAddress(newEmailAddress);
					
					if (status == false)
						JOptionPane.showMessageDialog(null, "Invalid email address");
					else
					{
						output=new Formatter("info.txt");
						for(i=0;i<index;i++)
							output.format("%s %s\n",advisorName[i], advisorEmail[i]);
						output.format("%s %s\n",newAdvisorName , newEmailAddress);
					}
			}
			catch(Exception e)
			{
				
			}
			output.close();
		}
	}
}
