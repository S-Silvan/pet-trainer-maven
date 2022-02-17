package controller;

import java.util.Map;

import model.Visitor;
import view.VisitorView;

public class VisitorController {
	/*
	 * New user registration is handler
	 * Gets details from the visitors view
	 * And passes values with the Visitor model
	 * */
	public void register() {
		Map<String,String> registrationDetails=VisitorView.registrationForm();
		int petId=0;
		Visitor visitor=new Visitor();
		boolean isSuccessfull=false;
		String message="";
		
		//Adding pet
		petId=visitor.addPet(registrationDetails.get("pt-name"),
				registrationDetails.get("pt-type"),
				registrationDetails.get("pt-breed"),
				registrationDetails.get("pt-dob"));
		
		//Adding User id pet added successfully
		if(petId!=0) {
			isSuccessfull=visitor.addClient(registrationDetails.get("cl-name"),
					registrationDetails.get("cl-phone-number"),
					registrationDetails.get("cl-email"),
					registrationDetails.get("cl-address"),
					registrationDetails.get("cl-password"),
					petId);
			if(isSuccessfull)
				message="Registration successful";
			else
				message="Sorry! Registrion unsuccessful";
		}else {
			message="Sorry! Registrion unsuccessful";
		}
		
		VisitorView.displayMessage(message);
	}
}
