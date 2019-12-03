package ie.gmit.phonemanager;

import java.io.Serializable;
import java.util.Date;

public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // Instance Variables
	private String phoneId;
	private String model;
    private String type;
    
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
    
    public String getType() {
		return type;
	}

    public void setType(String type) {
		this.type = type;
    }
}