package application;

public class Customer {
	
	private int id;
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private String prescription;
	
	public Customer() {
		this.id = 0;
		this.fname = "";
		this.lname = "";
		this.email = "";
		this.phone = "";
		this.prescription = "";
	}
	
	public Customer(int id, String fname, String lname, String email,
			String phone, String prescription) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
		this.prescription = prescription;
	}
	
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getPrescription() {
    	return prescription;
    }
    
    public void setPrescription(String prescription) {
    	this.prescription = prescription;
    }
}
