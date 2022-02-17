

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.Admin;

public class AdminTest {
	@Test
	public void validLogin() {
		Admin admin=new Admin();
		boolean result=admin.login("1", "1");
		
		assertTrue(result);
	}
	
	@Test
	public void invalidLogin() {
		Admin admin=new Admin();
		boolean result=admin.login("3", "2");
		
		assertFalse(result);
	}
}
