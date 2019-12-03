package ie.gmit.studentmanager;

import java.io.Serializable;
import java.util.Date;

public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // Instance Variables
	private String phoneId;
	private String model;
	private String type;
	private Date entryDate;
	private String description;
	private Course course;

	// Constructors
	public Phone(String phoneId) {
		this.phoneId = phoneId;
	}
	
	public Phone(String phoneId, String model, String type) {
		// this(phoneId); - could set studentId this way
		this.studentId = phoneId;
		this.model = model;
        this.type = type;
        

	}
	
	public Phone(String phoneId, String model, String type, Date entryDate, String description) {
		// this(studentId); - could set studentId this way
		this.studentId = phoneId;
		this.model = model;
		this.type = type;
		this.description = description;
	}

	// Getters and Setters
	public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getEnrtyDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public Date getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	//public Course getCourse() {
		//return course;
	//}

	//public void setCourse(Course course) {
		//this.course = course;
	//}

}