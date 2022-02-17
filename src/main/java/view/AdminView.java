package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Appointment;
import model.Trainer;
import service.PetType;

public class AdminView {
	private static Scanner in=new Scanner(System.in);
	
	public static void displayMessage(String message) {
		System.out.println(message);
	}
	public static int viewMenu() {
		System.out.println("1.View Appointment");
		System.out.println("2.View Trainers");
		System.out.println("3.Add Trainer");
		System.out.println("4.Log out");
		System.out.println(">>What you want to do?");
		int choice=in.nextInt();
		in.nextLine();
		
		return choice;
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
	public static void viewTrainers(List<Trainer> trainerList) {
		for(Trainer trainer:trainerList) {
			System.out.println("--Trainer Details--");
			System.out.println("Name: "+trainer.getName());
			System.out.println("Phone Number: "+trainer.getPhoneNumber());
			System.out.println("Email: "+trainer.getEmail());
		}
	}
	public static Map<String,String> addTrainer() {
		Map<String,String> trainerDetails=new HashMap<>();
		
		System.out.println(">>Enter your Name:");
		trainerDetails.put("tr-name",in.next());
		in.nextLine();
		
		System.out.println(">>Enter your Phone No:");
		trainerDetails.put("tr-phone-number",in.next());
		in.nextLine();
		
		System.out.println(">>Enter your Email ID:");
		trainerDetails.put("tr-email",in.next());
		in.nextLine();
		
		System.out.println(">>Enter your Address:");
		trainerDetails.put("tr-address",in.next());
		in.nextLine();
		
		System.out.println(">>Enter your Password:");
		trainerDetails.put("tr-password",in.next());
		in.nextLine();
		
		PetType[] petTypes=PetType.values();
		System.out.println(">>Select Trainer type:");
		for(int i=0;i<petTypes.length;i++)
			System.out.println(i+". "+petTypes[i]);
		trainerDetails.put("tr-type",petTypes[in.nextInt()].toString());
		in.nextLine();
		
		return trainerDetails;
	}
}
