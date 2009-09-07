import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;

public class DeleteAlarmTest {

	/*
	@Before
	public void setUp() throws Exception {
	}
	*/
	
	public boolean testDeleteEntryforTest(String emailAddress)
	{
		String advisorName[], advisorEmail[];
		advisorName=new String[100];
		advisorEmail=new String[100];
		Scanner input=null;
		int index, i;
		boolean exists=false;
		
		try{
			input=new Scanner(new File("info.txt"));
		}catch(Exception e){}
		
		index=0;
		while(input.hasNext())
		{
			System.out.println("next add");
			advisorName[index]=input.next().trim();
			advisorEmail[index]=input.next().trim();
			index++;
		}
		
		for(i=0;i<index;i++)
		{
			if(advisorEmail[i].equals(emailAddress)){
				exists=true;
				break;
			}
		}
		return !(exists);
	}
	
	@Test
	public void testDeleteEntry() {
		//fail("Not yet implemented");
		
		String emailAddress = new String("whitehouse@cs.virginia.edu");
		DeleteAlarm deleteAlarm = new DeleteAlarm();
		boolean programStatus = deleteAlarm.deleteEntry(emailAddress);
		System.out.println(programStatus);
		
		boolean testStatus = testDeleteEntryforTest(emailAddress);
		System.out.println(testStatus);
		
		//if(testStaus == true)
			assertEquals("Result",programStatus,testStatus);
		//else
			//assertEquals("Result",true,false);


	}

}
