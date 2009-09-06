
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class DeleteAlarm extends JFrame{
	
	private Scanner input;
	private Formatter output;
	
	private String advisorName[], advisorEmail[];
	private JLabel labelAdvisorName[], labelAdvisorEmail[];
	
	private JCheckBox checkBoxEnable[];
	private GridBagLayout layout1;
	private GridBagConstraints gbContraints;
	private Canvas c;
	private JButton buttonDelete;
	private JLabel labelEmpty;
	
	private int totalNumber;
	
	private CheckBoxHandler checkBoxHandler;
	private DeleteButtonHandler deleteButtonHandler;
	private static int indexToDelete[];
	
	public DeleteAlarm()
	{
		super("Delete Alarm");
		
		int index=0, i, j;
		
		c=new Canvas();
		c.setSize(10,5);
		
		layout1=new GridBagLayout();
		gbContraints=new GridBagConstraints();
		setLayout(layout1);
		
		//index=0;
		advisorName=new String[100];
		advisorEmail=new String[100];
		indexToDelete=new int[100];
		labelAdvisorName=new JLabel[100]; 
		labelAdvisorEmail=new JLabel[100];
		checkBoxEnable=new JCheckBox[100];
		checkBoxHandler=new CheckBoxHandler();
		deleteButtonHandler=new DeleteButtonHandler();
		
		buttonDelete=new JButton("Delete");
		
		try{
			input=new Scanner(new File("info.txt"));
			
			while(input.hasNext())
			{
				advisorName[index]=input.next();
				advisorEmail[index]=input.next();
				index++;
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
			checkBoxEnable[i].addItemListener(checkBoxHandler);
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
		
		addComponent(buttonDelete, i, 1, 1, 1);
		buttonDelete.addActionListener(deleteButtonHandler);
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
	
	private class DeleteButtonHandler implements ActionListener
	{
		private int i, j, index, updatedTotalNumber;
		private Scanner tempInput;
		private Formatter output;
		
		private String tempAdvisorName[], tempAdvisorEmail[];
		private String outAdvisorName[], outAdvisorEmail[];
		
		public void actionPerformed(ActionEvent event)
		{
			try{
				output=new Formatter("info.txt");
			}
			catch(Exception e)
			{
				
			}
			System.out.println("TotalNumber: "+totalNumber);
		
			index=0;
			outAdvisorName=new String[100];
			outAdvisorEmail=new String[100];
			
			for(i=0;i<totalNumber;i++){
				if(indexToDelete[i]==0)
				{
					System.out.print(i+" ");
					outAdvisorName[index]=advisorName[i];		
					outAdvisorEmail[index]=advisorEmail[i];
					index++;					
				}
			}
			
			updatedTotalNumber=index;
			System.out.println("Updated TotalNumber: "+updatedTotalNumber);
			
			//output.format("%s %s\n",outAdvisorName[0], outAdvisorEmail[0]);
			//output.close();
			
			try{
			output=new Formatter("info.txt");} catch(Exception e){}
			
			for(i=0;i<updatedTotalNumber;i++)
			{
				try
				{
					tempInput=new Scanner(new File("info.txt"));
								
					index=0;
					while(tempInput.hasNext())
					{
						System.out.println("next add");
						tempAdvisorName[index]=tempInput.next().trim();
						tempAdvisorEmail[index]=tempInput.next().trim();
						index++;
					}
				}
				
				catch(Exception e)
				{
					
				}
					
				tempInput.close();
				
				try{
					//output=new Formatter("info.txt");
					
					for(j=0;j<index;j++)
						output.format("%s %s\n",tempAdvisorName[j], tempAdvisorEmail[j]);
					
					output.format("%s %s\n", outAdvisorName[i], outAdvisorEmail[i]);
				}
				catch(Exception e)
				{
				
				}		
			}
			output.close();
		}
	}
	
	private class CheckBoxHandler implements ItemListener
	{
		int i;
			
		public void itemStateChanged(ItemEvent event)
        {
			System.out.println("Inside checkBoxHandler ");
			for(i=0;i<100;i++){
				if(event.getSource()==checkBoxEnable[i])
				{
					System.out.println("Inside checkBoxHandler "+i);
					indexToDelete[i]=1;
				}
			}
        }
	}
}
