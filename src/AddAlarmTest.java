import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;

public class AddAlarmTest {

	/*
	@Before
	public void setUp() throws Exception {
	}
	*/
	
	@Test
	public void testVerifyEmailAddress() {
		//fail("Not yet implemented");
		AddAlarm addAlarm = new AddAlarm();
		boolean stat = addAlarm.verifyEmailAddress("abc@def");
		System.out.println(stat);
		
		assertEquals("Result",true,stat);
		
		
	}

}
