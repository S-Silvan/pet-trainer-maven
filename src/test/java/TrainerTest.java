

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.Trainer;
import service.BookingStatus;

public class TrainerTest {
	@Test
	public void validLogin() {
		Trainer trainer=new Trainer();
		boolean result=trainer.login("james@gmail.com", "1");
		
		assertTrue(result);
	}
	@Test
	public void invalidLogin() {
		Trainer trainer=new Trainer();
		boolean result=trainer.login("jam@gmail.com", "2");
		
		assertFalse(result);
	}
	@Test 
	public void updateAppointmentStatus() {
		Trainer trainer=new Trainer();
		trainer.login("james@gmail.com", "1");
		
		boolean result=trainer.updateAppointmentStatus(BookingStatus.Booked.toString(), 13);
		
		assertTrue(result);
	}
}
