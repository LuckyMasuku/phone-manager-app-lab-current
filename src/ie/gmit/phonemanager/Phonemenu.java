package ie.gmit.phonemanager;

import java.util.List;
import java.util.Scanner;

public class PhoneMenu {
	// Instance Variables
	private Scanner userInput = null;
	private PhoneManager phoneManagerObject = null;
	private boolean keepRunning = false;
	
	// Constructor Method
	public Menu() {
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
		System.out.println("(5) Search for Students by First Name.");
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
					phonesManagerObject.findTotalPhones());
		} else if (userSelection == 8) {
			keepRunning = false;
			System.out.println("Goodbye!");
		}
			
	}

	private void addStudent() {
		System.out.println("Enter Student ID>");
		String studentId = userInput.next();
		System.out.println("Enter Student Name>");
		String studentName = userInput.next();
		System.out.println("Enter Student Surname>");
		String studentSurname = userInput.next();

		Student newStudentObject = new Student(studentId, studentName, studentSurname);
		
		boolean result = studentManagerObject.addStudent(newStudentObject);

		if (result) {
			System.out.println("Student " + studentId + " has been added successfully.");
		} else {
			System.out.println("ERROR: Student " + studentId +"  was not added!");
		}
	}

	private void deleteStudent() {
		System.out.println("Enter Student ID>");
		String studentId = userInput.next();
		boolean result = studentManagerObject.deleteStudentById(studentId);
		if (result) {
			System.out.println("Student " + studentId + " has been deleted successfully.");
		} else {
			System.out.println("ERROR: Student " + studentId +"  was not deleted!");
		}
	}

	private void searchByID() {
		System.out.println("Enter Student ID>");
		String studentId = userInput.next();
		Student student1 = studentManagerObject.findStudentById(studentId);
		if (student1 == null) {
			System.out.println("Student NOT found!");
		} else {
			System.out.println("Student " + student1.getStudentId() + " found!");
		}	
	}

	private void searchByName() {
		System.out.println("Enter Student Name>");
		String studentName = userInput.next();
		List<Student> students = studentManagerObject.getStudentsByFirstName(studentName);
		if (students == null) {
			System.out.println("No studnet with that first name found!");
		} else {
			for (Student student : students) {
				System.out.println(     student.getStudentId()    );
			}
		}
	}

}
