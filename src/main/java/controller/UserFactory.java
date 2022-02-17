package controller;

import model.Admin;
import model.Client;
import model.Trainer;
import model.User;

public class UserFactory {
	public static User getUser(UserType userType) {
		User user=null;
		
		switch(userType) {
		case Client:
			user=new Client();
			break;
		case Trainer:
			user=new Trainer();
			break;
		case Admin:
			user=new Admin();
		}
		
		return user;
	}
}
