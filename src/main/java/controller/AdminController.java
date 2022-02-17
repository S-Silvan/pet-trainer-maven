package controller;

import java.util.List;
import java.util.Map;

import model.Admin;
import model.Appointment;
import model.Trainer;
import view.AdminView;
import view.ClientView;

public class AdminController {
	private Admin admin;
	private boolean isAuthenticated;
	
	public void home() {
		//View
		int choice=AdminView.viewMenu();
		
		switch(choice) {
		case 1:
			viewAppointments();
			home();
			break;
		case 2:
			viewTrainers();
			home();
			break;
		case 3:
			addTrainer();
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
	
	public void viewAppointments() {
		List<Appointment> appointmentList;
		
		//Model
		appointmentList=admin.viewBooking();
		
		//View
		AdminView.viewAppointments(appointmentList);
	}
	
	public void viewTrainers() {
		List<Trainer> trainerList;
		
		//Model
		trainerList=admin.viewTrainers();
		
		//View
		AdminView.viewTrainers(trainerList);
	}
	
	public void addTrainer() {
		Map<String,String> trainerDetails;
		
		//View
		trainerDetails=AdminView.addTrainer();
		
		//Model
		admin.addTrainer(
				trainerDetails.get("tr-name"), 
				trainerDetails.get("tr-phone-number"), 
				trainerDetails.get("tr-email"), 
				trainerDetails.get("tr-address"), 
				trainerDetails.get("tr-password"), 
				trainerDetails.get("tr-type")
				);
	}
	
	public void login() {
		Map<String,String> credentials=null;
		Admin admin=(Admin)UserFactory.getUser(UserType.Admin);
		
		//View
		credentials=ClientView.login();
		
		//Model
		isAuthenticated=admin.login(credentials.get("user-id"),credentials.get("password"));
		
		if(isAuthenticated) {
			this.admin=admin;
			AdminView.displayMessage("--Login Successful--");
			home();
		}else {
			AdminView.displayMessage("--Invalid Credentials--");
			login();
		}
	}
	
	public void logout() {
		isAuthenticated=false;
		boolean isLoggedOut=admin.logout();
		this.admin=null;
		
		if(isLoggedOut)
			ClientView.displayMessage("--Logout Successfull--");
		else
			ClientView.displayMessage("--Logout Failed--");
	}
}
