package controller;

import view.VisitorView;

public class AppStarter {

	public static void main(String[] args) {
		int choice=VisitorView.mainMenu();
		switch(choice) {
		case 1:
			new ClientController().login();
			break;
		case 2:
			new TrainerController().login();
			break;
		case 3:
			new AdminController().login();
			break;
		case 4:
			new VisitorController().register();
			break;
		default:
			VisitorView.displayMessage("(X)Invalid Choice");
		}
	}

}
