package controller;

import java.util.List;
import java.util.Map;

import model.Appointment;
import model.Trainer;
import view.ClientView;
import view.TrainerView;

public class TrainerController implements Controller{
	private Trainer trainer;
	private boolean isAuthenticated=false;
	
	public void home() {
		//View
		int choice=TrainerView.viewMenu();
		
		switch(choice) {
		case 1:
			viewAppointments();
			home();
			break;
		case 2:
			updateAppointmentStatus();
			home();
			break;
		case 3:
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
		Trainer trainer=(Trainer)UserFactory.getUser(UserType.Trainer);
		
		//View
		credentials=ClientView.login();
		
		//Model
		isAuthenticated=trainer.login(credentials.get("user-id"),credentials.get("password"));
		
		if(isAuthenticated) {
			this.trainer=trainer;
			ClientView.displayMessage("--Login Successful--");
			home();
		}else {
			ClientView.displayMessage("--Invalid Credentials--");
			login();
		}
	}
	
	
	
	public void viewAppointments() {
		List<Appointment> appointmentList;
		
		//Model
		appointmentList=trainer.viewBooking();
		
		//View
		TrainerView.viewAppointments(appointmentList);
	}
	
	public void updateAppointmentStatus() {
		Map<String,String> appointmentDetails;
		//View
		appointmentDetails=TrainerView.updateAppointmentStatus();
		
		//Model
		trainer.updateAppointmentStatus(appointmentDetails.get("status"),Integer.parseInt(appointmentDetails.get("id")));
	}
	
	public void logout() {
		isAuthenticated=false;
		boolean isLoggedOut=trainer.logout();
		this.trainer=null;
		
		if(isLoggedOut)
			TrainerView.displayMessage("--Logout Successfull--");
		else
			TrainerView.displayMessage("--Logout Failed--");
	}
}
