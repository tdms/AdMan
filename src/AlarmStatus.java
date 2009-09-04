
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
	private GridLayout layout1;
	private JButton buttonEnable;
	private JLabel labelEmpty;
	
	public AlarmStatus()
	{
		super("Check Alarm Status");
		
		int index=0, i;
				
		layout1=new GridLayout(10,3,2,2);
		//layout1.setAlignment(GridLayout.LEFT);
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
				//System.out.println("add");
				advisorName[index]=input.next().trim();
				//System.out.println(advisorName[index]);
				
				advisorEmail[index]=input.next().trim();
				//System.out.println("email "+advisorEmail[index]);
								
				index++;
				//System.out.println("index1: "+index);
			}
		}
		catch(Exception e)
		{
			
		}
		
		//System.out.println("index2: "+index);
		
		/*for(i=0;i<index;i++){
			System.out.println("check");
			System.out.println(advisorName[i]+advisorEmail[i]);
		}*/
		
		i=0;
		for(i=0;i<index;i++){
			
			labelAdvisorName[i]=new JLabel("Name: "+advisorName[i]);
			labelAdvisorName[i].setFont(new Font("Serif",Font.PLAIN,20));
			add(labelAdvisorName[i]);
			
			labelAdvisorEmail[i]=new JLabel("      Email: "+advisorEmail[i]);
			labelAdvisorEmail[i].setFont(new Font("Serif",Font.PLAIN,20));
			add(labelAdvisorEmail[i]);
			
			checkBoxEnable[i]=new JCheckBox();
			add(checkBoxEnable[i]);
		}
		
		labelEmpty=new JLabel("                                               ");
		add(labelEmpty);
		
		labelEmpty=new JLabel("                                               ");
		add(labelEmpty);
		
		labelEmpty=new JLabel("                                               ");
		add(labelEmpty);
		
		labelEmpty=new JLabel("                                                                                   ");
		add(labelEmpty);
		add(buttonEnable);
	}
}
