package controller;

import java.util.List;
import java.util.Map;

import model.Appointment;
import model.Client;
import view.ClientView;

public class ClientController implements Controller{
	private Client client;
	private boolean isAuthenticated=false;
	
	public void home() {
		//View
		int choice=ClientView.viewMenu();
		
		switch(choice) {
		case 1:
			bookAppointment();
			home();
			break;
		case 2:
			viewAppointments();
			home();
			break;
		case 3:
			cancelAppointment();
			home();
			break;
		case 4:
			logout();
			login();
			break;
		default:
			System.out.println("(X)Invalid choice");
			home();
		}
	}
	
	public void login() {
		Map<String,String> credentials=null;
		Client client=(Client)UserFactory.getUser(UserType.Client);
		
		//View
		credentials=ClientView.login();
		
		//Model
		isAuthenticated=client.login(credentials.get("user-id"),credentials.get("password"));
		
		if(isAuthenticated) {
			this.client=client;
			ClientView.displayMessage("--Login Successful--");
			home();
		}else {
			ClientView.displayMessage("--Invalid Credentials--");
			login();
		}
	}
	
	public void bookAppointment() {
		boolean isBooked=false;
		
		//View
		Map<String,String> appointmentDetails=ClientView.addBooking();
		
		//Model
		isBooked=client.bookAppointment(appointmentDetails.get("date"),Integer.parseInt(appointmentDetails.get("slot")));
		
		if(isBooked)
			ClientView.displayMessage("--Appointment Booking Successful--");
		else
			ClientView.displayMessage("--Appointment Booking Failed--");
	}
	
	public void viewAppointments() {
		List<Appointment> appointmentList;
		
		//Model
		appointmentList=client.viewBooking();
		
		//View
		ClientView.viewAppointments(appointmentList);
	}
	
	public void cancelAppointment() {
		boolean isCancelled=false;
		
		//View
		int bookingId=ClientView.cancelBooking();
		
		//Model
		isCancelled=client.cancelBooking(bookingId);
		
		if(isCancelled)
			System.out.println("--Booking Cancellation Successfull--");
		else
			System.out.println("--Booking Cancellation Failed--");
	}
	
	public void logout() {
		isAuthenticated=false;
		boolean isLoggedOut=client.logout();
		this.client=null;
		
		if(isLoggedOut)
			ClientView.displayMessage("--Logout Successfull--");
		else
			ClientView.displayMessage("--Logout Failed--");
	}
}
