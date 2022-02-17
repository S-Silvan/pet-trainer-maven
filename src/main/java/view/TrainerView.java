package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Appointment;

public class TrainerView {
	private static Scanner in=new Scanner(System.in);
	
	public static int viewMenu() {
		System.out.println("1.View Appointments Details");
		System.out.println("2.Update Appointment status");
		System.out.println("3.Log out");
		System.out.println(">>What you want to do?");
		int choice=in.nextInt();
		in.nextLine();
		
		return choice;
	}
	public static Map<String, String> updateAppointmentStatus() {
		Map<String,String> appointmentDetails=new HashMap<>();
		
		System.out.println(">>Enter Appointment Id");
		appointmentDetails.put("id",in.nextLine());
		in.nextLine();
		System.out.println(">>Enter Appointment Status");
		appointmentDetails.put("status",in.nextLine());
		in.nextLine();
		
		return appointmentDetails;
	}
	public static void viewAppointments(List<Appointment> appointmentList) {
		for(Appointment appointment:appointmentList) {
			System.out.println("--Appointment Details--");
			System.out.println("Id: "+appointment.getId());
			System.out.println("Date: "+appointment.getDate());
			System.out.println("Slot: "+appointment.getSlot());
			
			System.out.println("--Client Details--");
			System.out.println("Name: "+appointment.getClient().getName());
			System.out.println("Phone Number: "+appointment.getClient().getPhoneNumber());
			System.out.println("Email: "+appointment.getClient().getEmail());
			System.out.println("Address: "+appointment.getClient().getAddress());
			
			System.out.println("--Pet Details--");
			System.out.println("Name: "+appointment.getPet().getName());
			System.out.println("Type: "+appointment.getPet().getType());
			System.out.println("Breed: "+appointment.getPet().getBreed());
		}
	}
	public static void displayMessage(String message) {
		System.out.println(message);
	}
}
