package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import service.PetType;

public class VisitorView {
	private static Scanner in=new Scanner(System.in);
	
	/*
	 * Registration form
	 * Gets registration details from the user 
	 * Return it to the Visitor Controller as Map
	 * */
	public static Map<String,String> registrationForm() {
		Map<String,String> registrationDetails=new HashMap<>();
		
		//Getting Client Details
		System.out.println(">>Enter your Name:");
		registrationDetails.put("cl-name",in.next());
		in.nextLine();
		
		System.out.println(">>Enter your Phone No:");
		registrationDetails.put("cl-phone-number",in.next());
		in.nextLine();
		
		System.out.println(">>Enter your Email ID:");
		registrationDetails.put("cl-email",in.next());
		in.nextLine();
		
		System.out.println(">>Enter your Address:");
		registrationDetails.put("cl-address",in.next());
		in.nextLine();
		
		System.out.println(">>Enter your Password:");
		registrationDetails.put("cl-password",in.next());
		in.nextLine();
		
		//Getting Pet Details
		System.out.println(">>Enter pet Name:");
		registrationDetails.put("pt-name",in.nextLine());
		in.nextLine();
		
		PetType[] petTypes=PetType.values();
		System.out.println(">>Select pet type:");
		for(int i=0;i<petTypes.length;i++)
			System.out.println(i+". "+petTypes[i]);
		registrationDetails.put("pt-type",petTypes[in.nextInt()].toString());
		in.nextLine();
		
		System.out.println(">>Enter pet breed:");
		registrationDetails.put("pt-breed",in.nextLine());
		in.nextLine();
		
		System.out.println(">>Enter pet Date of Birth:");
		registrationDetails.put("pt-dob",in.nextLine());
		in.nextLine();
		
		return registrationDetails;
	}
	
	public static void displayMessage(String message) {
		System.out.println(message);
	}
	
	/*
	 * Main menu
	 * Displayed for new user
	 * Gets an option from the user and return it to
	 * the user
	 * */
	public static int mainMenu() {
		System.out.println("1.Client Login");
		System.out.println("2.Trainer Login");
		System.out.println("3.Admin Login");
		System.out.println("4.register");
		System.out.println(">>Enter your choice");
		int choice=in.nextInt();
		return choice;
	}
}
