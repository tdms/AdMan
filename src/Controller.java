
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JFrame{
	
	static AdMan adMan;
	static Controller controller;
	private Canvas c;
	private GridBagLayout layout1;
	private GridBagConstraints gbContraints;
	
	private JLabel labelUserID;
	private JLabel labelPassword;
	private JLabel labelEmpty;
	
	private JTextField textFieldUserID;
	private JPasswordField textAreaPassword;
	private JButton buttonOK;
	
	private OKButtonHandler okButtonHandler;
	private MailFetcher mailFetcher;
	
	String userID = null, password = null;
	
	public Controller()
	{
		super("Login");
		System.out.println("Controller for AdMan");
		
		c=new Canvas();
		c.setSize(10,5);
		
		layout1=new GridBagLayout();
		gbContraints=new GridBagConstraints();
		setLayout(layout1);
		
		gbContraints.fill=GridBagConstraints.BOTH;
		gbContraints.anchor=GridBagConstraints.NORTH;
		
		labelUserID=new JLabel("userID[@gmail.com]: ");
		addComponent(labelUserID, 0, 0, 1, 1);
		
		textFieldUserID=new JTextField(20);
		addComponent(textFieldUserID, 0, 1, 1, 1);
		
		labelEmpty=new JLabel("             ");
		addComponent(labelEmpty, 1, 0, 1, 1);
		
		labelPassword=new JLabel("password: ");
		addComponent(labelPassword, 2, 0, 1, 1);
		
		textAreaPassword=new JPasswordField(10);
		addComponent(textAreaPassword, 2, 1, 1, 1);
		
		labelEmpty=new JLabel("             ");
		addComponent(labelEmpty, 3, 0, 1, 1);
		
		labelEmpty=new JLabel("             ");
		addComponent(labelEmpty, 4, 1, 1, 1);
		
		buttonOK=new JButton("OK");
		addComponent(buttonOK, 5, 1, 1, 1);
		
		okButtonHandler=new OKButtonHandler();
		buttonOK.addActionListener(okButtonHandler);
	}
	
	private class OKButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			userID=textFieldUserID.getText();
			password=textAreaPassword.getText();
			
			mailFetcher=new MailFetcher(textFieldUserID.getText(), textAreaPassword.getText());
			
			adMan=new AdMan();
			adMan.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			adMan.setSize( 400, 300 ); 
			adMan.setLocationRelativeTo(null);
			adMan.setVisible( true );
			
			new MainController(mailFetcher);
			
			//new MailFetcher();
		}
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
	
	public static void main(String[] args) {
				
		System.out.println("Main for AdMan");
		
		controller=new Controller();
		controller.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		controller.setSize( 380, 200 ); 
		controller.setLocationRelativeTo(null);
		controller.setVisible( true );
		
	}
}


class MainController implements Runnable{
	Thread t;
	MailFetcher mailFetcher;
	
	MainController(MailFetcher mailFetcher2)
	{
		mailFetcher = mailFetcher2;
		t = new Thread(this, "Main Controller");
		t.start();
	}
	public void run()
	{
		try
		{
			while(true)
			{
				//System.out.println("Main Controller running....");
				
				// fetch mail
				// new mail? display new dialog...
				
				mailFetcher.checkMail();
				
				
				Thread.sleep(60000);
			}
		}
		catch( InterruptedException e)
		{
			System.out.println("Exception in main controller.");
		}
	}
}

