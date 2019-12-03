package ie.gmit.phonemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PhoneManager implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // Declare a List called Phone to hold the Phone objects
	private List<Phone> phoneList;

	// Constructor
	public PhoneManager() {
		// Instantiate a Phone ArrayList
		phoneList = new ArrayList<Phone>();
	}

	// Getters and Setters
	public List<Phone> getPhones() {
		return phoneList;
	}

	public void setPhones(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}

	/**
	 * This method adds a Phone to the Phone List.
	 *
	 * @param Phone a Phones object that is to be added to the Phone list
	 * @return a boolean value indicating if the add was successful		
	 */                     
	public boolean MenuPhone(Phone phone) {
		try {
			// Using Collections add method. It returns true if this collection
			// changed as a result of the call
			return phoneList.add(phone);
		} catch (Exception error) {
			error.printStackTrace();
			return false;
		}
	}

	public boolean deleteStudent(Student student) {
		try {
			// Using Collections remove method. It returns true if this list 
			// contained the specified element
			return studentList.remove(student);
		} catch (Exception error) {
			error.printStackTrace();
			return false;
		}
	}

	public boolean deletePhoneById(String phoneId) {
		// Search for the Phone by ID
		Phone phone = findPhoneById(phoneId);
		// If a Phone was found then delete the student
		if (phone != null) {
			return deletePhone(phone);
		} else {
			// If no student was found Return false
			return false;
		}
	}

	public Phone findPhoneById(String phoneId) {

		// Loop over (i.e. Iterate over) arrayList for Phone type elements in
		// the Phone ArrayList

		// There are 3 ways you can iterate through a List.
		// 1. For Loop
		// 2. Advanced For Loop
		// 3. Iterator

		// 1. For Loop
//		for (int i = 0; i < phoneList.size(); i++) {
//			if (phoneList.get(i).getPhoneId().equals(phoneId)) {
//				return PhoneList.get(i);
//			}
//		}

		// 2. Advanced For Loop
//		for (Student student : studentList) {
//			// No need to check for null as ArrayList is dynamic and fills holes
//			if (student.getStudentId().equals(studentId)) {
//				return student;
//			}
//		}

		// 3. Iterator
		Iterator<Phone> phoneIterator = phoneList.iterator();
		Phone phoneObjectHolder;
		while (studentIterator.hasNext()) {
			// Store next Phone
			phoneObjectHolder = phoneIterator.next();
			// Check if studentId equals that of current Phone object
			if (phoneObjectHolder.getPhoneId().equals(phoneId)) {
				return phoneObjectHolder;
			}
		}

		// Return null if Phone ID was not found
		return null;
	}

	// Find a list of student by first name
	public List<Phone> getPhonesByModel(String model) {
		// Create a new ArrayList to Hold Phones with same names
		List<Phone> sameNames = new ArrayList<Phone>();
		// Loop over arrayList for Phones type elements in the Phones ArrayList do
		for (Phone phone : phoneList) {
			// If I find a Phones with the given first name then add to list
			if (phone.getModel().equalsIgnoreCase(model)) {
				sameNames.add(phone);
			}
		}
		// Check if list has any phone
		if (sameNames.size() > 0) {
			// If Phone were found then return the list
			return sameNames;
		}
		// If no students were found with that first name then return null
		return null;
	}

	public void loadPhoneFile(String pathToFile) {
		File inFile = new File(pathToFile);
		FileReader fileReader = null;
		BufferedReader br = null;
		String record = null;

		try {
			fileReader = new FileReader(inFile);
			br = new BufferedReader(fileReader);
			br.readLine(); //discard first line of csv file
			while ((record = br.readLine()) != null) {
				String[] elements = record.split(",");
				Phone newPhone = new Phone(elements[0], elements[1], elements[2]);
				this.addPhone(newPhone);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int findTotalPhones() {
		// returns the current number of Phones in the ArrayList
		return phoneList.size();
	}
	
	public PhoneManager loadDB(String dbPath){
    	PhoneManager pm = null;
    	try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(dbPath));
			pm = (PhoneManager) in.readObject();
    		in.close();
    	} catch (Exception e) {
    		System.out.print("[Error] Cannont load DB. Cause: ");
    		e.printStackTrace();
    	}
		return sm;
    }

    public void addPhone(Phone phone) {
    }

}