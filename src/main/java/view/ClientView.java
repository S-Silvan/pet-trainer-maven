package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Appointment;

public class ClientView {
	private static Scanner in=new Scanner(System.in);
	
	public static Map<String,String> login(){
		Map<String,String> credentials=new HashMap<>();
		
		System.out.println(">>Enter User ID:");
		credentials.put("user-id",in.nextLine());
		in.nextLine();
		System.out.println(">>Enter your Password:");
		credentials.put("password",in.nextLine());
		in.nextLine();
		
		return credentials;
	}
	public static void displayMessage(String message) {
		System.out.println(message);
	}
	public static int viewMenu() {
		System.out.println("1.Book Appointment");
		System.out.println("2.View Appointments Details");
		System.out.println("3.Cancel Appointment");
		System.out.println("4.Log out");
		System.out.println(">>What you want to do?");
		int choice=in.nextInt();
		in.nextLine();
		
		return choice;
	}
	public static Map<String,String> addBooking(){
		Map<String,String> appointmentDetails=new HashMap<>();
		
		System.out.println(">>Enter appointment Date");
		appointmentDetails.put("date", in.nextLine());
		in.nextLine();
		System.out.println("1. 09:00 - 10:30");
		System.out.println("2. 11:00 - 12:30");
		System.out.println("3. 02:00 - 03:30");
		System.out.println("4. 04:00 - 04:30");
		System.out.println(">>Enter Slot");
		appointmentDetails.put("slot", in.nextLine());
		in.nextLine();
		
		return appointmentDetails;
	}
	public static void viewAppointments(List<Appointment> appointmentList) {
		for(Appointment appointment:appointmentList) {
			System.out.println("--Appointment Details--");
			System.out.println("Id: "+appointment.getId());
			System.out.println("Date: "+appointment.getDate());
			System.out.println("Slot: "+appointment.getSlot());
			
			System.out.println("--Trainer Details--");
			System.out.println("Name: "+appointment.getTrainer().getName());
			System.out.println("Phone Number: "+appointment.getTrainer().getPhoneNumber());
			System.out.println("Email: "+appointment.getTrainer().getEmail());
			
			System.out.println("--Pet Details--");
			System.out.println("Name: "+appointment.getPet().getName());
			System.out.println("Type: "+appointment.getPet().getType());
			System.out.println("Breed: "+appointment.getPet().getBreed());
		}
	}
	public static int cancelBooking() {
		System.out.println("Enter Booking ID:");
		int bookingId=in.nextInt();
		in.nextLine();
		return bookingId;
	}
}
