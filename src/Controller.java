
import javax.swing.*;
import java.awt.*;

public class Controller {
	static AdMan adMan;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Main for AdMan");
		
		adMan=new AdMan();
		adMan.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		adMan.setSize( 400, 300 ); 
		adMan.setLocationRelativeTo(null);
		adMan.setVisible( true );
		
		//new MainController();
	}
}


class MainController implements Runnable{
	Thread t;
	
	MainController()
	{
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
				
				Thread.sleep(1000);
			}
		}
		catch( InterruptedException e)
		{
			System.out.println("Exception in main controller.");
		}
	}
}

