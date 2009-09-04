
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdMan extends JFrame{

	/**
	 * @param args
	 */
	private FlowLayout layout1;
	private JLabel labelLogo1, labelLogo2;
	
	private AddAlarm addAlarm;
	private AlarmStatus alarmStatus;
	private DeleteAlarm deleteAlarm;
	
	private JMenu menuMain;
	private JMenu menuAbout;
	
	private JMenuItem menuItemAlarmStatus; 
	private JMenuItem menuItemAddAlarm;
	private JMenuItem menuItemDeleteAlarm;
	private JMenuItem menuItemExit;
	
	private JMenuBar menuBarMain;
	
	public AdMan()
	{
		super("AdMan");
		
		layout1=new FlowLayout();
		layout1.setAlignment(FlowLayout.LEFT);
		setLayout(layout1);
		
		labelLogo1=new JLabel("             AdMan");
		labelLogo1.setFont(new Font("Serif",Font.ITALIC, 40 ) );
		add(labelLogo1);
		
		labelLogo2=new JLabel("          ");
		labelLogo2.setFont(new Font("Serif",Font.ITALIC, 40 ) );
		add(labelLogo2);
		
		menuMain=new JMenu("AdMan");
		menuMain.setMnemonic('M');
		
		menuAbout=new JMenu("About");
		menuAbout.setMnemonic('A');
		
		menuItemAlarmStatus = new JMenuItem("Alarm Status");
		menuItemAlarmStatus.setMnemonic('S');
		menuMain.add(menuItemAlarmStatus);
		menuItemAlarmStatus.addActionListener(
		new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				alarmStatus=new AlarmStatus();
				alarmStatus.setSize( 650, 350 ); 
				alarmStatus.setLocationRelativeTo(null);
				alarmStatus.setVisible( true );
			}
		}
		);
		
		menuItemAddAlarm = new JMenuItem("Add New Alarm");
		menuItemAddAlarm.setMnemonic('N');
		menuMain.add(menuItemAddAlarm);
		menuItemAddAlarm.addActionListener(
		new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				addAlarm=new AddAlarm();
				addAlarm.setSize( 400, 300 ); 
				addAlarm.setLocationRelativeTo(null);
				addAlarm.setVisible( true );
			}
		}
		);
		
		menuItemDeleteAlarm = new JMenuItem("Delete Alarm");
		menuItemDeleteAlarm.setMnemonic('D');
		menuMain.add(menuItemDeleteAlarm);
		menuItemDeleteAlarm.addActionListener(
		new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				deleteAlarm=new DeleteAlarm();
				deleteAlarm.setSize( 650, 350 ); 
				deleteAlarm.setLocationRelativeTo(null);
				deleteAlarm.setVisible( true );
			}
		}
		);
		menuItemExit = new JMenuItem("Exit");
		menuItemExit.setMnemonic('E');
		menuMain.add(menuItemExit);
		menuItemExit.addActionListener(
		new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		}
		);
		
		menuBarMain=new JMenuBar();
		getContentPane().add(menuBarMain);
		setJMenuBar(menuBarMain);
		
		menuBarMain.add(menuMain);
		menuBarMain.add(menuAbout);
				
	}
}
