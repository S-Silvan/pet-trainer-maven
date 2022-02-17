

import static org.junit.Assert.*;

import org.junit.Test;

import model.Client;

public class ClientTest {
	/*Valid Login*/
	@Test
	public void validLogin() {
		Client client=new Client();
		boolean result=client.login("silvan8124@gmail.com","1");
		assertTrue(result);
	}
	/*Invalid Login*/
	@Test
	public void invalidLogin() {
		Client client=new Client();
		boolean result=client.login("silvan@gmail.com", "2");
		assertFalse(result);
	}
	
	 /* Appointment booking available slot */
	/*@Test
	public void addBookingNew() {
		boolean result=client.bookAppointment("03-02-2021", 1);
		assertTrue(result);
	}*/
	
	/* Appointment booking unavailable slot */
	@Test
	public void addBookingExisting() {
		Client client=new Client();
		client.login("silvan8124@gmail.com","1");
		
		boolean result=client.bookAppointment("03-02-2021", 1);
		assertFalse(result);
	}
	
	/*Canceling uncompleted appointment*/
	@Test
	public void cancelUncompletedAppointment() {
		Client client=new Client();
		client.login("silvan8124@gmail.com","1");
		
		boolean result=client.cancelBooking(13);
		assertTrue(result);
	}
	
	/*Canceling completed appointment*/
	@Test
	public void cancelCcompletedAppointment() {
		Client client=new Client();
		client.login("silvan8124@gmail.com","1");
		
		boolean result=client.cancelBooking(16);
		assertFalse(result);
	}
	
	/*Logout*/
	@Test 
	public void logout() {
		Client client=new Client();
		client.login("silvan8124@gmail.com","1");
		
		boolean result=client.logout();
		assertTrue(result);
	}
}
