package ie.gmit.phonemanager;

import java.util.List;
import java.util.Scanner;

public class PhoneMenu {
	// Instance Variables
	private Scanner userInput = null;
	private PhoneManager phoneManagerObject = null;
	private boolean keepRunning = false;
	
	// Constructor Method
	public PhoneMenu() {
		// Create new scanner object to capture input from the user
		userInput = new Scanner(System.in);
		phoneManagerObject = new PhoneManager(); // Create a new PhoneManager object
		keepRunning = true; // Initialise loop checker
	}

	public void display() {
		while (keepRunning) {
			// Display Menu to user
			showOptions();

			// Save user Menu option selection
			int userSelectedOption = userInput.nextInt();

			// Execute option
			selectOption(userSelectedOption);
		}
	}

	private void showOptions() {
		System.out.println("###################################");
		System.out.println("#         Phone Manager         #");
		System.out.println("###################################");
		System.out.println("(1) Load Phone DB");
		System.out.println("(2) Add a Phone.");
		System.out.println("(3) Delete a Phone.");
		System.out.println("(4) Search for a Phone by ID.");
		System.out.println("(5) Search for Sphones by First Name.");
		System.out.println("(6) Show total Number of Phones.");
		System.out.println("(7) Save DB.");
		System.out.println("(8) Quit.");
		System.out.println("Select an option [1-8]>");
	}

	private void selectOption(int userSelection) {
		if (userSelection == 1) { // Load Phones DB
			System.out.println("User Selected 1");
		} else if (userSelection == 2) {  // Add Phone
			addPhone();
		} else if (userSelection == 3) {  // Delete Phone
			deletePhone();
		} else if (userSelection == 4) {  // Search Phone by ID
			searchByID();
		} else if (userSelection == 5) {  // Search Phone by Name
			searchByName();
		} else if (userSelection == 6) {  // Search Phone by Name
			System.out.println("Total number of phone = " +
					phoneManagerObject.findTotalPhones());
		} else if (userSelection == 8) {
			keepRunning = false;
			System.out.println("Goodbye!");
		}
			
	}

	private void addPhone() {
		System.out.println("Enter Phone ID>");
		String phoneId = userInput.next();
		System.out.println("Enter Phone Model");
		String phoneModel = userInput.next();
		System.out.println("Enter Phone Type>");
		String phonesType = userInput.next();

		Phone newPhoneObject = new Phone(phoneId, model, type);
		
		boolean result = phoneManagerObject.addPhone(newPhoneObject);

		if (result) {
			System.out.println("Phone " + phoneId + " has been added successfully.");
		} else {
			System.out.println("ERROR: Phone " + phoneId +"  was not added!");
		}
	}

	private void deletePhone() {
		System.out.println("Enter Phone ID>");
		String phoneId = userInput.next();
		boolean result = phoneManagerObject.deletePhoneById(phoneId);
		if (result) {
			System.out.println("Phone " + phoneId + " has been deleted successfully.");
		} else {
			System.out.println("ERROR: Phone " + phoneId +"  was not deleted!");
		}
	}

	private void searchByID() {
		System.out.println("Enter Phone ID>");
		String phoneId = userInput.next();
		Phone phone1 = phoneManagerObject.findPhoneById(phoneId);
		if (phone1 == null) {
			System.out.println("Phone NOT found!");
		} else {
			System.out.println("Phone " + phone1.getPhoneId() + " found!");
		}	
	}

	private void searchByName() {
		System.out.println("Enter Phone Name>");
		String phoneName = userInput.next();
		List<Phone> phones = phoneManagerObject.getModel(model);
		if (phones == null) {
			System.out.println("No Phone with that first model found!");
		} else {
			for (Phone phone : phones) {
				System.out.println(     phone.getPhoneId()    );
			}
		}
	}

}
