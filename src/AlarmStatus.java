
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class AlarmStatus extends JFrame{
	
	private Scanner input;
	private String advisorName[], advisorEmail[];
	private JLabel labelAdvisorName[], labelAdvisorEmail[];
	private JCheckBox checkBoxEnable[];
	private JButton buttonEnable;
	
	private JLabel labelEmpty;
	private GridBagLayout layout1;
	private GridBagConstraints gbContraints;
	private Canvas c;
	
	public AlarmStatus()
	{
		super("Check Alarm Status");
		
		int index=0, i, j, totalNumber;
		
		c=new Canvas();
		c.setSize(10,5);
		
		layout1=new GridBagLayout();
		gbContraints=new GridBagConstraints();
		setLayout(layout1);
		
		//index=0;
		advisorName=new String[100];
		advisorEmail=new String[100];
		labelAdvisorName=new JLabel[100]; 
		labelAdvisorEmail=new JLabel[100];
		checkBoxEnable=new JCheckBox[100];
		
		buttonEnable=new JButton("Enable");
		
		try{
			input=new Scanner(new File("info.txt"));
			
			while(input.hasNext())
			{
				advisorName[index]=input.next();
				//System.out.println(advisorName[index]);
				
				advisorEmail[index]=input.next();
				//System.out.println("email "+advisorEmail[index]);
								
				index++;
				//System.out.println("index1: "+index);
			}
		}
		catch(Exception e)
		{
			
		}
		
		System.out.println("index2: "+index);
		totalNumber=index;
		
		/*for(i=0;i<index;i++){
			System.out.println("check");
			System.out.println(advisorName[i]+advisorEmail[i]);
		}*/
		
		gbContraints.fill=GridBagConstraints.BOTH;
		gbContraints.anchor=GridBagConstraints.NORTH;
		
		i=0;
		for(i=0;i<index;i++)
		{
			gbContraints.anchor=GridBagConstraints.NORTH;
			labelAdvisorName[i]=new JLabel("Name: "+advisorName[i]);
			labelAdvisorName[i].setFont(new Font("Serif",Font.PLAIN,20));
			addComponent(labelAdvisorName[i], i, 0, 1, 1);
			
			labelAdvisorEmail[i]=new JLabel("       Email: "+advisorEmail[i]+"              ");
			labelAdvisorEmail[i].setFont(new Font("Serif",Font.PLAIN,20));
			addComponent(labelAdvisorEmail[i], i, 1, 1, 1);
			
			gbContraints.anchor=GridBagConstraints.CENTER;
			checkBoxEnable[i]=new JCheckBox();
			addComponent(checkBoxEnable[i], i, 2, 1, 1);
			
		}
		
		for(j=0;j<2;j++)
		{
			labelEmpty=new JLabel("                                              ");
			addComponent(labelEmpty, i, 0, 1, 1);
			
			labelEmpty=new JLabel("                                              ");
			addComponent(labelEmpty, i, 1, 1, 1);
			
			labelEmpty=new JLabel("                                              ");
			addComponent(labelEmpty, i, 2, 1, 1);
			
			i++;
		}
		
		addComponent(buttonEnable, i, 1, 1, 1);		
	}
	
	private void addComponent(Component c, int row, int col, int width, int height)
	{
		gbContraints.gridx=col;
		gbContraints.gridy=row;
		
		gbContraints.gridwidth=width;
		gbContraints.gridheight=height;
		
		layout1.setConstraints(c, gbContraints);
		add(c);
	}
}
